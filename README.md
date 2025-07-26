# 📚 Library Management System (Java + JDBC)

A simple Library Management System built using Java, JDBC, and MySQL. This project allows users to manage books, issue and return them, and handle user interactions through a console-based interface.

---

## 🚀 Features

- Add new books
- View all books
- Issue a book to a user
- Return a book
- Delete a book
- Connects to MySQL using JDBC
- Admin and user-based access

---

## 🛠️ Tech Stack

- Java
- JDBC
- MySQL
- Eclipse (IDE)

---

## 🗃️ Database Schema

**Table: `books`**
```sql
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    issued BOOLEAN DEFAULT FALSE,
    issued_to VARCHAR(255),
    issued_date DATE
);
