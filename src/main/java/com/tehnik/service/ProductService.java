package com.tehnik.service;


import com.tehnik.dao.ProductDAO;
import com.tehnik.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getByID(Long id) throws SQLException, ClassNotFoundException {
        return productDAO.getProductById(id);
    }

    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : productDAO.getAllProducts()) {
            if (!product.getName().isEmpty()) {
                products.add(product);
            }
        }
        return products;
    }


    public void save(Product product) throws SQLException, ClassNotFoundException {
        if (!product.getName().isEmpty() && product.getPrice() > 0) {
            productDAO.save(product);
        }
    }


}
