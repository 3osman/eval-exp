/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import evalspeedmotion.Experiment;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class StartExperimentController {
    // Reference to the main application.
    private EvalSpeedMotion mainApp;
    
    private Experiment experiment;
    
    @FXML
    private Text block;
    @FXML
    private Text totalBlocks;
    
    public void initialize() {
        
    }
    
    public void setMain(EvalSpeedMotion main) {
        System.out.println(main);
        this.mainApp = main;
    }
    
    public void setExperiment(Experiment ex) {
        this.experiment = ex;
        if(totalBlocks != null) {
            totalBlocks.setText(String.valueOf(Experiment.BLOCKS));
        }
        if(block != null) {
            block.setText(String.valueOf(experiment.getBlock()));
        }
    }
    
    @FXML
    public void startBlock(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            mainApp.startTrial();
        }
    }
}
