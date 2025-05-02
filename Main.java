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

        loadOrCreateProfile();

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
                case "4" -> exportMonthlySummary();
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
        System.out.println
        ("""\nMenu:
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
    private static void loadOrCreateProfile()
    {
        System.out.print("Load existing profile? (yes/no): ");

        if (scanner.nextLine().equalsIgnoreCase("yes")) 
        {
            try
            {
                // try to loadDdata from DataPersistenceManager for each json file
                // if it doesn't exist it will throw catch error
                user = DataPersistenceManager.loadData("user_profile.json", UserProfile.class);
                transactions = DataPersistenceManager.loadListData("transactions.json", new TypeToken<List<Transaction>>() {});
                budget.categoryLimits = DataPersistenceManager.loadData("budget.json", Map.class);
                savingsGoals = DataPersistenceManager.loadListData("savings_goals.json", new TypeToken<List<SavingsGoal>>() {});
                System.out.println("Data loaded successfully.");

            }
            catch (IOException e)
            {
                // handle exception
                System.err.println("Failed to load data. Creating new profile: " + e.getMessage());
                createNewProfile();
            }
        }

        else
        {
            // create a new profile
            createNewProfile();
        }
        
    }

    // Function creates user profile and prompts for all the user class fields
    private static void createNewProfile()
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

    //michelle
    /* Function to add to transactions on user profile */
    private static void addTransaction()
    {
        // check uml might need multiple functions for this like edit function, add funcntion, remove function
    }

    //michelle
    /* Function to add budget limits to user profile */
    private static void addBudgetLimits()
    {
        // check uml might need multiple functions for this 
    }

    //michelle
    /* Function to add saving goals to user profile */
    private static void addSavingsGoal()
    {
        //savegoals, editgoals, delete goals
          // check uml might need multiple functions for this  
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

    //michelle 
    /* Function to save data to json files */
    private static void saveData()
    {
        // check uml might need multiple functions for this 
    }
}