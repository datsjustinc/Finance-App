// Generates reports and exports data to CSV format
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportGenerator
{

    private List<Transaction> transactions;  // List of transactions
    private Budget budget;                   // Budget data
    private List<SavingsGoal> savingsGoals;  // List of savings goals

    // Constructor
    public ReportGenerator(List<Transaction> transactions, Budget budget, List<SavingsGoal> savingsGoals)
    {
        this.transactions = transactions;
        this.budget = budget;
        this.savingsGoals = savingsGoals;
    }

    // Outputs a summary for a specific month
    public void generateMonthlySummary(String month, String year)
    {
        float incomeTotal = 0f;
        float expenseTotal = 0f;

        for (Transaction tx : transactions)
        {
            if (tx.date.startsWith(year + "-" + month))
            {
                if (tx.type.equals("income"))
                {
                    incomeTotal += tx.amount;
                }
                else if (tx.type.equals("expense"))
                {
                    expenseTotal += tx.amount;
                }
            }
        }

        System.out.println("=== Monthly Summary ===");
        System.out.println("Income: " + incomeTotal);
        System.out.println("Expenses: " + expenseTotal);
        System.out.println("Net: " + (incomeTotal - expenseTotal));
    }

    // Exports all data to a CSV file
    public String exportReport(String basename)
    {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = basename.replace(".csv", "") + "_" + timestamp + ".csv";

        try (FileWriter out = new FileWriter(filename))
        {
            out.write("Date,Type,Category,Amount,Notes\n");

            for (Transaction tx : transactions)
            {
                out.write(tx.date + "," + tx.type + "," + tx.category + "," + tx.amount + "," + tx.notes + "\n");
            }

            out.write("\nBudget Limits:\nCategory,Limit\n");

            for (Map.Entry<String, Float> entry : budget.categoryLimits.entrySet())
            {
                out.write(entry.getKey() + "," + entry.getValue() + "\n");
            }

            out.write("\nSavings Goals:\nGoal,Target,Saved,Deadline\n");

            for (SavingsGoal goal : savingsGoals)
            {
                out.write(goal.goalName + "," + goal.targetAmount + "," + goal.savedAmount + "," + goal.deadline + "\n");
            }

            return filename; // if actual filename on success
        }
        catch (IOException e)
        {
            return null; // if filename failed
        }
    }
}