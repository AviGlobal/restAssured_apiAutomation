#!/bin/bash

# Step 1: Run the test suite using TestNG XML
mvn clean test -DsuiteXmlFile=ReqResAutomation.xml

# Step 2: Generate Allure report if test results are present
if [ -d "target/allure-results" ]; then
    echo "Generating Allure report..."
    allure generate target/allure-results --clean -o target/allure-report

    echo "Opening Allure report in browser..."
    allure open target/allure-report
else
    echo "Allure results not found! Skipping report generation."
fi