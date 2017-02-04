package com.example.android.qhacks;

import android.os.AsyncTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cyrus on 2017-02-04.
 */

public class ConnectToDB extends AsyncTask <String, String, Boolean> {
    boolean match = false;
    @Override
    protected Boolean doInBackground(String... credentials) {
        System.out.println("run");

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://userdb.cwayrc2lh8ji.ca-central-1.rds.amazonaws.com:1433","username","password");
            System.out.println("connected");
            if (credentials[credentials.length-1].equals("0"))
                storeUserCredentials(credentials[0], credentials[1], credentials[2], credentials[3], con);
            else
                match = retrieveUserCredentials(credentials[0], credentials[1], con);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error");
        }
        System.out.println("complete");
        if (credentials[credentials.length-1].equals("0"))
            return null;
        else {
            System.out.println("boo");
            return match;
        }
    }

    @Override
    protected void onPostExecute(Boolean match) {
        super.onPostExecute(match);
        
    }

    public boolean retrieveUserCredentials(String username, String hashedPassword, Connection con) throws SQLException {
        String selectUsernameQuery = "USE QHacks; SELECT USERNAME FROM login_info;", selectPasswordQuery = "USE QHacks; SELECT PASSCODE FROM login_info;", usernameDB, passwordDB;
        PreparedStatement stmt = null;
        boolean match = false;

        try{
            stmt = con.prepareStatement(selectUsernameQuery);
            //stmtPass = con.prepareStatement(selectPasswordQuery);

            ResultSet rS = stmt.executeQuery();
            //ResultSet rSPass = stmtPass.executeQuery();

            while (rS.next()){
                usernameDB = rS.getString("USERNAME");
                System.out.println("In retrieve with " + usernameDB + " but looking for " + username);
                if (username.equals(usernameDB)){
                    match = true;
                    break;
                }
            }

            if (match == false)
                return match;

            stmt = con.prepareStatement(selectPasswordQuery);
            rS = stmt.executeQuery();
            while (rS.next()){
                passwordDB = rS.getString("PASSCODE");
                System.out.println("In retrieve with " + passwordDB);
                if (hashedPassword.equals(passwordDB)){
                    match = true;
                    break;
                }else
                    match = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failure");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

        return match;

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
