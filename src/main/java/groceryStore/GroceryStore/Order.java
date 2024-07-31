package groceryStore.GroceryStore;

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

@SuppressWarnings({"unused", "SameParameterValue"})
public class Order {

    private int[] bread = new int[7]; // each index will represent the age of the bread in days
    // Example: { 1, 0, 0, 2, 0, 0, 0 } means a quantity of 1 bread that is 0 days old, and 2 breads that are 3 days old
    private final double vegetables; // quantity of vegetables in grams
    private final int belgianBeers; // quantity of Belgian beers in bottles
    private final int dutchBeers; // quantity of Dutch beers in bottles
    private final int germanBeers; // quantity of German beers in bottles
    private double subTotal = 0; //Order subtotal

    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());

    public Order() { //No-Argument Constructor
        Arrays.fill(this.bread, 0); // explicitly define as an array of zeros, even if new int[7] automatically
        // initializes the array as zeros
        this.vegetables = 0;
        this.belgianBeers = 0;
        this.dutchBeers = 0;
        this.germanBeers = 0;
    }

    public Order(int[] breadIn, double vegetablesIn, int belgianBeersIn, int germanBeersIn, int dutchBeersIn) {
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

    public void evaluateOrder() {

        double vegTotalPrice = 0;
        double beerTotalPrice = 0;
        double breadTotalPrice = 0;
        Prices priceInstance = Prices.getInstance();

        // evaluateVeg uses a wrapper class Double for inputs, so passed by reference
        this.evaluateVeg(vegTotalPrice, priceInstance.getVEGETABLES_PRICE());
        // uses a wrapper class as well, beerTotalPrice is passed by reference.
        this.evaluateBeers(beerTotalPrice, priceInstance.getBELGIAN_BEERS_PRICE(), priceInstance.getDUTCH_BEERS_PRICE(),
                priceInstance.getGERMAN_BEERS_PRICE());
        // Wrapper class, breadTotalPrice passed by reference.
        this.evaluateBread(breadTotalPrice, priceInstance.getBREAD_PRICE());

        setSubTotal(vegTotalPrice + beerTotalPrice + breadTotalPrice);
    }

    private void evaluateVeg(@SuppressWarnings("ParameterCanBeLocal") Double vegTotalPrice, double price) {
        // checking vegetables for discounts. Adding one decimal place everywhere to make it clear these are doubles
        if (this.vegetables < 100.0 && this.vegetables >= 0.0) { // apply 5% off
            vegTotalPrice = applyPercentOffDiscount(5.0, price);
        }
        else if (this.vegetables >= 100.0 && this.vegetables < 500.0) { // apply 7% off
            vegTotalPrice = applyPercentOffDiscount(7.0, price);
        }
        else if (this.vegetables >= 500.0) { // apply 10% off
            vegTotalPrice = applyPercentOffDiscount(10.0, price);
        }
        else if (this.vegetables < 0.0) { // Should never happen, but just in case. Maybe for potential returns in the future
            vegTotalPrice = 0.0;
        }
    }

    @SuppressWarnings("ParameterCanBeLocal")
    private void evaluateBeers(Double beerTotalPrice, double belgianBeerPrice, double dutchBeerPrice, double germanBeerPrice) {
        // Take the quantity of each beer and perform integer division by 6 to determine how many 6-packs for each beer.
        // The sum of (n + k) times (a/b), where k = 0, ... , i-1, i, is the same as
        // (n+0)(a/b) + ... + (n+(i-1))(a/b) + (n+1)(a/b) where a and b are integers, and b is not equal to 0.
        // This is relevant because adding up the sum of 6 discounted beer bottles will be the same as discounting
        // the sum of 6 beer bottles.

        // Per the example given in the assignment:
        // If a Dutch beer is 0.50 EUR per bottle, and a 6 pack of Dutch beers is 2.00 EUR, that means a 6 pack of Dutch
        // beer has a 33.3333% off discount. If 6 packs of Belgian and German beers are 3.00 EUR and 4.00 EUR respectively,
        // then to maintain the same 33.3333% off discount for 6 packs, the default individual bottle price for Belgian
        // and German beer bottles should be 0.75 EUR and 1.00 EUR respectively. Otherwise, if all bottles are 0.50 EUR
        // a 6 pack of German beer would be 4.00 EUR while 6 individual bottles would only be 3.00 EUR, so that'd be a
        // markup and not make much sense.

        double DutchBottlePrice = Prices.getInstance().getDUTCH_BEERS_PRICE();
        double DutchSixPackDiscount = (1.0/3.0); // ~33.3333...%
        double DutchBeerTotal = beerType_n_Packs(6, this.dutchBeers, DutchSixPackDiscount, DutchBottlePrice);

        double BelgianBottlePrice = Prices.getInstance().getBELGIAN_BEERS_PRICE();
        double BelgianSixPackDiscount = (1.0/3.0); // ~33.3333...%
        double BelgianBeerTotal = beerType_n_Packs(6, this.belgianBeers, BelgianSixPackDiscount, BelgianBottlePrice);

        double GermanBottlePrice = Prices.getInstance().getGERMAN_BEERS_PRICE();
        double GermanSixPackDiscount = (1.0/3.0); // ~33.3333...%
        double GermanBeerTotal = beerType_n_Packs(6, this.germanBeers, GermanSixPackDiscount, GermanBottlePrice);

        beerTotalPrice = DutchBeerTotal + BelgianBeerTotal + GermanBeerTotal;
    }

    // Examples: For a 6-pack, let n = 6. For Dutch beer, let beerType = this.dutchBeers.
    private double beerType_n_Packs(int n, int beerType, double packDiscount, double bottlePrice) {
        int nPacks = beerType / n; // the number of n-packs of beer for the given beer type
        int singleBottles = beerType % n; // the number of individual bottles for the given beer type
        double total = 0.0;

        total = nPacks * (n * applyPercentOffDiscount(packDiscount, bottlePrice)); // add the prices of n-packs
        total = total + (singleBottles * bottlePrice); // add the prices of individual bottles

        return total;
    }

    private void evaluateBread(Double breadTotalPrice, double price) {
        //TODO: implement bread discounts
    }

    private double applyPercentOffDiscount(double percentOff, double itemPrice) {
        double complementaryPercentage = (1.0 - percentOff);
        return itemPrice * complementaryPercentage;
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

    public double getSubTotal() {
        return this.subTotal;
    }

    private void setSubTotal(double value) {
        this.subTotal = value;
    }
}


