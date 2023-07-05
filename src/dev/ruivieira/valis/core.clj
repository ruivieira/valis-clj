(ns dev.ruivieira.valis.core)

(defn date-difference-in-days
  "Takes two dates in 'yyyy-MM-dd' format and returns the difference in days."
  [date1 date2]
  (let [fmt (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd")
        date1 (java.time.LocalDate/parse date1 fmt)
        date2 (java.time.LocalDate/parse date2 fmt)]
    (-> date1
        (.until date2 java.time.temporal.ChronoUnit/DAYS))))

