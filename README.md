# API Automation Framework

This project is a comprehensive REST API automation framework built using **Java**, **RestAssured**, **TestNG**, and **Allure Reports**. 
It automates all the API endpoints provided on REQRES: https://reqres.in.

---

##Tech Stack

- Java 17+
- RestAssured
- TestNG
- Maven
- Allure Reports
- GitHub Actions (optional for CI/CD)

---

##Run Tests via Maven

mvn clean test -DsuiteXmlFile=ReqResAutomation.xml


##Generate Allure Report

allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report

##Use the script:

sh run-test-script.sh

