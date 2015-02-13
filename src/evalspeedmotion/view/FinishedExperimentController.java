/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evalspeedmotion.view;

import evalspeedmotion.EvalSpeedMotion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author bojana
 */
public class FinishedExperimentController {
    private EvalSpeedMotion main;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        main.initRootLayout();
    }
    
    public void setMain(EvalSpeedMotion main) {
        this.main = main;
    }
}
