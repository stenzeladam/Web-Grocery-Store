package groceryStore.GroceryStore;

import java.util.Arrays;
import java.util.logging.Logger;

public class Order {
    private int[] bread = new int[7]; // each index will represent the age of the bread in days
    // Example: { 1, 0, 0, 2, 0, 0, 0 } means a quantity of 1 bread that is 0 days old, and 2 breads that are 3 days old
    private float vegetables; // quantity of vegetables in grams
    private int belgianBeers; // quantity of Belgian beers in bottles
    private int dutchBeers; // quantity of Dutch beers in bottles
    private int germanBeers; // quantity of German beers in bottles
    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());

    public Order() { //No-Argument Constructor
        Arrays.fill(this.bread, 0); // explicitly define as an array of zeros, even if new int[7] automatically
        // initializes the array as zeros
        this.vegetables = 0;
        this.belgianBeers = 0;
        this.dutchBeers = 0;
        this.germanBeers = 0;
    }

    public Order(int[] breadIn, float vegetablesIn, int belgianBeersIn, int germanBeersIn, int dutchBeersIn) {
        try {
            if (breadIn.length == this.bread.length) { // ensure the arrays are the same size
                this.bread = breadIn;
            }
            else {
                throw new IllegalArgumentException("The bread array argument is not the correct size as defined in the Order class");
            }
        } catch (IllegalArgumentException  e) {
            Arrays.fill(this.bread, 0);
            LOGGER.severe(e.getMessage());
        }
        this.vegetables = vegetablesIn;
        this.belgianBeers = belgianBeersIn;
        this.dutchBeers = dutchBeersIn;
        this.germanBeers = germanBeersIn;
    }

    public int getGermanBeers() {
        return germanBeers;
    }

    public int getDutchBeers() {
        return dutchBeers;
    }

    public int getBelgianBeers() {
        return belgianBeers;
    }

    public float getVegetables() {
        return vegetables;
    }
}


