/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author bojana
 */
public class EvalSpeedMotion extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        // translate root node to center of the screen
        //root.setTranslateX(320);
        //root.setTranslateY(200);

        // create scene
        //createScene(root);
        
        stage.show();
    }
    
    private void createScene(Group root) {
        Pane branch = new StackPane();
        root.getChildren().add(branch);

        // create a recangle, which will be added to the branch
        Rectangle r = new Rectangle(40, 20);
        branch.getChildren().add(r);

        // circle should orbit around the rectangle
        Circle c = new Circle(10);
        branch.getChildren().add(c);
        c.setTranslateY(-50);

        // rotate the branch
        Timeline rot = new Timeline();
        rot.setCycleCount(Timeline.INDEFINITE);
        rot.setRate(1);
        rot.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                                branch.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(branch
                                .rotateProperty(), -360)));
        rot.playFromStart();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
