package Screens;

import Body.ManagerPosts;
import Body.Post;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 

public class PublicationScreen {
    private Stage stage = new Stage();
    private static int id =0;
    private String photo = null;

    @FXML
    private ImageView photoPublic;

    @FXML
    private Button btnFile;
    
    @FXML
    private Button btnCancel;

    @FXML
    private Label btnCommunity;

    @FXML
    private Button btnPublic;

    @FXML
    private Label btnToChat;

    @FXML
    private ImageView exitToHome;

    @FXML
    private ImageView imageSetProfile;

    @FXML
    private TextArea luckyDay;

    @FXML
    private TextArea txtPublic;

    @FXML
    private TextField txtTitlePost;

    @FXML
    private Label userName;

    @FXML
    private ImageView viewSettings;

        public PublicationScreen(int i) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenPublication.fxml"));
            loader.setController(this);
            Pane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.setTitle("Tela de Publicação");
            stage.setResizable(false);
            pane.requestFocus();
            pane.setOnMouseClicked(event->{
                pane.requestFocus();
            });

            id=i;

            {
                txtPublic.setText("teste Publicação");
                txtTitlePost.setText("Titulo");
            }
        }

    public Stage getStage(){return this.stage;}

    @FXML
    private void backToLogin(MouseEvent event)throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void goToSettings(MouseEvent event)throws Exception {
        new SettingsScreen(id).getStage().show();
        this.stage.close();
    }

    @FXML
    private void CancelPost(MouseEvent event)throws Exception {
        new HomeScreen(id).getStage().show();
        this.stage.close();
    }
    private static short i=0;
    
    @FXML
    private void PublicPost(MouseEvent event)throws Exception {
        if(this.txtPublic.getText().length()==0 || this.txtTitlePost.getText().length()==0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Aviso!");
            alert.setContentText("Contém Campos vazios");
            alert.showAndWait();
            return;
        }
            ++i;
            Post post = new Post();
            post.setId(i);
            post.setIduser((short)id);
            post.setImagem((this.photo==null)? null: this.photo);
            post.setTitle(this.txtTitlePost.getText());
            post.setPostTxt(this.txtPublic.getText());
            List_User.getPoint(0).user[id].getPosts().add(post);
            ManagerPosts.geralPosts.add(post);
            new HomeScreen(id).getStage().show();
            this.stage.close();
    }

    @FXML
    private void chooseFile(MouseEvent event)throws FileNotFoundException {
        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        this.photo= file.showOpenDialog(stage).toPath().toString();
        this.photoPublic.setImage(new Image(new FileInputStream(photo)));
    }
}
