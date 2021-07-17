/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ChiTietHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ChiTietHDDataBase {
    public boolean insertCHTHD(ChiTietHD HD){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("insert into ChiTietHoaDon values('%s','%s',%d,%d,%d)",HD.getMaHD(),HD.getMaHang()
                ,HD.getSoLuong(),HD.getGiamGia(),HD.getThanhTien() );
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean deleteCHTHD(String MaHD ,String MaHang){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("delete from ChiTietHoaDon where MaHD ='%s' and MaHang ='%s'",MaHD,MaHang);
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    public boolean deleteCHTHD(String MaHD ){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("delete from ChiTietHoaDon where MaHD ='%s' ",MaHD);
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    
    
    public ArrayList<ChiTietHD> getChiTietHD(String MaHD){
        ArrayList<ChiTietHD> chiTietHDs=new ArrayList<>();
        Connection cn=DB_Connection.getCon();
        String sql=String.format("Select * from ChiTietHoaDon where MaHD ='%s'",MaHD);
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ChiTietHD cthd=new ChiTietHD(rs.getString("MaHD"), rs.getString("MaMH")
                        ,rs.getInt("SoLuong"), rs.getInt("GiamGia"), rs.getInt("ThanhTien"));
                chiTietHDs.add(cthd);
            }
            cn.close();
        } catch (Exception e) {
        }
        return chiTietHDs;
    }
    
    public boolean updateChiTietHD(ChiTietHD cthd){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("update ChiTietHoaDon set SoLuong =%d,GiamGia=%d where MaHD='%s' and MaMH='%s'" , cthd.getSoLuong()
                ,cthd.getGiamGia(),cthd.getMaHD(),cthd.getMaHang());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
