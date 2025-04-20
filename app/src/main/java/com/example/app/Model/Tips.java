package com.example.app.Model;

import java.io.Serializable;

public class Tips implements Serializable {
    String tid,tip;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
