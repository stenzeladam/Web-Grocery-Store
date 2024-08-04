package groceryStore.GroceryStore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SuppressWarnings("unused")
@WebMvcTest(GroceryStoreController.class)
class GroceryStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(new int[]{1, 0, 0, 3, 3, 4, 4}, 0, 0, 6, 7);
    }

    @Test
    void createOrder() throws Exception {
        mockMvc.perform(post("/send_order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0].itemName", is("Bread (0 days old)")))
                .andExpect(jsonPath("$[0].quantity", is(1.0)))
                .andExpect(jsonPath("$[0].discountRule", is("")))
                .andExpect(jsonPath("$[0].totalItemPrice", is(1.0)))
                .andExpect(jsonPath("$[0].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[0].formattedTotalItemPrice", is("1.00")))
                .andExpect(jsonPath("$[0].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[1].itemName", is("Bread (3, 4, or 5 days old)")))
                .andExpect(jsonPath("$[1].quantity", is(10.0)))
                .andExpect(jsonPath("$[1].discountRule", is("Buy 1, get 1 free")))
                .andExpect(jsonPath("$[1].totalItemPrice", is(5.0)))
                .andExpect(jsonPath("$[1].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[1].formattedTotalItemPrice", is("5.00")))
                .andExpect(jsonPath("$[1].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[2].itemName", is("Bread (6 days old)")))
                .andExpect(jsonPath("$[2].quantity", is(4.0)))
                .andExpect(jsonPath("$[2].discountRule", is("Buy 1, get 2 free")))
                .andExpect(jsonPath("$[2].totalItemPrice", is(2.0)))
                .andExpect(jsonPath("$[2].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[2].formattedTotalItemPrice", is("2.00")))
                .andExpect(jsonPath("$[2].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[3].itemName", is("6-Pack of Dutch Beer")))
                .andExpect(jsonPath("$[3].quantity", is(1.0)))
                .andExpect(jsonPath("$[3].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[3].totalItemPrice", is(2.0)))
                .andExpect(jsonPath("$[3].unitPrice", is(2.0)))
                .andExpect(jsonPath("$[3].formattedTotalItemPrice", is("2.00")))
                .andExpect(jsonPath("$[3].formattedUnitPrice", is("2.00")))

                .andExpect(jsonPath("$[4].itemName", is("6-Pack of German Beer")))
                .andExpect(jsonPath("$[4].quantity", is(1.0)))
                .andExpect(jsonPath("$[4].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[4].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[4].unitPrice", is(4.0)))
                .andExpect(jsonPath("$[4].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[4].formattedUnitPrice", is("4.00")))

                .andExpect(jsonPath("$[5].itemName", is("German Beer Bottle")))
                .andExpect(jsonPath("$[5].quantity", is(1.0)))
                .andExpect(jsonPath("$[5].discountRule", is("")))
                .andExpect(jsonPath("$[5].totalItemPrice", is(1.0)))
                .andExpect(jsonPath("$[5].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[5].formattedTotalItemPrice", is("1.00")))
                .andExpect(jsonPath("$[5].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[6].itemName", is("Subtotal")))
                .andExpect(jsonPath("$[6].quantity", is(-1.0)))
                .andExpect(jsonPath("$[6].discountRule", is("")))
                .andExpect(jsonPath("$[6].totalItemPrice", is(15.0)))
                .andExpect(jsonPath("$[6].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[6].formattedTotalItemPrice", is("15.00")))
                .andExpect(jsonPath("$[6].formattedUnitPrice", is("1.00")));
    }

    @Test
    void createOrder_2() throws Exception {
        Order newOrder = new Order(new int[]{0, 0, 0, 0, 0, 0, 0}, 0, 6, 12, 28);

        mockMvc.perform(post("/send_order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOrder)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].itemName", is("6-Pack of Belgian Beer")))
                .andExpect(jsonPath("$[0].quantity", is(1.0)))
                .andExpect(jsonPath("$[0].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[0].totalItemPrice", is(3.0)))
                .andExpect(jsonPath("$[0].unitPrice", is(3.0)))
                .andExpect(jsonPath("$[0].formattedTotalItemPrice", is("3.00")))
                .andExpect(jsonPath("$[0].formattedUnitPrice", is("3.00")))

                .andExpect(jsonPath("$[1].itemName", is("6-Pack of Dutch Beer")))
                .andExpect(jsonPath("$[1].quantity", is(2.0)))
                .andExpect(jsonPath("$[1].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[1].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[1].unitPrice", is(2.0)))
                .andExpect(jsonPath("$[1].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[1].formattedUnitPrice", is("2.00")))

                .andExpect(jsonPath("$[2].itemName", is("6-Pack of German Beer")))
                .andExpect(jsonPath("$[2].quantity", is(4.0)))
                .andExpect(jsonPath("$[2].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[2].totalItemPrice", is(16.0)))
                .andExpect(jsonPath("$[2].unitPrice", is(4.0)))
                .andExpect(jsonPath("$[2].formattedTotalItemPrice", is("16.00")))
                .andExpect(jsonPath("$[2].formattedUnitPrice", is("4.00")))

                .andExpect(jsonPath("$[3].itemName", is("German Beer Bottle")))
                .andExpect(jsonPath("$[3].quantity", is(4.0)))
                .andExpect(jsonPath("$[3].discountRule", is("")))
                .andExpect(jsonPath("$[3].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[3].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[3].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[3].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[4].itemName", is("Subtotal")))
                .andExpect(jsonPath("$[4].quantity", is(-1.0)))
                .andExpect(jsonPath("$[4].discountRule", is("")))
                .andExpect(jsonPath("$[4].totalItemPrice", is(27.0)))
                .andExpect(jsonPath("$[4].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[4].formattedTotalItemPrice", is("27.00")))
                .andExpect(jsonPath("$[4].formattedUnitPrice", is("1.00")));
    }

    @Test
    void createOrder_3() throws Exception {
        Order newOrder = new Order(new int[]{1, 0, 0, 0, 12, 0, 12}, 1234.46, 6, 12, 28);

        mockMvc.perform(post("/send_order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOrder)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[0].itemName", is("Bread (0 days old)")))
                .andExpect(jsonPath("$[0].quantity", is(1.0)))
                .andExpect(jsonPath("$[0].discountRule", is("")))
                .andExpect(jsonPath("$[0].totalItemPrice", is(1.0)))
                .andExpect(jsonPath("$[0].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[0].formattedTotalItemPrice", is("1.00")))
                .andExpect(jsonPath("$[0].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[1].itemName", is("Bread (3, 4, or 5 days old)")))
                .andExpect(jsonPath("$[1].quantity", is(12.0)))
                .andExpect(jsonPath("$[1].discountRule", is("Buy 1, get 1 free")))
                .andExpect(jsonPath("$[1].totalItemPrice", is(6.0)))
                .andExpect(jsonPath("$[1].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[1].formattedTotalItemPrice", is("6.00")))
                .andExpect(jsonPath("$[1].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[2].itemName", is("Bread (6 days old)")))
                .andExpect(jsonPath("$[2].quantity", is(12.0)))
                .andExpect(jsonPath("$[2].discountRule", is("Buy 1, get 2 free")))
                .andExpect(jsonPath("$[2].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[2].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[2].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[2].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[3].itemName", is("Vegetables")))
                .andExpect(jsonPath("$[3].quantity", is(1234.46)))
                .andExpect(jsonPath("$[3].discountRule", is("Over 500.0 grams: 10% Off")))
                .andExpect(jsonPath("$[3].totalItemPrice", is(11.11)))
                .andExpect(jsonPath("$[3].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[3].formattedTotalItemPrice", is("11.11")))
                .andExpect(jsonPath("$[3].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[4].itemName", is("6-Pack of Belgian Beer")))
                .andExpect(jsonPath("$[4].quantity", is(1.0)))
                .andExpect(jsonPath("$[4].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[4].totalItemPrice", is(3.0)))
                .andExpect(jsonPath("$[4].unitPrice", is(3.0)))
                .andExpect(jsonPath("$[4].formattedTotalItemPrice", is("3.00")))
                .andExpect(jsonPath("$[4].formattedUnitPrice", is("3.00")))

                .andExpect(jsonPath("$[5].itemName", is("6-Pack of Dutch Beer")))
                .andExpect(jsonPath("$[5].quantity", is(2.0)))
                .andExpect(jsonPath("$[5].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[5].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[5].unitPrice", is(2.0)))
                .andExpect(jsonPath("$[5].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[5].formattedUnitPrice", is("2.00")))

                .andExpect(jsonPath("$[6].itemName", is("6-Pack of German Beer")))
                .andExpect(jsonPath("$[6].quantity", is(4.0)))
                .andExpect(jsonPath("$[6].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[6].totalItemPrice", is(16.0)))
                .andExpect(jsonPath("$[6].unitPrice", is(4.0)))
                .andExpect(jsonPath("$[6].formattedTotalItemPrice", is("16.00")))
                .andExpect(jsonPath("$[6].formattedUnitPrice", is("4.00")))

                .andExpect(jsonPath("$[7].itemName", is("German Beer Bottle")))
                .andExpect(jsonPath("$[7].quantity", is(4.0)))
                .andExpect(jsonPath("$[7].discountRule", is("")))
                .andExpect(jsonPath("$[7].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[7].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[7].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[7].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[8].itemName", is("Subtotal")))
                .andExpect(jsonPath("$[8].quantity", is(-1.0)))
                .andExpect(jsonPath("$[8].discountRule", is("")))
                .andExpect(jsonPath("$[8].totalItemPrice", is(49.11)))
                .andExpect(jsonPath("$[8].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[8].formattedTotalItemPrice", is("49.11")))
                .andExpect(jsonPath("$[8].formattedUnitPrice", is("1.00")));
    }

    @Test
    void createOrder_4() throws Exception {
        Order newOrder = new Order(new int[]{1, 2, 3, 4, 5, 6, 7}, 8910.11, 12, 13, 14);

        mockMvc.perform(post("/send_order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOrder)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(12)))
                .andExpect(jsonPath("$[0].itemName", is("Bread (0 days old)")))
                .andExpect(jsonPath("$[0].quantity", is(1.0)))
                .andExpect(jsonPath("$[0].discountRule", is("")))
                .andExpect(jsonPath("$[0].totalItemPrice", is(1.0)))
                .andExpect(jsonPath("$[0].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[0].formattedTotalItemPrice", is("1.00")))
                .andExpect(jsonPath("$[0].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[1].itemName", is("Bread (1 day old)")))
                .andExpect(jsonPath("$[1].quantity", is(2.0)))
                .andExpect(jsonPath("$[1].discountRule", is("")))
                .andExpect(jsonPath("$[1].totalItemPrice", is(2.0)))
                .andExpect(jsonPath("$[1].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[1].formattedTotalItemPrice", is("2.00")))
                .andExpect(jsonPath("$[1].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[2].itemName", is("Bread (2 days old)")))
                .andExpect(jsonPath("$[2].quantity", is(3.0)))
                .andExpect(jsonPath("$[2].discountRule", is("")))
                .andExpect(jsonPath("$[2].totalItemPrice", is(3.0)))
                .andExpect(jsonPath("$[2].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[2].formattedTotalItemPrice", is("3.00")))
                .andExpect(jsonPath("$[2].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[3].itemName", is("Bread (3, 4, or 5 days old)")))
                .andExpect(jsonPath("$[3].quantity", is(15.0)))
                .andExpect(jsonPath("$[3].discountRule", is("Buy 1, get 1 free")))
                .andExpect(jsonPath("$[3].totalItemPrice", is(8.0)))
                .andExpect(jsonPath("$[3].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[3].formattedTotalItemPrice", is("8.00")))
                .andExpect(jsonPath("$[3].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[4].itemName", is("Bread (6 days old)")))
                .andExpect(jsonPath("$[4].quantity", is(7.0)))
                .andExpect(jsonPath("$[4].discountRule", is("Buy 1, get 2 free")))
                .andExpect(jsonPath("$[4].totalItemPrice", is(3.0)))
                .andExpect(jsonPath("$[4].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[4].formattedTotalItemPrice", is("3.00")))
                .andExpect(jsonPath("$[4].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[5].itemName", is("Vegetables")))
                .andExpect(jsonPath("$[5].quantity", is(8910.11)))
                .andExpect(jsonPath("$[5].discountRule", is("Over 500.0 grams: 10% Off")))
                .andExpect(jsonPath("$[5].totalItemPrice", is(80.19)))
                .andExpect(jsonPath("$[5].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[5].formattedTotalItemPrice", is("80.19")))
                .andExpect(jsonPath("$[5].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[6].itemName", is("6-Pack of Belgian Beer")))
                .andExpect(jsonPath("$[6].quantity", is(2.0)))
                .andExpect(jsonPath("$[6].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[6].totalItemPrice", is(6.0)))
                .andExpect(jsonPath("$[6].unitPrice", is(3.0)))
                .andExpect(jsonPath("$[6].formattedTotalItemPrice", is("6.00")))
                .andExpect(jsonPath("$[6].formattedUnitPrice", is("3.00")))

                .andExpect(jsonPath("$[7].itemName", is("6-Pack of Dutch Beer")))
                .andExpect(jsonPath("$[7].quantity", is(2.0)))
                .andExpect(jsonPath("$[7].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[7].totalItemPrice", is(4.0)))
                .andExpect(jsonPath("$[7].unitPrice", is(2.0)))
                .andExpect(jsonPath("$[7].formattedTotalItemPrice", is("4.00")))
                .andExpect(jsonPath("$[7].formattedUnitPrice", is("2.00")))

                .andExpect(jsonPath("$[8].itemName", is("Dutch Beer Bottle")))
                .andExpect(jsonPath("$[8].quantity", is(1.0)))
                .andExpect(jsonPath("$[8].discountRule", is("")))
                .andExpect(jsonPath("$[8].totalItemPrice", is(0.5)))
                .andExpect(jsonPath("$[8].unitPrice", is(0.5)))
                .andExpect(jsonPath("$[8].formattedTotalItemPrice", is("0.50")))
                .andExpect(jsonPath("$[8].formattedUnitPrice", is("0.50")))

                .andExpect(jsonPath("$[9].itemName", is("6-Pack of German Beer")))
                .andExpect(jsonPath("$[9].quantity", is(2.0)))
                .andExpect(jsonPath("$[9].discountRule", is("6-Pack of Beer")))
                .andExpect(jsonPath("$[9].totalItemPrice", is(8.0)))
                .andExpect(jsonPath("$[9].unitPrice", is(4.0)))
                .andExpect(jsonPath("$[9].formattedTotalItemPrice", is("8.00")))
                .andExpect(jsonPath("$[9].formattedUnitPrice", is("4.00")))

                .andExpect(jsonPath("$[10].itemName", is("German Beer Bottle")))
                .andExpect(jsonPath("$[10].quantity", is(2.0)))
                .andExpect(jsonPath("$[10].discountRule", is("")))
                .andExpect(jsonPath("$[10].totalItemPrice", is(2.0)))
                .andExpect(jsonPath("$[10].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[10].formattedTotalItemPrice", is("2.00")))
                .andExpect(jsonPath("$[10].formattedUnitPrice", is("1.00")))

                .andExpect(jsonPath("$[11].itemName", is("Subtotal")))
                .andExpect(jsonPath("$[11].quantity", is(-1.0)))
                .andExpect(jsonPath("$[11].discountRule", is("")))
                .andExpect(jsonPath("$[11].totalItemPrice", is(117.69)))
                .andExpect(jsonPath("$[11].unitPrice", is(1.0)))
                .andExpect(jsonPath("$[11].formattedTotalItemPrice", is("117.69")))
                .andExpect(jsonPath("$[11].formattedUnitPrice", is("1.00")));
    }
}
