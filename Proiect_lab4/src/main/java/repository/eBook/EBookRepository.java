package repository.eBook;

import model.EBook;

import java.util.List;
import java.util.Optional;

public interface EBookRepository {
    List<EBook> findAll();

    Optional<EBook> findById(Long id);

    boolean save(EBook audioBook);

    void removeAll();
}
