# salaries

Salaries is an application that generates simple salary reports. It takes as an input a csv file with salaries data and outputs two csv files containing different salary reports: one file - containing summed up salaries data with taxes info included, another - containig data with salaries grouped by type.

Application is developed in Java, using Gradle.

## Instalation

To run the application download salaries-1.0.jar file from Releases. In the same directory, where salaries-1.0.jar file is located, create directory named IOFiles and put input csv file into it (e.g. duomenys.csv).

## Usage

To start the application go to directory where salaries-1.0.jar file is located and enter `java -jar salaries-1.0.jar`. The application will output two new csv files in IOFiles directory as a result.
