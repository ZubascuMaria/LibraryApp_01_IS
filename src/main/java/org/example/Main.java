package org.example;

import database.JDBConnectionWrapper;
import model.AudioBook;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.EBookBuilder;
import repository.audioBook.AudioBookRepository;
import repository.audioBook.AudioBookRepositoryMySQL;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.eBook.EBookRepository;
import repository.eBook.EBookRepositoryMySQL;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");

        EBookRepository eBookRepository = new EBookRepositoryMySQL(connectionWrapper.getConnection());

//        Book book = new BookBuilder()
//                .setAuthor("', '', null); SLEEP(20); --")
//                .setTitle("Fram Ursul Polar")
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .build();
//
//        bookRepository.save(book);


       // System.out.println(bookRepository.findById(1L));
//        AudioBook audioBook = new AudioBookBuilder()
//                .setAuthor("Maria Petrescu")
//                .setTitle("Sa nu plangi")
//                .setPublishedDate(LocalDate.of(2010, 6, 2))
//                .setRunTime(67)
//                .build();
//        audioBookRepository.save(audioBook);
//        System.out.println(audioBookRepository.findById(1L));
//
        EBook eBook = new EBookBuilder()
                .setAuthor("Ana Maria")
                .setTitle("Zbori")
                .setPublishedDate(LocalDate.of(2003,6,3))
                .setFormat("pdf")
                .build();
        eBookRepository.save(eBook);
        System.out.println(eBookRepository.findById(1L));




    }
}