// vấn đề không đến từ đây
package service;

//import app.MessageType;
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.Configuration;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.corundumstudio.socketio.listener.DataListener;
//import com.corundumstudio.socketio.listener.DisconnectListener;
//import java.io.IOException;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JTextArea;


import app.MessageType;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;


import model.Model_File;//
import model.Model_Package_Sender;//
import model.Model_Receive_Image;//
import model.Model_Reques_File;
import model.Model_User_account;//
import model.Model_client;//
import model.Model_login;//
import model.Model_mess;//
import model.Model_recive_message;//
import model.Model_register;//
import model.Model_send_message;//

//public class Service {
//
//
//    private static Service instance;
//    private SocketIOServer server;
//    private ServiceUser serviceuser;
//    private List<Model_client> listClient;
//    private JTextArea textArea;
//    private final int PORT_NUMBER = 1205;
//    
//    
//    
//
//    public static Service getInstance(JTextArea textArea) {
//        if (instance == null) {
//            instance = new Service(textArea);
//        }
//        return instance;
//    }
//
//    private Service(JTextArea textArea) {
//        this.textArea = textArea;
//        serviceuser = new ServiceUser();
//        listClient = new ArrayList<>();
//    }
//
//    public void startServer() {
//        Configuration config = new Configuration();
//        config.setPort(PORT_NUMBER);
//        server = new SocketIOServer(config);
//        server.addConnectListener(new ConnectListener() {
//            @Override
//            public void onConnect(SocketIOClient sioc) {
//                textArea.append("One client connected\n");
//                  System.out.println("Client connected successfully.");
//            }
//        });
//        server.addEventListener("register", Model_register.class, new DataListener<Model_register>() {
//            @Override
//            public void onData(SocketIOClient sioc, Model_register t, AckRequest ar) throws Exception {
//                Model_mess message = serviceuser.register(t);
//                ar.sendAckData(message.isAction(), message.getMessage(), message.getData());
//             if(message.isAction()){
//                   textArea.append("User has Register :" + t.getUserName()+ " Pass :" + t.getPassword() + "\n");
//                   server.getBroadcastOperations().sendEvent("list_user", (Model_User_account) message.getData());
//                   addClient(sioc, (Model_User_account) message.getData());
//                   System.out.println("đã vào");
//             }
//            }
//        });
//
//
////server.addEventListener("login", Model_login.class, new DataListener<Model_login>() {
////    @Override
////    public void onData(SocketIOClient sioc, Model_login t, AckRequest ar) throws Exception {
////        System.out.println("Received login request: " + t.getUserName());
////        Model_User_account login = serviceuser.login(t);
////        if (login != null) {
////            ar.sendAckData(true, login);
////            addClient(sioc, login);
////            
////            userConnect(login.getUserID());
////            System.out.println("Login successful for user: " + t.getUserName());
////        System.out.println("User status: " + login.isStatus()); // Kiểm tra trạng thái người dùng
//// 
////        } else {
////            ar.sendAckData(false);
////            System.out.println("Login failed for user: " + t.getUserName());
////        }
////    }
////});
//server.addEventListener("login", Model_login.class, new DataListener<Model_login>() {
//    @Override
//    public void onData(SocketIOClient sioc, Model_login t, AckRequest ar) throws Exception {
//        System.out.println("Received login request: " + t.getUserName());
//        Model_User_account login = serviceuser.login(t);
//        if (login != null) {
//            ar.sendAckData(true, login);
//            addClient(sioc, login);
//            userConnect(login.getUserID());
//            System.out.println("Login successful for user: " + t.getUserName());
//        } else {
//            ar.sendAckData(false);
//            System.out.println("Login failed for user: " + t.getUserName());
//        }
//    }
//});
//
//
//
//        
//        
//    server.addEventListener("list_user", Integer.class, new DataListener<Integer>() {
//            @Override
//            public void onData(SocketIOClient sioc, Integer UserID, AckRequest ar) throws Exception {
//                try {
//                    List<Model_User_account> list = serviceuser.getUser(UserID);
//                     
//                     textArea.append("ondata "+list);
//                    sioc.sendEvent("list_user", list.toArray());
//                } catch (java.sql.SQLException e) {
//                    System.err.println(e);
//                    e.printStackTrace();
//                }
//            }
//        });
//    
//    
//    
//    server.addEventListener("send_to_user", Model_send_message.class, new DataListener<Model_send_message>() {
//            @Override
//            public void onData(SocketIOClient sioc, Model_send_message t, AckRequest ar) throws Exception {
//                sendToClient(t);
//            }
//    });
//    
//     
//server.addDisconnectListener(new DisconnectListener() {
//            @Override
//            public void onDisconnect(SocketIOClient sioc) {
//                int userID  = removeClient(sioc);
//                if(userID != 0){
//                    userDesconect(userID);
//                }
//                  }
//        });
//        server.start();
//        textArea.append("Server has Start on port : " + PORT_NUMBER + "\n");
//        
//    }
//    
//    private void userConnect(int userID){
//         Model_User_account user = getUserById(userID);
//    if (user != null) {
//        System.out.println("User status before connect: " + user.isStatus());
//        user.setStatus(true); // Cập nhật trạng thái người dùng
//        System.out.println("User status after connect: " + user.isStatus());
//        server.getBroadcastOperations().sendEvent("user_status", userID, true);
//    }
//    }
//    
//    private void userDesconect(int userID){
// Model_User_account user = getUserById(userID);
//    if (user != null) {
//        System.out.println("User status before disconnect: " + user.isStatus());
//        user.setStatus(false); // Cập nhật trạng thái người dùng
//        System.out.println("User status after disconnect: " + user.isStatus());
//
//        server.getBroadcastOperations().sendEvent("user_status", userID, false);
//    }
//    }
//    
//   
//    private void addClient(SocketIOClient client, Model_User_account user) {
//        listClient.add(new Model_client(client, user));
//    }
//    
//    public void sendToClient(Model_send_message data){
//        for(Model_client c : listClient){
//            if(c.getUser().getUserID() == data.getToUserID()){
//                c.getClient().sendEvent("receive_ms",new Model_recive_message(data.getFromUserID(), data.getText()));  
//                break;
//            }
//        }
//    }
//    
//    
// public int removeClient(SocketIOClient client) {
//        for (Model_client d : listClient) {
//            if (d.getClient() == client) {
//                listClient.remove(d);
//                return d.getUser().getUserID();
//            }
//        }
//        return 0;
//    }
// 
// //lấy thông tin người dùng
// private Model_User_account getUserById(int userID) {
//    for (Model_client client : listClient) {
//        if (client.getUser().getUserID() == userID) {
//            return client.getUser();
//        }
//    }
//    return null;
//}
//
//    
//
//        public List<Model_client> getListClient(){
//        return listClient;
//    }
//}



