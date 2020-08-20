package com.example.myapp;

public class Cart {

    private String Tensach;
    private String Anh;
    private  int Soluong;
    private int gia;

    public Cart() {
    }

    public Cart(String tensach, String anh, int soluong, int gia) {
        Tensach = tensach;
        Anh = anh;
        Soluong = soluong;
        this.gia = gia;
    }

    public String getTensach() {
        return Tensach;
    }

    public String getAnh() {
        return Anh;
    }

    public int getSoluong() {
        return Soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
