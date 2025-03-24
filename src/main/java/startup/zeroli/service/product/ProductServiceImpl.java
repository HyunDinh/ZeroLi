/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package startup.zeroli.service.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import startup.zeroli.model.Product;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author LENOVO
 */
public class ProductServiceImpl implements ProductService {

    private static final String filePath = "D:\\code\\University\\ZeroLi\\src\\main\\java\\startup\\zeroli\\service\\product\\product";

    public ProductServiceImpl() {
    }

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


    public static void main(String[] args) {
        ProductServiceImpl impl = new ProductServiceImpl();
        Map<Integer, Product> list = impl.searchProductsByName("le");
        for (Map.Entry<Integer, Product> entry : list.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(val);
        }
    }

}
