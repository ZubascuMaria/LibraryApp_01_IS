package database;

public class SQLTriggerCreationFactory {
    public String getCreateTriggers()
    {
        return "CREATE TRIGGER after_book_insert " +
                "AFTER INSERT ON book " +
                "FOR EACH ROW " +
                "BEGIN " +
                "    INSERT INTO deposit (bid, stock, price) " +
                "    VALUES (NEW.id, 0, 0); " +
                "END;";
    }
}
