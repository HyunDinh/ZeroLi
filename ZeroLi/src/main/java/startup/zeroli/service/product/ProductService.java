/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.product;

import startup.zeroli.model.Canvas;

import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public interface ProductService {
    
    ArrayList<Canvas> getAllProducts();
    
    Canvas getUserById(int id);


    
}
