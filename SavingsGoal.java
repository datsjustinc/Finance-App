// Represents a user's savings goal

public class SavingsGoal
{

    public String goalName;        // Name of the goal
    public float targetAmount;     // Target savings amount
    public float savedAmount;      // Current amount saved
    public String deadline;        // Optional deadline (YYYY-MM-DD or null)

    // Constructor
    public SavingsGoal(String goalName, float targetAmount, float savedAmount, String deadline)
    {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.deadline = deadline;
    }

}