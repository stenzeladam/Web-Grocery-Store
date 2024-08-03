package groceryStore.GroceryStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

// *** To add additional products in the future, follow these steps:
// 1) Add the product as a private member variable in this Order class
// 2) Add initializations to the No-Argument Constructor and the Constructor that takes arguments. Append the
//    arguments for the argument constructor as needed.
// 3) Modify the JSON body being sent from the front-end in the @PostMapping("/send_order") method in the
//    GroceryStoreController class
// 4) Create a get method in the Order class so that the member can be accessed from the JSON body in the controller
// 5) Update the Prices class to have member variable for the price of the product, and update the parameters for the
//    constructors, the getInstance methods, and get method for that variable.
// 6) Update the setPrices method in GroceryStoreController and call it to update the single Prices object instance to
//    include the new product. Also need to update the JSON body from the frontend (and the relevant FE interface) being
//    sent to setPrices to match the updated Prices member variable(s).

@SuppressWarnings({"unused"})
public class Order {

    private int[] bread = new int[7]; // each index will represent the age of the bread in days
    // Example: { 1, 0, 0, 2, 0, 0, 0 } means a quantity of 1 bread that is 0 days old, and 2 breads that are 3 days old
    private final double vegetables; // quantity of vegetables in grams
    private final int belgianBeers; // quantity of Belgian beers in bottles
    private final int dutchBeers; // quantity of Dutch beers in bottles
    private final int germanBeers; // quantity of German beers in bottles

    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());

    // Using helper classes so that I can have methods be public for unit testing purposes, without exposing the inner
    // workings of the Order class and preventing unwanted interactions with the Order class
    private final BreadEvaluator breadEvaluator = new BreadEvaluator();
    private final VegetableEvaluator vegetableEvaluator = new VegetableEvaluator();
    private final BeerEvaluator beerEvaluator = new BeerEvaluator();

    public Order() { //No-Argument Constructor
        Arrays.fill(this.bread, 0); // explicitly define as an array of zeros, even if new int[7] automatically
        // initializes the array as zeros
        this.vegetables = 0;
        this.belgianBeers = 0;
        this.dutchBeers = 0;
        this.germanBeers = 0;
    }

    public Order(int[] breadIn, double vegetablesIn, int belgianBeersIn, int dutchBeersIn, int germanBeersIn) {
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

    // Method that evaluates this Order object to determine if any discounts apply. Calls methods to apply discounts
    // when specific discounts are applicable. Edit this method to alter the rules for when discounts apply.

    public ArrayList<Item> evaluateOrder() {
        Prices priceInstance = Prices.getInstance();
        ArrayList<Item> Receipt = new ArrayList<>();
        double breadPrice = priceInstance.getBREAD_PRICE();
        double vegPrice = priceInstance.getVEGETABLES_PRICE();

        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        int[] zeros = new int[7];
        // Only check if bread is not [0, 0, 0, 0, 0, 0, 0]
        if (!Arrays.equals(this.bread, zeros)) {
            Receipt.addAll(breadEvaluator.evaluateBread(this.bread, breadPrice));
        }

        // No need to evaluate vegetables if there are none on order
        if (this.vegetables > 0) {
            Receipt.add(vegetableEvaluator.evaluateVeg(this.vegetables, vegPrice));
        }

        // No need to evaluate the beers if there are none on order
        if (this.germanBeers > 0 || this.belgianBeers > 0 || this.dutchBeers > 0) {
            Receipt.addAll(beerEvaluator.evaluateBeers(this.belgianBeers, this.dutchBeers, this.germanBeers));
        }

        double localSubtotal = 0.0;
        for (Item tempItem : Receipt) {
            localSubtotal = localSubtotal + tempItem.getTotalItemPrice();
        }

        // set the subtotal
        Item SubTotal = new Item(-1, "Subtotal", "", 1, localSubtotal);
        Receipt.add(SubTotal);

        return Receipt;
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

    public double getVegetables() {
        return this.vegetables;
    }
}


