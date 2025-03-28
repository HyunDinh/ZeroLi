package startup.zeroli.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import startup.zeroli.common.ProductStatus;
import startup.zeroli.model.Product;
import startup.zeroli.model.User;
import startup.zeroli.utils.ErrDialog;

public abstract class BaseDAO {

    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("zeroli");

    private static final String product_filePath = "D:\\code\\University\\ZeroLi\\src\\main\\java\\startup\\zeroli\\dal\\res\\product";

    public EntityManager getEntityManager() {
        EntityManager em = emf.createEntityManager();
        System.out.println(emf.getMetamodel().getEntities());
        return em;
    }

    public void close() {
        emf.close();
    }

    // save entities
    public static void saveRecords(Object obj) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
    }

    //

    public static void loadProductsIntoDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader(product_filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                Product product = new Product(
                        parts[1], // productName
                        parts[2], // brandName
                        new BigDecimal(parts[3]), // price
                        Integer.parseInt(parts[4]), // stock
                        ProductStatus.valueOf(parts[5].toUpperCase()), // productStatus
                        parts[6], // description
                        parts[7] // imageURL
                );
                saveRecords(product); // Lưu vào database
            }
        } catch (Exception e) {
            ErrDialog.showError(e.getMessage());
        }
    }

    public static void insertUsersIntoDatabase() {
        try {
            saveRecords(new User(null, "Hyundinh", "@@@@@", "admin001@gmail.com",
                    new SimpleDateFormat("DD-MM-YYYY").parse("01-11-2005"), "admin", true));
            saveRecords(new User(null, "Shinju", "@@@@@", "cllient001@gmail.com",
                    new SimpleDateFormat("DD-MM-YYYY").parse("22-11-2005"), "client", true));
        } catch (ParseException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        loadProductsIntoDatabase();
    }

}
