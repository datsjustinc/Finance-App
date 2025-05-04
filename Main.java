import java.util.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

public class Main
{

    // Sample user input for the profile
    private static Scanner scanner = new Scanner(System.in);

    // Stores user profile object
    private static UserProfile user;

    // Stores transaction objects
    private static List<Transaction> transactions = new ArrayList<>();
    
    // Stores budget object (with hashmap for categories)
    private static Budget budget = new Budget();

    // Stores savings goal objects
    private static List<SavingsGoal> savingsGoals = new ArrayList<>();

    // Justin
    public static void main(String[] args)
    {
        System.out.println("=== Personal Finance Tracker ===");

        loadProfile();

        String choice;
        do 
        {
            printMenu();
            System.out.print("Select an option: ");
            choice = scanner.nextLine().trim();

            switch (choice) 
            {
                case "1" -> addTransaction();
                case "2" -> addBudgetLimits();
                case "3" -> addSavingsGoal();
                case "4" -> generateMonthlySummary();
                case "5" -> exportReport();
                case "6" -> saveData();
                case "0" -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }
        } 
        
        while (!choice.equals("0"));

        scanner.close();
    }

    // Justin
    // Function dsplays menu options and prompts user to selection an option 
    private static void printMenu()
    {
        System.out.println("""
        \nMenu:
        1. Add Transaction
        2. Set Budget Limits
        3. Add Savings Goal
        4. View Monthly Summary
        5. Export Report to CSV
        6. Save All Data
        0. Exit
        """);
    }

    // Justin
    // Function to check for existing profile or create new one
    private static void loadProfile()
    {
        System.out.print("Load existing profile? (yes/no): ");

        if (scanner.nextLine().equalsIgnoreCase("yes")) 
        {
            try
            {
                // try to loadDdata from DataPersistenceManager for user json profile
                // if it doesn't exist it will throw catch error
                user = DataPersistenceManager.loadData("user_profile.json", UserProfile.class);
                System.out.println("Data loaded successfully.");

            }
            catch (IOException e)
            {
                // handle exception
                System.err.println("Failed to load data. Creating new profile: " + e.getMessage());
                createProfile();
            }
        }
        else
        {
            // create a new profile
            createProfile();
        }
    }

    // Justin * NEED TO ADD EDIT PROFILE FEATURE
    // Function creates user profile and prompts for all the user class fields
    private static void createProfile()
    {        
        // Prompt user for profile details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your preferred currency: ");
        String currency = scanner.nextLine();

        System.out.print("Enter your monthly income: ");
        float monthlyIncome = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        System.out.print("Entered preferred categories (comma-seperated): ");
        List<String> preferredCategories = Arrays.asList(scanner.nextLine().split("\\s*,\\s*"));

        // Create a new UserProfile object
        UserProfile user = new UserProfile(name, currency, monthlyIncome, preferredCategories);

        // Save the profile to a JSON file
        try 
        {
            DataPersistenceManager.saveData("user_profile.json", user);
            System.out.println("Profile created successfully.");
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving profile: " + e.getMessage());
        }
    }   

    private static void addTransaction() 
    {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter type (income or expense): ");
        String type = scanner.nextLine();

        System.out.print("Enter category (can enter multiple, comma-separated): ");
        List<String> categories = Arrays.asList(scanner.nextLine().split("\\s*,\\s*"));

        System.out.print("Enter amount: ");
        float amount = Float.parseFloat(scanner.nextLine());


        System.out.print("Enter amount: ");
        // float amount;
        try 
        {
            amount = Float.parseFloat(scanner.nextLine());
            if (amount < 0) 
            {
                System.out.println("Amount must be non-negative.");
                return;
            }
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid number format.");
            return;
        }

        System.out.print("Enter description/notes: ");
        String description = scanner.nextLine();

        for (String category : categories) 
        {
            try 
            {
                Transaction newTransaction = new Transaction(date, type, category, amount, description);
                transactions.add(newTransaction);
                System.out.println("Added transaction: " + category + " | $" + amount + " | " + description);
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("Failed to add transaction: " + e.getMessage());
            }
        }
    }

