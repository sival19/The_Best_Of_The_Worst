package presentation;

import Factory.CreditManagementSystemFactory;
import domain.ICreditManagementSystem;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class StartSideController {

    public Label label;
    public Button bt;
    private ICreditManagementSystem creditManagementSystem;

    public void Handler(ActionEvent event) {

    }

    public void initialize(){
        creditManagementSystem = CreditManagementSystemFactory.createCreditManagementSystem();
    }
}
