package repository.audioBook;

import model.AudioBook;
import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AudioBookRepositoryMock implements AudioBookRepository {

    private final List<AudioBook> audioBooks;

    public AudioBookRepositoryMock(){

        audioBooks = new ArrayList<>();
    }

    @Override
    public List<AudioBook> findAll() {
        return audioBooks;
    }

    @Override
    public Optional<AudioBook> findById(Long id) {
        return audioBooks.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean save(AudioBook audioBook) {
        return audioBooks.add(audioBook);
    }

    @Override
    public void removeAll() {
        audioBooks.clear();
    }
}
