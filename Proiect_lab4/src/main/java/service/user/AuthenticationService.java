package service.user;

import model.User;
import model.validator.Notification;

import java.util.List;

public interface AuthenticationService {
    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    boolean logout(User user);
    void deleteUser(Long id);
    void updateUserEmployee(Long id);
    List<User> findAllEmployees();
    List<User>findAllCustomers();
}