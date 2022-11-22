/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Impl;

import DomainModel.Login_domainmodel;
import Reponsitory.Login_Reponsitory;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Login_Impl implements Services.Login_Impl {

    Login_Reponsitory reponsitory = new Login_Reponsitory();

    @Override
    public List<Login_domainmodel> getAlllogin() {
        return reponsitory.getAlllogin();
    }
    
    public String checkLogin(String username, String passworld){
        for (Login_domainmodel loginmodel : getAlllogin()) {
            if(loginmodel.getUsername().equals(username) && loginmodel.getPassworld().equals(passworld)){
                return loginmodel.
            }
        }
    }
}
