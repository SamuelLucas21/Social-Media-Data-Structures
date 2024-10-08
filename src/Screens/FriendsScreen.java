package Screens;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;

import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FriendsScreen {
    private Stage stage = new Stage();
    private static int id =0;

    @FXML
    private Label btnCommunity;

    @FXML
    private Label btnToChat;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private TextArea luckyDay;

    @FXML
    private Label userName;

    @FXML
    private ImageView viewSettings;

     @FXML
    private VBox vboxFrinds;

    @FXML
    private VBox vboxOtherFriends;

    @FXML
    private VBox vboxNoticeFriends;

        public FriendsScreen(int i)throws Exception{
            id=i;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenFriends.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("Tela Amigos");
            stage.setResizable(false);
            this.userName.setText(List_User.getPoint(i).user[id].getName());
        }
    public Stage getStage(){return this.stage;}

    @FXML
    private void initialize(){
        try{
            this.vboxFrinds.getChildren().clear();
            this.vboxNoticeFriends.getChildren().clear();
            this.vboxOtherFriends.getChildren().clear();

            ArrayList<Integer> sort = new ArrayList<>();
            sort.addAll(List_User.getPoint(id).user[id].getFriends());
            User[] user = List_User.getPoint(id).user;

            for(int i=0;i<List_User.getPoint(i).user[id].getFriends().size();++i){
                for(int j=i+1;j<List_User.getPoint(i).user[id].getFriends().size();++j){
                    if((int)user[sort.get(i)].getName().charAt(0)>(int)user[sort.get(j)].getName().charAt(0)){
                        Collections.swap(sort, i, j);
                    }
                }
            }

            for(int i =0;i<sort.size();++i){
                HBox hBox = new HBox(4);
                ImageView img = (user[sort.get(i)].getPhotoProfile()==null)?new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                :
                new ImageView(new Image(new FileInputStream(user[sort.get(i)].getPhotoProfile())));
                img.setFitHeight(50);
                img.setFitWidth(50);

                Label userName = new Label(user[sort.get(i)].getName());
                userName.setStyle(
                    "-fx-font-family: Poppins;"+
                    "-fx-font-size: 20"
                );
                userName.setPadding(new Insets(5,0,0,20));
                userName.setPrefWidth(700);

                ImageView img1 = new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/ion_person.png")));
                ImageView img2 = new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/octicon_x.png")));
                img1.setCursor(Cursor.HAND);
                img2.setCursor(Cursor.HAND);
                
                img1.setOnMouseClicked(event->{
                    
                });
                img2.setOnMouseClicked(event->{

                });

                HBox hBox1 = new HBox(2);
                hBox1.setPadding(new Insets(5,0,0,30));
                hBox1.setSpacing(15);
                hBox1.getChildren().addAll(img1,img2);

                hBox.getChildren().addAll(img,userName,hBox1);
                hBox.setPadding(new Insets(5,10,5,10));
                this.vboxFrinds.getChildren().addAll(new Separator(),hBox,new Separator());
            }
            
            ArrayList<Integer> segs = new ArrayList<>();
            for(int i =0;i<=List_User.getPoint(i).getSizeUsers();++i){
                if(List_User.getPoint(i).user[id].checkFriend(i)!=0
                &&
                !List_User.getPoint(i).user[id].getSolicit().contains((Integer)i)
                &&
                i!=id
                &&
                List_User.getPoint(i).checkExistUser(i)==0
                &&
                !List_User.getPoint(i).user[id].getList_Solicit().contains((Integer)i)){
                    segs.add(i);
                }
            }
            label:
            while(!segs.isEmpty()){ 
                HBox hBox = new HBox(3);
                hBox.setSpacing(130);
                for(int j =0;j<3;++j){
                    HBox hbox1 = new HBox(3);
                    hbox1.setPadding(new Insets(10,0,0,10));
                    hbox1.setSpacing(5);
                    
                    ImageView img = (user[segs.get(0)].getPhotoProfile()==null)?new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                    :
                    new ImageView(new Image(new FileInputStream(user[segs.get(0)].getPhotoProfile()))) ;
                    img.setFitHeight(50);
                    img.setFitWidth(50);

                    Label userName = new Label(user[segs.get(0)].getName());
                    userName.setStyle(
                        "-fx-font-family: Poppins;"+
                        "-fx-font-size: 16;"
                    );
                    userName.setPadding(new Insets(15,0,0,10));

                    ImageView img1 = new ImageView(new Image(getClass().getResourceAsStream("./ScreensFXML/Imagens/add-friend.png")));
                    img1.setCursor(Cursor.HAND);

                    int index = segs.get(0);
                    img1.setOnMouseClicked(event->{
                        User _user = List_User.getPoint(0).user[index];
                        _user.sendSolict(id);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso!");
                        alert.setContentText("Solicitação enviada com sucesso para: \n\t"+_user.getName());
                        alert.showAndWait();
                        _user=List_User.getPoint(index).user[id];
                        _user.getList_Solicit().add(index);
                        this.initialize();
                    });
                    VBox auxToImage = new VBox();
                    auxToImage.getChildren().add(img1);
                    auxToImage.setPadding(new Insets(10,0,0,0));

                    HBox hBox2 = new HBox();
                    hBox2.getChildren().addAll(auxToImage);
                    hBox2.setPadding(new Insets(0,0,0,20));
                    
                    hbox1.getChildren().addAll(img,userName,hBox2);
                    hBox.getChildren().addAll(hbox1);
                    segs.remove(0);

                    if(segs.isEmpty())break;
                }
                this.vboxOtherFriends.getChildren().addAll(hBox);
                if(segs.isEmpty())break label;
            }
        
            ArrayList<Integer>solicit = List_User.getPoint(id).user[id].getSolicit();
            for(int i = solicit.size()-1;i>=0;--i){
                HBox hBoxSolict = new HBox(4);
                hBoxSolict.setPadding(new Insets(10,0,0,3));
                
                ImageView imageView = (user[solicit.get(i)].getPhotoProfile()==null)?new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                :
                new ImageView(new Image(new FileInputStream(user[solicit.get(i)].getPhotoProfile())))
                ;
                
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                int string_Name =user[solicit.get(i)].getName().indexOf(" ");
                Label label = new Label(user[solicit.get(i)].getName().substring(0, string_Name));
                label.setPadding(new Insets(15,0,0,0));
                label.setPrefWidth(50);

                ImageView accept = new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/check_accept.png")));
                ImageView deni = new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/x_deni.png"))); 
                
                accept.setCursor(Cursor.HAND);
                deni.setCursor(Cursor.HAND);

                int h =i;
                accept.setOnMouseClicked(event->{    
                    User _user = List_User.getPoint(h).user[id];
                    User __user=List_User.getPoint(h).user[_user.getSolicit().get(h)];
                    _user.AddFriends(_user.getSolicit().get(h));
                    __user.AddFriends(id);
                    __user.getList_Solicit().remove((Integer)id);
                    _user.getSolicit().remove(h);
                    initialize();
                });

                deni.setOnMouseClicked(event->{
                    User _user = List_User.getPoint(h).user[id];
                    User __user=List_User.getPoint(h).user[_user.getSolicit().get(h)];
                    __user.getList_Solicit().remove((Integer)id);
                    _user.getSolicit().remove(h);
                    initialize();
                });

                VBox vBoxAuxToAccept = new VBox();
                vBoxAuxToAccept.getChildren().add(accept);
                vBoxAuxToAccept.setPadding(new Insets(1,0,0,0));

                HBox hBoxToAux = new HBox(2);
                hBoxToAux.setPadding(new Insets(10,0,0,80));
                hBoxToAux.setSpacing(3);
                hBoxToAux.getChildren().addAll(vBoxAuxToAccept,deni);
                hBoxSolict.getChildren().addAll(imageView,label,hBoxToAux);
                this.vboxNoticeFriends.getChildren().addAll(new Separator(),hBoxSolict,new Separator());
            }

        }catch(Exception ie){
            ie.printStackTrace();
        }
    }

    @FXML
    private void backToLogin(MouseEvent event) throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception{
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }
    
}
