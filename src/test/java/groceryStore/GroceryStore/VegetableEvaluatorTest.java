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
        assertEquals(55.00, result, EPSILON); // Should add 10% to the original price
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
}
