package com.folaSmile.apartSearch.databaseModel.initialConnectionTest;

import com.folaSmile.apartSearch.databaseModel.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnectionDAO extends ConnectDB {

    // This method is called by Application.start method
    public int testConnect(int callCode) {


        ResultSet resultSet = null;

        int resultCode = 0;
        PreparedStatement connectTestQuery = null;


        try {
            connDB();

            connectTestQuery = conn.prepareStatement("""
                    SELECT response_code
                    FROM connection_test_table
                    WHERE call_code = ?
                    """);


            connectTestQuery.setString(1, callCode + " ");

            resultSet = connectTestQuery.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("response_code");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connectTestQuery != null) {
                try {
                    connectTestQuery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return resultCode;
    }

}
