// Main class to demonstrate usage of Personal Finance Tracker classes, including UserProfile

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {

        // Sample user input for the profile
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your preferred currency: ");
        String currency = scanner.nextLine();

        System.out.print("Enter your monthly income: ");
        float monthlyIncome = 0;
        while (true) {
            try {
                monthlyIncome = Float.parseFloat(scanner.nextLine());
                if (monthlyIncome < 0) {
                    System.out.print("Income cannot be negative. Please enter again: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter your income again: ");
            }
        }

        // Uncomment the following lines to allow user to input preferred categories
        // System.out.print("Enter your preferred categories (comma-separated): ");
        // String categoriesInput = scanner.nextLine();
        // List<String> preferredCategories = Arrays.asList(categoriesInput.split(",\\s*"));
        // scanner.close();

        UserProfile user = new UserProfile(
            name, 
            currency, 
            monthlyIncome, 
            Arrays.asList("Food", "Rent", "Transportation", "Entertainment") // utilize default categories for the time being
        );

        // Create user profile
        // UserProfile user = new UserProfile(
        //     "Justin",
        //     "USD",
        //     3000f,
        //     Arrays.asList("Food", "Rent", "Transportation", "Entertainment")
        // );

        // Print user profile details using getters
        System.out.println("=== User Profile ===");
        System.out.println("Name: " + user.getName());
        System.out.println("Currency: " + user.getCurrency());
        System.out.println("Monthly Income: " + user.getMonthlyIncome());
        System.out.println("Preferred Categories: " + user.getPreferredCategories());
        System.out.println();


        // Print user profile details using setters
        System.out.println("=== User Profile (Setters) ===");
        user.setName("Justin Doe");
        user.setCurrency("EUR");
        user.setMonthlyIncome(3500f);
        user.setPreferredCategories(Arrays.asList("Food", "Rent", "Transportation", "Entertainment", "Savings"));

        System.out.println("Name: " + user.getName());
        System.out.println("Currency: " + user.getCurrency());
        System.out.println("Monthly Income: " + user.getMonthlyIncome());
        System.out.println("Preferred Categories: " + user.getPreferredCategories());
        System.out.println();

        // Sample transactions
        Transaction income = new Transaction("2025-04-01", "income", "Salary", 3000, "April paycheck");
        Transaction rent = new Transaction("2025-04-03", "expense", "Rent", 1000, "April rent payment");
        Transaction groceries = new Transaction("2025-04-05", "expense", "Food", 250, "Groceries for the week");

        List<Transaction> transactions = Arrays.asList(income, rent, groceries);

        // Set up budget
        Budget budget = new Budget();
        budget.setLimit("Food", 300);
        budget.setLimit("Rent", 1000);
        budget.setLimit("Transportation", 150);
        budget.setLimit("Entertainment", 200);

        // Set up savings goals
        SavingsGoal emergencyFund = new SavingsGoal("Emergency Fund", 2000, 500, "2025-07-01");
        SavingsGoal laptopFund = new SavingsGoal("New Laptop", 1200, 400, null);

        List<SavingsGoal> savingsGoals = Arrays.asList(emergencyFund, laptopFund);

        // Create report generator
        ReportGenerator reportGen = new ReportGenerator(transactions, budget, savingsGoals);

        // Show monthly summary for April 2025
        reportGen.generateMonthlySummary("04", "2025");

        // Export to CSV
        boolean exported = reportGen.exportToCSV("report.csv");

        if (exported)
        {
            System.out.println("Report exported successfully to report.csv");
        }
        else
        {
            System.out.println("Failed to export report.");
        }

        try 
        {
            DataPersistenceManager.saveData("user_profile.json", user);
            DataPersistenceManager.saveData("transactions.json", transactions);
            DataPersistenceManager.saveData("budget.json", budget.categoryLimits);
            DataPersistenceManager.saveData("savings_goals.json", savingsGoals);
            System.out.println("All data saved to JSON files.");
        } 
        catch (IOException e)
        {
            System.err.println("Failed to save data: " + e.getMessage());
        }


    }

}