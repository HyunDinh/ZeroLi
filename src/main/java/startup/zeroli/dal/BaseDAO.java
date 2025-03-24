package startup.zeroli.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import startup.zeroli.model.User;

public abstract class BaseDAO {

    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("zeroli");

    public EntityManager getEntityManager() {
        EntityManager em = emf.createEntityManager();
        System.out.println(emf.getMetamodel().getEntities());
        return em;
    }

    public void close() {
        emf.close();
    }
    
    public static void saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public static List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return users;
    }

    public static void insertSampleData() {
        try {
            saveUser(new User(null, "Hyundinh", "@@@@@", "admin001@gmail.com",
                    new SimpleDateFormat("DD-MM-YYYY").parse("01-11-2005"), "admin", true));
            saveUser(new User(null, "Shinju", "@@@@@", "cllient001@gmail.com",
                    new SimpleDateFormat("DD-MM-YYYY").parse("22-11-2005"), "client", true));
        } catch (ParseException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        insertSampleData();
        getAllUsers().forEach(u -> System.out.println(u.getUsername() + " - " + u.getEmail()));
    }
}
