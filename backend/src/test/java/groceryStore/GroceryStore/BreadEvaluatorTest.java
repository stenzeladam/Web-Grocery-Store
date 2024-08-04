package groceryStore.GroceryStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
public class BreadEvaluatorTest {

    @Test
    public void trivialTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(0, result.size());
    }

    @Test
    public void dumbInputsTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {-1, -1, -1, -1, -1, -1, -1};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(0, result.size());
    }

    @Test
    public void basicTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {1, 0, 0, 0, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (0 days old)", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayOneTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 1, 0, 0, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (1 day old)", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayTwoTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 1, 0, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (2 days old)", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayThreeTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 1, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (3, 4, or 5 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 1 free", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayThreeDiscountTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 2, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (3, 4, or 5 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 1 free", result.get(0).getDiscountRule());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayFourTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 1, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (3, 4, or 5 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 1 free", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayFourDiscountTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 2, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (3, 4, or 5 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 1 free", result.get(0).getDiscountRule());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayFiveTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 1, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (3, 4, or 5 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 1 free", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void dayFiveDiscountTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 2, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (3, 4, or 5 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 1 free", result.get(0).getDiscountRule());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void daySixTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 0, 1};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (6 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 2 free", result.get(0).getDiscountRule());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void daySixDiscountTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 0, 3};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (6 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 2 free", result.get(0).getDiscountRule());
        assertEquals(3, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void daySixDiscountTest_2() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 0, 2};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());
        assertEquals("Bread (6 days old)", result.get(0).getItemName());
        assertEquals("Buy 1, get 2 free", result.get(0).getDiscountRule());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals(1.00, result.get(0).getTotalItemPrice());
    }

    @Test
    public void higherQuantityDayZero() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {10, 0, 0, 0, 0, 0, 0};
        double breadPrice = 2.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (0 days old)", item.getItemName());
        assertEquals("", item.getDiscountRule());
        assertEquals(10, item.getQuantity());
        assertEquals(20.00, item.getTotalItemPrice());
    }

    @Test
    public void discountOneDifferentPriceTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 6, 0, 0, 0};
        double breadPrice = 2.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", item.getItemName());
        assertEquals("Buy 1, get 1 free", item.getDiscountRule());
        assertEquals(6, item.getQuantity());
        assertEquals(6.00, item.getTotalItemPrice());
    }

    @Test
    public void discountOneTest_2() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 7, 0, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", item.getItemName());
        assertEquals("Buy 1, get 1 free", item.getDiscountRule());
        assertEquals(7, item.getQuantity());
        assertEquals(4.00, item.getTotalItemPrice());
    }

    @Test
    public void discountOneTest_3() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 7, 1, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", item.getItemName());
        assertEquals("Buy 1, get 1 free", item.getDiscountRule());
        assertEquals(8, item.getQuantity());
        assertEquals(4.00, item.getTotalItemPrice());
    }

    @Test
    public void discountOneTest_4() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 7, 8, 9, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", item.getItemName());
        assertEquals("Buy 1, get 1 free", item.getDiscountRule());
        assertEquals(24, item.getQuantity());
        assertEquals(12.00, item.getTotalItemPrice());
    }

    @Test
    public void discountOneTest_5() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {1, 2, 1, 8, 8, 9, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(4, result.size());

        Item dayZero = result.get(0);
        assertEquals("Bread (0 days old)", dayZero.getItemName());
        assertEquals("", dayZero.getDiscountRule());
        assertEquals(1, dayZero.getQuantity());
        assertEquals(1.00, dayZero.getTotalItemPrice());

        Item dayOne = result.get(1);
        assertEquals("Bread (1 day old)", dayOne.getItemName());
        assertEquals("", dayOne.getDiscountRule());
        assertEquals(2, dayOne.getQuantity());
        assertEquals(2.00, dayOne.getTotalItemPrice());

        Item dayTwo = result.get(2);
        assertEquals("Bread (2 days old)", dayTwo.getItemName());
        assertEquals("", dayTwo.getDiscountRule());
        assertEquals(1, dayTwo.getQuantity());
        assertEquals(1.00, dayTwo.getTotalItemPrice());

        Item discount = result.get(3);
        assertEquals("Bread (3, 4, or 5 days old)", discount.getItemName());
        assertEquals("Buy 1, get 1 free", discount.getDiscountRule());
        assertEquals(25, discount.getQuantity());
        assertEquals(13.00, discount.getTotalItemPrice());
    }

    @Test
    public void discountOneTest_6() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 4, 0, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", item.getItemName());
        assertEquals("Buy 1, get 1 free", item.getDiscountRule());
        assertEquals(4, item.getQuantity());
        assertEquals(2.00, item.getTotalItemPrice());
    }

    @Test
    public void discountOneTest_7() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 6, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", item.getItemName());
        assertEquals("Buy 1, get 1 free", item.getDiscountRule());
        assertEquals(6, item.getQuantity());
        assertEquals(3.00, item.getTotalItemPrice());
    }

    @Test
    public void differentPriceTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {1, 0, 2, 1, 1, 0, 0};
        double breadPrice = 2.99;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(3, result.size());

        Item item0 = result.get(0);
        assertEquals("Bread (0 days old)", item0.getItemName());
        assertEquals("", item0.getDiscountRule());
        assertEquals(1, item0.getQuantity());
        assertEquals(2.99, item0.getTotalItemPrice());

        Item item1 = result.get(1);
        assertEquals("Bread (2 days old)", item1.getItemName());
        assertEquals("", item1.getDiscountRule());
        assertEquals(2, item1.getQuantity());
        assertEquals(5.98, item1.getTotalItemPrice());

        Item item2 = result.get(2);
        assertEquals("Bread (3, 4, or 5 days old)", item2.getItemName());
        assertEquals("Buy 1, get 1 free", item2.getDiscountRule());
        assertEquals(2, item2.getQuantity());
        assertEquals(2.99, item2.getTotalItemPrice());
    }

    @Test
    public void discountTwoTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 0, 0, 0, 9};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item item = result.get(0);
        assertEquals("Bread (6 days old)", item.getItemName());
        assertEquals("Buy 1, get 2 free", item.getDiscountRule());
        assertEquals(9, item.getQuantity());
        assertEquals(3.00, item.getTotalItemPrice(), 0.01);
    }

    @Test
    public void discountTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 5, 0, 0, 6};
        double breadPrice = 2.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(2, result.size());

        Item discountOne = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", discountOne.getItemName());
        assertEquals("Buy 1, get 1 free", discountOne.getDiscountRule());
        assertEquals(5, discountOne.getQuantity());
        assertEquals(6.00, discountOne.getTotalItemPrice());

        Item discountTwo = result.get(1);
        assertEquals("Bread (6 days old)", discountTwo.getItemName());
        assertEquals("Buy 1, get 2 free", discountTwo.getDiscountRule());
        assertEquals(6, discountTwo.getQuantity());
        assertEquals(4.00, discountTwo.getTotalItemPrice());
    }

    @Test
    public void day3_5_Test() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 0, 10, 0, 10, 0};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(1, result.size());

        Item discountOne = result.get(0);
        assertEquals("Bread (3, 4, or 5 days old)", discountOne.getItemName());
        assertEquals("Buy 1, get 1 free", discountOne.getDiscountRule());
        assertEquals(20, discountOne.getQuantity());
        assertEquals(10.00, discountOne.getTotalItemPrice());
    }

    @Test
    public void mixedBreadTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {3, 2, 1, 0, 0, 0, 1};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(4, result.size());

        Item dayZero = result.get(0);
        assertEquals("Bread (0 days old)", dayZero.getItemName());
        assertEquals("", dayZero.getDiscountRule());
        assertEquals(3, dayZero.getQuantity());
        assertEquals(3.00, dayZero.getTotalItemPrice());

        Item dayOne = result.get(1);
        assertEquals("Bread (1 day old)", dayOne.getItemName());
        assertEquals("", dayOne.getDiscountRule());
        assertEquals(2, dayOne.getQuantity());
        assertEquals(2.00, dayOne.getTotalItemPrice());

        Item dayTwo = result.get(2);
        assertEquals("Bread (2 days old)", dayTwo.getItemName());
        assertEquals("", dayTwo.getDiscountRule());
        assertEquals(1, dayTwo.getQuantity());
        assertEquals(1.00, dayTwo.getTotalItemPrice());

        Item daySix = result.get(3);
        assertEquals("Bread (6 days old)", daySix.getItemName());
        assertEquals("Buy 1, get 2 free", daySix.getDiscountRule());
        assertEquals(1, daySix.getQuantity());
        assertEquals(1.00, daySix.getTotalItemPrice());
    }

    @Test
    public void mixedBreadTest_2() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 1, 5, 0, 0, 4};
        double breadPrice = 3.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(3, result.size());

        Item item0 = result.get(0);
        assertEquals("Bread (2 days old)", item0.getItemName());
        assertEquals("", item0.getDiscountRule());
        assertEquals(1, item0.getQuantity());
        assertEquals(3.00, item0.getTotalItemPrice());

        Item dayOne = result.get(1);
        assertEquals("Bread (3, 4, or 5 days old)", dayOne.getItemName());
        assertEquals("Buy 1, get 1 free", dayOne.getDiscountRule());
        assertEquals(5, dayOne.getQuantity());
        assertEquals(9.00, dayOne.getTotalItemPrice());

        Item daySix = result.get(2);
        assertEquals("Bread (6 days old)", daySix.getItemName());
        assertEquals("Buy 1, get 2 free", daySix.getDiscountRule());
        assertEquals(4, daySix.getQuantity());
        assertEquals(6.00, daySix.getTotalItemPrice());
    }

    @Test
    public void mixedBreadTest_3() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {0, 0, 1, 4, 0, 0, 6};
        double breadPrice = 2.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(3, result.size());

        Item item0 = result.get(0);
        assertEquals("Bread (2 days old)", item0.getItemName());
        assertEquals("", item0.getDiscountRule());
        assertEquals(1, item0.getQuantity());
        assertEquals(2.00, item0.getTotalItemPrice());

        Item dayOne = result.get(1);
        assertEquals("Bread (3, 4, or 5 days old)", dayOne.getItemName());
        assertEquals("Buy 1, get 1 free", dayOne.getDiscountRule());
        assertEquals(4, dayOne.getQuantity());
        assertEquals(4.00, dayOne.getTotalItemPrice());

        Item daySix = result.get(2);
        assertEquals("Bread (6 days old)", daySix.getItemName());
        assertEquals("Buy 1, get 2 free", daySix.getDiscountRule());
        assertEquals(6, daySix.getQuantity());
        assertEquals(4.00, daySix.getTotalItemPrice());
    }

    @Test
    public void integerOverflowTest() {
        BreadEvaluator evaluator = new BreadEvaluator();
        int[] bread = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        double breadPrice = 1.00;
        ArrayList<Item> result = evaluator.evaluateBread(bread, breadPrice);

        assertEquals(5, result.size());

        Item item0 = result.get(0);
        assertEquals("Bread (0 days old)", item0.getItemName());
        assertEquals("", item0.getDiscountRule());
        assertEquals(Integer.MAX_VALUE, item0.getQuantity());
        assertEquals(Integer.MAX_VALUE, item0.getTotalItemPrice());

        Item item1 = result.get(1);
        assertEquals("Bread (1 day old)", item1.getItemName());
        assertEquals("", item1.getDiscountRule());
        assertEquals(Integer.MAX_VALUE, item1.getQuantity());
        assertEquals(Integer.MAX_VALUE, item1.getTotalItemPrice());

        Item item2 = result.get(2);
        assertEquals("Bread (2 days old)", item2.getItemName());
        assertEquals("", item2.getDiscountRule());
        assertEquals(Integer.MAX_VALUE, item2.getQuantity());
        assertEquals(Integer.MAX_VALUE, item2.getTotalItemPrice());

        // intentional integer overflow
        @SuppressWarnings("NumericOverflow") int x = Integer.MAX_VALUE + Integer.MAX_VALUE + Integer.MAX_VALUE;
        Item discountOne = result.get(3);
        assertEquals("Bread (3, 4, or 5 days old)", discountOne.getItemName());
        assertEquals("Buy 1, get 1 free", discountOne.getDiscountRule());
        assertEquals(x, discountOne.getQuantity());
        assertEquals(1.073741823E9, discountOne.getTotalItemPrice());
    }
}
