/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilites;

import java.sql.*;

/**
 *
 * @author Soldier Boy
 */
public class JDBCHelper {

    public static ResultSet executeQuery(String sql, Object ... args) throws Exception {
        Connection con = null;
        PreparedStatement ptsm = null;
        ResultSet rs = null;

        con = DBConnection.getConnection();
        ptsm = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ptsm.setObject(i + 1, args[i]);
        }
        rs = ptsm.executeQuery();
        return rs;
    }

    public static Integer executeUpdate(String sql, Object... args) throws Exception {
        Connection con = null;
        PreparedStatement ptsm = null;
        Integer row = 0;

        con = DBConnection.getConnection();
        ptsm = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ptsm.setObject(i + 1, args[i]);
        }
        row = ptsm.executeUpdate();
        return row;
    }

}
