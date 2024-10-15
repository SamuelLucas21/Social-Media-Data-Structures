package Screens;

import java.io.FileInputStream;
import Body.Post;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ProfileScreen {
    private Stage stage = new Stage();
    private static int id=0;
    private Pane pane;

    @FXML
    private HBox EditProfile;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private Label cityUser;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private Label nameUser;

    @FXML
    private Label relationShipUser;

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private ImageView viewSettings;

        public ProfileScreen(int i)throws Exception{
            id=i;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenProfile.fxml"));
            loader.setController(this);
            pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Perfil");
            this.stage.setResizable(false);

            pane.requestFocus();
            
        }

    public Stage getStage(){return this.stage;}
    public Pane getPane(){return this.pane;}
    
    @FXML    
    private void initialize(){
        User user = List_User.getPoint(id).user[id];
        this.nameUser.setText(user.getName());
        this.cityUser.setText(user.getCity());
        this.relationShipUser.setText(user.getCivil());

        try{
            if(user.getPhotoProfile()!=null){
                this.imageSetProfile.setImage(new Image(new FileInputStream(user.getPhotoProfile())));
                this.imageSetProfile.setFitHeight(159);
                this.imageSetProfile.setFitWidth(159);
            }
            this.vBoxPrincipal.setSpacing((double)10);
            for(int i = user.getPosts().size()-1;i>=0;--i){
                Post post = user.getPosts().get(i);   
                VBox vBox = new VBox(5);
                HBox hBox = new HBox(2);
                hBox.setSpacing(3);
                
                ImageView imageView = (user.getPhotoProfile()!=null)? new ImageView(new Image(new FileInputStream(user.getPhotoProfile()))) 
                :
                new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.PNG")))
                ;

                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                imageView.setPreserveRatio(true);

                Label nameUser = new Label(List_User.getPoint(0).user[id].getName());
                nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
                nameUser.setPadding(new Insets(13,0,0,10));//user
                hBox.getChildren().addAll(imageView,nameUser);

                vBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                Label titlePost = new Label(post.getTitle());
                titlePost.setStyle("-fx-font-weight: bold; -fx-font-size: 14px");
                titlePost.setPadding(new Insets(10,0,5,100));
                titlePost.setWrapText(true);

                Text text = new Text(post.getPostTxt());
                TextFlow txtPost = new TextFlow();
                txtPost.setStyle("-fx-font-size: 14px");
                txtPost.setCache(false);
                txtPost.setCacheShape(false);
                txtPost.setCenterShape(false);
                txtPost.setFocusTraversable(false);
                txtPost.getChildren().add(text);
                
                HBox box = new HBox(1);
                ImageView image;
                if(post.getImagem()!=null){
                    image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                    image.setFitHeight(image.getFitHeight());
                    image.setFitWidth(image.getFitWidth());
                    image.setPreserveRatio(true);
                    box.getChildren().add(image);
                    box.setPadding(new Insets(10,0,0,100));
                }
                
                int likesQnt = post.getLikes();
                Label lblQntLikes = new Label(String.valueOf(likesQnt));
                lblQntLikes.setPadding(new Insets(3,0,0,2));

                Button like = new Button("   ");
                like.setCursor(Cursor.HAND);
                like.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\";"
                );
                if(post.checkLike(id)==0){
                    like.setStyle(
                        "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;"
                    );
                    post.addLike(id);
                }else{
                    like.setStyle(
                        "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;"
                    );
                 }
                
                Hyperlink comment = new Hyperlink("Comentários "+String.valueOf(post.getComments().size()));
                comment.setCache(false);
                comment.setCacheShape(false);
                comment.setBorder(null);
                comment.setFocusTraversable(false);

                like.setOnMouseClicked(event->generateLikes(post, like,lblQntLikes,likesQnt));

                comment.setOnMouseClicked(event ->{

                });

                comment.setUnderline(true);

                HBox box1 = new HBox(2);
                box1.getChildren().addAll(new HBox((double)5,like,lblQntLikes),comment);
                box1.setPadding(new Insets(10,10,10,100));
                box1.setSpacing(280);

                HBox box2 = new HBox(3);
                box2.getChildren().addAll(txtPost);
                box2.setPadding(new Insets(0,0,0,100));
                txtPost.setPrefWidth(800);

                vBox.getChildren().addAll(hBox,titlePost,box2,box,box1); 
                this.vBoxPrincipal.getChildren().add(vBox);
            }
        }catch(Exception ie){
            ie.printStackTrace();
        }
    }

    private void generateLikes(Post post, Button like, Label showLike, int likesQnt) {
        if(post.checkLike(id)!=0){
            like.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-background-color: red; -fx-text-fill:white;"
            );
            post.addLike(id);
          }else{
            like.setStyle(
                "-fx-shape: \"M11.8,0c-1.7,0-3.2,1.4-3.8,2.8C7.4,1.4,5.9,0,4.2,0C1.9,0,0,1.9,0,4.2c0,4.7,4.8,5.9,8,10.63.0-4.65,8-6.05,8-10.6C16,1.9,14.1,0,11.8,0z\"; -fx-text-fill: black;"
            );
            post.removeLike(id);
          }
          likesQnt=post.getLikes();
          showLike.setText(String.valueOf(likesQnt));
    }


    @FXML
    private void backToHome(MouseEvent event) throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event)throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToPublic(MouseEvent event)throws Exception {
        new PublicationScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event)throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception{
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToEditProfile(MouseEvent event) {

        try{
            new EditProfile(id, this, cityUser, relationShipUser, nameUser, imageSetProfile).getStage().show();
            pane.effectProperty().set(new MotionBlur(3.0,15.0));
            }catch(Exception ie){
            ie.printStackTrace();   
            }
    }
}
