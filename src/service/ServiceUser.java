//vấn đề có thể đến từ đây
package service;


import java.sql.Connection;//
import java.sql.PreparedStatement;//
import connection.DatabaseConnection;//


import java.sql.ResultSet;
import model.Model_mess;//
import model.Model_register;//
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Model_User_account;
import model.Model_client;
import model.Model_login;


//public class ServiceUser {
//
//    public ServiceUser() {
//       this.con = DatabaseConnection.getInstance().getConnection();
//        
//    }
//
//
//    
//        public Model_mess register(Model_register data) {
//        //  Check user exit
//        System.out.println("Received registration request for user: " + data.getUserName());
//        Model_mess message = new Model_mess();
//        try {
//            PreparedStatement p = con.prepareStatement(CHECK_USER);
//            p.setString(1, data.getUserName());
//            ResultSet r = p.executeQuery();
//            if (r.first()) {
//                message.setAction(false);
//                message.setMessage("User Already Exists");
//        System.out.println("User already exists in the database.");
//            } else {
//                message.setAction(true);
//                System.out.println("User doesn't exist in the database. Proceeding with registration.");
// 
//            }
//            r.close();
//            p.close();//
//            if (message.isAction()) {
//                //  Insert User Register
//                con.setAutoCommit(false);
//                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
//                p.setString(1, data.getUserName());
//                System.out.println("đáadsa: "+ data.getUserName());
//                p.setString(2, data.getPassword());
//                p.execute();
//                r = p.getGeneratedKeys();
//                r.first();
//                int userID = r.getInt(1);
//                r.close();
//                p.close();
//                //  Create user account
//                p = con.prepareStatement(INSERT_USER_ACCOUNT);
//                p.setInt(1, userID);
//                p.setString(2, data.getUserName());
//                p.execute();
//                p.close();
//                con.commit();
//                con.setAutoCommit(true);
//                message.setAction(true);
//                message.setMessage("Ok");
//                message.setData(new Model_User_account(userID, data.getUserName(), "", "", true));
//                  System.out.println("Registration successful for user: " + data.getUserName());
//  
//            }
//        } catch (SQLException e) {
//        //    System.out.println("sai");
//         e.printStackTrace();
//            message.setAction(false);
//            message.setMessage("Server Error");
//            try {
//                if (con.getAutoCommit() == false) {
//                    con.rollback();
//                    con.setAutoCommit(true);
//                }
//            } catch (SQLException e1) {
//            }
//        }
//        return message;
//    }
//
//        public Model_User_account login(Model_login login) throws SQLException {
//    Model_User_account data = null;
//    System.out.println("Executing SQL: " + LOGIN);
//    PreparedStatement p = con.prepareStatement(LOGIN);
//    p.setString(1, login.getUserName());
//    p.setString(2, login.getPassword());
//    
//    System.out.println("Parameters: UserName=" + login.getUserName() + ", Password=" + login.getPassword());
//    
//    ResultSet r = p.executeQuery();
//    if (r.first()) {
//        int userID = r.getInt("UserID");
//        String userName = r.getString("UserName");
//        String gender = r.getString("Gender");
//        String image = r.getString("ImageString");
//        
//        data = new Model_User_account(userID, userName, gender, image, true);
//        System.out.println("Login successful, userID=" + userID + ", userName=" + userName);
//    } else {
//        System.out.println("No matching user found");
//    }
//    r.close();
//    p.close();
//    
//    return data;
//}
//
//
//        
//        public List<Model_User_account> getUser(int exitUser) throws SQLException{
//            
//            List<Model_User_account> list = new ArrayList<>();
//            
//        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
//        p.setInt(1, exitUser);
//        ResultSet r = p.executeQuery();
//        while (r.next()) {
//            int userID = r.getInt("UserID");
//            String userName = r.getString("UserName");
//            String gender = r.getString("Gender");
//            String image = r.getString("ImageString");  
//
//            list.add(new Model_User_account(userID, userName, gender, image, checkUserStatus(userID)));
//       
//        System.out.println("User ID: " + userID + " Status: " + checkUserStatus(userID)); // Thêm log để kiểm tra trạng thái người dùng
//        }
//        r.close();
//        p.close();
//            
//            return list;
//        }
//            
//       
//  private boolean checkUserStatus(int userID) {
//        List<Model_client> clients = Service.getInstance(null).getListClient();
//        for (Model_client c : clients) {
//            if (c.getUser().getUserID() == userID) {
//                System.out.println("User status for userID " + userID + ": true");
//                return true;
//            }
//        }
//        System.out.println("User status for userID " + userID + ": false");
//        return false;
//        
//    }
//
//    //  SQL
// private final String LOGIN = "SELECT UserID, chat_application.user_account.UserName, Gender, ImageString " +
//                             "FROM chat_application.user " +
//                             "JOIN chat_application.user_account USING (UserID) " +
//                             "WHERE chat_application.user.UserName=BINARY(?) " +
//                             "AND chat_application.user.Password=BINARY(?) " +
//                             "AND chat_application.user_account.Status='1'";
//  //  private final String LOGIN = "select UserID, chat_application.user_account.UserName, Gender, ImageString from `chat_application.user` join chat_application.user_account using (UserID) where `chat_application.user`.UserName=BINARY(?) and `chat_application.user`.`Password`=BINARY(?) and chat_application.user_account.`Status`='1'";
//    private final String SELECT_USER_ACCOUNT = "select UserID, UserName, Gender, ImageString from chat_application.user_account where chat_application.user_account.`Status`='1' and UserID<>?";
//    private final String INSERT_USER = "insert into chat_application.user (UserName, `Password`) values (?,?)";
//    private final String INSERT_USER_ACCOUNT = "insert into chat_application.user_account (UserID, UserName) values (?,?)";
//    private final String CHECK_USER = "SELECT UserID FROM `chat_application`.`user` WHERE UserName = ? LIMIT 1";
//    //  Instance
//  private final Connection con;
//}





