// Represents monthly budget limits for different categories

import java.util.HashMap;
import java.util.Map;

public class Budget
{

    public Map<String, Float> categoryLimits; // Maps category to its budget limit

    // Constructor
    public Budget()
    {
        this.categoryLimits = new HashMap<>();
    }

    // Sets a budget limit for a category
    public void setLimit(String category, float limit)
    {
        categoryLimits.put(category, limit);
    }

    // Retrieves the budget limit for a category
    public float getLimit(String category)
    {
        return categoryLimits.getOrDefault(category, 0f);
    }

}