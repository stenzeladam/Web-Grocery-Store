package groceryStore.GroceryStore;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GroceryStoreController {

    // Ideally, handling price information would be set in a database with user authentication to restrict who
    // can change prices, and prices would always be accessible from the database so that running an active instance of
    // a Price object wouldn't be required before doing anything with an Order. But that is outside the requirements for
    // this assignment

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/set_Prices")
    public static Prices setPrices(@RequestBody Prices newPrices) {
        Prices.setInstance(newPrices.getBREAD_PRICE(), newPrices.getVEGETABLES_PRICE(),
                    newPrices.getBELGIAN_BEERS_PRICE(), newPrices.getDUTCH_BEERS_PRICE(),
                    newPrices.getGERMAN_BEERS_PRICE());
        return Prices.getInstance();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/send_order")
    public ArrayList<Item> createOrder(@RequestBody Order order) {
        // Creating a newOrder object instead of using the order object from the input to ensure data integrity of the
        // input order. The input data should not change, and should be separate from the return data.

        Order newOrder = new Order(order.getBread(), order.getVegetables(), order.getBelgianBeers(),
                order.getDutchBeers(), order.getGermanBeers());

        // If the number of potential products was to grow significantly, I'd use a vector or array list to list order
        // quantities in a single data structure instead of passing each item's quantities. Keeping it simple for now.

        // determine if discounts apply, and automatically apply any discounts that do apply. Will return a list of
        // objects with all the necessary information for a receipt.
        return newOrder.evaluateOrder();
    }
}
