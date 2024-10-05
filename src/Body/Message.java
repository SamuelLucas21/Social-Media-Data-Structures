package Body;

public class Message {
    private short id=0,receptor=0,sender=0;  
    private String txtMessage=null, photoToMessage=null;
    public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }
    public short getReceptor() {
        return receptor;
    }
    public void setReceptor(short receptor) {
        this.receptor = receptor;
    }
    public short getSender() {
        return sender;
    }
    public void setSender(short sender) {
        this.sender = sender;
    }
    public String getTxtMessage() {
        return txtMessage;
    }
    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }
    public String getPhotoToMessage() {
        return photoToMessage;
    }
    public void setPhotoToMessage(String photoToMessage) {
        this.photoToMessage = photoToMessage;
    }

}
