package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import service.book.BookService;

import java.util.List;

public class CustomerView {
    private Button viewBooks;
    private Button buyBook;
    private Button buy;
    private Stage stage;
    private HBox hBox;
    private final BookService bookService;

    private TableView tableView;
    private ListView listView;
    private Label buySuccesful;

    public CustomerView(Stage primaryStage, BookService bookService)
    {
        this.bookService = bookService;
        this.stage = primaryStage;
        primaryStage.setTitle("Customer");
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);


        primaryStage.show();
    }

    private void initializeFields(GridPane gridPane) {
        viewBooks = new Button("View Books");
        HBox signInButtonHBox = new HBox(10);
        signInButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonHBox.getChildren().add(viewBooks);
        gridPane.add(signInButtonHBox, 1, 4);

        buyBook = new Button("Buy a book");
        HBox logInButtonHBox = new HBox(10);
        logInButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        logInButtonHBox.getChildren().add(buyBook);
        gridPane.add(logInButtonHBox, 0, 4);

        getTable(gridPane);
        chooseBook(gridPane);
    }

    private void getTable(GridPane gridPane)
    {
        List<Book> listBooks = bookService.findAll();
        ObservableList<Book> books = FXCollections.observableArrayList(listBooks);
        tableView = new TableView<>();
        tableView.setItems(books);

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> publishedDateCol = new TableColumn<>("Published Date");
        publishedDateCol.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().setAll(authorCol, titleCol, publishedDateCol, priceCol);

        tableView.setVisible(false);
        gridPane.add(tableView,1,2);

    }

    public void showTable()
    {
        this.tableView.setVisible(true);
    }

    public void unshowTable()
    {
        this.tableView.setVisible(false);
    }

    private void chooseBook(GridPane gridPane)
    {
        List<Book> listBooks = bookService.findAll();
        ObservableList<Book> books = FXCollections.observableArrayList(listBooks);
        listView = new ListView<Book>(books);
        listView.setPrefWidth(500);
        hBox = new HBox(listView);
        //hBox.setPrefWidth(100);
        gridPane.add(hBox,1,2);
        hBox.setVisible(false);
        buy = new Button("Buy");
        gridPane.add(buy, 0, 1);
        buy.setVisible(false);
        buySuccesful = new Label("Buy with Succes!");
        buySuccesful.setVisible(false);
        gridPane.add(buySuccesful, 0, 1);
    }

    public void showChooseBook()
    {
        buy.setVisible(true);
        hBox.setVisible(true);
        buyBook.setVisible(false);
        viewBooks.setVisible(false);
    }
    public void buySucces()
    {
        buySuccesful.setVisible(true);
    }
    public void unshowBuy()
    {
        buy.setVisible(false);
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Hello dear customer!");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL,20));
        gridPane.add(sceneTitle,0,0,2,1);
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));


    }

    public Book getBookfromListView()
    {
        return (Book) listView.getSelectionModel().getSelectedItem();
    }
    public void addViewButtonListener(EventHandler<ActionEvent> viewButtonListener) {
        viewBooks.setOnAction(viewButtonListener);
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener) {
        buyBook.setOnAction(buyButtonListener);
    }

    public void buyButtonListener(EventHandler<ActionEvent>buyListener)
    {
        buy.setOnAction(buyListener);
    }

    public void decrementBookStock(Book book)
    {
        if(book.getStock() != 0)
        {bookService.updateStock(book.getStock()-1, book.getId());}
        else {
            buySuccesful.setText("Out of Stock :(");
            buySuccesful.setTextFill(Color.color(1, 0, 0));}

    }

}
