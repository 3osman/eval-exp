
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcToDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create root node
        Group root = new Group();
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);

        // translate root node to center of the screen
        root.setTranslateX(320);
        root.setTranslateY(200);

        // create scene
        createScene(root);

        primaryStage.show();
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

    public static void main(String[] args) {
        Experiment e = new Experiment(5,3,0);
        System.out.println("e done");               
        launch(args);
        
    }
}
