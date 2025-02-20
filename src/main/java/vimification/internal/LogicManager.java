package vimification.internal;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import vimification.commons.core.LogsCenter;
import vimification.internal.command.Command;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.internal.parser.ParserException;
import vimification.internal.parser.VimificationParser;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;
import vimification.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final LogicTaskList taskList;
    private final Storage storage;
    private final ObservableList<Task> viewTaskList;
    private final VimificationParser vimificationParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(LogicTaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.viewTaskList = FXCollections.observableList(taskList.getInternalList());
        this.vimificationParser = VimificationParser.getInstance();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParserException {
        logger.info("[USER COMMAND] " + commandText);

        // TODO : FIX THIS
        Command command = vimificationParser.parse(commandText);
        CommandResult result = command.execute(taskList);
        updateViewTaskList(command);

        // TODO: Fix this later
        // Only save when the result indicates that the task list should be saved
        try {
            storage.saveLogicTaskList(taskList);
        } catch (IOException ex) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ex, ex);
        }
        return result;
    }

    private void updateViewTaskList(Command command) {
        ObservableList<Task> newViewTaskList = command.getViewTaskList();
        if (newViewTaskList != null) {
            viewTaskList.setAll(newViewTaskList);
        }
    }

    // @Override
    // public ReadOnlyTaskPlanner getTaskList() {
    // return model.getTaskList();
    // }

    public ObservableList<Task> getFilteredTaskList() {
        return viewTaskList;
    }

    // @Override
    // public Path getTaskListFilePath() {
    // return model.getTaskListFilePath();
    // }

    // @Override
    // public GuiSettings getGuiSettings() {
    // return model.getGuiSettings();
    // }

    // @Override
    // public void setGuiSettings(GuiSettings guiSettings) {
    // model.setGuiSettings(guiSettings);
    // }
}
