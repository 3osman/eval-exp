package evalspeedmotion;

import evalspeedmotion.view.ExperimentController;
import evalspeedmotion.view.RootLayoutController;
import evalspeedmotion.view.StartExperimentController;
import evalspeedmotion.view.TrialExperimentController;
import evalspeedmotion.view.TimerExperimentController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EvalSpeedMotion extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    
    private BorderPane experimentLayout;
    
    private Experiment experiment;
    
    public EvalSpeedMotion() {
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        initRootLayout();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EvalSpeedMotion.class.getResource("view/RootLayout.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Displays the experiment start page.
     * 
     * @param participant 
     */
    public void showExperiment(String participant) {
        try {
            // Load the fxml file 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EvalSpeedMotion.class.getResource("view/Experiment.fxml"));
            experimentLayout = (BorderPane) loader.load();

            Scene scene = new Scene(experimentLayout);
            primaryStage.setScene(scene);

            experiment = new Experiment(participant, 1, 0);
            ExperimentController controller = loader.getController();
            controller.setExperiment(experiment);
            controller.setMain(this);
            
            FXMLLoader loaderStart = new FXMLLoader();
            loaderStart.setLocation(EvalSpeedMotion.class.getResource("view/ExperimentStart.fxml"));
            VBox startEx = (VBox) loaderStart.load();
            
            StartExperimentController controllerS = loaderStart.getController();
            controllerS.setExperiment(experiment);
            controllerS.setMain(this);
            
            experimentLayout.setCenter(startEx);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startTrial() {
        try {
            // Load the fxml file 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EvalSpeedMotion.class.getResource("view/ExperimentTrial.fxml"));
            AnchorPane trial = (AnchorPane) loader.load();

            TrialExperimentController controller = loader.getController();
            controller.setExperiment(experiment);
            controller.setMain(this);
            
            experimentLayout.setCenter(trial);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startTimerToNextTrial() {
        try {
            // Load the fxml file 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EvalSpeedMotion.class.getResource("view/ExperimentTimer.fxml"));
            AnchorPane timer = (AnchorPane) loader.load();

            TimerExperimentController controller = loader.getController();
            controller.setMain(this);
            
            experimentLayout.setCenter(timer);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
