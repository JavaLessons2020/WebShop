package com.tehnik.dao;


import com.tehnik.model.Product;
import com.tehnik.util.ConnectionJDBC;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {

    private static final String GETBYID = "select * from product where id=?";
    private static final String GETALL = "select * from product";
    private static final String INCERT = "insert into product(`name`,`description`, `price`) value (?,?,?);";


    public Product getProductById(Long id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = ConnectionJDBC.getConnection(GETBYID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = new Product();
        while (resultSet.next()) {
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getInt("price"));
            System.out.println(product);
        }
        return product;
    }

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = ConnectionJDBC.getConnection(GETALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getInt("price"));
            products.add(product);
            System.out.println(product);
        }
        return products;
    }

    public void save(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = ConnectionJDBC.getConnection(INCERT);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setInt(3, product.getPrice());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