public class Service {

    private static Service instance;
    private SocketIOServer server;
    private ServiceUser serviceUser;
    private ServiceFile serviceFile;
    private List<Model_client> listClient;
    private JTextArea textArea;
    private final int PORT_NUMBER = 1205;

    public static Service getInstance(JTextArea textArea) {
        if (instance == null) {
            instance = new Service(textArea);
        }
        return instance;
    }

    private Service(JTextArea textArea) {
        this.textArea = textArea;
        serviceUser = new ServiceUser();
        serviceFile = new ServiceFile();
        listClient = new ArrayList<>();
    }

    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                textArea.append("One client connected\n");
            }
        });
        server.addEventListener("register", Model_register.class, new DataListener<Model_register>() {
            @Override
            public void onData(SocketIOClient sioc, Model_register t, AckRequest ar) throws Exception {
                Model_mess message = serviceUser.register(t);
                ar.sendAckData(message.isAction(), message.getMessage(), message.getData());
                if (message.isAction()) {
                    textArea.append("User has Register :" + t.getUserName() + " Pass :" + t.getPassword() + "\n");
                    server.getBroadcastOperations().sendEvent("list_user", (Model_User_account) message.getData());
                    addClient(sioc, (Model_User_account) message.getData());
                }
            }
        });
        server.addEventListener("login", Model_login.class, new DataListener<Model_login>() {
            @Override
            public void onData(SocketIOClient sioc, Model_login t, AckRequest ar) throws Exception {
                Model_User_account login = serviceUser.login(t);
                if (login != null) {
                    ar.sendAckData(true, login);
                    addClient(sioc, login);
                    userConnect(login.getUserID());
                } else {
                    ar.sendAckData(false);
                }
            }
        });
        server.addEventListener("list_user", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer userID, AckRequest ar) throws Exception {
                try {
                    List<Model_User_account> list = serviceUser.getUser(userID);
                    sioc.sendEvent("list_user", list.toArray());
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        });
        server.addEventListener("send_to_user", Model_send_message.class, new DataListener<Model_send_message>() {
            @Override
            public void onData(SocketIOClient sioc, Model_send_message t, AckRequest ar) throws Exception {
                sendToClient(t, ar);
            }
        });
        
        
