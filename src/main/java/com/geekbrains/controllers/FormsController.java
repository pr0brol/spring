package com.geekbrains.controllers;

import javax.servlet.http.HttpServletRequest;

import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class FormsController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/show_simple_form")
    public String showSimpleForm() {
        return "simple_form";
    }

    @RequestMapping(path = "/simple_form_processing", method = RequestMethod.GET)
    public String simpleProcessForm() {
        return "simple_form_result";
    }

    // GET http://localhost:8189/app/find_form
    @GetMapping("/find_form")
    public String showSimpleForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "find_form";
    }

    // POST http://localhost:8189/app/find_process_form
    @PostMapping("/find_form_process")
    public String processForm(@ModelAttribute("product") Product product, Model model) {
        if(productService.existById(product.getId())){
            model.addAttribute("product", productService.findById(product.getId()));
            return "find_form_result";
        }
        else return "all_products";
    }
}
