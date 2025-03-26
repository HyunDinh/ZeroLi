package startup.zeroli.service.user;

import jakarta.persistence.EntityManager;
import startup.zeroli.dal.GenericDAO;
import startup.zeroli.model.User;

/**
 *
 * @author LENOVO
 */
public class UserServiceImpl implements UserService {

    private EntityManager userDAO;

    public UserServiceImpl() {
        userDAO = new GenericDAO<>(User.class).getEntityManager();
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return userDAO.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public User getUserById(Long id) {
        try {
            return userDAO.find(User.class, id);
        } catch (Exception e) {
            return null;
        }
    }
}