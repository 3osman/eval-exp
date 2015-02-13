/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerExperimentController {
     
    private EvalSpeedMotion mainApp;

    private Timeline timeline;
    
    @FXML
    private Label timerLabel;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty();
    private Duration time = Duration.seconds(5);
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        if(timerLabel != null) {
            timerLabel.textProperty().bind(timeSeconds.asString());
            int seconds = (int) time.toSeconds();
            timeSeconds.set(seconds);
            
            
            timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(time.toSeconds() + 1),
                    new KeyValue(timeSeconds, 0)));
            timeline.playFromStart();
            
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        mainApp.startTrial();
                    }
                });
        }
    }    
    
    public void setMain(EvalSpeedMotion main) {
        this.mainApp = main;
    }
    
}
