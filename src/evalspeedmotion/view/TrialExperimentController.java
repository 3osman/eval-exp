/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import evalspeedmotion.Experiment;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TrialExperimentController {
    // Reference to the main application.
    private EvalSpeedMotion mainApp;
    
    private Experiment experiment;

    @FXML
    private StackPane wheel;
    
    @FXML
    private Text participant, block, totalBlocks;
    @FXML
    private Text trial, totalTrials;
    
    private Timeline timeline;
    @FXML
    private Label timerLabel;
    private DoubleProperty timeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.ZERO;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        if(timerLabel != null) {
            timerLabel.textProperty().bind(timeSeconds.asString());

            timeline = new Timeline(
                new KeyFrame(Duration.millis(100),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        Duration duration = ((KeyFrame) t.getSource()).getTime();
                        time = time.add(duration);
                        timeSeconds.set(time.toSeconds());
                    }
                })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();  
        }
        
        // rotate the branch
        if(wheel != null) {
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
    }    
    
    public void setExperiment(Experiment ex) {
        this.experiment = ex;
        if(totalBlocks != null) {
            totalBlocks.setText(String.valueOf(Experiment.BLOCKS));
        }
        if(block != null) {
            block.setText(String.valueOf(experiment.getBlock()));
        }
        if(participant != null) {
            participant.setText(experiment.getParticipant());
        }
        if(totalTrials != null) {
            totalTrials.setText(String.valueOf(Experiment.TRIALS + 1));
        }
        if(trial != null) {
            trial.setText(String.valueOf(experiment.getTrial() + 1));
        }
    }
    
    public void stopTrial(KeyEvent event) {
        if (event.getCode().equals(KeyCode.SPACE)) {
            timeline.stop();
            mainApp.finishTrial();
        }
    }
    
    public void setMain(EvalSpeedMotion main) {
        this.mainApp = main;
    }
}
