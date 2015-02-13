/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import evalspeedmotion.Experiment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 */
public class ExperimentController {
    // Reference to the main application.
    private EvalSpeedMotion mainApp;
    
    private Experiment experiment;

    @FXML
    private StackPane wheel;
    
    @FXML
    private Text participant;
    @FXML
    private Text block;
    @FXML
    private Text totalBlocks;
    @FXML
    private Text trial;
    
    private Timeline timeline;
    @FXML
    private Label timerLabel;
    private DoubleProperty timeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.ZERO;
    
    /**
     * Initializes the controller class.
     */
    @FXML
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
    }
    
    @FXML
    public void startExperiment(KeyEvent event) {
        System.out.print("key pressed");
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.print("enter pressed" + mainApp);
            mainApp.startExperiment(experiment.getParticipant());
        }
    }
    
    @FXML
    public void stopTrial(KeyEvent event) {
        if (event.getCode().equals(KeyCode.SPACE)) {
            System.out.print("space pressed");
            timeline.stop();
        }
    }
    
    public void setMain(EvalSpeedMotion main) {
        System.out.println(main);
        this.mainApp = main;
    }
}
