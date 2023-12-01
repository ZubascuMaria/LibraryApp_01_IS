package model.builder;

import model.BookSold;

public class BookSoldBuilder {
    private BookSold bookSold;
    public BookSoldBuilder(){
        bookSold = new BookSold();
    }
    public BookSoldBuilder setId(Long id){
        bookSold.setId(id);
        return this;
    }
    public BookSoldBuilder setBookID(Long bookID)
    {
        bookSold.setBookID(bookID);
        return this;
    }
    public BookSoldBuilder setTitle(String title){
        bookSold.setTitle(title);
        return this;
    }
    public BookSoldBuilder setQuantity(int quantity){
        bookSold.setQuantity(quantity);
        return this;
    }
    public BookSold build(){
        return this.bookSold;
    }
}
