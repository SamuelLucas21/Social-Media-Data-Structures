package Screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import Body.Message;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ChatScreen {
    private Stage stage = new Stage();
    private static int id=0;
    private ArrayDeque<Integer> chats;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private ImageView exitToHome;

    @FXML
    private HBox hBoxUserProfile;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private TextField mensageToChat;

    @FXML
    private VBox vboxSelectChat;

    @FXML
    private VBox vboxStartChat;

    @FXML
    private VBox vboxViewChat;

    @FXML
    private ImageView viewSettings;

        public ChatScreen(int i)throws Exception{
            id=i;
            this.chats=List_User.getPoint(i).user[id].getDequeChat();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenChat.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Chat");
            this.stage.setResizable(false);

            pane.requestFocus();
        }
    
    @FXML
    private void initialize(){
        try{
            this.genarateChats();
            this.genareteViewChat(1);
            
        }catch(Exception ei){
            ei.printStackTrace();
        }
    }
    
    public Stage getStage(){return this.stage;}

    private void genareteViewChat(int friendUserId){
        User user = List_User.getPoint(id).user[id];
            if(user.getChats().size()!=0){
                ArrayList<Message> chat = user.getChats().get(friendUserId).getMessages();
                for(int i=0;i<chat.size();++i){
                    VBox vBox = new VBox(2);
                    
                    if(chat.get(i).getSender()==id){
                        vBox.setPadding(new Insets(10,10,10,this.vboxViewChat.getPrefWidth()/2));    
                        Text text=new Text(chat.get(i).getTxtMessage());
                        text.setStyle("-fx-font-size: 17;");
                        text.setFill(Paint.valueOf("rgb(255,255,255)"));
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(
                            "-fx-background-radius: 20px;"+
                            "-fx-background-color: rgb(123,56,255);"+
                            "-fx-border-color: transparent;"+
                            "-fx-border-radius: 20px;"
                        );

                        textFlow.setPadding(new Insets(5,20,5,20));
                    
                        vBox.getChildren().add(textFlow);
                    }else{
                        vBox.setPadding(new Insets(10,this.vboxViewChat.getPrefWidth()/2,10,10));    
                        Text text=new Text(chat.get(i).getTxtMessage());
                        text.setStyle("-fx-font-size: 17;");
                        text.setFill(Paint.valueOf("rgb(0,0,0)"));
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle(
                            "-fx-background-radius: 20px;"+
                            "-fx-background-color: rgb(211,211,211);"+
                            "-fx-border-color: transparent;"+
                            "-fx-border-radius: 20px;"
                        );

                        textFlow.setPadding(new Insets(5,20,5,20));
                    
                        vBox.getChildren().add(textFlow);
                    }

                    this.vboxViewChat.getChildren().add(vBox);
                }
            }
    }

    private void genarateChats()throws FileNotFoundException{
        for(int i=0;i<List_User.getPoint(i).user[id].getDequeChat().size();++i){
            User user = List_User.getPoint(id).user[this.chats.element()+i];    
            HBox Hbox = new HBox();
            Hbox.getStylesheets().add("ScreensFXML/CSS/Hbox.css");
            ImageView img = (user.getPhotoProfile()!=null)?
            new ImageView(new Image(new FileInputStream(user.getPhotoProfile())))
            :
            new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
            ;
            img.setFitHeight(50);
            img.setFitWidth(50);

            VBox vBox = new VBox(1);
            Label label = new Label(user.getName());
            label.setStyle(
                "-fx-font-family: Poppins;"+
                "-fx-font-size: 16;"+
                "-fx-font-weight: bold;"
            );
            label.setPadding(new Insets(10,0,0,5));

            Label msg = new Label(user.getChats().get(id).getLastMessage().getTxtMessage());
            msg.setStyle(
                ""
            );
            msg.setPadding(new Insets(0,0,5,8));

            vBox.getChildren().addAll(label,msg);
            Hbox.getChildren().addAll(img,vBox);
            this.vboxSelectChat.getChildren().add(Hbox);
        }
    }

    @FXML
    private void backToLogin(MouseEvent event)throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event) {

    }

    @FXML
    private void goToProfile(MouseEvent event) {

    }

    @FXML
    private void goToPublic(MouseEvent event) {

    }

    @FXML
    private void goToSettings(MouseEvent event) {

    }
}
