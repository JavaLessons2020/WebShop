package com.tehnik.controller;

import com.tehnik.model.Product;
import com.tehnik.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class ShopController {

    private final ProductService productService;

    public ShopController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    public String show(@RequestParam(required = false) String message,@RequestParam(required = false) String item, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("Hello", "Hello Shop");
        model.addAttribute("str", message);
        model.addAttribute("products", productService.getAll());
        return "show";
    }


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("getProduct", productService.getByID(id));
        return "product";
    }

    @GetMapping("/product/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("default", "Default value");
        return "addProduct";
    }


    @PostMapping("/product/done")
    public String postAdd(@ModelAttribute Product product) throws SQLException, ClassNotFoundException {
        productService.save(product);
        return "redirect:/";
    }

}
