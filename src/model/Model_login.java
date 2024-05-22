
package model;


public class Model_login {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Model_login() {
    }

    public Model_login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    
}