public class ServiceUser {

    public ServiceUser() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    public Model_mess register(Model_register data) {
        //  Check user exit
        Model_mess message = new Model_mess();
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(false);
                message.setMessage("User Already Exit");
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();
            if (message.isAction()) {
                //  Insert User Register
                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                int userID = r.getInt(1);
                r.close();
                p.close();
                //  Create user account
                p = con.prepareStatement(INSERT_USER_ACCOUNT);
                p.setInt(1, userID);
                p.setString(2, data.getUserName());
                p.execute();
                p.close();
                con.commit();
                con.setAutoCommit(true);
                message.setAction(true);
                message.setMessage("Ok");
                message.setData(new Model_User_account(userID, data.getUserName(), "", "", true));
            }
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Server Error");
            try {
                if (con.getAutoCommit() == false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e1) {
            }
        }
        return message;
    }

    public Model_User_account login(Model_login login) throws SQLException {
        Model_User_account data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        p.setString(1, login.getUserName());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.first()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            data = new Model_User_account(userID, userName, gender, image, true);
        }
        r.close();
        p.close();
        return data;
    }

    public List<Model_User_account> getUser(int exitUser) throws SQLException {
        List<Model_User_account> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exitUser);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            list.add(new Model_User_account(userID, userName, gender, image, checkUserStatus(userID)));
        }
        r.close();
        p.close();
        return list;
    }

    private boolean checkUserStatus(int userID) {
        List<Model_client> clients = Service.getInstance(null).getListClient();
        for (Model_client c : clients) {
            if (c.getUser().getUserID() == userID) {
                return true;
            }
        }
        return false;
    }

    //  SQL
    
    
    private final String LOGIN = "SELECT UserID, chat_application.user_account.UserName, Gender, ImageString " +
                             "FROM chat_application.user " +
                             "JOIN chat_application.user_account USING (UserID) " +
                             "WHERE chat_application.user.UserName=BINARY(?) " +
                            "AND chat_application.user.Password=BINARY(?) " +
                             "AND chat_application.user_account.Status='1'";
    private final String SELECT_USER_ACCOUNT = "select UserID, UserName, Gender, ImageString from chat_application.user_account where chat_application.user_account.`Status`='1' and UserID<>?";
   private final String INSERT_USER = "insert into chat_application.user (UserName, `Password`) values (?,?)";
   private final String INSERT_USER_ACCOUNT = "insert into chat_application.user_account (UserID, UserName) values (?,?)";
    private final String CHECK_USER = "SELECT UserID FROM `chat_application`.`user` WHERE UserName = ? LIMIT 1";
    //  Instance
    private final Connection con;
}
    