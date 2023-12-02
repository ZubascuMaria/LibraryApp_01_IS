package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.BookSold;
import model.builder.BookBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.w3c.dom.Document;
import service.book.BookService;
import service.bookSold.BookSoldService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class EmployeeView {
    private Button createBookButton;
    private Button createButton;
    private Button deleteBookButton;
    private Button updateBookButton;
    private Button viewBookSoldsButton;
    private TextField authorTextField;
    private TextField titleTextField,publishedDateField, stockField, priceField,updateField;
    private Label authorLabel, titleLabel, publishedDateLabel, stockLabel, priceLabel,error, deleteSuccesful;
    private ListView listView, listViewUpdate;
    private Button delete;
    private HBox hBox;
    private Button update;
    private HBox updateHBox;

    private final BookService bookService;

    private final BookSoldService bookSoldService;
    Stage stage;

    public EmployeeView(Stage primaryStage, BookService bookService, BookSoldService bookSoldService) {
        this.bookSoldService = bookSoldService;
        this.bookService = bookService;
        primaryStage.setTitle("Book Store");
        this.stage = primaryStage;

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        chooseBook(gridPane);

        update(gridPane);

        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane){
        Text sceneTitle = new Text("Welcome!");
        sceneTitle.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle,0,0, 2,1);
    }

    private void initializeFields(GridPane gridPane){

        createBookButton = new Button("Create Book");
        HBox ButtonHBox = new HBox(10);
        ButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        ButtonHBox.getChildren().add(createBookButton);

        updateBookButton = new Button("Update Book");
        ButtonHBox.getChildren().add(updateBookButton);

        deleteBookButton = new Button("Delete Book");
        ButtonHBox.getChildren().add(deleteBookButton);

        viewBookSoldsButton = new Button("Generate Report");
        ButtonHBox.getChildren().add(viewBookSoldsButton);
        gridPane.add(ButtonHBox, 1, 1);


        authorLabel = new Label("Author: ");
        //gridPane.add(authorLabel, 1, 2);
        authorLabel.setVisible(false);
        authorTextField = new TextField();
        //gridPane.add(authorTextField, 2, 2);
        authorTextField.setVisible(false);
        HBox authorHBOX = new HBox(10);
        authorHBOX.getChildren().add(authorLabel);
        authorHBOX.getChildren().add(authorTextField);

        titleLabel = new Label("Title: ");
        //gridPane.add(titleLabel, 1, 3);
        titleLabel.setVisible(false);
        titleTextField = new TextField();
        //gridPane.add(titleTextField, 2, 3);
        titleTextField.setVisible(false);
        HBox titleHBOX = new HBox(10);
        titleHBOX.getChildren().add(titleLabel);
        titleHBOX.getChildren().add(titleTextField);

        publishedDateLabel = new Label("Published Date: ");
        //gridPane.add(publishedDateLabel, 1, 4);
        publishedDateLabel.setVisible(false);
        publishedDateField = new TextField();
        //gridPane.add(publishedDateField, 2, 4);
        publishedDateField.setVisible(false);
        HBox publHBOX = new HBox(10);
        publHBOX.getChildren().add(publishedDateLabel);
        publHBOX.getChildren().add(publishedDateField);

        stockLabel = new Label("Stock: ");
        //gridPane.add(stockLabel, 1, 5);
        stockLabel.setVisible(false);
        stockField = new TextField();
        //gridPane.add(stockField, 2, 5);
        stockField.setVisible(false);
        HBox stockHBOX = new HBox(10);
        stockHBOX.getChildren().add(stockLabel);
        stockHBOX.getChildren().add(stockField);

        priceLabel = new Label("Price: ");
        //gridPane.add(priceLabel, 1, 6);
        priceLabel.setVisible(false);
        priceField = new TextField();
        //gridPane.add(priceField, 2, 6);
        priceField.setVisible(false);
        HBox priceHBOX = new HBox(10);
        priceHBOX.getChildren().add(priceLabel);
        priceHBOX.getChildren().add(priceField);

        createButton = new Button("Create");
        HBox createButtonHBox = new HBox(10);
        createButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createButtonHBox.getChildren().add(createButton);
        gridPane.add(createButtonHBox, 1, 7);
        createButton.setVisible(false);

        error = new Label("");
        error.setTextFill(Color.color(1, 0, 0));
        gridPane.add(error, 2, 7);
        error.setVisible(false);

        VBox vBoxCreate = new VBox(10);
        vBoxCreate.setAlignment(Pos.BOTTOM_LEFT);
        vBoxCreate.getChildren().addAll(authorHBOX,titleHBOX,publHBOX,stockHBOX,priceHBOX);
        gridPane.add(vBoxCreate,1,3);

    }

    public void getBook()
    {
        try {
            String author = authorTextField.getText();
            String title = titleTextField.getText();
            int stock = Integer.parseInt(stockField.getText());
            int price = Integer.parseInt(priceField.getText());
            LocalDate publishedDate = LocalDate.parse(publishedDateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Book book = new BookBuilder()
                    .setAuthor(author)
                    .setTitle(title)
                    .setPublishedDate(publishedDate)
                    .setStock(stock)
                    .setPrice(price)
                    .build();
            bookService.save(book);
        }catch (Exception e)
        {
            error.setText("Wrong Data!");
            error.setVisible(true);
        }

    }
    public String getTitle()
    {
        return titleTextField.getText();
    }

    public void showForCreateBook(Boolean k)
    {

        createButton.setVisible(k);
        priceField.setVisible(k);
        priceLabel.setVisible(k);
        stockField.setVisible(k);
        stockLabel.setVisible(k);
        publishedDateField.setVisible(k);
        publishedDateLabel.setVisible(k);
        titleTextField.setVisible(k);
        titleLabel.setVisible(k);
        authorTextField.setVisible(k);
        authorLabel.setVisible(k);
    }

    private void chooseBook(GridPane gridPane)
    {
        List<Book> listBooks = bookService.findAll();
        ObservableList<Book> books = FXCollections.observableArrayList(listBooks);
        listView = new ListView<Book>(books);
        listView.setPrefWidth(500);
        hBox = new HBox(listView);
        gridPane.add(hBox,1,3);
        hBox.setVisible(false);
        delete = new Button("Delete");
        gridPane.add(delete, 2, 5);
        delete.setVisible(false);
        deleteSuccesful = new Label("Delete with Succes!");
        deleteSuccesful.setVisible(false);
        gridPane.add(deleteSuccesful, 1, 5);
    }
    public void showChooseBook(Boolean k)
    {
        delete.setVisible(k);
        hBox.setVisible(k);
        deleteSuccesful.setVisible(false);
    }
    public void deleteSucces()
    {
        deleteSuccesful.setVisible(true);
    }
    public void unshowDelete()
    {
        delete.setVisible(false);
    }


    private void update(GridPane gridPane)
    {
        List<Book> listBooks = bookService.findAll();
        ObservableList<Book> books = FXCollections.observableArrayList(listBooks);
        listViewUpdate = new ListView<Book>(books);
        listViewUpdate.setPrefWidth(500);
        updateHBox = new HBox(listViewUpdate);
        gridPane.add(updateHBox,1,3);
        updateHBox.setVisible(false);

        update= new Button("Update");
        gridPane.add(update, 1, 4);
        update.setVisible(false);

        updateField = new TextField("New Stock");
        updateField.setVisible(false);
        gridPane.add(updateField,2,4);

        deleteSuccesful = new Label("Update with Succes!");
        deleteSuccesful.setVisible(false);
        gridPane.add(deleteSuccesful, 1, 5);
    }
    public void showupdate(Boolean k)
    {
        update.setVisible(k);
        updateHBox.setVisible(k);
        updateField.setVisible(k);
        deleteSuccesful.setVisible(false);
    }
    public void updateBook()
    {
        try{
            Book book = getBookfromListViewUpdate();
            Long id = book.getId();
            int newStock = Integer.parseInt(updateField.getText());
            bookService.updateStock(newStock,id);
        }catch (Exception e)
        {
            error.setText("Wrong Data");
            error.setVisible(true);
        }
    }
    public void viewReport() throws IOException {
        List<BookSold> bookSolds = bookSoldService.findAll();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(20,700);
        contentStream.setLeading(14.5f);

        //contentStream.close();
        for(BookSold bookSold : bookSolds)
        {
            //contentStream.beginText();
            contentStream.newLine();

            contentStream.showText("ID: " + bookSold.getId().toString());
            contentStream.showText("        Book ID: " + bookSold.getBookID());
            contentStream.showText("        Title: " + bookSold.getTitle());
            contentStream.showText("        Quantity: " + bookSold.getQuantity());
            // contentStream.close();
        }

        contentStream.close();

        document.save("ReportOfAllTheBooksSold.pdf");
        document.close();
    }

    public void deleteBook(Long id)
    {
        bookService.deleteBook(id);
    }


    public Book getBookfromListView()
    {
        return (Book) listView.getSelectionModel().getSelectedItem();
    }
    public Book getBookfromListViewUpdate()
    {
        return (Book) listViewUpdate.getSelectionModel().getSelectedItem();
    }

    public void addcreateBookButtonListener(EventHandler<ActionEvent> loginButtonListener) {
        createBookButton.setOnAction(loginButtonListener);
    }

    public void addCreateButtonListener(EventHandler<ActionEvent> createButtonListener){
        createButton.setOnAction(createButtonListener);
    }

    public void addUpdateBookButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        updateBookButton.setOnAction(signInButtonListener);
    }
    public void addUpdateButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        update.setOnAction(signInButtonListener);
    }

    public void adddeleteBookButtonListener(EventHandler<ActionEvent> loginButtonListener) {
        deleteBookButton.setOnAction(loginButtonListener);
    }
    public void addDeleteButtonListener(EventHandler<ActionEvent>buyListener)
    {
        delete.setOnAction(buyListener);
    }

    public void addviewBookSoldsButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        viewBookSoldsButton.setOnAction(signInButtonListener);
    }
}
