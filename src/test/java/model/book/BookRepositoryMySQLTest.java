package model.book;

import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class BookRepositoryMySQLTest {

    @Test
    void findAll() {
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");
        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());
        Book book = new BookBuilder()
                .setAuthor("Cineva")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        bookRepository.save(book);
        Book book1 = new BookBuilder()
                .setAuthor("Camil Petrescu")
                .setTitle("Otilia")
                .setPublishedDate(LocalDate.of(2000, 1, 7))
                .build();
        bookRepository.save(book1);
        List<Book> books=bookRepository.findAll();
        System.out.println(books);
    }

    @Test
    void findById() {
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");
        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());
        Optional<Book> book=bookRepository.findById(1L);
        System.out.println(book);
        Optional<Book> book1=bookRepository.findById(13L);
        System.out.println(book1);
    }

    @Test
    void save() {
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");
        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());
        Book book = new BookBuilder()
                .setAuthor("Ioan Slavici")
                .setTitle("Moara cu ghinion")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        bookRepository.save(book);
        Book book1 = new BookBuilder()
                .setAuthor("Mihai Eminescu")
                .setTitle("Luceafarul")
                .setPublishedDate(LocalDate.of(2002, 1, 7))
                .build();
        bookRepository.save(book1);
    }

    @Test
    void removeAll() {
        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");
        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());
        bookRepository.removeAll();
    }
}