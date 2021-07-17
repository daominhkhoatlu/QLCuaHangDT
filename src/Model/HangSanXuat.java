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
public class HangSanXuat {
    private String MaHangSanXuat;
    private String TenHangSanXuat;

    public String getMaHangSanXuat() {
        return MaHangSanXuat;
    }

    public void setMaHangSanXuat(String MaHangSanXuat) {
        this.MaHangSanXuat = MaHangSanXuat;
    }

    public String getTenHangSanXuat() {
        return TenHangSanXuat;
    }

    public void setTenangSanXuat(String TenHangSanXuat) {
        this.TenHangSanXuat = TenHangSanXuat;
    }

    public HangSanXuat(String MaHangSanXuat, String TenHangSanXuat) {
        this.MaHangSanXuat = MaHangSanXuat;
        this.TenHangSanXuat = TenHangSanXuat;
    }

    
    
}
