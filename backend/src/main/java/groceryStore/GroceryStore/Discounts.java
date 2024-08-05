package groceryStore.GroceryStore;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Discounts {

    private static boolean breadDiscountActive = true;
    private static int breadDiscountOne = 2; // how many pieces of bread total for the price of one. Ex: 2 is "buy 1, get 1 free"
    private static int breadDiscountTwo = 3; // how many pieces of bread total for the price of one. Ex: 3 is "buy 1, get 2 free"

    private static boolean vegetablesDiscountActive = true;
    private static ArrayList<VegetableDiscounts> vegDiscountRules = new ArrayList<>();
    static {
        vegDiscountRules.add(new VegetableDiscounts(0.0, 100.0, 5));
        vegDiscountRules.add(new VegetableDiscounts(100.0, 500.0, 7));
        vegDiscountRules.add(new VegetableDiscounts(500.0, Double.MAX_VALUE, 10));
    }

    private static boolean belgianBeerDiscountActive = true;
    private static double belgianBeerDiscountPercent = 33.3333;
    private static boolean dutchBeerDiscountActive = true;
    private static double dutchBeerDiscountPercent = 33.3333;
    private static boolean germanBeerDiscountActive = true;
    private static double germanBeerDiscountPercent = 33.3333;

    public static void addVegetableDiscountRule(VegetableDiscounts rule) {
        vegDiscountRules.add(rule);
    }

    public static void updateVegetableDiscountRule(int index, VegetableDiscounts newRule) {
        if (index >= 0 && index < vegDiscountRules.size()) {
            vegDiscountRules.set(index, newRule);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public static void removeVegetableDiscountRule(int index) {
        if (index >= 0 && index < vegDiscountRules.size()) {
            vegDiscountRules.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    public static ArrayList<VegetableDiscounts> getVegDiscountRules() {
        return vegDiscountRules;
    }

    public static boolean isBelgianBeerDiscountActive() {
        return belgianBeerDiscountActive;
    }

    public static void setBelgianBeerDiscountActive(boolean belgianBeerDiscountActive) {
        Discounts.belgianBeerDiscountActive = belgianBeerDiscountActive;
    }

    public static double getBelgianBeerDiscountPercent() {
        return belgianBeerDiscountPercent;
    }

    public static void setBelgianBeerDiscountPercent(double belgianBeerDiscountPercent) {
        Discounts.belgianBeerDiscountPercent = belgianBeerDiscountPercent;
    }

    public static boolean isDutchBeerDiscountActive() {
        return dutchBeerDiscountActive;
    }

    public static void setDutchBeerDiscountActive(boolean dutchBeerDiscountActive) {
        Discounts.dutchBeerDiscountActive = dutchBeerDiscountActive;
    }

    public static double getDutchBeerDiscountPercent() {
        return dutchBeerDiscountPercent;
    }

    public static void setDutchBeerDiscountPercent(double dutchBeerDiscountPercent) {
        Discounts.dutchBeerDiscountPercent = dutchBeerDiscountPercent;
    }

    public static boolean isGermanBeerDiscountActive() {
        return germanBeerDiscountActive;
    }

    public static void setGermanBeerDiscountActive(boolean germanBeerDiscountActive) {
        Discounts.germanBeerDiscountActive = germanBeerDiscountActive;
    }

    public static double getGermanBeerDiscountPercent() {
        return germanBeerDiscountPercent;
    }

    public static void setGermanBeerDiscountPercent(double germanBeerDiscountPercent) {
        Discounts.germanBeerDiscountPercent = germanBeerDiscountPercent;
    }

    public static boolean isVegetablesDiscount() {
        return vegetablesDiscountActive;
    }

    public static boolean isBreadDiscount() {
        return breadDiscountActive;
    }

    public static void setBreadDiscount(boolean breadDiscount) {
        Discounts.breadDiscountActive = breadDiscount;
    }

    public static void setVegetablesDiscount(boolean vegetablesDiscount) {
        Discounts.vegetablesDiscountActive = vegetablesDiscount;
    }

    public static int getBreadDiscountOne() {
        return breadDiscountOne;
    }

    public static void setBreadDiscountOne(int breadDiscountOne) {
        Discounts.breadDiscountOne = breadDiscountOne;
    }

    public static int getBreadDiscountTwo() {
        return breadDiscountTwo;
    }

    public static void setBreadDiscountTwo(int breadDiscountTwo) {
        Discounts.breadDiscountTwo = breadDiscountTwo;
    }

    public static DiscountSummary getDiscountSummary() {
        return new DiscountSummary(
                breadDiscountActive, breadDiscountOne, breadDiscountTwo,
                vegetablesDiscountActive, vegDiscountRules,
                belgianBeerDiscountActive, belgianBeerDiscountPercent,
                dutchBeerDiscountActive, dutchBeerDiscountPercent,
                germanBeerDiscountActive, germanBeerDiscountPercent
        );
    }

}
