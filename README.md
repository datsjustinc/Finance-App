# Personal Finance & Budget Tracker (Java)

This is a Java application for managing personal finances, allowing users to track income, expenses, budgets, and savings goals. All data is stored **locally** in JSON files using the lightweight [Gson](https://github.com/google/gson) library.

---

## Project Structure

| File                          | Purpose                                                   |
|-------------------------------|-----------------------------------------------------------|
| `Transaction.java`            | Represents a financial transaction (income or expense)    |
| `Budget.java`                 | Represents monthly budget limits for different categories |
| `SavingsGoal.java`            | Represents savings goals (target amounts, deadlines)      |
| `UserProfile.java`            | Holds all user data: name, income, currency, and categories |
| `DataPersistenceManager.java` | Provides save/load functionality for user data using Gson |
| `ReportGenerator.java`        | Generates monthly summaries and exports CSV reports       |
| `Main.java`                   | Demonstrates creating, saving, and displaying user data   |

---

## Requirements

- Java 11 or higher
- [Gson library](https://github.com/google/gson)

### How to Get Gson

- Download the file `gson-2.10.1.jar` from [here](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar)
- Move the file `gson-2.10.1.jar` into the same directory as your `.java` files

---

## Compilation Instructions

Open your terminal in the project directory and compile using:

### On Windows (Git Bash or CMD):
```bash
javac -cp ".;gson-2.10.1.jar" *.java
```

### On macOS or Linux:
```bash
javac -cp ".:gson-2.10.1.jar" *.java
```

---

## Run Instructions

Run the program after compiling using:

### On Windows:
```bash
java -cp ".;gson-2.10.1.jar" Main
```

### On macOS or Linux:
```bash
java -cp ".:gson-2.10.1.jar" Main
```

Ensure that `gson-2.10.1.jar` is present in your project directory before compiling and running.

---

## What the Program Does

1. **Creates a new user profile** with:
   - Name, currency, and monthly income
   - Preferred spending categories
2. **Adds sample transactions** for income and expenses
3. **Sets monthly budget limits** for various categories
4. **Creates multiple savings goals** (e.g., Emergency Fund, New Laptop)
5. **Generates a monthly summary report**
6. **Exports a detailed report** to `report.csv` containing transactions, budget, and savings
7. **Displays user and financial summary information** in the terminal

---

## Notes

- All data is saved **locally** on the user's machine in JSON format using Gson.
- The generated CSV file is compatible with Excel or Google Sheets.
- This version supports **only one user profile** (single-user design).
- The JSON files are formatted to be human-readable for easy manual editing if necessary.

---

## License

This project is open-source and free to use for educational or personal development purposes.

---

## Credits

Developed by:
- Justin Chen (jc9957@nyu.edu)
- Michelle Cai (mc8870@nyu.edu)
