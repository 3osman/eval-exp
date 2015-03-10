package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import evalspeedmotion.Experiment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RootLayoutController {
    
    // Reference to the main application.
    private EvalSpeedMotion main;
    
    @FXML
    private ComboBox participant;
    @FXML
    private ComboBox block;
    @FXML
    private ComboBox trial;
    
    private String participantSelected;
    private int blockSelected = -1;
    private int trialSelected = -1;
    
    @FXML 
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Starting Experiment!");
        main.showExperiment(participantSelected, blockSelected, trialSelected);
    }
    
    @FXML
    private void handleParticipantComboBoxAction(ActionEvent event) {  
        participantSelected = participant.getValue().toString();

        if(!participantSelected.equals("") && blockSelected > 0 && trialSelected >= 0) {
                    button.setDisable(false);
        }
    }
    
     @FXML
    private void handleBlockComboBoxAction(ActionEvent event) {  
        blockSelected = Integer.parseInt(block.getValue().toString());

        if(!participantSelected.equals("") && blockSelected > 0 && trialSelected >= 0) {
            button.setDisable(false);
        } 
    }
    
     @FXML
    private void handleTrialComboBoxAction(ActionEvent event) {  
        trialSelected = Integer.parseInt(trial.getValue().toString());

        if(!participantSelected.equals("") && blockSelected > 0 && trialSelected >= 0) {
                    button.setDisable(false);
        }
    }
    
    @FXML
    public void initialize() {
        // TODO
        ObservableList<Integer> listParticipants = FXCollections.observableArrayList();
        listParticipants.addAll(0,1,2,3,4,5);  
        participant.setItems(listParticipants);
        
        ObservableList<Integer> listBlocks = FXCollections.observableArrayList();
        listBlocks.addAll(1,2,3,4);  
        block.setItems(listBlocks);
        
        ObservableList<Integer> listTrials = FXCollections.observableArrayList();
        for(int i = 0; i <= Experiment.TRIALS; i++) {
            listTrials.add(i);  
        }
        
        trial.setItems(listTrials);

    }    
    
    public void setMain(EvalSpeedMotion main) {
        this.main = main;
    }
}
