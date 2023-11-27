package repository.audioBook;

import model.AudioBook;
import model.builder.AudioBookBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AudioBookRepositoryMySQL implements AudioBookRepository {
    private final Connection connection;

    public AudioBookRepositoryMySQL(Connection connection){
        this.connection = connection;
    }


    @Override
    public List<AudioBook> findAll() {
        String sql = "SELECT * FROM audioBook;";

        List<AudioBook> audioBooks = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                audioBooks.add(getAudioBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return audioBooks;
    }

    @Override
    public Optional<AudioBook> findById(Long id) {
        String sql = "SELECT * FROM audioBook where id= ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                AudioBook audioBook = getAudioBookFromResultSet(resultSet);
                return Optional.of(audioBook);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean save(AudioBook audioBook) {
        String sql = "INSERT INTO audioBook VALUES(null, ?, ?, ?, ?);";

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, audioBook.getAuthor());
            preparedStatement.setString(2, audioBook.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(audioBook.getPublishedDate()));
            preparedStatement.setInt(4,audioBook.getRunTime());

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1) ? false : true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM audioBook";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private AudioBook getAudioBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new AudioBookBuilder()
                .setId(resultSet.getLong("id"))
                .setAuthor(resultSet.getString("author"))
                .setTitle(resultSet.getString("title"))
                .setPublishedDate(new java.sql.Date((resultSet.getDate("publishedDate")).getTime()).toLocalDate())
                .setRunTime(resultSet.getInt("runTime"))
                .build();
    }
}
