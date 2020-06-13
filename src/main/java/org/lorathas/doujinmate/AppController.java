package org.lorathas.doujinmate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class AppController {

    private SessionFactory sessionFactory;

    @Inject
    public AppController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    public void exit(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    public void openSettings(ActionEvent e) {

    }

    @FXML
    public void openHelp(ActionEvent e) {

    }

}
