package com.example.feedback;

import java.io.Serializable;

public class GenDTO implements Serializable {
     private boolean status;
     private String msg;
    private String message;
     private  long rowCount;
     private  Exception ex;

    public GenDTO(boolean status, String msg, Exception ex) {
        this.status = status;
        this.msg = msg;
        this.ex = ex;
    }

    public GenDTO(boolean status, String msg, long rowCount, Exception ex) {
        this.status = status;
        this.msg = msg;
        this.rowCount = rowCount;
        this.ex = ex;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
    public String getMessage() {
        return message;
    }

    public long getRowCount() {
        return rowCount;
    }

    public Exception getException() {
        return ex;
    }

}
