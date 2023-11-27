package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CustomerView;

public class CustomerController {
    private final CustomerView customerView;

    public CustomerController(CustomerView customerView)
    {
        this.customerView=customerView;

        this.customerView.addViewButtonListener(new viewBooksListener());
    }

    private class viewBooksListener implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event) {

        }
    }
}
