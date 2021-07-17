/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDonDataBase {
    DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
     public Date addDaysToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, hours);
        return calendar.getTime();
    }
    public void insertHoaDon(HoaDon hd){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("insert into HoaDon values('%s','%s','%s','%s',%d)",
                hd.getMaHD(),hd.getMaNV(),df.format(hd.getNgayBan()),hd.getMaKhach(),hd.getTongTien());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
        }
    }
    
    public ArrayList<HoaDon> getHoaDon(){
        Connection cn=DB_Connection.getCon();
        String sql="select * from HoaDon";
        ArrayList<HoaDon> hoaDons=new ArrayList<>();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Date date=addDaysToJavaUtilDate(rs.getDate("NgayBan"), 0);
                HoaDon hd=new HoaDon(rs.getString("MaHD"), rs.getString("MaNV"), rs.getString("MaKH"), date, rs.getInt("TongTien"));
                hoaDons.add(hd);
            }
            cn.close();
        } catch (Exception e) {
        }
        return hoaDons;
    }
    
    public HoaDon getHoaDon(String MaHD){
        Connection cn=DB_Connection.getCon();
        String sql="select * from HoaDon where MaHD = '"+MaHD+"'";
        HoaDon hoaDon=new HoaDon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Date date=addDaysToJavaUtilDate(rs.getDate("NgayBan"), 0);
                HoaDon hd=new HoaDon(rs.getString("MaHD"), rs.getString("MaNV"), rs.getString("MaKH"), date, rs.getInt("TongTien"));
                hoaDon=hd;
            }
            cn.close();
        } catch (Exception e) {
        }
        return hoaDon;
    }
    
    public boolean deleteHD(String MaHD){
        Connection cn=DB_Connection.getCon();
        String sql="delete from HoaDon where MaHD ='"+MaHD+"'";
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean UpdateHD(HoaDon hd){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("update HoaDon set MaKH='%s' where MaHD = '%s'", hd.getMaKhach(),hd.getMaHD()) ;
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public int TongSoHD(String Ngay1,String Ngay2){
        
        Connection cn=DB_Connection.getCon();
        int TongSo=0;
        String sql=String.format("select dbo.UF_HoaDonTheoNgay ('%s', '%s') as 'SoLuong' ", Ngay1,Ngay2);
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                TongSo= rs.getInt("SoLuong");
            }
            cn.close();
        } catch (Exception e) {
            return 0;
        }
        return TongSo;
    }
    
    public int DoanhThu(String Ngay1,String Ngay2){
        
        Connection cn=DB_Connection.getCon();
        int TongSo=0;
        String sql=String.format("select dbo.Fun_DoanhThu_TheoNgay ('%s', '%s') as 'DoanhThu' ", Ngay1,Ngay2);
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                TongSo= rs.getInt("DoanhThu");
            }
            cn.close();
        } catch (Exception e) {
            return 0;
        }
        return TongSo;
    }
    public int LoiNhuan(String Ngay1,String Ngay2){
        Connection cn=DB_Connection.getCon();
        int LoiNhuan=0;
        String sql=String.format("select dbo.Fun_LoiNhuan_TheoNgay ('%s', '%s') as 'LoiNhuan'", Ngay1,Ngay2);
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                LoiNhuan= rs.getInt("LoiNhuan");
            }
            cn.close();
        } catch (Exception e) {
            return 0;
        }
        return LoiNhuan;
    }
}
