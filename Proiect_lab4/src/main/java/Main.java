import com.mysql.cj.jdbc.ConnectionWrapper;
import database.JDBConnectionWrapper;
import model.*;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.BookSoldBuilder;
import model.builder.UserBuilder;
import model.validator.Notification;
import model.validator.UserValidator;
import repository.BookSold.BookSoldRepository;
import repository.BookSold.BookSoldRepositoryMySQL;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import java.time.LocalDate;
import java.util.Collections;

import static database.Constants.Roles.CUSTOMER;

public class Main {
    public static void main(String[] args){

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("library");


        BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connectionWrapper.getConnection()),
                new Cache<Book>());

       /* Book book = new BookBuilder()
                .setAuthor("Oana Donis")
                .setTitle("Cine stie")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .setStock(33)
                .setPrice(12)
                .build();

        bookRepository.save(book);

        Book book1 = new BookBuilder()
                .setAuthor("Ion Creanga")
                .setTitle("Amintiri din copilarie")
                .setPublishedDate(LocalDate.of(1900, 11, 15))
                .setStock(33)
                .setPrice(12)
                .build();

        bookRepository.save(book1);

        Book book2 = new BookBuilder()
                .setAuthor("Raluca Feher")
                .setTitle("Sa nu razi :(")
                .setPublishedDate(LocalDate.of(2019, 1, 18))
                .setStock(33)
                .setPrice(12)
                .build();

        bookRepository.save(book2);

        Book book3 = new BookBuilder()
                .setAuthor("Prince Harry")
                .setTitle("Spare")
                .setPublishedDate(LocalDate.of(2023, 1, 10))
                .setStock(33)
                .setPrice(12)
                .build();

        bookRepository.save(book3);

        */

        BookRepository bookRepository1 = new BookRepositoryMySQL(connectionWrapper.getConnection());
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());

        UserRepository userRepository = new UserRepositoryMySQL(connectionWrapper.getConnection(),rightsRolesRepository);
        //userRepository.updateUserEmployee(1L);
        userRepository.deleteUser(2L);




    }
}