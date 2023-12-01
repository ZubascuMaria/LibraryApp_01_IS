package model;

import java.util.List;

public class BookSold {
    private Long id;
    private Long BookID;
    private String title;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookID() {
        return BookID;
    }

    public void setBookID(Long bookID) {
        BookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
