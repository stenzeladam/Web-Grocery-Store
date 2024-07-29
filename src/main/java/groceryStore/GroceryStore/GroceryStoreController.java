package groceryStore.GroceryStore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroceryStoreController {

    @GetMapping("/test")
    public String test() {
        return "Test message";
    }

    @PostMapping("/send_order")
    public Order createOrder(@RequestBody Order newOrder) {
        System.out.println(newOrder);
        return newOrder;
    }
}
