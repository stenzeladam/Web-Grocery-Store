package groceryStore.GroceryStore;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class DiscountSummary {
    private boolean breadDiscountActive;
    private int breadDiscountOne;
    private int breadDiscountTwo;

    private boolean vegetablesDiscountActive;
    private ArrayList<VegetableDiscounts> vegDiscountRules;

    private boolean belgianBeerDiscountActive;
    private double belgianBeerDiscountPercent;
    private boolean dutchBeerDiscountActive;
    private double dutchBeerDiscountPercent;
    private boolean germanBeerDiscountActive;
    private double germanBeerDiscountPercent;

    private String breadDiscountOneMessage;
    private String breadDiscountTwoMessage;

    public DiscountSummary(boolean breadDiscountActive, int breadDiscountOne, int breadDiscountTwo,
                           boolean vegetablesDiscountActive, ArrayList<VegetableDiscounts> vegDiscountRules,
                           boolean belgianBeerDiscountActive, double belgianBeerDiscountPercent,
                           boolean dutchBeerDiscountActive, double dutchBeerDiscountPercent,
                           boolean germanBeerDiscountActive, double germanBeerDiscountPercent) {
        this.breadDiscountActive = breadDiscountActive;
        this.breadDiscountOne = breadDiscountOne;
        this.breadDiscountTwo = breadDiscountTwo;
        this.vegetablesDiscountActive = vegetablesDiscountActive;
        this.vegDiscountRules = new ArrayList<>(vegDiscountRules);
        this.belgianBeerDiscountActive = belgianBeerDiscountActive;
        this.belgianBeerDiscountPercent = belgianBeerDiscountPercent;
        this.dutchBeerDiscountActive = dutchBeerDiscountActive;
        this.dutchBeerDiscountPercent = dutchBeerDiscountPercent;
        this.germanBeerDiscountActive = germanBeerDiscountActive;
        this.germanBeerDiscountPercent = germanBeerDiscountPercent;

        this.breadDiscountOneMessage = "Bread Discount One: Buy 1, get " + (breadDiscountOne - 1) + " free";
        this.breadDiscountTwoMessage = "Bread Discount Two: Buy 1, get " + (breadDiscountTwo - 1) + " free";
    }

    // Getters for all the fields
    public boolean isBreadDiscountActive() {
        return breadDiscountActive;
    }

    public int getBreadDiscountOne() {
        return breadDiscountOne;
    }

    public int getBreadDiscountTwo() {
        return breadDiscountTwo;
    }

    public boolean isVegetablesDiscountActive() {
        return vegetablesDiscountActive;
    }

    public ArrayList<VegetableDiscounts> getVegDiscountRules() {
        return vegDiscountRules;
    }

    public boolean isBelgianBeerDiscountActive() {
        return belgianBeerDiscountActive;
    }

    public double getBelgianBeerDiscountPercent() {
        return belgianBeerDiscountPercent;
    }

    public boolean isDutchBeerDiscountActive() {
        return dutchBeerDiscountActive;
    }

    public double getDutchBeerDiscountPercent() {
        return dutchBeerDiscountPercent;
    }

    public boolean isGermanBeerDiscountActive() {
        return germanBeerDiscountActive;
    }

    public double getGermanBeerDiscountPercent() {
        return germanBeerDiscountPercent;
    }

    public String getBreadDiscountOneMessage() {
        return breadDiscountOneMessage;
    }

    public String getBreadDiscountTwoMessage() {
        return breadDiscountTwoMessage;
    }
}
