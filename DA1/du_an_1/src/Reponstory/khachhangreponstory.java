/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.Khachhang;
import Utilites.JDBCHelper;
import Viewmodel.KhachHangviewmodel;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author HP
 */
public class khachhangreponstory {

    public List<Khachhang> getall() {
        List<Khachhang> listkh = new ArrayList<>();
        try {
            String sql = "SELECT [MaKH]\n"
                    + "      ,[HoTenKH]\n"
                    + "      ,[NgaySinh]\n"
                    + "      ,[DiaChi]\n"
                    + "      ,[Sdt]\n"
                    + "      ,[ThanhPho]\n"
                    + "      ,[QuocGia]\n"
                    + "  FROM [dbo].[KhachHang]";
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                listkh.add(new Khachhang(rs.getString("MaKH"), rs.getString("HoTenKH"), rs.getString("NgaySinh"),
                        rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("ThanhPho"), rs.getString("QuocGia")));
            }
            return listkh;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;

    }

    public List<KhachHangviewmodel> getallDB() {
        List<Khachhang> listkh = getall();
        List<KhachHangviewmodel> listch = new ArrayList<>();
        for (Khachhang x : listkh) {
            KhachHangviewmodel kh = new KhachHangviewmodel();
            kh.setMaKH(x.getMaKH());
            kh.setHotenKH(x.getHotenKH());
            kh.setSDT(x.getSDT());
            kh.setDiachi(x.getDiachi());

            listch.add(kh);
        }
        return listch;
    }

    public Integer add(Khachhang kh) {
        try {
            String sql = "INSERT INTO [dbo].[KhachHang]\n"
                    + "           ([MaKH]\n"
                    + "           ,[HoTenKH]\n"
                    + "           ,[NgaySinh]\n"
                    + "           ,[DiaChi]\n"
                    + "           ,[Sdt]\n"
                    + "           ,[ThanhPho]\n"
                    + "           ,[QuocGia])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
            Integer row = JDBCHelper.executeUpdate(sql, kh.getMaKH(), kh.getHotenKH(),
                    kh.getNgaSinh(), kh.getDiachi(), kh.getSDT(), kh.getThanhpho(), kh.getQuocgia());
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

    public Integer Delete(String ma) {
        try {
            String sql = "DELETE FROM [dbo].[KhachHang]\n"
                    + "      WHERE MaKH = ?";
            Integer row = JDBCHelper.executeUpdate(sql, ma);
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

    public Integer Update(Khachhang kh) {
        try {
            String sql = "UPDATE [dbo].[KhachHang]\n"
                    + "   SET [HoTenKH] = ?\n"
                    + "      ,[NgaySinh] = ?\n"
                    + "      ,[DiaChi] = ?\n"
                    + "      ,[Sdt] = ?\n"
                    + "      ,[ThanhPho] = ?\n"
                    + "      ,[QuocGia] = ?\n"
                    + " WHERE MaKH = ?";
            Integer row = JDBCHelper.executeUpdate(sql, kh.getHotenKH(),
                    kh.getNgaSinh(), kh.getDiachi(), kh.getSDT(), kh.getThanhpho(), kh.getQuocgia(), kh.getMaKH());
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }
}
