package vimification.taskui;


import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import vimification.model.task.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";

    @FXML
    private ListView<Task> taskListView;


    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a
     * {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            }
            // else {
            // setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            // }
        }
    }
}
