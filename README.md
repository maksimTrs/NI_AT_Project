**# playwright NI framework**

### **_NOTE_**: By Default this framework is using "src/test/resources/uat.properties" prop file.

1. [x] To change prop file, add in the CLI command property, e.g.:

**_-DBUNDLE_TYPE=preprod_**

2. [x] This framework is using mvn wrapper (mvn -N wrapper:wrapper -Dmaven=3.8.5). To run command via maven, use "mvn"
   instead of "./mvnw".

=================================================================================================================

#### **--->>> To run TestNG suite with parameters  (e.g. BROWSER_TYPE=firefox) - run command:**

`./mvnw -DBROWSER_TYPE=chrome -DTRACE_FLAG=false -DsuiteXmlFile="PosNgAppCreationSmoke.xml"  clean test`

OR

`./mvnw -DBROWSER_TYPE=msedge -DTRACE_FLAG=true -DsuiteXmlFile="EcomAppCreationSmoke.xml"  clean test"`

=================================================================================================================

#### --->>> **_To run test report Allure - run command:_**

`mvn allure:serve`

=================================================================================================================

#### --->>> **_To run PlayWright trace report zip archive (change .zip archive name) - run command:_**

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace traces/createSoftPosApplicationTest.zip"`

=================================================================================================================

#### --->>> **_To run tests via Docker - run commands:_**

1. Run docker compose file (and open after that http://localhost:4444/ui/index.html#/):

`docker compose up`

2. Export PlayWright variable to the project:

export SELENIUM_REMOTE_URL=http://localhost:4444
export PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1

OR

set SELENIUM_REMOTE_URL=http://localhost:4444
set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1

and check the export:

echo %SELENIUM_REMOTE_URL%

OR

echo $SELENIUM_REMOTE_URL

3. Run tests for chrome:

`mvnw -DBROWSER_TYPE=chrome -DsuiteXmlFile="PosNgAppCreationSmoke.xml"  clean test`

4. Check video record or test execution via UI http://localhost:4444/ui/index.html#/ (default UI pass: "secret")