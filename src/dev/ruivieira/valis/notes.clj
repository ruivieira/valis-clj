(ns dev.ruivieira.valis.notes)

(require '[babashka.fs :as fs]
         '[dev.ruivieira.valis.core :as core]
         '[clj-yaml.core :as yaml]
         '[clojure.string :as str])

(defn find-files
  "Takes a directory path as an argument. Returns a sequence of maps for all Markdown files
     in that directory and its subdirectories. Each map has two keys:
     :path, which is the full path of the file, and
     :filename, which is the name of the file without the directory."
  [dir]
  (->> (fs/glob dir "**.md" {:recursive true})
       (filter fs/regular-file?)
       (pmap (fn [f] {:path (str f) :filename (.toString (.getFileName f))}))))

(defn read-file
  "Takes a path to a Markdown file as an argument. Returns a map with four keys:
   :path, which is the given path;
   :title, which is the name of the file without the directory or .md extension;
   :frontmatter, which is a map of the frontmatter entries;
   :contents, which is the rest of the text in the file."
  [path]
  (let [full-contents (slurp path)
        [pre frontmatter contents] (str/split full-contents #"---" 3)]
    (if (and frontmatter contents)
      (let [frontmatter (try
                          (yaml/parse-string frontmatter)
                          (catch Exception _ {}))]          ;; if YAML parsing fails, return an empty map
        {:path        path
         :title       (-> path fs/file .getName (str/replace #"\.md$" ""))
         :frontmatter frontmatter
         :contents    contents})
      ;; if there's no frontmatter, treat the whole content as the main content
      {:path        path
       :title       (-> path fs/file .getName (str/replace #"\.md$" ""))
       :frontmatter {}
       :contents    full-contents})))

(defn publishable? [file-data]
  (true? (or (= "true" (:publish (:frontmatter file-data))) ;; Check if string "true"
             (= true (:publish (:frontmatter file-data)))))) ;; Check if boolean true

(defn find-publishable-files [dir]
  (->> (find-files dir)
       (pmap :path)
       (pmap read-file)
       (filter publishable?)))

(defn parse-tasks
  "Takes markdown contents and returns a sequence of maps. Each map represents a
   task and has a :status key, which can be one of 'todo', 'inprogress', or 'later',
   and may also have :priority, :start-date, :scheduled and :due keys."
  [contents]
  (let [lines (str/split-lines contents)
        task-lines (filter #(re-find #"^- \[[ .p]\]" %) lines)]
    (map (fn [line]
           (let [[_ checkbox] (re-find #"^- \[(.)\]" line)
                 status (case checkbox
                          " " "todo"
                          "p" "inprogress"
                          "." "later")
                 priority (when (re-find #"⏫" line) "high")
                 start-date (first (re-seq #"(?<=🛫 )\d{4}-\d{2}-\d{2}" line))
                 scheduled (first (re-seq #"(?<=⏳ )\d{4}-\d{2}-\d{2}" line))
                 due (first (re-seq #"(?<=📅 )\d{4}-\d{2}-\d{2}" line))]
             (cond-> {:status status :task line}
                     priority (assoc :priority priority)
                     start-date (assoc :start-date start-date)
                     scheduled (assoc :scheduled scheduled)
                     due (assoc :due due))))
         task-lines)))

(defn remaining-days
  "Takes a task map and returns the remaining days based on either due or scheduled date."
  [task]
  (let [today (java.time.LocalDate/now)
        today-str (.format today (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd"))
        {:keys [due scheduled]} task
        due-date (or due scheduled)]
    (when due-date (core/date-difference-in-days today-str due-date))))
