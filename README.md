# 📚 Library Management System (Java Console App)

A console-based **Library Management System** built with **Java**, using **JDBC**, **DAO-Service design pattern**, and **OOP principles**.  
The project demonstrates clean layered architecture for better code reusability and maintainability.

---

## 🚀 Features
- **Books**
  - Add, View, Search, Delete
  - Issue & Return Books
  - Track Book Issue Records
- **Members**
  - Add and View Members
- **Transactions**
  - Maintain book issue & return history  
- **Utilities**
  - Date & Time handling
- **Exit** – Close the application safely  

---

## 🛠️ Technologies & Concepts Used
- **Java (Core Java, OOP)**
- **JDBC (MySQL/PostgreSQL)** – Database connectivity
- **DAO Pattern** – Separate database access logic
- **Service Layer** – Business logic separated from UI
- **Collections (ArrayList)** – For in-memory operations
- **Exception Handling** – Input validation and safe execution
- **Utility Classes** – Reusable helpers for Date/Time

---
## 📂 Project Structure
src/
├── app
│ └── LibraryManagementSystem.java # Main entry point (menu-driven UI)
│
├── connection
│ └── DBConnection.java # Handles database connection
│
├── dao
│ ├── BookDao.java # CRUD operations for books
│ ├── MemberDao.java # CRUD operations for members
│ └── TransactionDao.java # CRUD operations for transactions
│
├── model
│ ├── Book.java # Book entity
│ ├── BookIssue.java # Issued book entity
│ ├── Member.java # Member entity
│ └── Transactions.java # Transaction entity
│
├── services
│ ├── BookService.java # Business logic for books
│ ├── MemberService.java # Business logic for members
│ └── TransactionService.java # Business logic for transactions
│
└── util
└── DateAndTime.java # Utility for date & time handling


---

## ⚡ How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/library-management-system.git
2. Open in IntelliJ IDEA / NetBeans / Eclipse
3. Configure your database credentials in DBConnection.java
4. Run the program:
javac src/app/LibraryManagementSystem.java
java src/app/LibraryManagementSystem

CREATE TABLE members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(50)
);

## 🗄️ Database Schema (MySQL Example)

CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100),
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    member_id INT,
    issue_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

📌 Future Enhancements

GUI support (Swing/JavaFX)
Authentication (Admin & Members)
Export reports (PDF/Excel)
Advanced search filters

👨‍💻 Author
Developed by Hamadullah Odho ✨

