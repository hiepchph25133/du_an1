/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory;

import DomainModel.Login_domainmodel;
import Utilites.JDBCHelper;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Lenovo
 */
public class Login_Reponsitory {

    JDBCHelper helper;

    public ArrayList<Login_domainmodel> getAlllogin(){
        ArrayList<Login_domainmodel> listlogin = new ArrayList<>();
        helper = new JDBCHelper();
        String sql = "select * from Login";
        ResultSet rs = helper.excuteQuery(sql);
        try {
            while(rs.next()){
                Login_domainmodel loginmodel = new Login_domainmodel(rs.getString(1), rs.getString(2), rs.getString(3));
                listlogin.add(loginmodel);
            }
                } catch (SQLException ex) {
            System.out.println("Lỗi Tại Câu Lệnh SQL: " +sql);
            ex.printStackTrace();
        }
        return listlogin;
    }
}
