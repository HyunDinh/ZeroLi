/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import startup.zeroli.common.ProductStatus;
import startup.zeroli.model.Product;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author LENOVO
 */
public class ProductServiceImpl implements ProductService {

    private final int option = 0;// 0 : txt file | 1 : JPA

    private static final String filePath = "C:\\Users\\Admin\\Downloads\\ZeroLi\\ZeroLi\\src\\main\\java\\startup\\zeroli\\service\\product\\product";

//    private static Map<Integer, Product> productMap = new HashMap<>();

    public ProductServiceImpl() {
    }

//    @Override
//    public Map<Integer, Product> getAllProducts() {
//        switch (option) {
//            case 0 -> {
//                loadData();
//                return productMap;
//            }
//            case 1 -> {
//                // JPA
//            }
//            default ->
//                throw new AssertionError();
//        }
//        return null;
//    }
    @Override
    public Map<Integer, Product> getAllProducts() {
        Map<Integer, Product> productMap = new HashMap<>();
        Product product = new Product();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                productMap.put(Integer.parseInt(parts[0]), new Product(Integer.parseInt(parts[0]),
                        parts[1], parts[2],
                        BigDecimal.valueOf(Double.parseDouble(parts[3])),
                        Integer.parseInt(parts[4]),
                        product.setProductStatusFromString(parts[5]),
                        parts[6],
                        parts[7])
                );
            }
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
        return productMap;
    }

    @Override
    public Product getProductById(int id) {
        Map<Integer, Product> productMap = getAllProducts();
        return productMap.get(id);
    }

    @Override
    public Map<Integer, Product> searchProductsByName(String name) {
        Map<Integer, Product> productMap = getAllProducts();
        Map<Integer, Product> listProductByName = new HashMap<>();
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            Integer id = entry.getKey();
            Product p = entry.getValue();
            if (p.getProductName().toLowerCase().contains(name.toLowerCase())) {
                listProductByName.put(id, p);
            }
        }
        return listProductByName;
    }
//    @Override
//    public Map<Integer, Product> searchProductByName(String name, BigDecimal minPrice, BigDecimal maxPrice) {
//        productMap = getAllProducts();
//        Map<Integer, Product> listProductByName = new HashMap<>();
//
//        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
//            Integer id = entry.getKey();
//            Product p = entry.getValue();
//            boolean matchesName = true;
//            boolean matchesPrice = true;
//
//            if (name != null && !name.trim().isEmpty()) {
//                matchesName = p.getProductName().toLowerCase().contains(name.toLowerCase());
//            }
//
//            if (matchesName) {
//                if (minPrice != null) {
//                    matchesPrice = p.getPrice().compareTo(minPrice) >= 0;
//                }
//                if (maxPrice != null) {
//                    matchesPrice = matchesPrice && p.getPrice().compareTo(maxPrice) <= 0;
//                }
//            } else if (minPrice != null || maxPrice != null) {
//                matchesPrice = true;
//                if (minPrice != null) {
//                    matchesPrice = p.getPrice().compareTo(minPrice) >= 0;
//                }
//                if (maxPrice != null) {
//                    matchesPrice = matchesPrice && p.getPrice().compareTo(maxPrice) <= 0;
//                }
//            }
//
//            if (matchesName || matchesPrice) {
//                listProductByName.put(id, p);
//            }
//        }
//        return listProductByName;
//    }

    public static void main(String[] args) {
        ProductServiceImpl impl = new ProductServiceImpl();
//            Object key = entry.getKey();
//            Object val = entry.getValue();
//            System.out.println(key + " " + val);
//        }
//        System.out.println("Id 12: " + impl.getProductById(12));
        Map<Integer, Product> list = impl.searchProductsByName("le");
        for (Map.Entry<Integer, Product> entry : list.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(val);
        }
    }

}
