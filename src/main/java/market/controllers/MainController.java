package market.controllers;

import com.geekbrains.bootdata.entities.Product;
import com.geekbrains.bootdata.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private ProductsService productService;

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "all_products";
    }

    @PostMapping("/find_form_processing")
    public String pagingExample(Model model,
                                @RequestParam(required = false, name = "min_price") Integer minPrice,
                                @RequestParam(required = false, name = "max_price") Integer maxPrice,
                                @RequestParam(required = false, name = "word") String word
    ) {

        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(productService.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(productService.priceLesserThanOrEq(maxPrice));
        }
        if (word != null) {
            spec = spec.and(productService.titleContains(word));
        }
        Page<Product> page = productService.findByParam(spec, 0, 5, "price");
        model.addAttribute("products", page.getContent());
        model.addAttribute("prodCounts", page.getTotalElements());
        return "find_products";
    }
}

