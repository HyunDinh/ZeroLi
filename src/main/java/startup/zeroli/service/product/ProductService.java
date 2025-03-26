/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.product;

import startup.zeroli.model.Product;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface ProductService {

    Map<Integer, Product> getAllProducts();
    Product getProductById(int id);
    Map<Integer, Product> searchProductsByName(String name);

}
