package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Book;
import view.EmployeeView;

public class EmployeeController {
    private final EmployeeView employeeView;

    public EmployeeController(EmployeeView employeeView)
    {
        this.employeeView = employeeView;
        this.employeeView.addcreateBookButtonListener(new CreateBookButtonListener());
        this.employeeView.addCreateButtonListener(new CreateButtonListener());
        this.employeeView.adddeleteBookButtonListener(new DeleteBookButtonListener());
        this.employeeView.addDeleteButtonListener(new DeleteButtonListener());
        this.employeeView.addUpdateBookButtonListener(new UpdateBookButtonListener());
        this.employeeView.addUpdateButtonListener(new UpdateButtonListener());
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
        }
    }

    private class DeleteBookButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            employeeView.showChooseBook(true);
            employeeView.showForCreateBook(false);
            employeeView.showupdate(false);
            employeeView.unshowDelete();
        }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Book book = employeeView.getBookfromListView();
            employeeView.deleteBook(book.getId());
            employeeView.deleteSucces();
            employeeView.unshowDelete();
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
        }
    }
}
