package Screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Examples {
        public Examples()throws Exception{
            Stage stage = new Stage();
            HBox contentContainer = new HBox(50d, createNormalButton(), createHeartShapedButton());
            contentContainer.setAlignment(Pos.CENTER);
            Scene scene = new Scene(contentContainer, 500, 500);
            stage.setTitle("JavaFX Button Tutorial");
            stage.setScene(scene);
            stage.show();

        }
        private Button createNormalButton() {
            Button button = new Button("Normal Button!");
            button.setPrefSize(200, 200);
            return button;
          }
        private Button createHeartShapedButton() {
            Button button = new Button("Custom Button!");
            button.setStyle(
                "-fx-shape: \"M23.6,0c-3.4,0-6.3,2.7-7.6,5.6C14.7,2.7,11.8,0,8.4,0C3.8,0,0,3.8,0,8.4c0,9.4,9.5,11.9,16,21.26.1-9.3,16-12.1,16-21.2C32,3.8,28.2,0,23.6,0z\";"
            );
            button.setPrefSize(200, 200);
            return button;
          }
}
