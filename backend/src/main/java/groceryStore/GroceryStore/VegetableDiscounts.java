package groceryStore.GroceryStore;

public class VegetableDiscounts {
    private double bottomRange; // in grams
    private double upperRange; // in grams
    private double discountAmount; // percentage

    public VegetableDiscounts(double bottom, double top, double amount) {
        this.bottomRange = bottom;
        this.upperRange = top;
        this.discountAmount = amount;
    }

    public double getBottomRange() {
        return this.bottomRange;
    }

    public void setBottomRange(double bottomRange) {
        this.bottomRange = bottomRange;
    }

    public double getUpperRange() {
        return this.upperRange;
    }

    public void setUpperRange(double upperRange) {
        this.upperRange = upperRange;
    }

    public double getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}
