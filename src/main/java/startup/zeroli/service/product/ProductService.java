/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.product;

import java.math.BigDecimal;
import startup.zeroli.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface ProductService {

//    ArrayList<Product> getAllProducts();
    Map<Integer, Product> getAllProducts();

    Product getProductById(int id);

//    Map<Integer, Product> searchProductsByName(String name, BigDecimal minPrice, BigDecimal maxPrice);
    Map<Integer, Product> searchProductsByName(String name);

}
