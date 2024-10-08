package Screens;

import java.io.FileInputStream;
import java.util.Optional;

import Body.ManagerPosts;
import Body.Post;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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

public class HomeScreen{

    private Stage stage = new Stage();
    private Pane pane;
    private static int id =0;

    @FXML
    private HBox hBoxUserProfile;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private HBox Hbox_to_ScreenPublic;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private ImageView exitToHome;

    @FXML
    private VBox vboxPost;
    
    @FXML
    private Label userName;

    @FXML
    private TextArea luckyDay;

    @FXML
    private ScrollPane scrollPosts;

    @FXML
    private ImageView viewSettings;

    @FXML
    private HBox Hbox_to_ScreenChat;

        public HomeScreen(int i)throws Exception{
            id=i;
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenHome.fxml"));
            loader.setController(this);
            pane = loader.load();
            pane.requestFocus();
            stage.setScene(new Scene(pane));
            stage.setTitle("Home about "+ List_User.getPoint(0).user[i].getName());
            stage.setResizable(false);
        }

    public Stage getStage(){return this.stage;}

    @FXML
    private void initialize(){
        this.userName.setText(List_User.getPoint(0).user[id].getName());
        try{
            this.vboxPost.setSpacing((double)10);
            for(int i =ManagerPosts.geralPosts.size()-1;i>=0;--i){
                Post post = ManagerPosts.geralPosts.get(i);
                VBox vBox = new VBox(5);
                HBox hBox = new HBox(2);
                hBox.setSpacing(3);

                String photo = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile())!=null)? List_User.getPoint(i).user[post.getIduser()].getPhotoProfile(): "ScreensFXML/Imagens/PERFIL.PNG";
                
                ImageView imageView = ((List_User.getPoint(i).user[post.getIduser()].getPhotoProfile())!=null)? 
                new ImageView(new Image(new FileInputStream(photo)))
                :
                new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.png")))
                ;

                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                Label nameUser = new Label(List_User.getPoint(0).user[post.getIduser()].getName());
                nameUser.setStyle("-fx-font-weight: bold");
                nameUser.setPadding(new Insets(40,0,0,10));
                hBox.getChildren().addAll(imageView,nameUser);

                vBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                Label titlePost = new Label(post.getTitle());
                titlePost.setStyle("-fx-font-weight: bold");
                titlePost.setPadding(new Insets(10,0,5,100));
                titlePost.setWrapText(true);

                Text text = new Text(post.getPostTxt());
                TextFlow txtPost = new TextFlow();
                txtPost.getChildren().add(text);
                txtPost.setCache(false);
                txtPost.setCacheShape(false);
                txtPost.setCenterShape(false);
                txtPost.setFocusTraversable(false);
                txtPost.getStylesheets().add(this.getClass().getResource("ScreensFXML/CSS/TxtAreaToPosts.css").toString());
                txtPost.setOnMouseClicked(event->{
                    this.pane.requestFocus();
                });

                HBox box = new HBox(1);
                box.setPadding(new Insets(10,0,0,100));

                if(post.getImagem()!=null){
                    ImageView image = new ImageView(new Image(new FileInputStream(post.getImagem())));
                    image.setFitHeight(image.getFitHeight());
                    image.setFitWidth(image.getFitWidth());
                    box.getChildren().add(image);
                    image.setPreserveRatio(true);
                }
                
                int likesQnt = post.getLikes();
                Label lblQntLikes = new Label(String.valueOf(likesQnt));
                lblQntLikes.setPadding(new Insets(3,0,0,2));
                
                Button like = new Button("   ");
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

                like.setOnMouseClicked(event->generateLikes(post,like,lblQntLikes,likesQnt));

                comment.setOnMouseClicked(event ->{
                        try {
                            new CommentScreen(id, post, pane, comment,like,lblQntLikes).getStage().show();
                            pane.effectProperty().set(new MotionBlur(3.0,15.0));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                });

                comment.setUnderline(true);
                
                HBox box1 = new HBox(2);
                box1.getChildren().addAll(new HBox((double)5,like,lblQntLikes),comment);
                box1.setPadding(new Insets(10,10,10,100));
                box1.setSpacing(650);

                HBox box2 = new HBox(3);
                box2.getChildren().addAll(txtPost);
                box2.setPadding(new Insets(0,0,0,100));
                txtPost.setPrefWidth(800);
                
                vBox.getChildren().addAll(hBox,titlePost,box2,box,box1);
                this.vboxPost.getChildren().add(vBox);   
            }
        }catch(Exception ie){
            ie.printStackTrace();
        }
        new Thread(){
           @SuppressWarnings("removal")
            public void run(){
                try{
                    while(true){
                        Thread.sleep(100);
                        if(scrollPosts.getVvalue()!=0.0){
                            scrollPosts.setVvalue(0.0);
                            stop();
                        }
                        if(!stage.isShowing())stop();         
                    }
                }catch(Exception ie){
                    ie.printStackTrace();
                }        
            }
        }.start();
    }

    private void generateLikes(Post post, Button like,Label lblLike, int likesQnt) {
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
          lblLike.setText(String.valueOf(likesQnt));
    }
    
    @FXML
    private void backToLogin(MouseEvent event)throws Exception {
        pane.effectProperty().set(new MotionBlur(3.0,15.0));
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aviso!");
        alert.setHeaderText("Fazer Log Off");
        alert.setContentText("Gostaria de fazer log off?");

        ButtonType buttonTypeOne = new ButtonType("Sim");
        ButtonType buttonTypeTwo = new ButtonType("Voltar ao Sistema");

        alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            new LoginScreen().getStage().show();
            this.stage.close();     
        }else {
            pane.effectProperty().set(null);
        }
    }

    @FXML
    private void goToSettings(MouseEvent event) throws Exception{
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToFriends(MouseEvent event) throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToPublic(MouseEvent event) throws Exception {
        new PublicationScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToProfile(MouseEvent event) throws Exception {
        new ProfileScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToChat(MouseEvent event) throws Exception{
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }
            
}
