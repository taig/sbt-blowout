# Changelog

## 0.2.0

_2025-01-22_

- Move comment handling into generator
- Fix missing sbt in CI
- Upgrade to sbt-houserules 0.11.3
- Update sbt, scripted-plugin to 1.10.7 (#78)
- Update sbt-blowout-yaml-circe to 0.1.2 (#76)

## 0.1.2

_2024-12-21_

- Add an example section to the documentation
- Add scalafix CI step
- Better describe the various modules
- Enforce scala 2 style for scalafix imports
- Fetch entire git history to resolve proper version information
- Include enablePlugin into highlighted example
- Update circe-yaml to 0.15.2 (#56)
- Update sbt-blowout-yaml-circe to 0.1.1 (#10)
- Update sbt-ci-release to 1.9.2 (#75)
- Upgrade to circe 0.14.3
- Upgrade to java 17
- Upgrade to sbt 1.10.6
- Upgrade to sbt-houserules 0.11.0
- Upgrade to scala 2.12.20

## 0.1.1

_2022-01-05_

- Add BlowoutGenerator.lzy builders
- Don't publish root module
- Add scala steward badge
- Update sbt to 1.6.1 (#8)
- Update sbt-ci-release to 1.5.10 (#3)
- Update sbt-houserules to 0.3.18 (#9)

## 0.1.0

_2021-12-14_

- Change BlowoutPlugin trigger to noTrigger enforcing to explicitly enable the plugin
- Rename json and yaml modules to json-circe and yaml-circe
- Upgrade to sbt-blowout-yaml 0.0.6
- Upgrade to sbt-houserules 0.3.16
- Upgrade to sbt 1.5.6

## 0.0.6

_2021-11-25_

- Add header support for Dockerfile

## 0.0.5

_2021-11-22_

- Preserve object order in yaml printer

## 0.0.4

_2021-11-22_

- Fix comment header not being prepended to file
- Allow passing a custom printer to json and yaml generators
- Bootstrap github actions generation

## 0.0.3

_2021-11-21_

- Add json module

## 0.0.2

_2021-11-21_

- Rename `YamlBlowout` to `BlowoutYaml`
- Add Acknowledgements section to readme

## 0.0.1

_2021-11-21_

- Initial release
