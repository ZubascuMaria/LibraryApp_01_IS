package service.bookSold;

import model.BookSold;

import java.util.List;

public interface BookSoldService {
    public List<BookSold> findAll();
    public void add(BookSold bookSold);
}
