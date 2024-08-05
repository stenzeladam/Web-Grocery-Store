package groceryStore.GroceryStore;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VegetableEvaluator {

    public Item evaluateVeg(double vegetables, double vegPrice) {
        // Checks vegetables for discounts. Adding one decimal place everywhere to make it clear values are doubles
        //double vegPrice = Prices.getInstance().getVEGETABLES_PRICE();
        double vegTotalPrice = 0.0;
        String discountRule = "";

        ArrayList<VegetableDiscounts> vegetableDiscountRules = Discounts.getVegDiscountRules();
        if (Discounts.isVegetablesDiscount()) {
            for (int i = 0; i < vegetableDiscountRules.size(); i++) {
                VegetableDiscounts rule = vegetableDiscountRules.get(i);
                double ceiling = BigDecimal.valueOf(rule.getUpperRange()).setScale(1, RoundingMode.HALF_UP).doubleValue();
                double floor = BigDecimal.valueOf(rule.getBottomRange()).setScale(1, RoundingMode.HALF_UP).doubleValue();

                if (vegetables >= rule.getBottomRange() && (i == vegetableDiscountRules.size() - 1 || vegetables < rule.getUpperRange())) {
                    double discountPercent = rule.getDiscountAmount();
                    vegTotalPrice = vegetables * (applyPercentOffDiscount(discountPercent, vegPrice) / 100);

                    if (i == vegetableDiscountRules.size() - 1) {
                        // Last rule
                        discountRule = "Over " + floor + " grams: " + discountPercent + "% Off";
                    } else {
                        discountRule = "Between " + floor + " and " + ceiling + " grams: " + discountPercent + "% Off";
                    }
                    break; // Rule found and applied, exit loop
                }
            }
        }
        else {
            vegTotalPrice = vegetables * vegPrice;
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
