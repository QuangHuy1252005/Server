
package model;




public class Model_recive_message {

   
    private int messageType; 
    private int fromUserID;
    private String text;
 Model_Receive_Image dataImage;
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Receive_Image getDataImage() {
        return dataImage;
    }

    public void setDataImage(Model_Receive_Image dataImage) {
        this.dataImage = dataImage;
    }

    public Model_recive_message() {
    }

    public Model_recive_message(int messageType, int fromUserID, String text, Model_Receive_Image dataImage) {
        this.messageType = messageType;
        this.fromUserID = fromUserID;
        this.text = text;
        this.dataImage = dataImage;
    }
   
 
    
    
    

    
    

}
