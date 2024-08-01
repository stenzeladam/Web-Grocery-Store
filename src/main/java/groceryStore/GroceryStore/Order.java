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

        // No need to add vegetables to the receipt if none are on order
        if (this.vegetables > 0) {
            Item vegetables = this.evaluateVeg();
            Receipt.add(vegetables);
        }

        // No need to evaluate the beers if there are none on order
        if (this.germanBeers > 0 || this.belgianBeers > 0 || this.dutchBeers > 0) {
            Receipt.addAll(this.evaluateBeers());
        }

        // TODO: Implement evaluate Bread and add to the Receipt
        this.evaluateBread();

        double localSubtotal = 0.0;
        for (Item tempItem : Receipt) {
            localSubtotal = localSubtotal + tempItem.getTotalItemPrice();
        }
        //Item.setSubtotal(localSubtotal); // set the subtotal
        Item SubTotal = new Item(1, "Subtotal", "", -1, localSubtotal);
        Receipt.add(SubTotal);

        return Receipt;
    }

    private Item evaluateVeg() {
        // Checks vegetables for discounts. Adding one decimal place everywhere to make it clear values are doubles
        double vegPrice = Prices.getInstance().getVEGETABLES_PRICE();
        double vegTotalPrice = 0.0;
        String discountRule = "";
        if (this.vegetables < 100.0 && this.vegetables > 0.0) { // apply 5% off
            vegTotalPrice = applyPercentOffDiscount(5.0, vegPrice);
            discountRule = "Between 0.0 and 100.0 grams: 5% Off";
        }
        else if (this.vegetables >= 100.0 && this.vegetables < 500.0) { // apply 7% off
            vegTotalPrice = applyPercentOffDiscount(7.0, vegPrice);
            discountRule = "Between 100.0 and 500.0 grams: 7% Off";
        }
        else if (this.vegetables >= 500.0) { // apply 10% off
            vegTotalPrice = applyPercentOffDiscount(10.0, vegPrice);
            discountRule = "Over 500.0 grams: 10% Off";
        }
        else if (this.vegetables < 0.0) { // Should never happen, but just in case. Maybe for potential returns in the future
            vegTotalPrice = 0.0;
        }
        return new Item(this.vegetables, "Vegetables", discountRule, vegPrice, vegTotalPrice);
    }

    private ArrayList<Item> evaluateBeers() {
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

        ArrayList<Item> beerReceipt = new ArrayList<>();
        int n = 6; // set the number of bottles to define a pack

        double DutchBottlePrice = Prices.getInstance().getDUTCH_BEERS_PRICE();
        double Dutch_n_PackDiscount = 33.3333; // ~33.3333...%

        // No need to add item to receipt if not on order
        if (this.dutchBeers > 0) {
            beerReceipt.addAll(addBeerTypeToReceipt(n, this.dutchBeers, "Dutch", DutchBottlePrice, Dutch_n_PackDiscount));
        }

        double BelgianBottlePrice = Prices.getInstance().getBELGIAN_BEERS_PRICE();
        double Belgian_n_PackDiscount = 33.3333; // ~33.3333...%

        if (this.belgianBeers > 0) {
            beerReceipt.addAll(addBeerTypeToReceipt(n, this.belgianBeers, "Belgian", BelgianBottlePrice, Belgian_n_PackDiscount));
        }

        double GermanBottlePrice = Prices.getInstance().getGERMAN_BEERS_PRICE();
        double German_n_PackDiscount = 33.3333; // ~33.3333...%

        if (this.germanBeers > 0) {
            beerReceipt.addAll(addBeerTypeToReceipt(n, this.germanBeers, "German", GermanBottlePrice, German_n_PackDiscount));
        }

        return beerReceipt;
    }

    private ArrayList<Item> addBeerTypeToReceipt(int n, int beerType, String beerName, double bottlePrice, double packDiscount) {

        ArrayList<Item> localList = new ArrayList<>();
        int numOfPacks = beerType / n;
        int numOfSingleBottles = beerType % n;

        if (numOfPacks > 0) {
            String localItemName = n + "-Pack of " + beerName + " Beer";
            String discountRule = n + "-Pack of Beer";
            double localPackPrice = applyPercentOffDiscount(packDiscount, bottlePrice * n);
            Item beerPack = new Item(numOfPacks, localItemName, discountRule, localPackPrice, localPackPrice * numOfPacks);
            localList.add(beerPack);
        }
        if (numOfSingleBottles > 0) {
            String localItemName = beerName + " Beer Bottle";
            String discountRule = "";
            Item beerBottles = new Item(numOfSingleBottles, localItemName, discountRule, bottlePrice, bottlePrice * numOfSingleBottles);
            localList.add(beerBottles);
        }

        return localList;
    }

    private void evaluateBread() {
        //TODO: implement bread discounts
    }

    private double applyPercentOffDiscount(double percentOff, double itemPrice) {
        double complementaryPercentage = (1.0 - (percentOff / 100.0));
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
}


