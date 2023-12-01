package repository.BookSold;

import model.BookSold;
import java.util.List;

public interface BookSoldRepository  {
    List<BookSold> findAll();
    public void add(BookSold bookSold);
}
