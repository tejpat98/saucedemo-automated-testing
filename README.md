# SauceDemoTesting

This project is designed to automate the testing of the SauceDemo website using various testing frameworks and tools.

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)

## Introduction

SauceDemoTesting is a project to create an automated test suite using Java, Selenium WebDriver, and TestNG to validate core functionalities of an demo e-commerce website.

Demo E-Commerce Website: https://www.saucedemo.com/
(SauceDemo is a publicly accessible site for testing automation tools.)

- Automated test scripts using Selenium WebDriver.
- Structure tests using TestNG (annotations like @Test, @BeforeMethod, @AfterMethod).
- Use Page Object Model (POM) to improve maintainability.

Tests covering:

- Logging in / out
- Viewing Products catelog / product details
- Shopping cart functionality
- Checkout process


https://github.com/user-attachments/assets/aca2a299-415f-4f20-b397-889f2aaeca5c


## Installation

To get started with SauceDemoTesting, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/tejpat98/saucedemotesting.git
   ```
2. Navigate to the project directory:
   ```bash
   cd saucedemotesting
   ```
3. Install the required dependencies:
   ```bash
   mvn install
   ```

## Usage

To run the tests, use the following command:

```bash
mvn test
```

This will execute all the test scripts and generate a report of the test results.

## Demo

### Page Object Model (POM) Implementation

The Page Object Model (POM) is a design pattern that helps create an object repository for web UI elements. It improves test maintenance and reduces code duplication. Below is an example of how POM is implemented in the `LoginTest` and `LoginPage` classes.

#### LoginPage.java

The `LoginPage` class represents the login page of the SauceDemo website. It contains web elements and methods to interact with those elements.

#### LoginTest.java

The `LoginTest` class contains test methods to validate the login functionality of the SauceDemo website. It uses the `LoginPage` class to interact with the login page.

In this example, the `LoginPage` class encapsulates the elements and actions related to the login page, while the `LoginTest` class contains the test logic. This separation of concerns makes the code more maintainable and reusable.

#### LogoutTest.java

The `LogoutTest` class also uses `LoginPage`, to first login so that logout functionalities can be tested. Reducing the amount of duplicate code that gets written.
