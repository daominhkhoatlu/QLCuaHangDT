/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ChiTietHD {
    private String MaHD;
    private String MaHang;
    private int SoLuong;
    private int GiamGia;
    private int ThanhTien;

    public ChiTietHD() {
    }

    public ChiTietHD(String MaHD, String MaHang, int SoLuong, int GiamGia, int ThanhTien) {
        this.MaHD = MaHD;
        this.MaHang = MaHang;
        this.SoLuong = SoLuong;
        this.GiamGia = GiamGia;
        this.ThanhTien = ThanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
