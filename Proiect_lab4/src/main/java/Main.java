import com.mysql.cj.jdbc.ConnectionWrapper;
import database.JDBConnectionWrapper;
import model.*;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.BookSoldBuilder;
import model.builder.UserBuilder;
import model.validator.Notification;
import model.validator.UserValidator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import repository.BookSold.BookSoldRepository;
import repository.BookSold.BookSoldRepositoryMySQL;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.employeeReport.EmployeeReportRepository;
import repository.employeeReport.EmployeeReportRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.bookSold.BookSoldService;
import service.bookSold.BookSoldServiceImpl;
import view.EmployeeView;

import javax.swing.text.Document;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.CUSTOMER;

public class Main {
    public static void main(String[] args) throws IOException {

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("library");

        /*BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());

       // BookRepository bookRepository = new BookRepositoryCacheDecorator(new BookRepositoryMySQL(connectionWrapper.getConnection()),
          //      new Cache<Book>());

        Book book = new BookBuilder()
                .setAuthor("Mihai Eminescu")
                .setTitle("Luceafarul")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .setStock(10)
                .setPrice(30)
                .build();

        bookRepository.save(book);

        Book book1 = new BookBuilder()
                .setAuthor("Ion Creanga")
                .setTitle("Amintiri din copilarie")
                .setPublishedDate(LocalDate.of(1900, 11, 15))
                .setStock(20)
                .setPrice(40)
                .build();

        bookRepository.save(book1);

        Book book2 = new BookBuilder()
                .setAuthor("Raluca Feher")
                .setTitle("Sa nu razi :(")
                .setPublishedDate(LocalDate.of(2019, 1, 18))
                .setStock(50)
                .setPrice(80)
                .build();

        bookRepository.save(book2);

        Book book3 = new BookBuilder()
                .setAuthor("Prince Harry")
                .setTitle("Spare")
                .setPublishedDate(LocalDate.of(2023, 1, 10))
                .setStock(33)
                .setPrice(100)
                .build();

        bookRepository.save(book3);

         */


       /* BookRepository bookRepository1 = new BookRepositoryMySQL(connectionWrapper.getConnection());
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());

        UserRepository userRepository = new UserRepositoryMySQL(connectionWrapper.getConnection(),rightsRolesRepository);
        //userRepository.updateUserEmployee(1L);
        userRepository.deleteUser(2L);
        */
        EmployeeReportRepository employeeReportRepository = new EmployeeReportRepositoryMySQL(connectionWrapper.getConnection());
        List<EmployeeReport> employeeReports = employeeReportRepository.findAll();
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

        document.save("pdfBoxHelloWorld.pdf");
        document.close();

    }
}