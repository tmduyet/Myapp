package com.example.myapp;

import android.net.Uri;

public class Sach {
    private String Tensach;
    private String Tacgia;
    private String Theloai;
    private String Anh;
    private float Gia;
    private int Soluong;
    private String Mota;

    public Sach() {
    }

    public Sach(String tensach, String tacgia, String theloai, String anh, float gia, int soluong, String mota) {
        Tensach = tensach;
        Tacgia = tacgia;
        Theloai = theloai;
        Anh = anh;
        Gia = gia;
        Soluong = soluong;
        Mota = mota;
    }

    public String getTensach() {
        return Tensach;
    }

    public String getTacgia() {
        return Tacgia;
    }

    public String getTheloai() {
        return Theloai;
    }

    public String getAnh() {
        return Anh;
    }

    public float getGia() {
        return Gia;
    }

    public int getSoluong() {
        return Soluong;
    }

    public String getMota() {
        return Mota;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    public void setTheloai(String theloai) {
        Theloai = theloai;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public void setGia(float gia) {
        Gia = gia;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
