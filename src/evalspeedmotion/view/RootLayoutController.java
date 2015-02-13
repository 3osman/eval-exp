package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
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
    private String participantSelected;
    
    @FXML 
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Starting Experiment!");
        main.showExperiment(participantSelected);
    }
    
    @FXML
    private void handleComboBoxAction(ActionEvent event) {  
        participantSelected = participant.getValue().toString();

        button.setDisable(false);
    }
    
    @FXML
    public void initialize() {
        // TODO
        ObservableList<Integer> list = FXCollections.observableArrayList();
        list.addAll(0,1,2,3,4,5);  
        participant.setItems(list);
    }    
    
    public void setMain(EvalSpeedMotion main) {
        this.main = main;
    }
}
