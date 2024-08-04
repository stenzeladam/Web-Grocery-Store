package groceryStore.GroceryStore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
class BeerEvaluatorTest {

    private final double EPSILON = 1e-4;

    @Test
    void testBelgianBeerNoPacks() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(5, 0, 0);

        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getQuantity());
        assertEquals("Belgian Beer Bottle", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(0.75, result.get(0).getUnitPrice());
        assertEquals(3.75, result.get(0).getTotalItemPrice());
    }

    @Test
    void testBelgianBeerOnePack() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(6, 0, 0);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals("6-Pack of Belgian Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(3.00, result.get(0).getUnitPrice());
        assertEquals(3.00, result.get(0).getTotalItemPrice());
    }

    @Test
    void testBelgianBeerMultiplePacksAndBottles() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(13, 0, 0);

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals("6-Pack of Belgian Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(3.00, result.get(0).getUnitPrice());
        assertEquals(6.00, result.get(0).getTotalItemPrice());

        assertEquals(1, result.get(1).getQuantity());
        assertEquals("Belgian Beer Bottle", result.get(1).getItemName());
        assertEquals("", result.get(1).getDiscountRule());
        assertEquals(0.75, result.get(1).getUnitPrice());
        assertEquals(0.75, result.get(1).getTotalItemPrice());
    }

    @Test
    void testDutchBeerNoPacks() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 5, 0);

        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getQuantity());
        assertEquals("Dutch Beer Bottle", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(0.50, result.get(0).getUnitPrice());
        assertEquals(2.50, result.get(0).getTotalItemPrice());
    }

    @Test
    void testDutchBeerOnePack() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 6, 0);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals("6-Pack of Dutch Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(2.00, result.get(0).getUnitPrice());
        assertEquals(2.00, result.get(0).getTotalItemPrice());
    }

    @Test
    void testDutchBeerMultiplePacksAndBottles() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 14, 0);

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals("6-Pack of Dutch Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(2.00, result.get(0).getUnitPrice());
        assertEquals(4.00, result.get(0).getTotalItemPrice());

        assertEquals(2, result.get(1).getQuantity());
        assertEquals("Dutch Beer Bottle", result.get(1).getItemName());
        assertEquals("", result.get(1).getDiscountRule());
        assertEquals(0.50, result.get(1).getUnitPrice());
        assertEquals(1.00, result.get(1).getTotalItemPrice());
    }

    @Test
    void testGermanBeerNoPacks() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 0, 4);

        assertEquals(1, result.size());
        assertEquals(4, result.get(0).getQuantity());
        assertEquals("German Beer Bottle", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(1.00, result.get(0).getUnitPrice());
        assertEquals(4.00, result.get(0).getTotalItemPrice());
    }

    @Test
    void testGermanBeerOnePack() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 0, 6);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals("6-Pack of German Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(4.00, result.get(0).getUnitPrice());
        assertEquals(4.00, result.get(0).getTotalItemPrice());
    }

    @Test
    void testGermanBeerMultiplePacksAndBottles() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 0, 15);

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals("6-Pack of German Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(4.00, result.get(0).getUnitPrice());
        assertEquals(8.00, result.get(0).getTotalItemPrice());

        assertEquals(3, result.get(1).getQuantity());
        assertEquals("German Beer Bottle", result.get(1).getItemName());
        assertEquals("", result.get(1).getDiscountRule());
        assertEquals(1.00, result.get(1).getUnitPrice());
        assertEquals(3.00, result.get(1).getTotalItemPrice());
    }

    @Test
    void testAllBeersBottles() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(2, 3, 4);

        assertEquals(3, result.size());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals("Belgian Beer Bottle", result.get(0).getItemName());
        assertEquals("", result.get(0).getDiscountRule());
        assertEquals(0.75, result.get(0).getUnitPrice());
        assertEquals(1.50, result.get(0).getTotalItemPrice());

        assertEquals(3, result.get(1).getQuantity());
        assertEquals("Dutch Beer Bottle", result.get(1).getItemName());
        assertEquals("", result.get(1).getDiscountRule());
        assertEquals(0.50, result.get(1).getUnitPrice());
        assertEquals(1.50, result.get(1).getTotalItemPrice());

        assertEquals(4, result.get(2).getQuantity());
        assertEquals("German Beer Bottle", result.get(2).getItemName());
        assertEquals("", result.get(2).getDiscountRule());
        assertEquals(1.00, result.get(2).getUnitPrice());
        assertEquals(4.00, result.get(2).getTotalItemPrice());
    }

    @Test
    void testAllBeersPacks() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(6, 12, 18);

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getQuantity());
        assertEquals("6-Pack of Belgian Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(3.00, result.get(0).getUnitPrice());
        assertEquals(3.00, result.get(0).getTotalItemPrice());

        assertEquals(2, result.get(1).getQuantity());
        assertEquals("6-Pack of Dutch Beer", result.get(1).getItemName());
        assertEquals("6-Pack of Beer", result.get(1).getDiscountRule());
        assertEquals(2.00, result.get(1).getUnitPrice());
        assertEquals(4.00, result.get(1).getTotalItemPrice());

        assertEquals(3, result.get(2).getQuantity());
        assertEquals("6-Pack of German Beer", result.get(2).getItemName());
        assertEquals("6-Pack of Beer", result.get(2).getDiscountRule());
        assertEquals(4.00, result.get(2).getUnitPrice());
        assertEquals(12.00, result.get(2).getTotalItemPrice());
    }

    @Test
    void testAllBeersBottlesAndPacksMixed() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(13, 15, 23);

        assertEquals(6, result.size());
        assertEquals(2, result.get(0).getQuantity());
        assertEquals("6-Pack of Belgian Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(3.00, result.get(0).getUnitPrice());
        assertEquals(6.00, result.get(0).getTotalItemPrice());

        assertEquals(1, result.get(1).getQuantity());
        assertEquals("Belgian Beer Bottle", result.get(1).getItemName());
        assertEquals("", result.get(1).getDiscountRule());
        assertEquals(0.75, result.get(1).getUnitPrice());
        assertEquals(0.75, result.get(1).getTotalItemPrice());

        assertEquals(2, result.get(2).getQuantity());
        assertEquals("6-Pack of Dutch Beer", result.get(2).getItemName());
        assertEquals("6-Pack of Beer", result.get(2).getDiscountRule());
        assertEquals(2.00, result.get(2).getUnitPrice());
        assertEquals(4.00, result.get(2).getTotalItemPrice());

        assertEquals(3, result.get(3).getQuantity());
        assertEquals("Dutch Beer Bottle", result.get(3).getItemName());
        assertEquals("", result.get(3).getDiscountRule());
        assertEquals(0.50, result.get(3).getUnitPrice());
        assertEquals(1.50, result.get(3).getTotalItemPrice());

        assertEquals(3, result.get(4).getQuantity());
        assertEquals("6-Pack of German Beer", result.get(4).getItemName());
        assertEquals("6-Pack of Beer", result.get(4).getDiscountRule());
        assertEquals(4.00, result.get(4).getUnitPrice());
        assertEquals(12.00, result.get(4).getTotalItemPrice());

        assertEquals(5, result.get(5).getQuantity());
        assertEquals("German Beer Bottle", result.get(5).getItemName());
        assertEquals("", result.get(5).getDiscountRule());
        assertEquals(1.00, result.get(5).getUnitPrice());
        assertEquals(5.00, result.get(5).getTotalItemPrice());
    }

    @Test
    void testZeroBeers() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(0, 0, 0);
        assertEquals(0, result.size());
    }

    @Test
    void testNegativeBeerQuantities() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(-1, -1, -1);

        assertEquals(0, result.size());
    }

    @Test
    void testLargeBeerQuantities() {
        BeerEvaluator evaluator = new BeerEvaluator();
        ArrayList<Item> result = evaluator.evaluateBeers(10000, 10000, 10000);
        assertFalse(result.isEmpty());
        assertEquals(6, result.size());

        assertEquals(1666, result.get(0).getQuantity());
        assertEquals("6-Pack of Belgian Beer", result.get(0).getItemName());
        assertEquals("6-Pack of Beer", result.get(0).getDiscountRule());
        assertEquals(3.00, result.get(0).getUnitPrice());
        assertEquals(4998.00, result.get(0).getTotalItemPrice());

        assertEquals(4, result.get(1).getQuantity());
        assertEquals("Belgian Beer Bottle", result.get(1).getItemName());
        assertEquals("", result.get(1).getDiscountRule());
        assertEquals(0.75, result.get(1).getUnitPrice());
        assertEquals(3.00, result.get(1).getTotalItemPrice());

        assertEquals(1666, result.get(2).getQuantity());
        assertEquals("6-Pack of Dutch Beer", result.get(2).getItemName());
        assertEquals("6-Pack of Beer", result.get(2).getDiscountRule());
        assertEquals(2.00, result.get(2).getUnitPrice());
        assertEquals(3332.00, result.get(2).getTotalItemPrice());

        assertEquals(4, result.get(3).getQuantity());
        assertEquals("Dutch Beer Bottle", result.get(3).getItemName());
        assertEquals("", result.get(3).getDiscountRule());
        assertEquals(0.50, result.get(3).getUnitPrice());
        assertEquals(2.00, result.get(3).getTotalItemPrice());

        assertEquals(1666, result.get(4).getQuantity());
        assertEquals("6-Pack of German Beer", result.get(4).getItemName());
        assertEquals("6-Pack of Beer", result.get(4).getDiscountRule());
        assertEquals(4.00, result.get(4).getUnitPrice());
        assertEquals(6664.00, result.get(4).getTotalItemPrice());

        assertEquals(4, result.get(5).getQuantity());
        assertEquals("German Beer Bottle", result.get(5).getItemName());
        assertEquals("", result.get(5).getDiscountRule());
        assertEquals(1.00, result.get(5).getUnitPrice());
        assertEquals(4.00, result.get(5).getTotalItemPrice());
    }

    @Test
    void testCorrectDiscountPercentage() {
        BeerEvaluator percentTest = new BeerEvaluator();
        double result = percentTest.applyPercentOffDiscount(5.0, 1.00);
        assertEquals(0.95, result, EPSILON);
    }

    @Test
    void testDiscountGreaterThan100Percent() {
        BeerEvaluator evaluator = new BeerEvaluator();
        double result = evaluator.applyPercentOffDiscount(150.0, 50.00);

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testFullDiscountExactly100Percent() {
        BeerEvaluator evaluator = new BeerEvaluator();
        double result = evaluator.applyPercentOffDiscount(100.0, 100.00);

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testZeroPercentDiscount() {
        BeerEvaluator percentTest = new BeerEvaluator();
        double result = percentTest.applyPercentOffDiscount(0.0, 50.00);
        assertEquals(50.00, result, EPSILON);
    }

    @Test
    void testFullDiscount() {
        BeerEvaluator percentTest = new BeerEvaluator();
        double result = percentTest.applyPercentOffDiscount(100.0, 100.00);
        assertEquals(0.00, result, EPSILON);
    }

    @Test
    void testNegativeDiscount() {
        BeerEvaluator percentTest = new BeerEvaluator();
        double result = percentTest.applyPercentOffDiscount(-10.0, 50.00);
        assertEquals(55.00, result, EPSILON);
    }

    @Test
    void testLargeDiscount() {
        BeerEvaluator percentTest = new BeerEvaluator();
        double result = percentTest.applyPercentOffDiscount(150.0, 200.00);
        assertEquals(0.00, result, EPSILON); // Should not go below 0
    }

    @Test
    void testSmallItemPrice() {
        BeerEvaluator percentTest = new BeerEvaluator();
        double result = percentTest.applyPercentOffDiscount(10.0, 0.01);
        assertEquals(0.009, result, EPSILON);
    }
}
