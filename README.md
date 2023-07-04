**# playwright NI framework**

### **_NOTE_**: By Default this framework is using "src/test/resources/uat.properties" prop file.

1. [x] To change prop file, add in the CLI command property, e.g.:

**_-DBUNDLE_TYPE=preprod_**

2. [x] This framework is using mvn wrapper (mvn -N wrapper:wrapper -Dmaven=3.8.5). To run command via maven, use "mvn" instead of "./mvnw".

=================================================================================================================

#### **--->>> To run TestNG suite with parameters  (e.g. BROWSER_TYPE=firefox) - run command:**

`./mvnw -DBROWSER_TYPE=chrome -DTRACE_FLAG=false -DsuiteXmlFile="posNgeniousAppCreationSmoke.xml"  clean test`

OR

`./mvnw -DBROWSER_TYPE=firefox -DTRACE_FLAG=true -DsuiteXmlFile="posNgeniousAppCreationSmoke.xml"  clean test"`

=================================================================================================================

#### --->>> **_To run test report Allure - run command:_**

`mvn allure:serve`

=================================================================================================================

#### --->>> **_To run PlayWright trace report zip archive (change .zip archive name) - run command:_**

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace traces/createSoftPosApplicationTest.zip"`

=================================================================================================================

