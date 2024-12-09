# Bank System Project

This project implements a bank system in Java, leveraging object-oriented programming (OOP) principles to manage various account types and their functionalities. It includes the use of inheritance, interfaces, and abstract classes to ensure modular, scalable, and maintainable code.
---

## Class Diagram

 [Bank Class Diagram.pdf](https://github.com/user-attachments/files/18066212/Bank.Class.Diagram.pdf) 
 
---

## Features

1. **Object-Oriented Design**  
   - Inheritance: Classes like `RegularCheckingAccount` and `BusinessCheckingAccount` inherit from `CheckingAccount`.  
   - Abstract Classes: The `Account` class serves as a base class, defining shared attributes and abstract methods for all account types.  
   - Interfaces: A `Profitable` interface defines the structure for profit-generating accounts.  

2. **Account Types**  
   - Checking Accounts: `RegularCheckingAccount`, `BusinessCheckingAccount`.  
   - Savings Accounts.  
   - Mortgage Accounts.  

3. **Key Functionalities**  
   - Automatic account creation with pre-defined data using the `AccountsFactory`.  
   - Dynamic account storage using expandable arrays.  
   - Sorting accounts based on profitability.  
   - Exception handling for duplicate account numbers.  
   - A menu-driven interface for user interaction.  

4. **Design Principles**  
   - Adheres to OOP principles, including encapsulation and polymorphism.  
   - Implements SOLID design principles for clean and modular code.  

---

## Installation

1. Clone the repository:  
   ```bash
   git clone <repository-url>
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).  
3. Ensure you have JDK 11 or higher installed.  
4. Build the project to compile the code.

---

## Usage

1. Run the `Main` class to start the program.  
2. Interact with the menu-driven interface. Options include:  
   - **Create Accounts**: Automatically populate the system with pre-defined accounts.  
   - **Display Accounts**: View accounts stored in the system.  
   - **Calculate Profits**: Show profit-generating accounts sorted by descending profit.  
   - **Add New Account**: Add an account manually through the menu.  
   - **Exit**: Exit the program.  

---

## Project Structure

- **Packages**:  
  - `accounts`: Contains account classes, subtypes, and the `Account` abstract class.  
  - `factory`: Includes `AccountsFactory` for pre-defined account creation.  
  - `utils`: Utility classes and the `Profitable` interface.  
  - `main`: The entry point (`Main` class).  

- **Key Classes**:  
  - `Account` (abstract class serving as the base for all accounts).  
  - `CheckingAccount` (inherits from `Account`).  
  - `SavingsAccount` and `MortgageAccount` (inherits from `Account`).
  - `RegularCheckingAccount` and `BusinessCheckingAccount` (inherit from `CheckingAccount`).  
  - `AccountsFactory` for automatic account creation.  
  - `Bank` class to manage account storage and operations.
---

## Known Constraints

- The system uses regular arrays, not `ArrayList`, for account storage.  
- Expandable arrays are implemented manually.  
- The menu does not support concurrency or multi-threading. 
---

## Author

**Marah Mahajne**  
A computer science student specializing in full-stack development.  
Completed this project as part of a Java programming assignment.  
