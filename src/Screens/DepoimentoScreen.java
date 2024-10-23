package Screens;

import Body.Depoimento;
import Body.User;
import Structs.List_User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DepoimentoScreen {
    private Stage stage = new Stage();
    private static int idAmg = 0;
    private static FriendProfile _FriendProfile;
    private User user;
    private Pane pane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPublic;

    @FXML
    private TextArea textMessage;

    public DepoimentoScreen(int newIdAmg, FriendProfile newFriendProfile)throws Exception{

        idAmg = newIdAmg;
        _FriendProfile = newFriendProfile;
        user = List_User.getPoint(idAmg).user[idAmg];

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreensFXML/ScreenDepoimento.fxml"));
            loader.setController(this);
            pane = loader.load();
            this.stage.setScene(new Scene(pane));
            this.stage.setTitle("Depoimento");
            this.stage.setResizable(false);
            this.stage.initStyle(StageStyle.UNDECORATED);

            pane.requestFocus();
            pane.setOnMouseClicked(event ->{
                pane.requestFocus();
            });
    }

    public Stage getStage(){return this.stage;}
    public Pane getPane(){return this.pane;}


    @FXML
    private void publicDepoimento(MouseEvent event) {

        Depoimento depoimento = new Depoimento();
        depoimento.setDepoimento(textMessage.getText());

        user.getDepoimentos().add(depoimento);

        _FriendProfile.initialize();

        cancel(null);

    }

    @FXML
    private void cancel(MouseEvent event) {

        _FriendProfile.getPane().effectProperty().set(null);
        _FriendProfile.getPane().toFront();
        this.stage.close();
    }
}
