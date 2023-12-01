package service.bookSold;

import model.BookSold;
import repository.BookSold.BookSoldRepository;

import java.util.List;

public class BookSoldServiceImpl implements BookSoldService{
    private final BookSoldRepository bookSoldRepository;

    public BookSoldServiceImpl(BookSoldRepository bookSoldRepository) {
        this.bookSoldRepository = bookSoldRepository;
    }

    @Override
    public List<BookSold> findAll() {
        return bookSoldRepository.findAll();
    }

    @Override
    public void add(BookSold bookSold) {
        bookSoldRepository.add(bookSold);
    }
}
