/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.IPL;

import Model.Khachhang;
import Viewmodel.KhachHangviewmodel;
import java.util.List;

/**
 *
 * @author HP
 */
public interface khachhangIPL {

    List<Khachhang> getall();

    public Integer add(Khachhang kh);

    public Integer Delete(String id);

    public Integer Update(Khachhang kh);
    List<KhachHangviewmodel> getallDB();
}
