/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Khachhang;
import Reponstory.khachhangreponstory;
import Services.IPL.khachhangIPL;
import Viewmodel.KhachHangviewmodel;
import java.util.List;

/**
 *
 * @author HP
 */
public class khachHangServices implements khachhangIPL{
    khachhangreponstory khachhangrepo = new khachhangreponstory();
    
    @Override
      public List<Khachhang> getall(){
        return khachhangrepo.getall();
      }
          @Override
        public Integer add(Khachhang kh){
             return khachhangrepo.add(kh);
        }
            @Override
        public Integer Delete(String id) {
              return khachhangrepo.Delete(id);
        }
            @Override
            public Integer Update(Khachhang kh){
                return khachhangrepo.Update(kh);
            }
                @Override
              public List<KhachHangviewmodel> getallDB(){
                  return khachhangrepo.getallDB();
              }
}
