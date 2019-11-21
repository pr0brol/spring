package market.repository;

import market.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<Product>();
        products.add(new Product(1L, "test", new BigDecimal(0.0)));
    }

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(products);
    }

    public void insert(Product product){
        products.add(product);
    }

}
