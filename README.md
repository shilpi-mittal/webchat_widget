## Webchat Widget
<br/>This repository contains UI tests for WebChat Widget.
<br/>Currently it contains a module, named functional_tests, where all functional tests are kept.
<br/>It further contains a package, named ui_tests, where all UI tests go.
<br/>
<br/> The tests execute UI actions and perform assertions on result. Gherkin is used to help provide scenarios in plain English level.
<br/> The scenarios are capable of accepting a table of test data, to avoid duplicate scenarios.
<br/> The tests write logs at different logging levels and generate test failure logs and messaging.
<br/> They also generate HTML report, named index.html.
<br/> Currently we are leveraging Cucumber team's free, cloud-based service for sharing reports throughout the organisation.

<br/>

### Prerequisite:
* Java 17
* IDE
* ChromeDriver
* FirefoxDriver

### Tech Stack
* Java 17
* Gradle
* Cucumber
* Git
* Logger slf4j
* selenium-java
* webdrivermanager

<br/>

### Commands to execute


###### To build project:
./gradlew build

###### To run all UI acceptance/regression tests:
./gradlew ui_acceptance_tests

###### To run all UI smoke tests:
./gradlew tagged_ui_tests -Dtags=@smoke

###### To run API tests by tag
./gradlew tagged_ui_tests -Dtags=@acceptance_test
</br>./gradlew tagged_ui_tests -Dtags="not @acceptance_test"

###### To run tests against a specified environment, append
 -Dtest_env=[local | qa] (default is local)

###### To change the log level, append
 -Dtest_log_level=[ERROR | WARN | INFO | DEBUG | TRACE] (default is DEBUG)

###### To change the browser for UI tests, append
 -Dtest_browser=[chrome | firefox] (default is chrome)

<br/>

### Directory Structure
functional_tests/src:
* main/java/constants - Contains common constants
* main/java/common_utils - Contains common common utils
* main/java/ui_tests/page_objects - Contains all page objects to interact with web UI
* main/java/ui_tests/utils - Contains all ui tests utils/helpers
* main/resources - contains property files for different environments, and logback.xml
* test/java/ui_tests.features - contains ui tests feature files
* test/java/ui_tests.step_definitions - contains step definitions for features in ui_tests.features
* test/resources - contains cucumber properties

#### Next Steps:
* Add more UI tests
* Expand to add API tests
* Add more test data to tests
* Integrate with CI/CD pipeline
* Add Screenshot capability for failed tests
* Leverage property files and move all static content in location specific files to support Localization

### Bugs
* Non-matching locations are displayed as well
* Sometimes there are 5 locations in list and sometimes 3
* Location list is never empty no matter what the search input is
* Only special chars,  like '<', is allowed as search input
* Only special chars, like '<', is a valid name
* Valid name needs only one char
* Anything more than 8 digits is a valid phone number. Phone number formatting goes away after entering 11 digits
* Flag doesn't change when picked from picker
* Flag doesn't appear as per std code
* Flag picker doesn't appear when Mobile Phone input field is empty
* Phone number is not retained when moved back and forward
* message and name data is retained in all locations, even when modal is closed and re-opened, even on reload
* Send button is active even when message is bigger than chars limit
* Send button is active even when phone number is invalid
