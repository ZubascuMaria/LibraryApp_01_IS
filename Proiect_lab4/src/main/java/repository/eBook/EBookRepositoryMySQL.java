package repository.eBook;

import model.EBook;
import model.builder.EBookBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EBookRepositoryMySQL implements EBookRepository{
    private final Connection connection;

    public EBookRepositoryMySQL(Connection connection){
        this.connection = connection;
    }


    @Override
    public List<EBook> findAll() {
        String sql = "SELECT * FROM eBook;";

        List<EBook> eBooks = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                eBooks.add(getEBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return eBooks;
    }

    @Override
    public Optional<EBook> findById(Long id) {
        String sql = "SELECT * FROM eBook where id= ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                EBook eBook = getEBookFromResultSet(resultSet);
                return Optional.of(eBook);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean save(EBook audioBook) {
        String sql = "INSERT INTO eBook VALUES(null, ?, ?, ?, ?);";

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, audioBook.getAuthor());
            preparedStatement.setString(2, audioBook.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(audioBook.getPublishedDate()));
            preparedStatement.setString(4,audioBook.getFormat());

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1) ? false : true;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM eBook";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private EBook getEBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new EBookBuilder()
                .setId(resultSet.getLong("id"))
                .setAuthor(resultSet.getString("author"))
                .setTitle(resultSet.getString("title"))
                .setPublishedDate(new java.sql.Date((resultSet.getDate("publishedDate")).getTime()).toLocalDate())
                .setFormat(resultSet.getString("format"))
                .build();
    }
}
