package com.example.spacetrader.entity;

import java.io.Serializable;

public class Good implements Serializable {
    private String name;
    private int mtlp;
    private int mtlu;
    private int ttp;
    private int ipl;
    private int var;
    private String ie;
    private String cr;
    private String er;
    private int mtl;
    private int mth;

    public Good(String name, int mtlp, int mtlu,
                int ttp, int ipl, int var, String ie,
                String cr, String er, int mtl, int mth) {
        this.name = name;
        this.mtlp = mtlp;
        this.mtlu = mtlu;
        this.ttp = ttp;
        this.ipl = ipl;
        this.var = var;
        this.ie = ie;
        this.cr = cr;
        this.er = er;
        this.mtl = mtl;
        this.mth = mth;
    }

    public String getName() {return name;}
    public int getMtlp() {return mtlp;}
    public int getMtlu() {return mtlu;}
    public int getTtp() {return ttp;}
    public int getIpl() {return ipl;}
    public int getVar() {return var;}
    public String getIe() {return ie;}
    public String getCr() {return cr;}
    public String getEr() {return er;}
    public int getMtl() {return mtl;}
    public int getMth() {return mth;}

    public void setName(String name) {this.name = name;}
    public void setMtlp(int mtlp) {this.mtlp = mtlp;}
    public void setMtlu(int mtlu) {this.mtlu = mtlu;}
    public void setTtp(int ttp) {this.ttp = ttp;}
    public void setIpl(int ipl) {this.ipl = ipl;}
    public void setVar(int var) {this.var = var;}
    public void setIe(String ie) {this.ie = ie;}
    public void setCr(String cr) {this.cr = cr;}
    public void setEr(String er) {this.er = er;}
    public void setMtl(int mtl) {this.mtl = mtl;}
    public void setMth(int mth) {this.mth = mth;}
}
