package repository.audioBook;

import model.AudioBook;

import java.util.List;
import java.util.Optional;

public interface AudioBookRepository {
    List<AudioBook> findAll();

    Optional<AudioBook> findById(Long id);

    boolean save(AudioBook audioBook);

    void removeAll();
}
