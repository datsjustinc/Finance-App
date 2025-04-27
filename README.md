# Personal Finance & Budget Tracker (C++)

This is a C++ application for managing personal finances, allowing users to track income, expenses, budgets, and savings goals. All data is stored **locally** in JSON files using the lightweight [nlohmann/json](https://github.com/nlohmann/json) library.

---

## Project Structure

| File                          | Purpose                                                   |
|-------------------------------|-----------------------------------------------------------|
| `Transaction.h/.cpp`          | Represents a financial transaction (income or expense)    |
| `Budget.h/.cpp`               | Represents monthly budget limits for different categories |
| `SavingsGoal.h/.cpp`          | Represents savings goals (target amounts, deadlines)      |
| `UserProfile.h/.cpp`          | Holds all user data: profile, transactions, budget, and goals |
| `DataPersistenceManager.h/.cpp` | Provides save/load functionality for user profiles     |
| `main.cpp`                    | Demonstrates creating, saving, and loading user data      |

---

## Requirements

- C++11 or higher
- [nlohmann/json.hpp](https://github.com/nlohmann/json) (single header library)

### How to Get nlohmann/json

- Download the file `json.hpp` from [here](https://github.com/nlohmann/json/releases/latest)
- Place `json.hpp` in the project directory

Alternatively, install via package manager:

```bash
sudo apt-get install nlohmann-json-dev
```

---

## Compilation Instructions

Open your terminal in the project directory and compile using:

```bash
g++ main.cpp Transaction.cpp Budget.cpp SavingsGoal.cpp UserProfile.cpp DataPersistenceManager.cpp -o finance_app
```

Ensure that `json.hpp` is present in your project directory before compiling.

Run the program with:

```bash
./finance_app
```

---

## What the Program Does

1. **Creates a new user profile** with:
   - Name, currency, and monthly income
   - Preferred spending categories
2. **Adds sample transactions** for income and expenses
3. **Sets monthly budget limits** for various categories
4. **Creates multiple savings goals** (e.g., Emergency Fund, New Laptop)
5. **Saves all data** to a local JSON file (`user_profile.json`)
6. **Loads data back** from the JSON file to confirm persistence
7. **Displays user information** after loading to verify data integrity

---

## Notes

- All data is saved **locally** on the user's machine; there is no cloud storage involved.
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
