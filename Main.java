// Main class to demonstrate usage of Personal Finance Tracker classes, including UserProfile

import java.util.Arrays;
import java.util.List;

public class Main
{

    public static void main(String[] args)
    {

        // Create user profile
        UserProfile user = new UserProfile(
            "Justin",
            "USD",
            3000f,
            Arrays.asList("Food", "Rent", "Transportation", "Entertainment")
        );

        // Print user profile details
        System.out.println("=== User Profile ===");
        System.out.println("Name: " + user.name);
        System.out.println("Currency: " + user.currency);
        System.out.println("Monthly Income: " + user.monthlyIncome);
        System.out.println("Preferred Categories: " + user.preferredCategories);
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

    }

}