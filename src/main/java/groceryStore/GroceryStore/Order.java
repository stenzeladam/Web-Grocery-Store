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

    // Interesting note to self: public getters are necessary here for the object instance being created in the API
    // controller, because the private keyword in Java is enforced during and after compile-time while in TypeScript,
    // the private keyword is only enforced at compile-time, but not after, so the private members could be accessed
    // without get methods in the class. Important distinction for two different OOP languages. It is worth noting
    // because I plan to use TypeScript to develop a web-based frontend. Ignore the "no usages" warnings for these
    // methods.

    public int[] getBread() {
        return this.bread;
    }

    public int getGermanBeers() {
        return this.germanBeers;
    }

    public int getDutchBeers() {
        return this.dutchBeers;
    }

    public int getBelgianBeers() {
        return this.belgianBeers;
    }

    public float getVegetables() {
        return this.vegetables;
    }
}


