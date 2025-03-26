
package startup.zeroli.service.user;

import startup.zeroli.model.User;

/**
 *
 * @author LENOVO
 */
public interface UserService {
    User getUserByEmail(String email);
    User getUserById(Long id);
}