//        server.addEventListener("send_file", Model_Package_Sender.class, new DataListener<Model_Package_Sender>() {
//            @Override
//            public void onData(SocketIOClient sioc, Model_Package_Sender t, AckRequest ar) throws Exception {
//               serviceFile.receiveFile(t);
//                    try {
//                    serviceFile.receiveFile(t);
//                    if (t.isFinish()) {
//                        ar.sendAckData(true);
//                        Model_Receive_Image dataImage = new Model_Receive_Image();
//                        dataImage.setFileID(t.getFileID());
//                        Model_send_message message = serviceFile.closeFile(dataImage);
//                        //  Send to client 'message'
//                       sendTempFileToClient(message, dataImage);
//
//                    } else {
//                        ar.sendAckData(true);
//                    }
//                } catch (IOException | SQLException e) {
//                        System.out.println("111111111: Service");
//                    ar.sendAckData(false);
//                    e.printStackTrace();
//                }
//            }
//        });




 server.addEventListener("send_file", Model_Package_Sender.class, new DataListener<Model_Package_Sender>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Package_Sender t, AckRequest ar) throws Exception {
                try {
                    serviceFile.receiveFile(t);
                    if (t.isFinish()) {
                        ar.sendAckData(true);
                        Model_Receive_Image dataImage = new Model_Receive_Image();
                        dataImage.setFileID(t.getFileID());
                        Model_send_message message = serviceFile.closeFile(dataImage);
                        //  Send to client 'message'
                        sendTempFileToClient(message, dataImage);

                    } else {
                        ar.sendAckData(true);
                    }
                } catch (IOException | SQLException e) {
                    
                    System.out.println("111111111: Service");
                    ar.sendAckData(false);
                    e.printStackTrace();
                }
            }
        });
 
 
 
 
 
  server.addEventListener("get_file", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer t, AckRequest ar) throws Exception {
                Model_File file = serviceFile.initFile(t);
                long fileSize = serviceFile.getFileSize(t);
                ar.sendAckData(file.getFileExtension(), fileSize);
            }
        });
        server.addEventListener("reques_file", Model_Reques_File.class, new DataListener<Model_Reques_File>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Reques_File t, AckRequest ar) throws Exception {
                byte[] data = serviceFile.getFileData(t.getCurrentLength(), t.getFileID());
                if (data != null) {
                    ar.sendAckData(data);
                } else {
                    ar.sendAckData();
                }
            }
        });
        
        
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient sioc) {
                int userID = removeClient(sioc);
                if (userID != 0) {
                    //  removed
                    userDisconnect(userID);
                }
            }
        });
        server.start();
        textArea.append("Server has Start on port : " + PORT_NUMBER + "\n");
    }

    private void userConnect(int userID) {
        server.getBroadcastOperations().sendEvent("user_status", userID, true);
    }

    private void userDisconnect(int userID) {
        server.getBroadcastOperations().sendEvent("user_status", userID, false);
    }

    private void addClient(SocketIOClient client, Model_User_account user) {
        listClient.add(new Model_client(client, user));
    }

    private void sendToClient(Model_send_message data, AckRequest ar) {
        if (data.getMessageType() == MessageType.IMAGE.getValue() || data.getMessageType() == MessageType.FILE.getValue()) {
            try {
                Model_File file = serviceFile.addFileReceiver(data.getText());
                serviceFile.initFile(file, data);
                ar.sendAckData(file.getFileID());
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            for (Model_client c : listClient) {
                if (c.getUser().getUserID() == data.getToUserID()) {
                    c.getClient().sendEvent("receive_ms", new Model_recive_message(data.getMessageType(), data.getFromUserID(), data.getText(), null));
                    break;
                }
            }
        }
    }
    
    
     private void sendTempFileToClient(Model_send_message data, Model_Receive_Image dataImage) {
        for (Model_client c : listClient) {
            if (c.getUser().getUserID() == data.getToUserID()) {
                c.getClient().sendEvent("receive_ms", new Model_recive_message(data.getMessageType(), data.getFromUserID(), data.getText(), dataImage));
                break;
            }
        }
    }
    

    public int removeClient(SocketIOClient client) {
        for (Model_client d : listClient) {
            if (d.getClient() == client) {
                listClient.remove(d);
                return d.getUser().getUserID();
            }
        }
        return 0;
    }

    public List<Model_client> getListClient() {
        return listClient;
    }
}
