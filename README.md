# SessionManager
is a simple Java-based session management utility that uses the Ehcache library for caching session data. It provides functionality to start, end, and check the status of sessions, and it periodically clears the cache to manage session timeouts.
## Table of Contents
Installation
Usage
Features
Dependencies
Contributing
License
## Installation
- `Clone the repository`


```shell
git clone https://github.com/yourusername/session-manager.git
cd session-manager
```
- `Build the project`

Make sure you have Maven installed. In the project directory, run:

```shell
mvn clean install
```

## Usage
Run the application:

```shell
java -cp target/session-manager-1.0-SNAPSHOT.jar com.nl.mo.session.SessionManager [sessionId]
```
Example:

```shell
java -cp target/session-manager-1.0-SNAPSHOT.jar com.nl.mo.session.SessionManager abc123
```

## Features

- `Session Management:` Start, end, and check the status of sessions.
- `Timeout Handling:` Sessions automatically timeout after 15 minutes (default).
- `Logging:` Uses java.util.logging for logging session activities and errors.
- `Command-Line Arguments:` Accepts session IDs as command-line arguments.

## Dependencies
- `Ehcache:` Used for caching session data.
- `Java Standard Library:` Uses `java.util.Timer`, `java.util.logging.Logger`, and other standard libraries.
Dependencies are managed via Maven. Ensure that you have Maven installed and configured.

## Contributing
- `Fork the repository`
- `Create a new branch` (git checkout -b feature/YourFeature)
- `Commit your changes` (git commit -m 'Add some feature')
- `Push to the branch` (git push origin feature/YourFeature)
- `Open a pull request`

## License

This project is licensed under the MIT License.
