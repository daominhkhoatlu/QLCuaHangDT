/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class NhanVienDataBase {
    DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
    public Date addDaysToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, hours);
        return calendar.getTime();
    }
    
   
    public boolean InsertNhanVien(NhanVien nv){
        Connection cn=DB_Connection.getCon();  
        String sql=String.format("insert into NhanVien(MaNV,HoTen,GioiTinh,NgaySinh,DiaChi,SDT,ChucVu,Luong)"
                + "values('%s',N'%s',N'%s','%s',N'%s','%s',N'%s','%f')", nv.getMaNV(),nv.getTenNV(),nv.getGioiTinh()
                ,df.format(nv.getNgaySinh()),nv.getDiaChi(),nv.getSDT(),nv.getChucVu(),nv.getLuong());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {     
        }
        return false;
    }
    
    public String getTenNhanVien(String MaNV){
        Connection cn=DB_Connection.getCon();
        String sql="select HoTen from NhanVien where MaNV ='"+MaNV+"'";
        
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString("HoTen");
            }
        } catch (Exception e) {
        }
        return "";
    }
    
    public ArrayList<NhanVien> GetNhanVien(){
        
        Connection cn=DB_Connection.getCon();
        ArrayList<NhanVien> nhanViens=new ArrayList<>();
        String sql="select *from NhanVien";
        try{
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Date date=addDaysToJavaUtilDate(rs.getDate("NgaySinh"), 0);
                NhanVien nv=new NhanVien(rs.getString("MaNV"), rs.getString("HoTen"), rs.getString("GioiTinh"), 
                        date, rs.getString("DiaChi"), rs.getString("SDT"),rs.getString("ChucVu"),rs.getDouble("Luong"));
                nhanViens.add(nv);
            }
            cn.close();
            
        }
        catch(Exception e){
            
        }
        return nhanViens;
    }
    
    
    
    public boolean UpdateNhanVien(NhanVien nv){
        Connection cn=DB_Connection.getCon();
        String sql=String.format("Update NhanVien set HoTen =N'%s',GioiTinh =N'%s',NgaySinh='%s'"
                + ",DiaChi=N'%s',SDT='%s',ChucVu=N'%s' where MaNV='%s'", nv.getTenNV(),nv.getGioiTinh(),
                df.format(nv.getNgaySinh()),nv.getDiaChi(),nv.getSDT(),nv.getChucVu(),nv.getMaNV());
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement(sql);
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean DeleteNhanVien(String MaNV){
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("delete from NhanVien where MaNV = '"+MaNV+"'");
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public int IndexNhanVien(){
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareStatement("select COUNT(MaNV) as So_NV from NhanVien");
            ResultSet rs=ps.executeQuery();
            int index=0;
            while(rs.next()){
                index= rs.getInt("So_NV");
            }
           
            return index;
        } catch (Exception e) {
            return -1;
        }
    }
    
   
    public int ID(String s){
        String a="";
        for(int i=0;i<s.length();i++ ){
            if(Character.isDigit(s.charAt(i))){
                a+=String.valueOf(s.charAt(i));
            }
        }
        return Integer.parseInt(a);
    }
    
    public int getID(int n,Vector<String> v_ID){
        boolean exist=false;
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=(i+1);
        }
        int ID=0;
        int dem=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(ID(v_ID.get(j))==a[i]){
                    exist=true;
                    dem++;
                    break;
                }
                else{
                    exist=false;
                }
            }
            if(!exist){
                ID=a[i];
                break;
            }
        }
        
        if(n==dem){
            return (n+1);
        }
        return ID;
    }
    
    public  Vector<String> v_ID_NV(ArrayList<NhanVien> list){
        Vector<String> v=new Vector<>();
        for(NhanVien nv:list){
            v.add(nv.getMaNV());
        }
        return v;
    }
}
