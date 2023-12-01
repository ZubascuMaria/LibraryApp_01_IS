package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.Book;

import model.BookSold;
import model.builder.BookSoldBuilder;
import service.bookSold.BookSoldService;
import view.CustomerView;


public class CustomerController {
    private final CustomerView customerView;
    public final BookSoldService bookSoldService;

    public CustomerController(CustomerView customerView, BookSoldService bookSoldService)
    {
        this.customerView=customerView;
        this.bookSoldService = bookSoldService;
        this.customerView.addViewButtonListener(new viewBooksListener());
        this.customerView.addBuyButtonListener(new buyBooksListener());
        this.customerView.buyButtonListener(new buyButtonListener());
    }

    private class viewBooksListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            customerView.showTable();
        }
    }

    private class buyBooksListener implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            customerView.showChooseBook();
        }
    }

    private class buyButtonListener implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            Book book = customerView.getBookfromListView();
            customerView.decrementBookStock(book);
            customerView.buySucces();
            customerView.unshowBuy();
            BookSold bookSold = new BookSoldBuilder()
                            .setBookID(book.getId())
                            .setTitle(book.getTitle())
                            .setQuantity(1)
                            .build();
            bookSoldService.add(bookSold);
        }
    }

}
