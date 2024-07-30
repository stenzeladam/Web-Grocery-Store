package groceryStore.GroceryStore;

public class Prices {

    // Prices will be a singleton class as there should only ever be
    // one instance of the Price class for consistent pricing across the entire application
    private static volatile Prices single_instance = null;

    // Default prices in Euros. Final to make the class instance immutable
    private final double BREAD_PRICE; // per piece of bread
    private final double VEGETABLES_PRICE; // per 100 grams
    private final double BELGIAN_BEERS_PRICE; // per bottle
    private final double DUTCH_BEERS_PRICE; // per bottle
    private final double GERMAN_BEERS_PRICE; // per bottle

    // No-Argument Constructor for default prices, this is private to restrict instantiation
    private Prices() {
        BREAD_PRICE = 1.00; // per piece of bread
        VEGETABLES_PRICE = 1.00; // per 100 grams
        BELGIAN_BEERS_PRICE = 0.75; // per bottle
        DUTCH_BEERS_PRICE = 0.50; // per bottle
        GERMAN_BEERS_PRICE = 1.00; // per bottle
    }

    // Argument Constructor for custom prices, this is private to restrict instantiation
    private Prices(double bread, double vegetables, double belgianBeers, double dutchBeers, double germanBeers) {
        BREAD_PRICE = bread;
        VEGETABLES_PRICE = vegetables;
        BELGIAN_BEERS_PRICE = belgianBeers;
        DUTCH_BEERS_PRICE = dutchBeers;
        GERMAN_BEERS_PRICE = germanBeers;
    }

    // No-Argument getInstance method will use the No-Argument Constructor
    public static Prices getInstance() {
        if (single_instance == null) {
            synchronized (Prices.class) {
                if (single_instance == null) {
                    single_instance = new Prices();
                }
            }
        }
        return single_instance;
    }

    // Overloaded getInstance method to use the Argument Constructor. Use this to change the prices
    public static Prices getInstance(double bread, double veg,
                                        double belgianBeer, double dutchBeer, double germanBeer) {
        if (single_instance == null) {
            synchronized (Prices.class) {
                if (single_instance == null) {
                    single_instance = new Prices(bread, veg, belgianBeer, dutchBeer, germanBeer);
                }
            }
        }
        return single_instance;
    }

    // Idea: could create a method for currency conversions from Euros. There are APIs for this that use current
    // exchange rates in real time. Interesting idea that could be handled in this class, but outside the
    // requirements for this assignment. It would require handling currencies that have more than two decimal places.

    public double getBREAD_PRICE() {
        return BREAD_PRICE;
    }

    public double getVEGETABLES_PRICE() {
        return VEGETABLES_PRICE;
    }

    public double getBELGIAN_BEERS_PRICE() {
        return BELGIAN_BEERS_PRICE;
    }

    public double getDUTCH_BEERS_PRICE() {
        return DUTCH_BEERS_PRICE;
    }

    public double getGERMAN_BEERS_PRICE() {
        return GERMAN_BEERS_PRICE;
    }
}
