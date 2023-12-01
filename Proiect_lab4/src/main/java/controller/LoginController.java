package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Role;
import model.User;
import model.validator.Notification;
import model.validator.UserValidator;
import repository.book.BookRepository;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.bookSold.BookSoldService;
import service.user.AuthenticationService;
import view.AdministratorView;
import view.CustomerView;
import view.EmployeeView;
import view.LoginView;

import java.util.EventListener;
import java.util.List;

import static database.Constants.Roles.*;

public class LoginController {

    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final BookService bookService;
    private final BookSoldService bookSoldService;


    public LoginController(LoginView loginView, AuthenticationService authenticationService, BookService bookService, BookSoldService bookSoldService) {
        this.loginView = loginView;
        this.bookSoldService = bookSoldService;
        this.authenticationService = authenticationService;
        this.bookService = bookService;
        this.loginView.addLoginButtonListener(new LoginButtonListener());
        this.loginView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()){
                loginView.setActionTargetText(loginNotification.getFormattedErrors());
            }else{
                loginView.setActionTargetText("LogIn Successfull!");
                User user = loginNotification.getResult();
                List<Role> roles = user.getRoles();
                String role = roles.get(0).getRole();
                System.out.println(role);
                switch (role){
                    case ADMINISTRATOR : AdministratorController administratorController = new AdministratorController(new AdministratorView(loginView.getStage(),authenticationService));
                                    break;
                    case EMPLOYEE : EmployeeController employeeController = new EmployeeController(new EmployeeView(loginView.getStage(),bookService));
                                    break;
                    case CUSTOMER : CustomerController controller = new CustomerController(new CustomerView(loginView.getStage(),bookService), bookSoldService);
                                    break;
                }
            }

        }
    }

    private class RegisterButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                loginView.setActionTargetText(registerNotification.getFormattedErrors());
            } else {
                loginView.setActionTargetText("Register successful!");
            }
        }
    }
}
