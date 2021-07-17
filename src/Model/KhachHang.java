/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class KhachHang {
    private String MaKhach;
    private String TenKhach;
    private String GioiTinh;
    private String DiaChi;
    private String SDT;
   

    public KhachHang() {
    }

    public KhachHang(String MaKhach, String TenKhach, String GioiTinh,String DiaChi, String SDT) {
        this.MaKhach = MaKhach;
        this.TenKhach = TenKhach;
        this.GioiTinh=GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        
    }

    public String getMaKhach() {
        return MaKhach;
    }

    public void setMaKhach(String MaKhach) {
        this.MaKhach = MaKhach;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getTenKhach() {
        return TenKhach;
    }

    public void setTenKhach(String TenKhach) {
        this.TenKhach = TenKhach;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    
    
}
