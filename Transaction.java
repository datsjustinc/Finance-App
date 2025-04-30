// Represents a financial transaction: income or expense

public class Transaction
{

    public String date;       // Date of the transaction (YYYY-MM-DD)
    public String type;       // "income" or "expense"
    public String category;   // Category such as Food, Rent, etc.
    public float amount;      // Transaction amount
    public String notes;      // Optional notes about the transaction

    // Constructor to initialize a transaction
    public Transaction(String date, String type, String category, float amount, String notes)
    {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.notes = notes;
    }

}