# anz-api-testing

Goal:

To automate REST based web services for a Bank
The test suite involves testing customer, accounts and position based GET calls and verifying the response

TestData:

Endpoint Address: http://parabank.parasoft.com/parabank/services/bank

WADL: http://parabank.parasoft.com/parabank/services/bank?_wadl

Swagger: http://parabank.parasoft.com/parabank/services/bank/swagger.json

Note: Please make sure response content type is set to “json”.
Note: You can use customer no # 12212 for your tests.



# Project details:

1. Project Type: Maven
2. Tool: REST Assured (v 2.9.0)
3. Program Language: Java (v 1.8 SDK)
4. Testing Framework: TestNG (v 6.9.10)
5. Test Framework Design: Page Object Model (POM) Design
6. Logging: Log4j
7. Reproting: Default TestNG Reporting
8. Platform: Tested on Windows XP

# Steps to run the Tetsts:

Run the the test suite file as TestNG suite

Test Suite xml File: src/test/resources/TestSuite/RESTAPITestSuite.xml

Added Default Test Execution Report: RESTAPI_TestExecution_Report.html


