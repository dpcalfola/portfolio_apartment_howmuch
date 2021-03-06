package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseModel.systemInformation.SystemInformationDAO;
import com.folaSmile.apartSearch.databaseModel.systemInformation.SystemInformationVO;
import com.folaSmile.apartSearch.env.EnvClientVer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationController extends PrimaryController implements Initializable {


    @FXML
    private Text clientVerText;
    @FXML
    private Text databaseVerText;
    @FXML
    private Text databaseRangeText;
    @FXML
    private Text databaseCaseText;
    @FXML
    private Text noticeText;


    // Initialize method
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {


        EnvClientVer envClientVer = new EnvClientVer();
        String clientVer = envClientVer.getEnvClientVer();
        clientVerText.setText(clientVer);

        try {
            SystemInformationDAO sysInfoDAO = new SystemInformationDAO();
            SystemInformationVO sysInfoVO = sysInfoDAO.getSystemInfo();


//            String clientVer = sysInfoVO.getClientVer();

            String databaseVer = sysInfoVO.getDatabaseVer();
            String databaseRange = sysInfoVO.getDatabaseRange();
            String notice = sysInfoVO.getNotice();
            int databaseCase = sysInfoVO.getDataCase();

            databaseVerText.setText(databaseVer);
            databaseRangeText.setText(databaseRange);
            databaseCaseText.setText(databaseCase + " 개");
            noticeText.setText(notice);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
