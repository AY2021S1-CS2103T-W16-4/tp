package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.sessionlistcommands.EditSessionCommand;
import seedu.address.logic.commands.studentlistcommands.EditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.attendance.Session;
import seedu.address.model.attendance.SessionDate;
import seedu.address.model.attendance.SessionName;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditSessionDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_MATRICULATION_AMY = "A3252832E";
    public static final String VALID_MATRICULATION_BOB = "A3459682D";
    public static final String VALID_EMAIL_AMY = "amy@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@u.nus.edu";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String MATRICULATION_DESC_AMY = " " + PREFIX_MATRICULATION + VALID_MATRICULATION_AMY;
    public static final String MATRICULATION_DESC_BOB = " " + PREFIX_MATRICULATION + VALID_MATRICULATION_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    // should have a letter at the end
    public static final String INVALID_MATRICULATION_DESC = " " + PREFIX_MATRICULATION + "A0234567";
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;


    // ---------------------- Session Testing -------------------------------
    public static final String VALID_SESSIONNAME_REC = "recitation1";
    public static final String VALID_SESSIONNAME_CON = "midterm consultation";
    public static final String VALID_SESSIONDATE_REC = "12/7/2017";
    public static final String VALID_SESSIONDATE_CON = "27/11/2015";

    public static final String VALID_SESSIONNAME_REC_DESC = " " + PREFIX_SESSIONNAME + VALID_SESSIONNAME_REC;
    public static final String VALID_SESSIONNAME_CON_DESC = " " + PREFIX_SESSIONNAME + VALID_SESSIONNAME_CON;
    public static final String VALID_SESSIONDATE_REC_DESC = " " + PREFIX_SESSIONDATE + VALID_SESSIONDATE_REC;
    public static final String VALID_SESSIONDATE_CON_DESC = " " + PREFIX_SESSIONDATE + VALID_SESSIONDATE_CON;

    public static final SessionName VALID_NAME_REC = new SessionName(VALID_SESSIONNAME_REC);
    public static final SessionName VALID_NAME_CON = new SessionName(VALID_SESSIONNAME_CON);
    public static final SessionDate VALID_DATE_REC = new SessionDate(VALID_SESSIONDATE_REC);
    public static final SessionDate VALID_DATE_CON = new SessionDate(VALID_SESSIONDATE_CON);

    public static final Session REC = new Session(VALID_NAME_REC, VALID_DATE_REC);
    public static final Session CON = new Session(VALID_NAME_CON, VALID_DATE_CON);



    public static final String INVALID_SESSIONNAME = "@tut3";
    public static final String INVALID_SESSIONDATE = "13/13/2020";

    public static final String INVALID_SESSIONNAME_DESC = " " + PREFIX_SESSIONNAME + INVALID_SESSIONNAME;
    public static final String INVALID_SESSIONDATE_DESC = " " + PREFIX_SESSIONDATE + INVALID_SESSIONDATE;



    public static final EditSessionCommand.EditSessionDescriptor DESC_REC;
    public static final EditSessionCommand.EditSessionDescriptor DESC_CON;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withMatriculation(VALID_MATRICULATION_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withMatriculation(VALID_MATRICULATION_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_REC = new EditSessionDescriptorBuilder().withSessionName(VALID_SESSIONNAME_REC)
                .withSessionDate(VALID_SESSIONDATE_REC).build();
        DESC_CON = new EditSessionDescriptorBuilder().withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {

        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
