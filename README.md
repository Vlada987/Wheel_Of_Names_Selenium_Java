# Automated Test Suite for Wheel of Names App

This is an automated test suite for the **Wheel of Names** app, built using **Selenium WebDriver** and **TestNG**. The suite supports parallel execution on **Windows 7** and **Windows 10** environments, testing on both **Chrome** and **Firefox** browsers, both locally and remotely via **Sauce Labs**.

## Overview

The test suite covers a wide range of functionalities of the **Wheel of Names** app, such as validating the page title and description, name management operations (adding, shuffling, sorting), customization features, functional testing, saving and loading functionality, and localization support (verifying different languages). The suite is designed for **cross-browser** testing to ensure consistency across different environments.

## Prerequisites

Before you begin, ensure you have the following installed and configured:

### 1. **Java 8 or Higher**
   - Ensure you have **Java 8** or a later version installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).

### 2. **Maven or Gradle**
   - This project uses **Maven** for dependency management. If you prefer **Gradle**, you can easily adapt the configuration.
   - Download Maven from [here](https://maven.apache.org/download.cgi) if not already installed.

### 3. **Selenium WebDriver and TestNG**
   - The framework uses **Selenium WebDriver** to interact with the application and **TestNG** for test execution.
   - Both dependencies will be managed via Maven or Gradle.

### 4. **Sauce Labs Account** (for remote execution)
   - You’ll need a **Sauce Labs** account for executing tests remotely on their cloud grid. Sign up [here](https://saucelabs.com/sign-up).
   - For remote execution, configure your Sauce Labs credentials (username and access key).

### 5. **ChromeDriver and GeckoDriver** (for local tests)
   - For local execution on **Chrome** and **Firefox**, you will need **ChromeDriver** and **GeckoDriver**.
   - Download them from:
     - [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/)
     - [GeckoDriver](https://github.com/mozilla/geckodriver/releases)
   - Ensure they are available in your system’s PATH.

## Features

The test suite validates several key functionalities of the Wheel of Names app:

- **Page Title and Description Validation**: Verify that the app’s title and description are correctly displayed.
- **Name Management**:
  - Add names to the wheel.
  - Shuffle the names.
  - Sort names alphabetically.
- **Customization**: Test the ability to:
  - Set spin time.
  - Customize the message displayed after a spin.
- **Functional Testing**: Verify core functionality, such as spinning the wheel and selecting random names.
- **Save and Load Functionality**: Ensure that the user can save and load their wheel configurations.
- **Localization**: Test the app's support for different languages and check for proper localization.

## Reporting

- The test results are saved in the **TestNG output** folder.
- You can generate **HTML** or **XML** reports, which provide detailed information about test execution,
  including pass/fail status, test duration, and logs.

### TestNG Reports:
- **HTML Report**: Provides a visually rich report with information on test status, logs, and screenshots.
- **XML Report**: Suitable for CI/CD integration and machine-readable data.

