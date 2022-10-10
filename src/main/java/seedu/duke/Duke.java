package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.duke.item.ItemList;
import seedu.duke.transaction.TransactionList;
import seedu.duke.command.Command;
import seedu.duke.exception.ExceptionHandler;
import seedu.duke.parser.CommandParser;
import seedu.duke.user.UserList;

public class Duke {
    private static Logger logger = Logger.getLogger("Foo");

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        Ui.printGreeting();
        UserList userList = new UserList();
        ItemList itemList = new ItemList();
        TransactionList txList = new TransactionList();
        logger.log(Level.INFO, "start to read and process user inputs");

        String input = Ui.readInput();
        Command command;
        boolean isLastCommand = false;
        // maintain conversation
        while (true) {
            try {
                command = CommandParser.createCommand(input, userList, itemList, txList);
                isLastCommand = command.executeCommand();
            } catch (Exception e) {
                logger.log(Level.WARNING, "processing error", e);
                ExceptionHandler.handleError(e);
            } finally {
                if (isLastCommand) {
                    break;
                }
                input = Ui.readInput();
            }
        }

        logger.log(Level.INFO, "stop processing user inputs");
        Ui.exit();
    }
}
