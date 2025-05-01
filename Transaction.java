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
        if (date == null || type == null || category == null || amount < 0) {
            throw new IllegalArgumentException("Invalid transaction details");
        }
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.notes = notes;
    }

        // Getters
    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public float getAmount() {
        return amount;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}