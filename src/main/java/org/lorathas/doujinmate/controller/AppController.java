package org.lorathas.doujinmate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.hibernate.SessionFactory;
import org.lorathas.doujinmate.entity.Doujin;
import org.lorathas.doujinmate.task.ImportTask;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class AppController {

    private final SessionFactory sessionFactory;
    private final BlockingQueue<ImportTask> importTaskQueue;

    @FXML
    private GridPane pane;
    @FXML
    private ListView<Doujin> doujins;
    private final ObservableList<Doujin> doujinItems;

    @Inject
    public AppController(SessionFactory sessionFactory, BlockingQueue<ImportTask> importTaskQueue) {
        this.sessionFactory = sessionFactory;
        this.importTaskQueue = importTaskQueue;

        var session = sessionFactory.openSession();
        var results = session.createQuery("FROM Doujin d ORDER BY d.name").list();
        session.close();

        var queriedDoujins = new ArrayList<Doujin>();

        for (var d : results) {
            Doujin doujin = (Doujin) d;

            queriedDoujins.add(doujin);
        }

        doujinItems = FXCollections.observableArrayList(queriedDoujins);
    }

    @FXML
    public void initialize() {
        doujins.setItems(doujinItems);

        doujins.setCellFactory(param -> {
            return new ListCell<>();
        });
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

    @FXML
    public void refreshLibraryQuery(ActionEvent e) {

    }

    @FXML
    public void importBooks(ActionEvent e) {
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Comic Book Archives", "*.zip", "*.7z", "*.cbz", "*.cb7"));
        var files = fileChooser.showOpenMultipleDialog(pane.getScene().getWindow());

        for (var file : files) {
            importTaskQueue.add(new ImportTask(file.toPath()));
        }
    }

}
