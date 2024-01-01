# Cat Image Downloader

This project is a Java application that allows users to download cat images from the CATAAS API.

## Installation

1. Install Java 8 or higher.
2. Run `mvn install` to resolve Maven project dependencies.
3. Start the application using the command `java -jar Cat-App-0.0.1-SNAPSHOT.jar`.

## Usage

- Start the application.
- Choose an option from the menu: 1, 2, or 3.
- Enter the required information and follow the instructions.

## Configuration

You can edit the `application.properties` file for application settings. For example:
- `download.directory=/path/to/your/download/directory`

## H2 Database Console

The application uses H2 in-memory database to store cat image details.
- [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
