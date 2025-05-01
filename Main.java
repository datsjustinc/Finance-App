import java.util.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{

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

    // justin
    public static void main(String[] args)
    {

        loadOrCreateProfile();

    }

    // justin
    /* Function dsplays menu options and prompts user to selection an option 
    */
   private static void printMenu()
   {
        System.out.print("""
        \n
        """);
   }

    // justin
    /* Function to check for existing profile or create new one; prompts 
    for user inputs and creates user class with arguments.
    */
   //check if json exists, if not create new profile
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

            }
            catch (IOException e)
            {
                // handle exception
                System.err.println("Error loading profile: " + e.getMessage());
                createNewProfile();
            }
        }

        else
        {
            // create a new profile
            createNewProfile();
        }
        
    }

    /* Function creates user profile and prompts for all the user class fields
    */
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
        } catch (IOException e) {
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

    //justin
    /* Function to generate monthly summary from user profile */
    private static void exportMonthlySummary()
    {
        // check uml might need multiple functions for this 
    }

    //justin
    /* Function to generate monthly report from user profile */
    private static void exportReport()
    {
        // check uml might need multiple functions for this 
    }

    //michelle 
    /* Function to save data to json files */
    private static void saveData()
    {
        // check uml might need multiple functions for this 
    }
}