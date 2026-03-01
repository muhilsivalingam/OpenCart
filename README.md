** OpenCart Hybrid Selenium Automation Framework**

 **Project Description**
This project demonstrates a scalable Hybrid Automation Framework built using Java, Selenium WebDriver, TestNG, and Maven.  

The framework is designed following the Page Object Model (POM) pattern with Data-Driven testing capability to simulate a real-time enterprise automation architecture.

This project covers end-to-end UI automation scenarios for the OpenCart application.

---

**** Technology Stack********

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Data-Driven Framework
- Git

---

** Framework Design

✔ Hybrid Framework (POM + Data-Driven)  
✔ Reusable utility classes  
✔ Centralized WebDriver initialization  
✔ TestNG XML configuration  
✔ Maven build management  
✔ Clean project structure  

---
**Project Structure**
OpenCart
│── src/test/java
│ ├── base → Driver setup & initialization
│ ├── pages → Page Object classes
│ ├── testcases → Test classes
│ └── utilities → Reusable methods & helpers
│
│── testData → External test data
│── testng.xml → Test execution configuration
│── pom.xml → Dependency management
│── .gitignore → Ignored build artifacts
│── README.md

**Automated Test Scenarios**
1. User Registration
2. User Login
3. Product Search
Framework is extensible to add more regression scenarios.
**How to Execute**
Run via Maven:

mvn clean test
**Run via TestNG:**
Right-click on `testng.xml` → Run as TestNG Suite
Reports

Test execution reports are generated under:

test-output/

##  Objective

This repository demonstrates:
- Automation framework design capability
- Code reusability principles
- Clean project structuring
- Industry-standard automation practices

---

##  Author

Ponmuhil Sivalingam  
Senior QA Automation Engineer  
Specialized in Selenium, Java, TestNG, CI/CD

