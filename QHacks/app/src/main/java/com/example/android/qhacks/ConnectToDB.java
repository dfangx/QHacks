package com.example.android.qhacks;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cyrus on 2017-02-04.
 */

public class ConnectToDB extends AsyncTask <String, Void, Void> {

    @Override
    protected Void doInBackground(String... credentials) {
        System.out.println("run");
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://userdb.cwayrc2lh8ji.ca-central-1.rds.amazonaws.com:1433","username","password");
            System.out.println("connected");
            storeUserCredentials(credentials[0], credentials[1], credentials[2], credentials[3], con);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error");
        }
        System.out.println("complete");

        return null;
    }

    public void storeUserCredentials(String name, String username, String password, String email, Connection con) throws SQLException {
        String query = "USE QHacks; INSERT INTO login_info(NAMES, USERNAME, PASSCODE, EMAIL) VALUES('" + name + "', '" + username + "', '" + password + "', '" + email+ "');";

        //query.replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement(query);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failure");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
