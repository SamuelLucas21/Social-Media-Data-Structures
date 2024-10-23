package Screens;

import java.io.FileInputStream;

import Body.Depoimento;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class FriendProfile {
    private Stage stage = new Stage();
    private static int id=0;
    private int idFriend = 0;
    private String visibilidadeProfile = null;
    private String noDepoim = null;//button depoimento
    private Pane pane;

    @FXML
    private HBox Hbox_to_ScreenDepoimento;

    @FXML
    private HBox Hbox_to_ScreenFriends;

    @FXML
    private Label cityUser;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private Label nameUser;

    @FXML
    private Pane paneWhite;

    @FXML
    private Label relationShipUser;

    @FXML
    private ScrollPane scroolPane;

    @FXML
    private VBox vBoxPrincipal;

    @FXML
    private ImageView viewSettings;

    @FXML
    private Pane PanePrivProfile;

    @FXML
    private ScrollPane scroolPaneDep;

    @FXML
    private VBox vBoxDepoiment;

    @FXML
    private Pane PanePrivProfileDep;

    public FriendProfile(int newId, int newIdFriend, String newVisibilidadeProfile, String newnoDep)throws Exception{

            id = newId;
            idFriend = newIdFriend;
            visibilidadeProfile = newVisibilidadeProfile;
            noDepoim = newnoDep;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenFriendProfile.fxml"));
            loader.setController(this);
            pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Perfil Amigo");
            this.stage.setResizable(false);

            pane.requestFocus();

    }

    public Stage getStage(){return this.stage;}
    public Pane getPane(){return this.pane;}
    
    @FXML    
    public void initialize(){

        Hbox_to_ScreenDepoimento.setVisible(false);
        Hbox_to_ScreenDepoimento.setManaged(false);

        if(noDepoim == "nao pode"){

            Hbox_to_ScreenDepoimento.setVisible(true);
            Hbox_to_ScreenDepoimento.setManaged(true);
        }

        scroolPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scroolPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scroolPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical

        scroolPaneDep.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        scroolPaneDep.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra horizontal do scroll
        scroolPaneDep.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // oculta a barra vertical


        User user = List_User.getPoint(idFriend).user[idFriend];
        this.nameUser.setText(user.getName());
        this.cityUser.setText(user.getCity());
        this.relationShipUser.setText(user.getCivil());

        try{
            if(user.getPhotoProfile()!=null){
                this.imageSetProfile.setImage(new Image(new FileInputStream(user.getPhotoProfile())));
                this.imageSetProfile.setFitHeight(159);
                this.imageSetProfile.setFitWidth(159);

                // Criar um círculo para o clipping
                Circle circle = new Circle(75.5, 75.5, 75.5); // O raio do círculo é metade do tamanho da imagem (53 / 2)

                // Aplicar o círculo como um clip na ImageView
                imageSetProfile.setClip(circle);
            }

            
            //mexendo aquiiiiiiiiiiii
            if(visibilidadeProfile != "Perfil privado"){
            PanePrivProfile.setVisible(false);
            PanePrivProfile.setManaged(false);

            Hbox_to_ScreenDepoimento.setVisible(true);
            Hbox_to_ScreenDepoimento.setManaged(true);
            

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

                // Criar um círculo para o clipping
                Circle circle = new Circle(25, 25, 25); // O raio do círculo é metade do tamanho da imagem (53 / 2)

                // Aplicar o círculo como um clip na ImageView
                imageView.setClip(circle);
                
                imageView.setPreserveRatio(true);

                Label nameUser = new Label(List_User.getPoint(0).user[idFriend].getName());
                nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
                nameUser.setPadding(new Insets(13,0,0,11));//user
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
                    image.setFitHeight(400);//mudei aqui
                    image.setFitWidth(400);
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
                box1.setSpacing(280);

                HBox box2 = new HBox(3);
                box2.getChildren().addAll(txtPost);
                box2.setPadding(new Insets(0,0,0,100));
                txtPost.setPrefWidth(800);

                vBox.getChildren().addAll(hBox,titlePost,box2,box,box1); 
                this.vBoxPrincipal.getChildren().add(vBox);
            }

            }

        }catch(Exception ie){
            ie.printStackTrace();
        }

        if(visibilidadeProfile != "Perfil privado"){
            PanePrivProfileDep.setVisible(false);
            PanePrivProfileDep.setManaged(false);

        try{
            
            //depoimentos do profile
            this.vBoxDepoiment.setSpacing((double)10);
            for(int i = user.getDepoimentos().size()-1;i>=0;--i){
                Depoimento depoimento = user.getDepoimentos().get(i);   
                VBox vBox = new VBox(5);//conjunto do depoimento
                HBox hBoxProfile = new HBox(2); //icon perfil, nome, lixeira
                hBoxProfile.setSpacing(3);

                
                int idepAMG = depoimento.getIdAmg();

                // Imagem do perfil da postagem
                ImageView imageIcon = (List_User.getPoint(id).user[idepAMG].getPhotoProfile() != null) 
                    ? new ImageView(new Image(new FileInputStream(List_User.getPoint(id).user[idepAMG].getPhotoProfile()))) 
                    : new ImageView(new Image(getClass().getResourceAsStream("ScreensFXML/Imagens/PERFIL.PNG")));

                imageIcon.setFitHeight(50);
                imageIcon.setFitWidth(50);
                Circle circle = new Circle(25, 25, 25);
                imageIcon.setClip(circle);
                imageIcon.setPreserveRatio(true);

                // label nome
                Label nameUser = new Label(List_User.getPoint(0).user[idepAMG].getName());
                nameUser.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
                nameUser.setPadding(new Insets(13, 0, 0, 11)); 

                hBoxProfile = new HBox();
                hBoxProfile.getChildren().addAll(imageIcon, nameUser);
                hBoxProfile.setPadding(new Insets(10, 15, 10, 10)); 


                //texto depoimento
                Text text = new Text(depoimento.getDepoimento());
                TextFlow txtDep = new TextFlow();
                txtDep.setStyle("-fx-font-size: 14px");
                txtDep.setCache(false);
                txtDep.setCacheShape(false);
                txtDep.setCenterShape(false);
                txtDep.setFocusTraversable(false);
                txtDep.getChildren().add(text);

                HBox box2 = new HBox(3);
                box2.getChildren().addAll(txtDep);
                box2.setPadding(new Insets(0,0,0,90));
                txtDep.setPrefWidth(800);

                //data e hora depoimento
                Text dateTimeText = new Text(depoimento.getFormattedDateTime());
                dateTimeText.setStyle("-fx-font-size: 12; -fx-fill: gray;");
                TextFlow dateTimeFlow = new TextFlow(dateTimeText);
                dateTimeFlow.setPadding(new Insets(0, 0, 0, 500));

                //adicionando tudo
                vBox.setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");
                vBox.getChildren().addAll(hBoxProfile,box2,dateTimeFlow); 
                this.vBoxDepoiment.getChildren().add(vBox);

            }


        }catch(Exception ie){
            ie.printStackTrace();
        }

    }

    }

    public void clearPosts(){
        vBoxDepoiment.getChildren().clear();
        vBoxPrincipal.getChildren().clear();
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

    //vai para a tela friends
    @FXML
    void backToHome(MouseEvent event)throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToChat(MouseEvent event)throws Exception {
        new ChatScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToDepoimento(MouseEvent event)throws Exception {
        
        try{
            new DepoimentoScreen(id, idFriend, this).getStage().show();
            pane.effectProperty().set(new MotionBlur(3.0,15.0));

            }catch(Exception ie){
            ie.printStackTrace();   
            }
    }

    @FXML
    void goToFriends(MouseEvent event)throws Exception {
        new FriendsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToSettings(MouseEvent event)throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    void goToEditProfile(MouseEvent event) {

    }

}
