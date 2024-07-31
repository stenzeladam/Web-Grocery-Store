package groceryStore.GroceryStore;

@SuppressWarnings("unused")
public class Item {

    private final double quantity;
    private final String itemName;
    private final String discountRule;
    private final double unitPrice;
    private final double totalItemPrice;
    private static double subtotal; // Static so that the subtotal will be the same for all Item instances

    Item(double quantity, String itemName, String discountRule, double unitPrice, double totalItemPrice) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.discountRule = discountRule;
        this. unitPrice = unitPrice;
        this.totalItemPrice = totalItemPrice;
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

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public double getTotalItemPrice() {
        return this.totalItemPrice;
    }

    public static void setSubtotal(double input) {
        subtotal = input;
    }

    public static double getSubtotal() {
        return subtotal;
    }
}
