[![Clojars Project](https://img.shields.io/clojars/v/dev.ruivieira/valis.svg)](https://clojars.org/dev.ruivieira/valis)
[![valis-clj CI](https://github.com/ruivieira/valis-clj/actions/workflows/CI.yaml/badge.svg)](https://github.com/ruivieira/valis-clj/actions/workflows/CI.yaml)
[![bb compatible](https://raw.githubusercontent.com/babashka/babashka/master/logo/badge.svg)](https://babashka.org)
# valis-clj

![valis](docs/valis-core-small.png)

A Clojure library for personal workflows.

## Installation

Include the following dependency in your `project.clj` file:

```clojure
[valis-clj "0.1.0-SNAPSHOT"]
```

Then run the following command to download the dependencies:

```shell
lein deps
```

## Usage

Once the library is installed, you can use it by requiring it in your namespace:

```clojure
(ns my-app.core
  (:require [valis-clj.core :as valis]))
```

Replace `valis-clj.core` with the actual namespaces provided by this library.

The exact usage of valis-clj will depend on the specific functions and utilities it provides.
For more detailed information, refer to the individual docstrings for each function in the library.

## Documentation

Documentation is a work in progress and will be updated as the library expands. 
[Please refer to codox documentation](https://ruivieira.github.io/valis-clj/) and docstrings in the source code for the most up-to-date information.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. For larger changes, please open an issue first to discuss the proposed changes.

## License

This project is licensed under the GNU General Public License v3.0 (GPL-3.0) - see the [LICENSE](LICENSE) file for details.
