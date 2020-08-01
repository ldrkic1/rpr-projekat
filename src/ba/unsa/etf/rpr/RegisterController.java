package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterController {
    public Button registerButton;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;

    public void  registerAction(ActionEvent actionEvent) {
        System.out.println("klik");
    }
}
