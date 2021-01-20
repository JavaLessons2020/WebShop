package com.tehnik.validator;

import com.tehnik.dao.jdbc.ProductDAO;
import com.tehnik.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ProductValidator implements Validator {

    private final ProductDAO productDAO;

    public ProductValidator(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product)target;
        try {
//            if(productDAO.getProductByName(product.getName()) != null){
//                errors.rejectValue("name", "", "Product with a takin name already exists");
//            }
//            if(product.getName().isEmpty()){
//                errors.rejectValue("name", "", "This field is empty");
//            }
//            if(product.getDescription().length()<5 || product.getDescription().length() > 20){
//                errors.rejectValue("description", "", "Field must be between 5 and 20 characters");
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
