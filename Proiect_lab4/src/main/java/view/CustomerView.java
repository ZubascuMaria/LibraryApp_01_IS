package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerView {
    private Button viewBooks;
    private Button buyBook;

    public CustomerView(Stage primaryStage)
    {
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
    public void addViewButtonListener(EventHandler<ActionEvent> viewButtonListener) {
        viewBooks.setOnAction(viewButtonListener);
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener) {
        buyBook.setOnAction(buyButtonListener);
    }
}
