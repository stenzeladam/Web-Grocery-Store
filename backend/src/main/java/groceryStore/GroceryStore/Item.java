package groceryStore.GroceryStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@SuppressWarnings("unused")
public class Item {

    private final double quantity;
    private final String itemName;
    private final String discountRule;
    private final double unitPrice;
    private final double totalItemPrice;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    Item(double quantity, String itemName, String discountRule, double unitPrice, double totalItemPrice) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.discountRule = discountRule;
        this.unitPrice = roundToTwoDecimals(unitPrice);
        this.totalItemPrice = roundToTwoDecimals(totalItemPrice);
    }

    public double getQuantity() {
        return this.quantity;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getDiscountRule() {
        return this.discountRule;
    }

    public String getFormattedUnitPrice() {
        return df.format(this.unitPrice);
    }

    public String getFormattedTotalItemPrice() {
        return df.format(this.totalItemPrice);
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public double getTotalItemPrice() {
        return this.totalItemPrice;
    }

    private double roundToTwoDecimals(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", itemName='" + itemName + '\'' +
                ", discountRule='" + discountRule + '\'' +
                ", unitPrice=" + getFormattedUnitPrice() +
                ", totalItemPrice=" + getFormattedTotalItemPrice() +
                '}';
    }


}
