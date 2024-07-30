package groceryStore.GroceryStore;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Prices {

    // Prices will be a singleton class as there should only ever be
    // one instance of the Price class for consistent pricing across the entire application
    private static volatile Prices single_instance = null;

    // Default prices in Euros. These are private, but not final, so that the class instance is mutable, but
    // the class will retain some level of immutability by having the members private without any set methods.
    // This makes it so a new Prices object has to be instantiated every time a price needs to be updated, which
    // should prevent accidentally altering a price unintentionally, as the only time a Price object should be
    // instantiated is in the respective API method for setting prices.

    private double BREAD_PRICE; // per piece of bread
    private double VEGETABLES_PRICE; // per 100 grams
    private double BELGIAN_BEERS_PRICE; // per bottle
    private double DUTCH_BEERS_PRICE; // per bottle
    private double GERMAN_BEERS_PRICE; // per bottle

    // No-Argument Constructor for default prices, this is private to restrict instantiation
    private Prices() {
        BREAD_PRICE = 1.00; // per piece of bread
        VEGETABLES_PRICE = 1.00; // per 100 grams
        BELGIAN_BEERS_PRICE = 0.75; // per bottle
        DUTCH_BEERS_PRICE = 0.50; // per bottle
        GERMAN_BEERS_PRICE = 1.00; // per bottle
    }

    // Argument Constructor for custom prices, this is private to restrict instantiation
    @JsonCreator
    private Prices(@JsonProperty("BREAD_PRICE") double bread,
                   @JsonProperty("VEGETABLES_PRICE") double vegetables,
                   @JsonProperty("BELGIAN_BEERS_PRICE") double belgianBeers,
                   @JsonProperty("DUTCH_BEERS_PRICE") double dutchBeers,
                   @JsonProperty("GERMAN_BEERS_PRICE") double germanBeers) {
        this.BREAD_PRICE = bread;
        this.VEGETABLES_PRICE = vegetables;
        this.BELGIAN_BEERS_PRICE = belgianBeers;
        this.DUTCH_BEERS_PRICE = dutchBeers;
        this.GERMAN_BEERS_PRICE = germanBeers;
    }

    // No-Argument setInstance method will use the No-Argument Constructor
    public static void setInstance() {
        if (single_instance == null) {
            synchronized (Prices.class) {
                if (single_instance == null) {
                    single_instance = new Prices();
                }
            }
        }
    }

    // Overloaded setInstance method to use the Argument Constructor. Use this to change the prices
    // Always update the singleton instance
    public static synchronized void setInstance(double bread, double veg, double belgianBeer,
                                                double dutchBeer, double germanBeer) {
        single_instance = new Prices(bread, veg, belgianBeer, dutchBeer, germanBeer);
    }

    // Idea: could create a method for currency conversions from Euros. There are APIs for this that use current
    // exchange rates in real time. Interesting idea that could be handled in this class, but outside the
    // requirements for this assignment. It would require handling currencies that have more than two decimal places.

    // Get method for prices, but ensures that Prices instance is not null before being accessed
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
