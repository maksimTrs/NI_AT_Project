**# playwright NI framework**

### **_NOTE_**: By Default this framework is using "src/test/resources/uat.properties" prop file.

1. [ ] To change prop file, add in the CLI command property, e.g.:

**_-DBUNDLE_TYPE=preprod_**

=================================================================================================================

#### --->>> **_To run a particular test - run command:_**

`./gradlew clean test --tests nisfapp.tests.AppTabsClosingTest`

=================================================================================================================

#### --->>> **_To run a particular test with parameters BROWSER_TYPE, TRACE_FLAG, HEADLESS_MODE - run command:_**

`./gradlew clean test -DBROWSER_TYPE=firefox -DTRACE_FLAG=true -DHEADLESS_MODE=false --tests nisfapp.tests.PosNgeniusApplicationCreationTest`

=================================================================================================================

#### --->>> **_To run TestNG suite with parameters  (e.g. BROWSER_TYPE=firefox) - run command:_**

`./gradlew clean test -Psuite="posNgeniousAppCreationSmoke.xml"`

OR

`./gradlew clean test -DBROWSER_TYPE=firefox -Psuitepath="src/test/resources/ecomAppCreationSmoke.xml"`

=================================================================================================================

#### --->>> **_To run test report Allure - run command:_**

`./gradlew allureServe`

=================================================================================================================

#### --->>>*

*_To run PlayWright trace report zip archive (where zipFileName= trace .zip file prop + file name) - run command:_**

`./gradlew runPlaywrightCLI -PzipFileName=custom-trace.zip`

=================================================================================================================


mvn clean test -Dsuite=yourSuiteName

mvn clean test -Dsuitepath=path/to/yourSuite.xml

mvn -DBROWSER=firefox -DsuiteXmlFile=src/test/resources/mail-ru-regression.xml -DENVIRONMENT=qa clean test

mvn allure:serve