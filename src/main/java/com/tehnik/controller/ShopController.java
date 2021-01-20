package com.tehnik.controller;

import com.tehnik.model.Product;
import com.tehnik.service.ProductService;
import com.tehnik.validator.ProductValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class ShopController {

    private final ProductService productService;

    private final ProductValidator validator;

    public ShopController(ProductService productService, ProductValidator validator) {
        this.productService = productService;
        this.validator = validator;
    }


    @GetMapping("")
    public String show(@RequestParam(required = false) String message, @RequestParam(required = false) String item, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("Hello", "Hello Shop");
        model.addAttribute("str", message);
        model.addAttribute("products", productService.getAll());

        return "show";
    }


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("getProduct", productService.getByID(id));
        Product byID = productService.getByID(id);
        System.out.println(byID);
        return "product";
    }

    @GetMapping("/product/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("default", "Default value");
        return "addProduct";
    }


    @PostMapping("/product/done")
    public String postAdd(@ModelAttribute @Valid Product product, BindingResult result) throws SQLException, ClassNotFoundException {
        validator.validate(product, result);
        if (result.hasErrors()) {
            System.out.println("Error");
            return "addProduct";
        }
        //System.out.println(product);
        productService.save(product);
        return "redirect:/";
    }

}
