// Represents the user’s profile settings

import java.util.List;

public class UserProfile
{

    public String name;                      // User's name
    public String currency;                  // Preferred currency
    public float monthlyIncome;              // User's monthly income
    public List<String> preferredCategories; // Preferred categories for spending

    // Constructor
    public UserProfile(String name, String currency, float monthlyIncome, List<String> preferredCategories)
    {
        this.name = name;
        this.currency = currency;
        this.monthlyIncome = monthlyIncome;
        this.preferredCategories = preferredCategories;
    }
    
    // Getters
    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public List<String> getPreferredCategories() {
        return preferredCategories;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setPreferredCategories(List<String> preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

}