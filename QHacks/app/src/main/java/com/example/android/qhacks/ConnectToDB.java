package com.example.android.qhacks;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by cyrus on 2017-02-04.
 */

public class ConnectToDB extends AsyncTask <String, Void, Void> {

    @Override
    protected Void doInBackground(String... url) {
        System.out.println("run");
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://userdb.cwayrc2lh8ji.ca-central-1.rds.amazonaws.com:1433","username","password");
            System.out.println("connected");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error");
        }
        System.out.println("complete");

        return null;
    }
}
//com.microsoft.sqlserver.jdbc.SQLServerDriver