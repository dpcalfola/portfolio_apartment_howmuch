package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseModel.userModel.UserLoginModelDAO;
import com.folaSmile.apartSearch.databaseModel.userModel.UserModelVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController extends PrimaryController implements Initializable {

    @FXML
    TextField idLgSbTextField;
    @FXML
    PasswordField pwLgSbPasswordField;
    @FXML
    Text loginConsequenceTextLgSbView;
    @FXML
    Button signupButton;


    // *LgSbView == Login-subView

    @FXML
    private void handleLoginButtonLgSbViewAction(ActionEvent event) throws IOException {

        String getID = idLgSbTextField.getText();
        String getPW = pwLgSbPasswordField.getText();


        // try login
        UserLoginModelDAO dao = new UserLoginModelDAO();
        UserModelVO userInfo = dao.loginUser(getID, getPW);


        // find userVO in database -> userInfo.isCorrectUserInfo == true
        if (userInfo.isCorrectUserInfo() && !Objects.equals(getID, "") && !Objects.equals(getPW, "")) {


            // set message on top text
            setGreetingTextField(getID + " 님 반갑습니다 !!");
            setStatusDisplayText("로그인 (" + getID + ")");

            loginConsequenceTextLgSbView.setText(getID + "님의 계정에 로그인 되었습니다.");
            loginConsequenceTextLgSbView.setVisible(true);


            // deactivate signup button on login view
            signupButton.setVisible(false);

            //
            // setting after logged-in status
            // Change button status
            setButtonsAfterLogin();

            // set static user field
            setLoggedInUserKey(userInfo.getUserPrimaryKey());
            setLoggedInUserID(userInfo.getUserID());


        } else if (!Objects.equals(getID, "") && !Objects.equals(getPW, "")) {
            // this code doesn't execute getID or getPW is empty
            loginConsequenceTextLgSbView.setText("계정 정보를 찾을 수 없습니다.");
            loginConsequenceTextLgSbView.setVisible(true);

        }


    }

    @FXML
    private void handleSignUpButtonLgSbViewAction(ActionEvent event) throws IOException {


        // Reset status message
        setStatusDisplayText("");

        // set button color
        setSignUpButtonColor();


        // Change center pane
        changeBorderPaneCenter("signup-subView.fxml");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginConsequenceTextLgSbView.setVisible(false);

    }
}
