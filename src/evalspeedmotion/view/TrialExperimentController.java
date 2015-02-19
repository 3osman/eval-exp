/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import evalspeedmotion.Experiment;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TrialExperimentController {

    // Reference to the main application.
    private EvalSpeedMotion mainApp;

    private Experiment experiment;

    @FXML
    private Text instructions;
    @FXML
    private Text space;
    @FXML
    private StackPane wheel, wheel1, wheel2, wheel3, wheel4, wheel5, wheel6, wheel7, wheel8, wheel9, wheel10, wheel11, wheel12, wheel13, wheel14, wheel15, wheel16, wheel17, wheel18, wheel19, wheel20, wheel21, wheel22, wheel23, wheel24;
    ArrayList<StackPane> wheels = new ArrayList<>();
    int differentItem;
    @FXML
    private TilePane grid;
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
        if (timerLabel != null) {
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
    }

    public void setGrid() {
        wheel = new StackPane();
        wheel1 = new StackPane();
        wheel2 = new StackPane();
        wheel3 = new StackPane();
        wheel4 = new StackPane();
        wheel5 = new StackPane();
        wheel6 = new StackPane();
        wheel7 = new StackPane();
        wheel8 = new StackPane();
        wheel9 = new StackPane();
        wheel10 = new StackPane();
        wheel11 = new StackPane();
        wheel12 = new StackPane();
        wheel13 = new StackPane();
        wheel14 = new StackPane();
        wheel15 = new StackPane();
        wheel16 = new StackPane();
        wheel17 = new StackPane();
        wheel18 = new StackPane();
        wheel19 = new StackPane();
        wheel20 = new StackPane();
        wheel21 = new StackPane();
        wheel22 = new StackPane();
        wheel23 = new StackPane();
        wheel24 = new StackPane();

        wheels.add(wheel);
        wheels.add(wheel1);
        wheels.add(wheel2);
        wheels.add(wheel3);
        wheels.add(wheel4);
        wheels.add(wheel5);
        wheels.add(wheel6);
        wheels.add(wheel7);
        wheels.add(wheel8);
        wheels.add(wheel9);
        wheels.add(wheel10);
        wheels.add(wheel11);
        wheels.add(wheel12);
        wheels.add(wheel13);
        wheels.add(wheel14);
        wheels.add(wheel15);
        wheels.add(wheel16);
        wheels.add(wheel17);
        wheels.add(wheel18);
        wheels.add(wheel19);
        wheels.add(wheel20);
        wheels.add(wheel21);
        wheels.add(wheel22);
        wheels.add(wheel23);
        wheels.add(wheel24);
        grid.setPrefColumns((int) Math.sqrt(this.experiment.getCurrentTrial().getSize()));
        grid.setPrefRows((int) Math.sqrt(this.experiment.getCurrentTrial().getSize()));

        switch (this.experiment.getCurrentTrial().getSize()) {
            case 4:
                AnchorPane.setLeftAnchor(grid, 300.0);
                AnchorPane.setRightAnchor(grid, 300.0);
                AnchorPane.setBottomAnchor(grid, 60.0);
                break;
            case 9:
                AnchorPane.setLeftAnchor(grid, 200.0);
                AnchorPane.setBottomAnchor(grid, 60.0);
                AnchorPane.setRightAnchor(grid, 200.0);
                break;
            case 16:
                AnchorPane.setLeftAnchor(grid, 140.0);
                AnchorPane.setBottomAnchor(grid, 60.0);
                AnchorPane.setRightAnchor(grid, 140.0);
                break;
            case 25:
                AnchorPane.setLeftAnchor(grid, 80.0);
                AnchorPane.setBottomAnchor(grid, 60.0);
                AnchorPane.setRightAnchor(grid, 80.0);
                break;
        }

        Random generator = new Random();
        differentItem = generator.nextInt(this.experiment.getCurrentTrial().getSize());

        for (int i = 0; i < this.experiment.getCurrentTrial().getSize(); i++) {
            wheels.get(i).setPrefHeight(130);
            wheels.get(i).setPrefWidth(130);
            Circle current = new Circle(10, Color.BLACK);
            current.setTranslateY(30.0);
            Circle bigCircle = new Circle(30, Color.TRANSPARENT);
            bigCircle.setStroke(Color.GREY);
            bigCircle.setStrokeWidth(1);
            current.setStrokeType(StrokeType.INSIDE);
            wheels.get(i).getChildren().add(bigCircle);
            wheels.get(i).getChildren().add(current);

            grid.getChildren().add(wheels.get(i));
            Timeline rot = new Timeline();
            rot.setCycleCount(Timeline.INDEFINITE);
            rot.setRate(1);
            int direction = -360;
            int duration = 7;

            switch (this.experiment.getCurrentTrial().getVisual()) {
                case (0): //direction
                    if (i == differentItem) {
                        direction = 360;
                    }
                    break;
                case (1): //speed
                    if (i == differentItem) {
                        duration = 2;
                    }
                    break;
                case (2): //both of them
                    int both = generator.nextInt(2);
                    switch (both) {
                        case 0:
                            duration = 2;
                            direction = -360;
                            break;
                        case 1:
                            duration = 7;
                            direction = 360;
                            break;

                    }
                    if (i == differentItem) {
                        direction = 360;
                        duration = 2;
                    }
                    break;

            }
            rot.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                                    wheels.get(i).rotateProperty(), 0)),
                    new KeyFrame(Duration.seconds(duration), new KeyValue(wheels.get(i)
                                    .rotateProperty(), direction)));
            rot.playFromStart();
        }
    }

    public void setExperiment(Experiment ex) {
        this.experiment = ex;

        if (totalBlocks != null) {
            totalBlocks.setText(String.valueOf(Experiment.BLOCKS));
        }
        if (block != null) {
            block.setText(String.valueOf(experiment.getBlock()));
        }
        if (participant != null) {
            participant.setText(experiment.getParticipant());
        }
        if (totalTrials != null) {
            totalTrials.setText(String.valueOf(Experiment.TRIALS + 1));
        }
        if (trial != null) {
            trial.setText(String.valueOf(experiment.getTrial() + 1));
        }
    }

    public void setTrialInstructions(String ins) {
        instructions.setText(ins);
    }

    public void setHit(boolean hit) {
        this.experiment.getCurrentTrial().setHit(hit);
    }

    public void putPlaceholders() {
        setTrialInstructions("Choose the different one");
        space.setText("");
        grid.getChildren().clear();
        //==================================
        //Time
        this.experiment.getCurrentTrial().setDuration(Double.parseDouble(timerLabel.getText()));
        //==================================

        for (int i = 0; i < this.experiment.getCurrentTrial().getSize(); i++) {
            wheels.set(i, new StackPane());
            wheels.get(i).setPrefHeight(130);
            wheels.get(i).setPrefWidth(130);
            ImageView currentImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("resources/xshape.gif"), 50, 50, true, true));
            wheels.get(i).getChildren().add(currentImage);
            final int id = i;

            currentImage.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {

                    //===============================
                    // hit or not
                    setHit((id == differentItem) ? true : false);

                    //===============================
                    mainApp.finishTrial();
                }
            });
            grid.getChildren().add(wheels.get(i));

        }
    }

    public void stopTrial(KeyEvent event) {
        if (event.getCode().equals(KeyCode.SPACE)) {
            timeline.stop();
            putPlaceholders();

        }
    }

    public void setMain(EvalSpeedMotion main) {
        this.mainApp = main;
    }
}
