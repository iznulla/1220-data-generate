# Fake Data Generate

The Fake Data Generate project is developed to generate fake data for testing purposes. The generated data is automatically stored in a database, requiring database settings input.

## Technologies Used
- Spring Boot
- Java
- Reflection
- PostgreSQL
- Rest API
- Spring Data JPA

## About the Program
The project utilizes the Datafaker library, one of the major libraries providing fake ready-made data.

### Implementation Details
- **Dataapp Package**: Contains classes implemented using reflection. Given the extensive number of classes and methods in the library for generating required data, it was decided to invoke them by scanning the library and selecting a specific class and its method.
- **Web Package**: Houses the database with predefined class names and methods.

## Program Workflow
The client-side program runs on the local host in a web browser. In the "data-sources" tab, the user inputs data for the database they intend to work with. The program then scans all tables in the database along with their fields. Users can specify the desired type of information for each field in the required table. Finally, users select the number of data generations needed.

## Setup Instructions
1. Clone the repository.
2. Ensure you have Java and PostgreSQL installed on your system.
3. Configure the database settings in the application properties file.
4. Build and run the application using Spring Boot.
5. Access the application through a web browser on `localhost`.

## Contributors
- Iznulla Askarov

