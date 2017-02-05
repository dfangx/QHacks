package com.example.android.qhacks;

import android.os.AsyncTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.reflect.Array.getLength;

/**
 * Created by cyrus on 2017-02-04.
 */

public class ConnectToDB extends AsyncTask <String, String, String[]> {
    public int arrayLength;
    @Override
    protected String[] doInBackground(String... credentials) {
        System.out.println("run");
        String[]login = new String[2];
        String[] logListArray = new String[arrayLength];
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://userdb.cwayrc2lh8ji.ca-central-1.rds.amazonaws.com:1433","username","password");
            System.out.println("connected");
            if (credentials[credentials.length-1].equals("0"))
                storeUserCredentials(credentials[0], credentials[1], credentials[2], credentials[3], con);

            else if (credentials[credentials.length-1].equals("1")){
                login = retrieveUserCredentials(credentials[0], credentials[1], con);
            }else if (credentials[credentials.length-1].equals("2"))
                storeUserInfo(credentials[0], credentials[1], credentials[2], credentials[3], credentials[4], credentials[5], con);
            else
                logListArray = retrieveAllUserInfo(con);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error");
        }
        System.out.println("complete");

        if (credentials[credentials.length-1].equals("0") || credentials[credentials.length-1].equals("2"))
            return null;
        else if (credentials[credentials.length-1].equals("1")){
            return login;
        }else
            return logListArray;
    }

    public String[] retrieveAllUserInfo(Connection con) throws SQLException {
        String query = "USE QHacks; SELECT NAMES, EMAIL FROM login_info; SELECT * FROM profile_info;";
        PreparedStatement stmt = null;
        ArrayList logList = new ArrayList();
        stmt = con.prepareStatement(query);

        ResultSet rS = stmt.executeQuery();
        while(rS.next()){
            logList.add((rS.getInt("ID")-1), rS.getString("NAMES") + rS.getString("EMAIL") + rS.getString("PHONE") + rS.getString("COUNTRY") +
                    rS.getString("PROVINCE") + rS.getString("AGE") + rS.getString("QUALIFICATIONS") + rS.getString("ISDOC"));
        }

        String[] logListArray = new String [getLength(logList)];

        for (int i = 0; i < logListArray.length; i++)
            logListArray[i] = (String) logList.get(i);

        arrayLength = logListArray.length;

        return logListArray;
    }

    public String[] retrieveUserCredentials(String username, String hashedPassword, Connection con) throws SQLException {
        String selectUsernameQuery = "USE QHacks; SELECT USERNAME FROM login_info;", selectPasswordQuery = "USE QHacks; SELECT PASSCODE FROM login_info;", usernameDB, passwordDB;
        PreparedStatement stmt = null;
        String[]login = new String[2];

        try{
            stmt = con.prepareStatement(selectUsernameQuery);

            ResultSet rS = stmt.executeQuery();
            while (rS.next()){
                usernameDB = rS.getString("USERNAME");
                System.out.println("In retrieve with " + usernameDB + " but looking for " + username);
                if (username.equals(usernameDB)){
                    login[0] = usernameDB;
                    break;
                }
            }


            stmt = con.prepareStatement(selectPasswordQuery);
            rS = stmt.executeQuery();
            while (rS.next()){
                passwordDB = rS.getString("PASSCODE");
                System.out.println("In retrieve with " + passwordDB);
                if (hashedPassword.equals(passwordDB)){
                    login[1] = passwordDB;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failure");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        System.out.println("done done");
        return login;
    }

    public void storeUserCredentials(String name, String username, String password, String email, Connection con) throws SQLException {
        password = hashing(password);

        String query = "USE QHacks; INSERT INTO login_info(NAMES, USERNAME, PASSCODE, EMAIL) VALUES('" + name + "', '" + username + "', '" + password + "', '" + email+ "');";

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

    public void storeUserInfo(String country, String phoneNumber, String age, String province, String description, String isDoc, Connection con) throws SQLException {
        int isDoctor = Integer.parseInt(isDoc);
        String query = "USE QHacks; INSERT INTO profile_info(PHONE, COUNTRY, PROVINCE, AGE, QUALIFICATIONS, ISDOC) VALUES('" + phoneNumber + "', '" + country + "', '" + province + "', '" + age+ "', '" + description + "', '" + isDoctor+"');";

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

    public String hashing(String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    @Override
    public String toString(){
        return ("Test");
    }
}
