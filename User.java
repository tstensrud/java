public class User {
    
    private String username;
    private String password;
    private String email;
    private String name;

    User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setNewName (String newName) {
        this.username = newName;
    }

    public void setNewPassword (String newPassword) {
        this.password = newPassword;
    }
}
