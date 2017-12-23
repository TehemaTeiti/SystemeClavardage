package ui;

import modele.User;
import modele.UserList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.Assert.assertEquals;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.UnknownHostException;

public class TestUserList {

    private UserList users;

    private static final String FIELD_SEPARATOR = " ";
    private static final String ELEMENT_SEPARATOR = "\n";
    private static final String JEAN_ADDR = "10.0.0.1";
    private static final String JEAN_NAME = "Jean";
    private static final String PIERRE_ADDR = "10.0.0.2";
    private static final String PIERRE_NAME = "Pierre";
    private static final String ALICE_ADDR = "10.0.0.3";
    private static final String ALICE_NAME = "ALICE";

    private ByteArrayOutputStream out;


    @Before
    public void setUp() {
        users = new UserList();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void empty_list() {
        users.printAll();
        assertEquals(standardOutput(), "");
    }

    @Test
    public void add_one_user() throws UnknownHostException {
        User u1 = new User(JEAN_ADDR, JEAN_NAME);
        users.add(u1);
        users.printAll();

        String expectedOutput = JEAN_ADDR + FIELD_SEPARATOR + JEAN_NAME;
        assertThat(standardOutput(), containsString(expectedOutput));
    }

    @Test
    public void add_two_users() throws UnknownHostException {
        User u1 = new User(JEAN_ADDR, JEAN_NAME);
        User u2 = new User(PIERRE_ADDR, PIERRE_NAME);
        users.add(u1);
        users.add(u2);
        users.printAll();

        String expectedOutput = JEAN_ADDR + FIELD_SEPARATOR + JEAN_NAME + ELEMENT_SEPARATOR
                + PIERRE_ADDR + FIELD_SEPARATOR+ PIERRE_NAME;
        assertThat(standardOutput(), containsString(expectedOutput));
    }

    @Test
    public void remove_second_user() throws UnknownHostException {
        User u1 = new User(JEAN_ADDR, JEAN_NAME);
        User u2 = new User(PIERRE_ADDR, PIERRE_NAME);
        User u3 = new User(ALICE_ADDR, ALICE_NAME);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.printAll();

        String expectedOutputBeforeRemove = JEAN_ADDR + FIELD_SEPARATOR + JEAN_NAME + ELEMENT_SEPARATOR
                + PIERRE_ADDR + FIELD_SEPARATOR + PIERRE_NAME + ELEMENT_SEPARATOR
                + ALICE_ADDR + FIELD_SEPARATOR + ALICE_NAME + ELEMENT_SEPARATOR;
        assertThat(standardOutput(), containsString(expectedOutputBeforeRemove));

        users.remove(1);
        users.printAll();
        String expectedOutputAfterRemove = JEAN_ADDR + FIELD_SEPARATOR + JEAN_NAME + ELEMENT_SEPARATOR
                 + ALICE_ADDR + FIELD_SEPARATOR + ALICE_NAME;
        assertThat(standardOutput(), containsString(expectedOutputAfterRemove));

    }


    private String standardOutput() {
        return out.toString();
    }

}
