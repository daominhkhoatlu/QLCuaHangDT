/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HangSanXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HangSanXuatDataBase {
    public ArrayList<HangSanXuat> getHangSanXuat(){
        ArrayList<HangSanXuat> hsx=new ArrayList<>();
        Connection cn=DB_Connection.getCon();
        String sql="select * from HangSX";
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                HangSanXuat h=new HangSanXuat(rs.getString("ID_HangSX"),rs.getString("TenHangSX"));
                hsx.add(h);
            }
            cn.close();
        } catch (Exception e) {
        }
        return hsx;
    }
    
    public void InsertHangSanXuat(HangSanXuat hangSanXuat){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("insert into HangSX values('%s','%s')", 
                hangSanXuat.getMaHangSanXuat(),hangSanXuat.getTenHangSanXuat());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
        } catch (Exception e) {
        }
    }
    
    public boolean DeleteHangSanXuat(String MaHangSanXuat){
        Connection cn=DB_Connection.getCon();
        String sql="delete from HangSX where ID_HangSX = '"+MaHangSanXuat+"'";
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public String getTenHangSanXuat(String MaHangSanXuat){
        Connection cn=DB_Connection.getCon();
        String sql="select TenHangSX from HangSX where ID_HangSX = '"+MaHangSanXuat+"'";
        String TenHangSanXuat="";
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                TenHangSanXuat=rs.getString("TenHangSX");
            }
            cn.close();
        } catch (Exception e) {
        }
        return TenHangSanXuat;
    }

   public void UpdateHangSanXuat(HangSanXuat hsx){
       Connection cn=DB_Connection.getCon();
       String sql=String.format("update HangSX set TenHangSX = N'%s' where ID_HangSX='%s'", hsx.getTenHangSanXuat(),hsx.getMaHangSanXuat());
       try {
           PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
           ps.executeUpdate();
           cn.close();
       } catch (Exception e) {
       }
   }
}