    private static void editTransaction(int index, String newDate, String newType, String newCategory, float newAmount, String newDescription) 
    {
        if (index < 0 || index >= transactions.size()) 
        {
            System.out.println("Invalid transaction index.");
            return;
        }

        try 
        {
            Transaction t = transactions.get(index);
            t.setDate(newDate);
            t.setType(newType);
            t.setCategory(newCategory);
            t.setAmount(newAmount);
            t.setNotes(newDescription);
            System.out.println("Transaction updated.");
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Failed to edit transaction: " + e.getMessage());
        }
    }
 
    private static void deleteTransaction(int index) 
    {
        if (index < 0 || index >= transactions.size()) 
        {
            System.out.println("Invalid transaction index.");
            return;
        }

        Transaction removed = transactions.remove(index);
        System.out.println("Removed transaction: " + removed.getCategory() + " | $" + removed.getAmount());
    }


    //michelle
    /* Function to add budget limits to user profile */
// Prompts user to input a budget limit and returns it as a (category, limit) pair
    private static Map.Entry<String, Float> getBudgetLimit() {
        System.out.println("Enter category for budget limit: ");
        String category = scanner.nextLine();

        System.out.println("Enter monthly limit for " + category + ": ");
        try {
            float limit = Float.parseFloat(scanner.nextLine());
            if (limit < 0) {
                System.out.println("Limit must be non-negative.");
                return null;
            }
            return new AbstractMap.SimpleEntry<>(category, limit);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }
    }

    // Applies the limit to the budget map and prints confirmation
    private static void setBudgetLimit(String category, float limit) {
        budget.setLimit(category, limit);
        System.out.println("Budget limit set for " + category + ": $" + limit);
    }

    // Wrapper function that combines both
    private static void addBudgetLimits() {
        Map.Entry<String, Float> entry = getBudgetLimit();
        if (entry != null) {
            setBudgetLimit(entry.getKey(), entry.getValue());
        }
    }


    //michelle split into CREATE, UPDATE, and DELETE GOALS
    /* Function to add saving goals to user profile */
    private static void createSavingsGoal() 
    {
        System.out.print("Enter savings goal name: ");
        String goalName = scanner.nextLine();

        System.out.print("Enter target amount: ");
        float target;
        try {
            target = Float.parseFloat(scanner.nextLine());
            if (target <= 0) {
                System.out.println("Target amount must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
            return;
        }

        SavingsGoal goal = new SavingsGoal(goalName, target, 0.0f, "in progress");
        savingsGoals.add(goal);
        System.out.println("Savings goal added: " + goalName + " | Target: $" + target);
    }


    // Justin
    // Function prompts the user for a month and year, then generates a financial summary for that period
    private static void generateMonthlySummary()
    {
        // Prompt user to enter month and year in "MM YYYY" format
        System.out.print("Enter month and year (MM YYYY): ");

        // Read input and split by whitespace into [month, year]
        String[] input = scanner.nextLine().split("\\s+");

        // Validate input format: must have exactly 2 parts
        if (input.length != 2) 
        {
            System.out.println("Invalid format. Use MM YYYY.");
            return; // Exit the method early if format is invalid
        }

        // Extract month and year from user input
        String month = input[0];
        String year = input[1];

        // Create a ReportGenerator with the current data in memory
        ReportGenerator report = new ReportGenerator(transactions, budget, savingsGoals);

        // Generate and display the monthly summary for the given period
        report.generateMonthlySummary(month, year);
    }

    // Justin
    // Function to generate and export a full financial report to a CSV file
    private static void exportReport()
    {
        // Create a ReportGenerator using current in-memory data
        ReportGenerator report = new ReportGenerator(transactions, budget, savingsGoals);

        // Attempt to export the report to a CSV file named "report.csv"
        boolean success = report.exportReport("report.csv");

        // Notify the user of success or failure
        if (success) 
        {
            System.out.println("Report exported to report.csv");
        } 
        else 
        {
            System.out.println("Failed to export report.");
        }
    }

    //Justin save/load profile save/load transaction save/load budget save/load goals
    /* Function to save data to json files */
    private static void saveData() 
    {
        try {
            DataPersistenceManager.saveData("user_profile.json", user);
            DataPersistenceManager.saveData("transactions.json", transactions);
            DataPersistenceManager.saveData("budget.json", budget);
            DataPersistenceManager.saveData("savings_goals.json", savingsGoals);
            System.out.println("All data saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }
}