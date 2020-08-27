package com.example.myapp;

public class Donhang {


    private String Tensach;
    private String Ngaydat;
    private int Soluong;
    private int Tongtienct;
    private String Trangthai;

    public Donhang() {
    }

    public Donhang( String tensach, String ngaydat, int soluong, int tongtienct, String trangthai) {
        Tensach = tensach;
        Ngaydat = ngaydat;
        Soluong = soluong;
        Tongtienct = tongtienct;
        Trangthai = trangthai;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String trangthai) {
        Trangthai = trangthai;
    }


    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getNgaydat() {
        return Ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        Ngaydat = ngaydat;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getTongtienct() {
        return Tongtienct;
    }

    public void setTongtienct(int tongtienct) {
        Tongtienct = tongtienct;
    }
}
