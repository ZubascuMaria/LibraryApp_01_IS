package repository.eBook;

import model.EBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EBookRepositoryMock implements EBookRepository{

    private final List<EBook> eBooks;

    public EBookRepositoryMock()
    {
        eBooks = new ArrayList<>();
    }
    @Override
    public List<EBook> findAll() {
        return eBooks;
    }

    @Override
    public Optional<EBook> findById(Long id) {
        return eBooks.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean save(EBook audioBook) {
        return eBooks.add(audioBook);
    }

    @Override
    public void removeAll() {
        eBooks.clear();
    }
}
