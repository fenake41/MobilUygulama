package com.kodlab.ara_takip_sistemi;

public class Arac {
    public Arac()
    {}
    public Arac(int arac_km,String arac_plaka,int arac_muayene)
    {
        this.arac_km=arac_km;
        this.arac_muayene=arac_muayene;
        this.arac_plaka=arac_plaka;

    }

    public  int arac_km;
    public  int arac_muayene;
    public   String arac_plaka;

    public int getArac_km() {
        return arac_km;
    }

    public void setArac_km(int arac_km) {
        this.arac_km = arac_km;
    }

    public String getArac_plaka() {
        return arac_plaka;
    }

    public void setArac_plaka(String arac_plaka) {
        this.arac_plaka = arac_plaka;
    }

    public int getArac_muayene() {
        return arac_muayene;
    }

    public void setArac_muayene(int arac_muayene) {
        this.arac_muayene = arac_muayene;
    }




}
