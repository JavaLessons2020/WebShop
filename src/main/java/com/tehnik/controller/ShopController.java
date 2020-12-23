package com.tehnik.controller;

import com.tehnik.dao.ProductDAO;
import com.tehnik.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class ShopController {

    public final ProductDAO productDAO;

    public ShopController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @GetMapping("")
    public String show(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("Hello", "Hello Shop");
        model.addAttribute("products", productDAO.getAllProducts());
        return "show";
    }


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id")Long id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("getProduct", productDAO.getProductById(id));
        return "product";
    }

    @GetMapping("/product/new")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }


    @PostMapping("/product/done")
    public String postAdd(@ModelAttribute Product product) throws SQLException, ClassNotFoundException {
        productDAO.save(product);
        return "redirect:/";
    }
}
