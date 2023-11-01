package org.example;

import database.JDBConnectionWrapper;
import model.Book;
import model.builder.BookBuilder;
import model.book.BookRepository;
import model.book.BookRepositoryMySQL;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");



        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());

//        Book book = new BookBuilder()
//                .setAuthor("', '', null); SLEEP(20); --")
//                .setTitle("Fram Ursul Polar")
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .build();
//
//        bookRepository.save(book);
        bookRepository.removeAll();

       // System.out.println(bookRepository.findById(1L));


    }
}