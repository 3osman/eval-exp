/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 */
public class ExperimentController {

    @FXML
    private StackPane wheel;
    
    @FXML
    private Text participant;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // rotate the branch
        Timeline rot = new Timeline();
        rot.setCycleCount(Timeline.INDEFINITE);
        rot.setRate(1);
        rot.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                                wheel.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(wheel
                                .rotateProperty(), -360)));
        rot.playFromStart();
    }    
    
    public void setParticipant(String num) {
        participant.setText(num);
    }
}
