package groceryStore.GroceryStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class VegetableEvaluatorTest {
    private final double EPSILON = 1e-4;
    @Test
    void testCorrectDiscountPercentage() {
        VegetableEvaluator percentTest = new VegetableEvaluator();
        double result = percentTest.applyPercentOffDiscount(5.0, 1.00);
        assertEquals(0.95, result, EPSILON);
    }

    @Test
    void testZeroPercentDiscount() {
        VegetableEvaluator percentTest = new VegetableEvaluator();
        double result = percentTest.applyPercentOffDiscount(0.0, 50.00);
        assertEquals(50.00, result, EPSILON);
    }

    @Test
    void testFullDiscount() {
        VegetableEvaluator percentTest = new VegetableEvaluator();
        double result = percentTest.applyPercentOffDiscount(100.0, 100.00);
        assertEquals(0.00, result, EPSILON);
    }

    @Test
    void testNegativeDiscount() {
        VegetableEvaluator percentTest = new VegetableEvaluator();
        double result = percentTest.applyPercentOffDiscount(-10.0, 50.00);
        assertEquals(55.00, result, EPSILON);
    }

    @Test
    void testLargeDiscount() {
        VegetableEvaluator percentTest = new VegetableEvaluator();
        double result = percentTest.applyPercentOffDiscount(150.0, 200.00);
        assertEquals(0.00, result, EPSILON); // Should not go below 0
    }

    @Test
    void testSmallItemPrice() {
        VegetableEvaluator percentTest = new VegetableEvaluator();
        double result = percentTest.applyPercentOffDiscount(10.0, 0.01);
        assertEquals(0.009, result, EPSILON);
    }

    @Test
    void testDiscountForLessThan100Grams() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(50.0, 2.00);
        assertEquals(50.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 0.0 and 100.0 grams: 5.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(0.95, result.getTotalItemPrice());
    }

    @Test
    void testDiscountFor100To499Grams() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(250.0, 2.00);
        assertEquals(250.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 100.0 and 500.0 grams: 7.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(4.65, result.getTotalItemPrice());
    }

    @Test
    void testDiscountFor500OrMoreGrams() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(600.0, 2.00);
        assertEquals(600.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Over 500.0 grams: 10.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(10.80, result.getTotalItemPrice());
    }

    @Test
    void testNegativeQuantity() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(-10.0, 2.00);
        assertEquals(-10.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(0.00, result.getTotalItemPrice());
    }

    @Test
    void testZeroQuantity() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(0.0, 2.00);
        assertEquals(0.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 0.0 and 100.0 grams: 5.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(0.00, result.getTotalItemPrice());
    }

    @Test
    void testExtremePriceValue() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(100.0, 1000000.00);
        assertEquals(100.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 100.0 and 500.0 grams: 7.0% Off", result.getDiscountRule());
        assertEquals(1000000.00, result.getUnitPrice());
        assertEquals(930000.00, result.getTotalItemPrice());
    }

    @Test
    void testBoundaryConditionFor100Grams() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(100.0, 2.00);
        assertEquals(100.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 100.0 and 500.0 grams: 7.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(1.86, result.getTotalItemPrice());
    }

    @Test
    void testBoundaryConditionFor500Grams() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(500.0, 2.00);
        assertEquals(500.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Over 500.0 grams: 10.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(9.00, result.getTotalItemPrice());
    }

    @Test
    void testVeryLargeQuantity() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(1000000.0, 2.00);
        assertEquals(1000000.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Over 500.0 grams: 10.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(18000.00, result.getTotalItemPrice());
    }

    @Test
    void testNonIntegerQuantity() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(99.5, 2.00);
        assertEquals(99.5, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 0.0 and 100.0 grams: 5.0% Off", result.getDiscountRule());
        assertEquals(2.00, result.getUnitPrice());
        assertEquals(1.89, result.getTotalItemPrice());
    }

    @Test
    void testWonkyVegPrice() {
        VegetableEvaluator evaluator = new VegetableEvaluator();
        Item result = evaluator.evaluateVeg(150.0, 3.1415);
        assertEquals(150.0, result.getQuantity());
        assertEquals("Vegetables", result.getItemName());
        assertEquals("Between 100.0 and 500.0 grams: 7.0% Off", result.getDiscountRule());
        assertEquals(3.14, result.getUnitPrice());
        assertEquals(4.38, result.getTotalItemPrice());
    }
}
