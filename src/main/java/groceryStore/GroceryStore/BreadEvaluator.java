package groceryStore.GroceryStore;

import java.util.ArrayList;

public class BreadEvaluator {
    public ArrayList<Item> evaluateBread(int[] bread, double breadPrice) {
        // First, create a temporary ArrayList to add all potential bread items.
        ArrayList<Item> breadReceipt = new ArrayList<>();

        // Then go through this.bread, where each index number represents the age of the bread in days
        // There is no discount for bread that is 0, 1, or 2 days old. Those pieces of bread will always be full price
        int dayBreadDiscountBegins = 3;
        int dayBreadDiscountTwoBegins = 6;

        // Evaluate the bread with no discounts
        for (int i = 0; i < dayBreadDiscountBegins; i++) {
            if (bread[i] > 0) { // bread[i] is the quantity. No need to add quantities of 0
                if (i == 1) {
                    Item dayOneItem = new Item(bread[i], "Bread (1 day old)", "", breadPrice, bread[i] * breadPrice);
                    breadReceipt.add(dayOneItem);
                }
                else {
                    Item tempBreadItem = new Item(bread[i], "Bread (" + i + " days old)", "", breadPrice, bread[i] * breadPrice);
                    breadReceipt.add(tempBreadItem);
                }
            }
        }

        // Bread that is 3, 4, or 5 days old is "buy 1, get 1 free" aka "buy 1, take 2". Call this discount one.
        // Bread that is 6 days old is "buy 1, get 2 free" aka "buy 1, take 3". Call this discount two.
        // For simplicity's sake, assume that 6 day old bread cannot be included in the "buy 1, get 1 free" discount

        // Alternatively, I think it would be much simpler and intuitive if the rule of "buy 1, get 1 free" simply meant
        // the unit price was half off, and that is how you would get 2 pieces for the price of one. Similar logic would
        // work for "buy 1, get 2 free" where the unit price would be  1/3 of the non-discounted price, and that is how
        // 3 pieces of bread can be bought for the price of one. This would make it easy to include both types of
        // bread discounts in the same order, but this is not how the requirement in the assignment is worded, so the
        // following discount logic should stand.

        // Change these to change the number of free bread pieces per discount. The number of free pieces will be n-1
        // Ex: numDiscountOne = 2 because "buy 1, get 1 free" is a total of 2
        // Ex: numDiscountTwo = 3 because "buy 1, get 2 free" is a total of 3
        int numDiscountOne = 2; // Buy 1, get 1 free
        int numDiscountTwo = 3; // Buy 1, get 2 free

        // Buy 1, get 1 free discount for bread that is 3, 4, and/or 5 days old
        int totalBreadDiscountOne = 0;
        for (int i = dayBreadDiscountBegins; i < dayBreadDiscountTwoBegins; i++) {
            totalBreadDiscountOne += bread[i];
        }
        int paidBreadDiscountOne = (totalBreadDiscountOne + (numDiscountOne - 1)) / numDiscountOne; // The number of pieces to pay for in discount one

        // Buy 1, get 2 free discount for bread that is 6 days old
        int breadPieces6 = bread[6];
        int paidBreadDiscountTwo = (breadPieces6 + (numDiscountTwo - 1)) / numDiscountTwo; // The number of pieces to pay for in discount two

        // Calculate the total item prices
        double breadDiscountOnePrice = paidBreadDiscountOne * breadPrice;
        double breadDiscountTwoPrice = paidBreadDiscountTwo * breadPrice;

        // Add items to the receipt
        if (totalBreadDiscountOne > 0) {
            Item discountOne = new Item(totalBreadDiscountOne, "Bread (3, 4, or 5 days old)", "Buy 1, get 1 free", breadPrice, breadDiscountOnePrice);
            breadReceipt.add(discountOne);
        }
        if (breadPieces6 > 0) {
            Item discountTwo = new Item(breadPieces6, "Bread (6 days old)", "Buy 1, get 2 free", breadPrice, breadDiscountTwoPrice);
            breadReceipt.add(discountTwo);
        }

        return breadReceipt;
    }
}
