/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.HangSanXuat;
import Model.Hang;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DB_Process {
    DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
    public Date addDaysToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, hours);
        return calendar.getTime();
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
    
     public int ID_HD(String s){
        return Integer.parseInt(s.substring(12));
    }
    
    public boolean isSDT(String s){
        if(s.length()==10&&s.charAt(0)=='0'){
            for(int i=0;i<s.length();i++){
                if(!Character.isDigit(s.charAt(i))){
                    return false;
                }
            }  
        }
        else{
            return false;
        }
        return true;
    }
    
    public  Vector<String> v_ID_KH(ArrayList<KhachHang> list){
        Vector<String> v=new Vector<>();
        for(KhachHang nv:list){
            v.add(nv.getMaKhach());
        }
        return v;
    }
    public  Vector<String> v_ID_MH(ArrayList<Hang> list){
        Vector<String> v=new Vector<>();
        for(Hang h:list){
            v.add(h.getMaHang());
        }
        return v;
    }
    
    public  Vector<String> v_ID_MHSX(ArrayList<HangSanXuat> list){
        Vector<String> v=new Vector<>();
        for(HangSanXuat cl:list){
            v.add(cl.getMaHangSanXuat());
        }
        return v;
    }
    public Vector<String>v_ID_HD(ArrayList<HoaDon> list){
        Vector<String> v=new Vector<>();
        for(HoaDon hd:list){
            v.add(hd.getMaHD());
        }
        return v;
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
    
    public boolean isUser(String id,String pass,String chucvu){
        NhanVienDataBase nvdb=new NhanVienDataBase();
        ArrayList<NhanVien> list=nvdb.GetNhanVien();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMaNV().equals(id)&&df.format(list.get(i).getNgaySinh()).equals(pass)&&
                    list.get(i).getChucVu().equals(chucvu)){
                return true;
            }
        }
        return false;
    }
    
    
    public String ID(){
        java.util.Date date=new java.util.Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyMMdd"); 
        String ID="HDB"+formatter.format(date)+"|";
        return ID;
        
    }
    public int getID_HD(int n,Vector<String> v_ID){
        boolean exist=false;
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            a[i]=(i+1);
        }
        int ID=0;
        int dem=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(ID_HD(v_ID.get(j))==a[i]){
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
    
    
    public boolean deleteHD(String MaHD){
        Connection cn=DB_Connection.getCon();
        try {
            PreparedStatement ps=(PreparedStatement)cn.prepareCall("exec sp_Xoa_HoaDon_CTHD'"+MaHD+"'");
            ps.executeUpdate();
            cn.close();
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
