package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.EmployeeReport;
import model.User;
import model.validator.Notification;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import service.employeeReport.EmployeeReportService;
import service.user.AuthenticationService;

import java.io.IOException;
import java.util.List;


public class AdministratorView {
    private Button deleteEmployeeButton;
    private Button updateEmployeeButton;
    private Button createUserButton;
    private Button viewEmployeesActivityButton;
    private HBox hBox,hBoxDel;
    private Button update;
    private ListView listView,listViewDel;
    private Label updateSucc;
    private Button delete;
    private Label usernameLabel, passLabel;
    private TextField usernameField, passField;
    private Button registerButton;
    private final AuthenticationService authenticationService;
    private final EmployeeReportService employeeReportService;

    public AdministratorView(Stage primaryStage, AuthenticationService authenticationService, EmployeeReportService employeeReportService){
        this.employeeReportService = employeeReportService;
        this.authenticationService = authenticationService;
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        chooseEmployeeforUpdate(gridPane);

        chooseDeleteEmployee(gridPane);

        chooseUserAdd(gridPane);

        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
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
        createUserButton = new Button("Create user");
        HBox ButtonHBox = new HBox(10);
        ButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        ButtonHBox.getChildren().add(createUserButton);

        updateEmployeeButton = new Button("Update Employee");
        ButtonHBox.getChildren().add(updateEmployeeButton);

        deleteEmployeeButton = new Button("Delete Employee");
        ButtonHBox.getChildren().add(deleteEmployeeButton);

        viewEmployeesActivityButton = new Button("Employees Activity");
        ButtonHBox.getChildren().add(viewEmployeesActivityButton);
        gridPane.add(ButtonHBox, 1, 1);

    }
    private void chooseEmployeeforUpdate(GridPane gridPane)
    {
        List<User> listEmployees = authenticationService.findAllCustomers();
        ObservableList<User> users = FXCollections.observableArrayList(listEmployees);
        listView = new ListView<User>(users);
        listView.setPrefWidth(500);
        hBox = new HBox(listView);
        gridPane.add(hBox,1,3);
        hBox.setVisible(false);
        update = new Button("Update");
        gridPane.add(update, 1, 4);
        update.setVisible(false);
        updateSucc = new Label("Update with Succes!");
        updateSucc.setVisible(false);
        gridPane.add(updateSucc, 1, 5);
    }
    public void isShowUpdateEmployee(Boolean k)
    {
        update.setVisible(k);
        hBox.setVisible(k);
        updateSucc.setVisible(false);
    }
    public void isShowUpdateSucc(Boolean k)
    {
        updateSucc.setVisible(k);
    }
    public void updateEmployee(Long id)
    {
        authenticationService.updateUserEmployee(id);
    }
    public User getUserfromListView()
    {
        return (User) listView.getSelectionModel().getSelectedItem();
    }

   // -------------deleteEMPLOYEE
   private void chooseDeleteEmployee(GridPane gridPane)
   {
       List<User> listEmployees = authenticationService.findAllEmployees();
       ObservableList<User> users = FXCollections.observableArrayList(listEmployees);
       listViewDel = new ListView<User>(users);
       listViewDel.setPrefWidth(500);
       hBoxDel = new HBox(listViewDel);
       gridPane.add(hBoxDel,1,3);
       hBoxDel.setVisible(false);
       delete = new Button("Delete");
       gridPane.add(delete, 1, 4);
       delete.setVisible(false);
       updateSucc = new Label("Delete with Succes!");
       updateSucc.setVisible(false);
       gridPane.add(updateSucc, 1, 5);
   }
    public void isShowDeleteEmployee(Boolean k)
    {
        delete.setVisible(k);
        hBoxDel.setVisible(k);
        updateSucc.setVisible(false);
    }
    public void deleteEmployee(Long id)
    {
        authenticationService.deleteUser(id);
    }

    public User getUserfromListViewDel()
    {
        return (User) listViewDel.getSelectionModel().getSelectedItem();
    }
    //--------------AddUser
    private void chooseUserAdd(GridPane gridPane)
    {
        usernameLabel = new Label("username: ");
        usernameLabel.setVisible(false);
        usernameField = new TextField();
        usernameField.setVisible(false);
        HBox usernameHBOX = new HBox(10);
        usernameHBOX.getChildren().add(usernameLabel);
        usernameHBOX.getChildren().add(usernameField);

        passLabel = new Label("Password: ");
        passLabel.setVisible(false);
        passField = new TextField();
        passField.setVisible(false);
        HBox passHBOX = new HBox(10);
        passHBOX.getChildren().add(passLabel);
        passHBOX.getChildren().add(passField);

        VBox vBoxCreate = new VBox(10);
        vBoxCreate.setAlignment(Pos.BOTTOM_LEFT);
        vBoxCreate.getChildren().addAll(usernameHBOX,passHBOX);
        gridPane.add(vBoxCreate,1,5);

        registerButton = new Button("Register");
        HBox createButtonHBox = new HBox(10);
        createButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        createButtonHBox.getChildren().add(registerButton);
        gridPane.add(createButtonHBox, 1, 7);
        registerButton.setVisible(false);
        updateSucc = new Label("Register with Succes!");
        updateSucc.setVisible(false);
        gridPane.add(updateSucc, 1, 5);
    }
    public void isShowCreateUser(Boolean k)
    {
        usernameLabel.setVisible(k);
        usernameField.setVisible(k);
        passLabel.setVisible(k);
        passField.setVisible(k);
        registerButton.setVisible(k);
        updateSucc.setVisible(false);
    }

    public String getUsername()
    {
        return usernameField.getText();
    }
    public String getPass()
    {
        return passField.getText();
    }
    public Notification<Boolean> addUser(String username, String pass){
       return authenticationService.register(username, pass);
    }
    public void viewEmployeesActivity() throws IOException {
        List<EmployeeReport> employeeReports = employeeReportService.findAll();
        System.out.println(employeeReports);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(20,700);
        contentStream.setLeading(12.5f);

        for(EmployeeReport employeeReport : employeeReports)
        {
            contentStream.newLine();

            contentStream.showText( employeeReport.getId().toString() + ". ");
            contentStream.showText("    " + employeeReport.getDescription());
        }

        contentStream.close();

        document.save("ViewEmployeesActivity.pdf");
        document.close();
    }

    public void addUpdateEmployeeButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        updateEmployeeButton.setOnAction(signInButtonListener);
    }
    public void addUpdateButtonListener(EventHandler<ActionEvent> signInButtonListener) {
        update.setOnAction(signInButtonListener);
    }

    public void addDeleteEmployeeButtonListener(EventHandler<ActionEvent>singListener){
        deleteEmployeeButton.setOnAction(singListener);
    }
    public void addDeleteButtonListener(EventHandler<ActionEvent>singListener){
        delete.setOnAction(singListener);
    }

    public void addCreateUserButtonListener(EventHandler<ActionEvent>singListener)
    {
        createUserButton.setOnAction(singListener);
    }
    public void addRegisterButtonListener(EventHandler<ActionEvent>singListener)
    {
        registerButton.setOnAction(singListener);
    }
    public void addViewEmployeesActivityListener(EventHandler<ActionEvent>singListener){
        viewEmployeesActivityButton.setOnAction(singListener);
    }

}
