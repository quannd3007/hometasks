# Home tasks 
- This project was written in Java and used Selenium WebDriver, Appium, Cucumber integrates with TestNG for parallel execution and Allure report.

- This project contains 2 test suites: 
  + The first test suite executes login test cases on website.
  + The other executes calculator functionalities of the Calculator app (apk file in 'app' folder). 

- Currently, I just support Chrome and Firefox for website testing and just support Android platform for mobile testing.

# Getting Started
First you need to clone this project into your local machine (Your machine should have Git installed).

# Prerequisites
- Need to install Firefox browser and Chrome browser in your machine.

- Need to install Java, Maven, Appium, Android SDK then add those paths to your environment variables.

- My environment information:
  + Java: 1.8
  + Maven: 3.6.0
  + Appium: 1.13.0
  + Android SDK: Android API 28    

# Running the Test Suite
 - To run website scenarios:

    `mvn clean test` or `mvn clean test -PWebsite`
    
 - To run mobile scenarios:
  
    `mvn clean test -PMobile`

# Edit existing Test Suite
- You can change: 
  + The Browser in WebsiteTest.xml file.
  + The Platform and UDID in MobileTest.xml file.
  
- To run tests in parallel, you have to add more test blocks with corresponding parameters.

# View Allure report 
First you need to download allure from https://github.com/allure-framework/allure2/releases

Then add the bin folder location into your PATH variable. Once you run your test suite, 'allure-results' directory will create inside target directory. Copy the path of it. Then open the terminal (or command prompt). Run following command:

   `allure serve <location to your allure-results directory>`

# Integrate with Jenkins
- You have to add Maven in Global Tool Configuration (I use Maven 3.6.1)
- To view Allure report, you have to add Allure plugin in Plugin Manager. Afterwards add Allure Commandline in Global Tool Configuration
- With Freestyle job: 
  + Add git source code with repository and your credential.
  + Add 2 "Invoke top-level Maven targets" build steps with your Maven version: the first one with Goals "clean test -PWebsite" and the second one with Golds "clean test -PMobile"
  + Add "Allure Report" post-build action with path "target/allure-results"
- With pipeline job: use "Pipeline script from SCM" definition then point to Git repository with your credential   
