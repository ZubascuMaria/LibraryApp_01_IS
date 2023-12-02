package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import model.EmployeeReport;
import model.User;
import model.builder.EmployeeReportBuilder;
import service.employeeReport.EmployeeReportService;
import view.EmployeeView;

import java.io.IOException;

public class EmployeeController {
    private final EmployeeView employeeView;
    private final EmployeeReportService employeeReportService;
    private final User user;

    public EmployeeController(EmployeeView employeeView, EmployeeReportService employeeReportService, User user)
    {
        this.user = user;
        this.employeeReportService = employeeReportService;
        this.employeeView = employeeView;
        this.employeeView.addcreateBookButtonListener(new CreateBookButtonListener());
        this.employeeView.addCreateButtonListener(new CreateButtonListener());
        this.employeeView.adddeleteBookButtonListener(new DeleteBookButtonListener());
        this.employeeView.addDeleteButtonListener(new DeleteButtonListener());
        this.employeeView.addUpdateBookButtonListener(new UpdateBookButtonListener());
        this.employeeView.addUpdateButtonListener(new UpdateButtonListener());
        this.employeeView.addviewBookSoldsButtonListener(new ViewBookSoldsButtonListener());
    }

    private class CreateBookButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            employeeView.showChooseBook(false);
            employeeView.showupdate(false);
            employeeView.unshowDelete();
            employeeView.showForCreateBook(true);

        }
    }

    private class CreateButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {

            employeeView.getBook();
            employeeReportService.save(new EmployeeReportBuilder()
                    .setDescription("Employee with username= "+ user.getUsername()+" has created a book with title: "+ employeeView.getTitle())
                    .build());
        }
    }

    private class DeleteBookButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            employeeView.showForCreateBook(false);
            employeeView.showupdate(false);
            employeeView.unshowDelete();
            employeeView.showChooseBook(true);
        }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Book book = employeeView.getBookfromListView();
            employeeView.deleteBook(book.getId());
            employeeView.deleteSucces();
            employeeView.unshowDelete();
            employeeReportService.save(new EmployeeReportBuilder()
                    .setDescription("Employee with username= "+ user.getUsername()+" has deleted a book with title: "+ book.getTitle())
                    .build());
        }
    }

    private class UpdateBookButtonListener implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {
            employeeView.showChooseBook(false);
            employeeView.showForCreateBook(false);
            employeeView.unshowDelete();
            employeeView.showupdate(true);
        }
    }

    private class UpdateButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            employeeView.updateBook();
            employeeView.deleteSucces();
            employeeReportService.save(new EmployeeReportBuilder()
                    .setDescription("Employee with username= "+ user.getUsername()+" has updated the book stock with title: "+ employeeView.getBookfromListViewUpdate().getTitle())
                    .build());
        }
    }
    private class ViewBookSoldsButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            try {
                employeeView.viewReport();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
