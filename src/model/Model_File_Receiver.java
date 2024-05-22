
package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Model_File_Receiver {
    private Model_send_message message;
    private File file;
    RandomAccessFile accFile;

    public Model_send_message getMessage() {
        return message;
    }

    public void setMessage(Model_send_message message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public RandomAccessFile getAccFile() {
        return accFile;
    }

    public void setAccFile(RandomAccessFile accFile) {
        this.accFile = accFile;
    }

    public Model_File_Receiver() {
    }

    public Model_File_Receiver(Model_send_message message, File file) throws IOException{
        this.message = message;
        this.file = file;
        this.accFile = new RandomAccessFile(file, "rw");
        
    }
    
    
    public synchronized long writeFile(byte[] data) throws IOException{
        accFile.seek(accFile.length());
        accFile.write(data);
        return accFile.length();
    }
    
    public void close() throws IOException{
        accFile.close();
    }
    
}
