package controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.User;
import model.validator.Notification;
import view.AdministratorView;

public class AdministratorController {
    private final AdministratorView administratorView;


    public AdministratorController(AdministratorView administratorView) {
        this.administratorView = administratorView;
        this.administratorView.addUpdateEmployeeButtonListener(new updateEmployeeButtonListener());
        this.administratorView.addUpdateButtonListener(new updateButtonListener());
        this.administratorView.addDeleteEmployeeButtonListener(new deleteEmployeeButtonListener());
        this.administratorView.addDeleteButtonListener(new deleteButtonListener());
        this.administratorView.addCreateUserButtonListener(new CreateUserButtonListener());
        this.administratorView.addRegisterButtonListener(new RegisterButtonListener());
    }

    private class updateEmployeeButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            administratorView.isShowDeleteEmployee(false);
            administratorView.isShowUpdateSucc(false);
            administratorView.isShowCreateUser(false);
            administratorView.isShowUpdateEmployee(true);
        }
    }

    private class updateButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            User user = administratorView.getUserfromListView();
            administratorView.updateEmployee(user.getId());
            administratorView.isShowUpdateSucc(true);

        }
    }

    private class deleteEmployeeButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            administratorView.isShowUpdateSucc(false);
            administratorView.isShowUpdateEmployee(false);
            administratorView.isShowCreateUser(false);
            administratorView.isShowDeleteEmployee(true);
        }
    }

    private class deleteButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            User user = administratorView.getUserfromListViewDel();
            administratorView.deleteEmployee(user.getId());
            administratorView.isShowUpdateSucc(true);
        }
    }

    private class CreateUserButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            administratorView.isShowUpdateSucc(false);
            administratorView.isShowUpdateEmployee(false);
            administratorView.isShowDeleteEmployee(false);
            administratorView.isShowCreateUser(true);
        }
    }
    private class RegisterButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String username = administratorView.getUsername();
            String pass = administratorView.getPass();

            Notification<Boolean> registerNotification = administratorView.addUser(username,pass);
            if (!registerNotification.hasErrors())
                administratorView.isShowUpdateSucc(true);
        }
    }
}
