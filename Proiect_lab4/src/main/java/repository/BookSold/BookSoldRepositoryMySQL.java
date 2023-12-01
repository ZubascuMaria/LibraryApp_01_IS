package repository.BookSold;

import model.Book;
import model.BookSold;
import model.builder.BookBuilder;
import model.builder.BookSoldBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookSoldRepositoryMySQL implements BookSoldRepository{
    private final Connection connection;
    public BookSoldRepositoryMySQL(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<BookSold> findAll() {
        String sql = "SELECT * from book_solds";

        List<BookSold> bookSolds = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                bookSolds.add(getBookFromResultSet(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return bookSolds;
    }

    @Override
    public void add(BookSold bookSold) {
        String sql = "INSERT INTO book_solds VALUES(null, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,bookSold.getBookID());
            preparedStatement.setString(2,bookSold.getTitle());
            preparedStatement.setInt(3,bookSold.getQuantity());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private BookSold getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new BookSoldBuilder()
                .setId(resultSet.getLong("id"))
                .setBookID(resultSet.getLong("bookID"))
                .setTitle(resultSet.getString("title"))
                .setQuantity(resultSet.getInt("quantity"))
                .build();
    }
}
