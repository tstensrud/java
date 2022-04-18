import java.util.LinkedList;
import java.util.Scanner;


public class Login {
    static Scanner in = new Scanner(System.in);
    static boolean run = true;
    static boolean loggedIn = false;
    static int userId = -1;
    static LinkedList<User> users = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        createUsers();
        while(run) {
                runProgram();
        }
        in.close();
    }
    
    // runs if user is not logged in. Default when starting up
    private static boolean runProgram() {
        if (!loggedIn) {
            System.out.println("[L]ogin or [r]egister new user. E[x]it");
            String entry = in.nextLine().toLowerCase();
            switch (entry) {
                case "l":
                login();
                return true;
    
                case "r":
                newUser();
                return true;
    
                case "x":
                exit();

                default:
                System.out.println("This input does nothing.");
            }
        }
        else if (loggedIn) {
            System.out.println();
            loggedIn();

        }

        return run;
    }

    // what program does while logged in
    public static void loggedIn() {
        while (loggedIn) {
            System.out.println("[P]rint userdata. [L]ogout. E[x]it.");
            String a = in.nextLine().toLowerCase();
            System.out.println();
            switch (a) {
                case "p":
                System.out.println("Email: " + users.get(userId).getEmail());
                System.out.println("Name: " + users.get(userId).getName());
                System.out.println("Username: " + users.get(userId).getUsername());
                System.out.println();
                loggedIn = true;
                return;

                case "l":
                logOut();
                return;

                case "x":
                exit();
                return;
            }
        }
    }

    // terminates program
    public static boolean exit() {
        System.out.println("Bye :)");
        run = false;
        return run;
    }

    // logout
    private static void logOut() {
        System.out.println("Logged out.");
        loggedIn = false;
        userId = -1;
    }

    // log user in if found in user database
    private static void login() {
        System.out.println("Username: ");
        String username = in.nextLine();
        System.out.println("Password: ");
        String password = in.nextLine();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                loggedIn = true;
                userId = i;
                System.out.println("Welcome " + username + ".");
                return;
            }
        }
        System.out.println("Username and/or password not found.");
        return;
    }

    // add new user
    private static void newUser() {
        System.out.println("Choose a username: ");
        String username = in.nextLine();
        while (checkForUser(username)) {
            System.out.println("Username already in use.");
            System.out.println("Username: ");
            username = in.nextLine();
        }

        System.out.println("Password: ");
        String password = in.nextLine();
        
        System.out.println("Name: ");
        String name = in.nextLine();

        System.out.println("Email: ");
        String email = in.nextLine();

        while(!validEmail(email)) {
            System.out.println("Email invalid.");
            System.out.println("Email: ");
            email = in.nextLine();
        }

        while(checkForEmail(email)) {
            System.out.println("Email allready in use.");
            System.out.println("Email: ");
            email = in.nextLine();
        }

        users.add(new User(username, password, name, email));
        System.out.println("User " + username + " added.");
    }

    // check if username exists
    private static boolean checkForUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // check if email exists in user database
    private static boolean checkForEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // check if email is valid in terms of name@domain
    private static boolean validEmail(String email) {
        
        String[] splitString = email.split("@", -1);
        if (splitString.length != 2) {
            return false;
        }
        return true;
    }

    // generate some basic userprofiles
    public static void createUsers() {
        for (int i = 0; i < 10; i++) {
            users.add(new User("username" + i, "password" + i, "TestName" + i, "email"));
        }
    }
}
