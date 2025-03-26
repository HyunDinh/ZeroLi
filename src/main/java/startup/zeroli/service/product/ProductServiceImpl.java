package startup.zeroli.service.product;

import startup.zeroli.dal.GenericDAO;
import startup.zeroli.model.Product;
import startup.zeroli.utils.ErrDialog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final GenericDAO<Product> productDAO;

    public ProductServiceImpl() {
        this.productDAO = new GenericDAO<>(Product.class);
    }

    @Override
    public Map<Integer, Product> getAllProducts() {
        Map<Integer, Product> productMap = new HashMap<>();
        try {
            List<Product> productList = productDAO.findAll();
            productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, p -> p));
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
        return productMap;
    }

    @Override
    public Product getProductById(int id) {
        try {
            return productDAO.findById(id);
        } catch (Exception e) {
            ErrDialog.showError("Error At Function : getProductByID - " + e.getMessage());
            return null;
        }
    }

    @Override
    public Map<Integer, Product> searchProductsByName(String name) {
        return getAllProducts().entrySet().stream()
                .filter(entry -> entry.getValue().getProductName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void saveProduct(Product product) {
        try {
            productDAO.save(product);
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        try {
            productDAO.update(product);
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
    }

    public void deleteProduct(int id) {
        try {
            productDAO.delete(id);
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductServiceImpl impl = new ProductServiceImpl();
        Map<Integer, Product> list = impl.searchProductsByName("le");
        list.values().forEach(System.out::println);
    }
}
