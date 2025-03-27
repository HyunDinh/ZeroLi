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
public class ProductServiceImpl implements ProductService {

    private final int option = 0;
    // 0 : txt file
    // 1 : JPA
    
    @Override
    public ArrayList<Canvas> getAllProducts() {
        switch (option) {
            case 0 -> {
                // TODO :
            }
            case 1 -> {
                // JPA
            }
            default ->
                throw new AssertionError();
        }
        return new ArrayList<>();
    }

    @Override
    public Canvas getUserById(int id) {
        switch (option) {
            case 0 -> {
                
            }
            case 1 -> {
                // JPA
            }
            default ->
                throw new AssertionError();
        }
        return null;
    }
    
    

}
