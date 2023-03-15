package vimification.taskui;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import vimification.logic.Logic;
import vimification.model.task.Description;
import vimification.model.task.Task;
import vimification.model.task.Title;

/**
 * The Main Window. Provides the basic application layout containing a menu bar and space where
 * other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<VBox> {

    // private static final String FXML = "Main.fxml";

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;
    private CommandInput commandInput;

    @FXML
    private VBox leftComponent;

    @FXML
    private VBox rightComponent;

    @FXML
    private VBox textBoxComponent;

    // @FXML
    // private TextField textBox;

    private static final String FXML = "Main.fxml";


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Logic logic) {
        super(FXML);
        this.getRoot().setFocusTraversable(true); // Important
        this.logic = logic;

        commandInput = new CommandInput(this.getRoot());
        textBoxComponent.getChildren().add(commandInput);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Listener for handling all keyboard events to Vimification.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {

        switch (event.getText()) {
        case ":":
            handleCommand();
            break;
        case "i":
            System.out.println("You've created a task!");
            break;
        case "d":
            System.out.println("You've deleted a task!");
            break;
        case "h":
            System.out.println("You've moved to the left");
            break;
        case "l":
            System.out.println("You've moved to the right");
            break;
        case "j":
            System.out.println("You've moved up");
            break;
        case "k":
            System.out.println("You've moved down");
            break;
        }
    }

    private void handleCommand() {
        commandInput.setVisible(true);
        commandInput.requestFocus();
    }



}
