# 🛒 LambdaTest eCommerce Automation Project

An enterprise-grade UI test automation framework built to validate critical e-commerce Search and Cart workflows on the **LambdaTest eCommerce Playground** platform. 

The framework is strictly engineered around the **Page Object Model (POM)** design pattern, leveraging robust Object-Oriented Programming (OOP) principles like inheritance, encapsulation, and polymorphism to achieve high maintainability, stable execution, and zero code redundancy.

## 👥 Contributor
* **Salah Aldin** - QA Automation Engineer

---

## 🛠️ Tech Stack & Framework Architecture

| Layer | Tool / Framework | Language / Version |
| :--- | :--- | :--- |
| **Language** | Java | Java 21 |
| **Build Tool** | Maven | Apache Maven 3.6+ |
| **UI Automation** | Selenium Java | Selenium WebDriver 4.39.0 |
| **Driver Management** | WebDriverManager | Automated Driver Management 6.1.1 |
| **Test Runner** | TestNG | TestNG 7.11.0 |
| **Design Pattern** | Page Object Model | Clean Architecture (POM) |

---

## 📂 Project Structure & Directory Tree

```text
LambdaTest_Playground_Automation/
├── docs/                                  # Project Documentation
│   ├── Test_Plan_LambdaTest.docx          # Strategic QA Automation Test Plan document
│   ├── Test_Cases_LambdaTest.xlsx         # Detailed Test Cases execution sheet
│   └── Automation_Project_Presentation.pdf # Project showcase & architecture presentation slide deck
├── src/
│   ├── main/java/pages/                   # Page Objects (UI Locators & Actions)
│   │   ├── CartPage.java                  # Cart operations, quantities, and stock controls
│   │   └── SearchPage.java                # Search functionality and product catalog filters
│   └── test/java/test/                    # TestNG Test Suites & Test Logic
│       ├── BaseTest.java                  # Centralized Driver setup/teardown (@BeforeMethod)
│       ├── CartTests.java                 # Dynamic Data-Driven Cart validations
│       └── SearchTests.java               # Product search verification scenarios
├── pom.xml                                # Maven dependencies, encoding & compiler plugins
└── testng.xml                             # TestNG execution suite configurations






📝 Automated Test Cases Specifications
The framework covers critical end-to-end functionality mapped directly from business requirements:

🎯 Search Module (SearchTests)
TC_SEARCH_001: Global Product Search

Objective: Verify that searching for an active product returns accurate and relevant catalog results.

TC_SEARCH_002: Empty Search & Validation

Objective: Validate system behavior and error messaging when initiating a blank or non-existent keyword search query.

🛒 Shopping Cart Module (CartTests)
TC_CART_001: Data-Driven Quick Add to Cart (ADD)

Objective: Validate that hovering over a product and clicking the quick action button successfully populates the cart database.

TC_CART_002: Dynamic Quantity Modification (UPDATE)

Objective: Assert that modifying product quantity values dynamically updates the core cart layout values and sub-totals.

TC_CART_003: Product Deletion & Empty State (REMOVE)

Objective: Ensure that removing the last remaining product correctly transitions the cart layout into its baseline empty state.

TC_CART_008: Business Logic Restriction (Zero Stock)

Objective: Assert that the Quick Buy Now functionality is structurally disabled or hidden for catalog elements that have a zero-stock indicator.

🏛️ Architectural Highlights
1. Advanced Data-Driven Testing (TestNG DataProvider)
Matrix Execution: Incorporates TestNG @DataProvider to feed a sequential data matrix (ADD, UPDATE, REMOVE) into a single parameterized test method, significantly reducing code duplication and boilerplates.

State Isolation: Leverages localized setups to ensure each iteration executed by the DataProvider runs in a clean browser session, preventing state contamination and ensuring high test reliability.

2. Synchronization & Robust Element Interaction
Implicit vs. Explicit Waits: Replaced brittle implicit delays with robust Explicit Waits (WebDriverWait) mapped to real-time asynchronous Ajax updates (e.g., waiting for the .alert-success modal visibility before page transit), completely mitigating test flakiness.

JavaScriptExecutor Fallbacks: Actions like hover triggers and dynamic element clicks utilize JavascriptExecutor fallback blocks to override occasional layout rendering obstructions.

3. Encapsulation & Inherited Setup
Strict Encapsulation: Page class locators are declared private or protected, ensuring state boundaries. Elements are safely manipulated solely via public action-driven methods.

DRY Principle compliance: Shared setups, teardowns, and driver lifecycle properties are centralized via Java OOP inheritance within BaseTest.

🚀 Setup & Installation
Clone the repository:

Bash
   git clone [https://github.com/your-username/LambdaTest_Playground_Automation.git](https://github.com/your-username/LambdaTest_Playground_Automation.git)
   cd LambdaTest_Playground_Automation
Install all project dependencies:

Bash
   mvn clean install -DskipTests
🏃 Running the Tests
Run the full test suite via TestNG XML configuration:

Bash
  mvn test
Run a single specific test class (e.g., Cart Lifecycle):

Bash
  mvn test -Dtest=CartTests
Run the Search suite validation:

Bash
  mvn test -Dtest=SearchTests
📬 Reports Generation
After each execution flow, comprehensive TestNG reports are auto-generated under the test-output/ directory:

index.html - Complete interactive HTML execution pipeline report.

emailable-report.html - Lightweight, shareable execution summary.
