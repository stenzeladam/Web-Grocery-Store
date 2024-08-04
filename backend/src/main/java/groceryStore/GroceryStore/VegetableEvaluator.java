package groceryStore.GroceryStore;

public class VegetableEvaluator {

    public Item evaluateVeg(double vegetables, double vegPrice) {
        // Checks vegetables for discounts. Adding one decimal place everywhere to make it clear values are doubles
        //double vegPrice = Prices.getInstance().getVEGETABLES_PRICE();
        double vegTotalPrice = 0.0;
        String discountRule = "";
        if (vegetables < 100.0 && vegetables > 0.0) { // apply 5% off
            // Discount price is divided by 100 because the price is per 100g, and this.vegetables is per ~1.0 gram.
            vegTotalPrice = vegetables * (applyPercentOffDiscount(5.0, vegPrice) / 100);
            discountRule = "Between 0.0 and 100.0 grams: 5% Off";
        }
        else if (vegetables >= 100.0 && vegetables < 500.0) { // apply 7% off
            vegTotalPrice = vegetables * (applyPercentOffDiscount(7.0, vegPrice) / 100);
            discountRule = "Between 100.0 and 500.0 grams: 7% Off";
        }
        else if (vegetables >= 500.0) { // apply 10% off
            vegTotalPrice = vegetables * (applyPercentOffDiscount(10.0, vegPrice) / 100);
            discountRule = "Over 500.0 grams: 10% Off";
        }
        else if (vegetables < 0.0) { // Should never happen, but just in case. Maybe for potential returns in the future
            vegTotalPrice = 0.0;
        }
        return new Item(vegetables, "Vegetables", discountRule, vegPrice, vegTotalPrice);
    }

    public double applyPercentOffDiscount(double percentOff, double itemPrice) {
        double complementaryPercentage = (1.0 - (percentOff / 100.0));
        double result = itemPrice * complementaryPercentage;
        if (result < 0.0) {
            return 0.0;
        }
        return itemPrice * complementaryPercentage;
    }
}
