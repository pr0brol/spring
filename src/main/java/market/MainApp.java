package market;

import market.config.MarketConfig;
import market.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MarketConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        List<Product> products = productService.getAllProducts();
        System.out.println(products);

    }
}
