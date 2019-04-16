package com.pojo;

public class Activity {
    int id;
    int affair;
    int priority;
    String date;
    String detail;
    boolean isSolved;

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    String solve;
    public void solveString(){
        if (this.isSolved){
            solve = "已完成";
        }else{
            solve = "待解决";
        }
    }
    String type;
    public void typeString(){
        if(this.affair == 0){
            type = "补货";
        }else{
            type = "订单";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAffair() {
        return affair;
    }

    public void setAffair(int affair) {
        this.affair = affair;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
