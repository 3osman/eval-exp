/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import evalspeedmotion.Experiment;

/**
 * FXML Controller class
 */
public class ExperimentController {
    // Reference to the main application.
    private EvalSpeedMotion mainApp;
    
    private Experiment experiment;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        
    }    
    
    public void setExperiment(Experiment ex) {
        this.experiment = ex;
    }
    
    public void setMain(EvalSpeedMotion main) {
        System.out.println(main);
        this.mainApp = main;
    }
}
