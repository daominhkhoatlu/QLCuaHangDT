/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HoaDon;
import Model.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class KhachHangDatabase {
    
    public ArrayList<KhachHang> getKhachHang(){
        ArrayList<KhachHang> list_khachHang=new ArrayList<>();
        Connection cn=DB_Connection.getCon();
        String sql="select *from KhachHang";
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                KhachHang kh=new KhachHang(rs.getString("MaKH"), rs.getString("TenKH"),rs.getString("GioiTinh"),
                rs.getString("DiaChi"),rs.getString("SDT") );
                list_khachHang.add(kh);
            }
            cn.close();
        } catch (SQLException e) {
            
        }
        return list_khachHang;
    }
    
    public String getTenKhachHang(String MaKhach){
        Connection cn=DB_Connection.getCon();
        String sql="select TenKH from KhachHang where MaKH ='"+MaKhach+"'";
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString("TenKH");
            }
            cn.close();
        } catch (Exception e) {
        }
        return "";
    }
    
    public boolean InsertKhachHang(KhachHang kh){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("insert into KhachHang values('%s',N'%s',N'%s',N'%s','%s')",
        kh.getMaKhach(),kh.getTenKhach(),kh.getGioiTinh(),kh.getDiaChi(),kh.getSDT());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public int IndexKhachHang(){
        Connection cn=DB_Connection.getCon();
        int n=0;
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("select count(MaKH) as so_Khach from KhachHang");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                n=rs.getInt("So_Khach");
            }
        } catch (Exception e) {
        }
        return n;
    }
    
    public void UpdateKhachHang(KhachHang kh){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("Update KhachHang set TenKH=N'%s',GioiTinh=N'%s',DiaChi=N'%s',SDT='%s' where MaKH='%s'",
        kh.getTenKhach(),kh.getGioiTinh(),kh.getDiaChi(),kh.getSDT(),kh.getMaKhach());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
        }
    }
    
     public boolean DeleteKhachHang(String kh){
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("delete from KhachHang where MaKH = '"+kh+"'");
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
     
}
