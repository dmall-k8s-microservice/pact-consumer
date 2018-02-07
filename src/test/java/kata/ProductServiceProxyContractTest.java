package kata;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class ProductServiceProxyContractTest {
    @Rule
    public PactProviderRuleMk2 rule = new PactProviderRuleMk2("inventory_service", "localhost", 8080, PactSpecVersion.V3, this);

    @Pact(provider="inventory_service", consumer="consumer_app")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
                .given("The products in Review service are ready")
                .uponReceiving("A request for products")
                .path("/inventories")
                .method("GET")
                .willRespondWith()
                .headers(responseHeaders())
                .status(200)
                .body(new PactDslJsonArray().arrayEachLike()
                        .stringMatcher("inventoryId", "^p[0-9][0-9][0-9]$", "p001")
                        .stringMatcher("name", ".+", "iphone 手机")
                        .stringMatcher("amount", "\\d+", "100")
                )
                .toPact();
    }


    @NotNull
    private Map<String, String> responseHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        return headers;
    }

    @Test
    @PactVerification("inventory_service")
    public void should_get_a_list_of_inventories () {
        ProductServiceProxy productServiceProxy = new ProductServiceProxy("http://localhost:8080/inventories");
        final List<Product> products = productServiceProxy.getProducts();
        assertThat(products.get(0).getName(), instanceOf(String.class));
    }
}
