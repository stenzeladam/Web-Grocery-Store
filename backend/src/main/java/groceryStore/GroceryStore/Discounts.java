package groceryStore.GroceryStore;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class Discounts {
    private static boolean beerPackDiscount = true;
    private static boolean vegetablesDiscount = true;
    private static boolean breadDiscount = true;
    private static double beerDiscountAmount = 33.3333; // percentage

    static ArrayList<VegetableDiscounts> vegDiscountRules = new ArrayList<>();
    static {
        vegDiscountRules.add(new VegetableDiscounts(0.0, 100.0, 5));
        vegDiscountRules.add(new VegetableDiscounts(100.0, 500.0, 7));
        vegDiscountRules.add(new VegetableDiscounts(500.0, Double.MAX_VALUE, 10));
    }

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

    public static String setBeerPackDiscount(boolean beerPackDiscount) {
        Discounts.beerPackDiscount = beerPackDiscount;
        return "beerPackDiscount: " + Discounts.beerPackDiscount;
    }

    public static String setBreadDiscount(boolean breadDiscount) {
        Discounts.breadDiscount = breadDiscount;
        return "breadDiscount: " + Discounts.breadDiscount;
    }

    public static String setVegetablesDiscount(boolean vegetablesDiscount) {
        Discounts.vegetablesDiscount = vegetablesDiscount;
        return "vegetablesDiscount: " + Discounts.vegetablesDiscount;
    }

    public static void setBeerDiscountAmount(double beerDiscountAmount) {
        Discounts.beerDiscountAmount = beerDiscountAmount;
    }

    public static boolean isVegetablesDiscount() {
        return vegetablesDiscount;
    }

    public static boolean isBeerPackDiscount() {
        return beerPackDiscount;
    }

    public static boolean isBreadDiscount() {
        return breadDiscount;
    }
}
