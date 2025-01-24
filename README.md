# LibraryManagementSystem(Console-Based)
This is a console-based Library Management System developed in Java. The application allows users to manage a collection of books through a set of simple commands. Users can add new books, view available books, issue or return books, and search for books by title. The data is serialized and saved to a file to persist across sessions.

Features:
Add a New Book: Add books by providing details like Book ID, Title, and Author.
View Available Books: Display a list of all books in the library with their availability status.
Issue a Book: Mark a book as issued. A book can only be issued if it's not already checked out.
Return a Book: Mark a book as returned. It can only be returned if it has been previously issued.
Search for a Book: Search for books by title, case-insensitive.
Persistence:
The list of books is stored in a file so that data persists even after the application exits and restarts.

Project Structure:
Book Class: Represents a book with its ID, title, author, and issuance status.
Main Library Management Class: Handles user input, book management, and persistence (saving and loading books).
Technologies Used:
Java (Core Libraries)
Serialization (for data persistence)
