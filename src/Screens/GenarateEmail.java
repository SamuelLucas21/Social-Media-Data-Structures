package Screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.apache.commons.mail.EmailException;

import Body.SendEmail;
import Structs.List_User;

public class GenarateEmail{

    private Stage stage = new Stage();
    private Pane paneLogin;
    
    @FXML
    private ImageView imgX;

    @FXML
    private Button btnRecuperar;

    @FXML
    private TextField txtEmail;

        public GenarateEmail(Pane newPane)throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenGenareteEmail.fxml"));    
            loader.setController(this);
            Pane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            this.paneLogin=newPane;
            stage.setOnCloseRequest(event->{
                this.backToLogin(null);
            });
            pane.requestFocus();
            pane.setOnMouseClicked(event->{
                pane.requestFocus();
            });
        }

    public Stage getStage(){return this.stage;}

    @FXML
    private void backToLogin(MouseEvent event) {
        this.paneLogin.effectProperty().set(null);
        this.paneLogin.toFront();
        this.paneLogin.setDisable(false);
        this.stage.close(); 
    }

    @FXML
    private void genareteNewPass(MouseEvent event) throws InterruptedException, EmailException{
        if(this.txtEmail.getText().length()!=0){
            if(List_User.getPoint(0).checkExistUser(this.txtEmail.getText())){
                List_User.getPoint(0).user[List_User.getPoint(0).getId(this.txtEmail.getText())].setPassword(String.valueOf(new SendEmail(this.txtEmail.getText()).getCode()));
                backToLogin(null);
            }else {
                Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Aviso!");
				alert.setHeaderText(null);
				alert.setContentText("Usuário não existente.");
				alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso!");
			alert.setHeaderText(null);
			alert.setContentText("Campo de E-mail Vázio.");
			alert.showAndWait();
        }
    }
}
