package launcher;

import controller.LoginController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import repository.BookSold.BookSoldRepositoryMySQL;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.bookSold.BookSoldService;
import service.bookSold.BookSoldServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import view.LoginView;

import java.sql.Connection;

public class ComponentFactory {
    private final LoginView loginView;
    private final LoginController loginController;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private static ComponentFactory instance;
    private final BookSoldService bookSoldService;

    public static ComponentFactory getInstance(Boolean componentsForTests, Stage stage){
        if (instance == null){
            instance = new ComponentFactory(componentsForTests, stage);
        }

        return instance;
    }

    public ComponentFactory(Boolean componentsForTests, Stage stage){
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceImpl(userRepository, rightsRolesRepository);
        this.loginView = new LoginView(stage);
        this.bookRepository = new BookRepositoryMySQL(connection);
        this.bookService = new BookServiceImpl(bookRepository);
        this.bookSoldService = new BookSoldServiceImpl(new BookSoldRepositoryMySQL(connection));
        this.loginController = new LoginController(loginView, authenticationService,bookService,bookSoldService);
    }

    public AuthenticationService getAuthenticationService(){
        return authenticationService;
    }

    public UserRepository getUserRepository(){
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository(){
        return rightsRolesRepository;
    }

    public LoginView getLoginView(){
        return loginView;
    }

    public BookRepository getBookRepository(){
        return bookRepository;
    }

    public LoginController getLoginController(){
        return loginController;
    }

}