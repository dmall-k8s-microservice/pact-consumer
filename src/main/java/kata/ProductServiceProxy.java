package kata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductServiceProxy {

    private RestTemplate restTemplate;
    private String url;

    @Autowired
    public ProductServiceProxy(@Value("${provider}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    public List<Product> getProducts() {
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>() {};
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }

    public List<Rating> getRatings() {
        ParameterizedTypeReference<List<Rating>> responseType = new ParameterizedTypeReference<List<Rating>>() {};
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }

    public List<Inventory> getInventories () {
        ParameterizedTypeReference<List<Inventory>> responseType = new ParameterizedTypeReference<List<Inventory>>() {};
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }
}
