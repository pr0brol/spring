package market.utils;

import market.Product;
import market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class Cart {
    private ProductService productService;
    private List<Product> cartProducts;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void add(Long id){
        for (Product prod: productService.getAllProducts()){
            if(prod.getId() == id){
                cartProducts.add(prod);
            }
        }
    }

}
