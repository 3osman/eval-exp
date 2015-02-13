package evalspeedmotion;

import evalspeedmotion.view.ExperimentController;
import evalspeedmotion.view.FinishedExperimentController;
import evalspeedmotion.view.RootLayoutController;
import evalspeedmotion.view.StartExperimentController;
import evalspeedmotion.view.TrialExperimentController;
import evalspeedmotion.view.TimerExperimentController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Initializes the root layout where a participant can be selected.
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
     * Displays the experiment start page. Start with ENTER prompt to start block.
     * Creates the experiment for this participant
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
            
            startBlock();
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Layout with enter prompt to start a block.
     */
    public void startBlock() {
        try {
            FXMLLoader loaderStart = new FXMLLoader();
            loaderStart.setLocation(EvalSpeedMotion.class.getResource("view/ExperimentStart.fxml"));
            VBox startEx = (VBox) loaderStart.load();
            
            StartExperimentController controllerStart = loaderStart.getController();
            controllerStart.setExperiment(experiment);
            controllerStart.setMain(this);
            
            experimentLayout.setCenter(startEx);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the layout for a trail.
     */
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
    
    /**
     * Finished the trial.
     * 
     * If there are trials left, start the next one.
     * 
     * Else if block is finished and there is a next block, start next block.
     * 
     * Else finish the experiment and return to the root layout.
     */
    public void finishTrial() {
        if(experiment.getTrial() < Experiment.TRIALS) {
            experiment.trialCompleted();
            startTimerToNextTrial();
        } else if(experiment.getBlock() < Experiment.BLOCKS) {
            experiment.blockCompleted();
            startBlock();
        } else {
            finishExperiment();
        }        
    }
    
    /**
     * Display thank you message.
     */
    public void finishExperiment() {
        try {
            // Load the fxml file 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EvalSpeedMotion.class.getResource("view/ExperimentFinished.fxml"));
            AnchorPane timer = (AnchorPane) loader.load();

            FinishedExperimentController controller = loader.getController();
            controller.setMain(this);
            
            experimentLayout.setCenter(timer);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Show timer until start of next trial.
     */
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
