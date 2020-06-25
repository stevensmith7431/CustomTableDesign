package com.example.customtabledesign;

public class MyList {

    String studno,name,team,dept,mobile,city,leavedate,session,purpose,remarks;

    public MyList(String studno, String name, String team, String dept, String mobile, String city, String leavedate, String session, String purpose, String remarks) {
        this.studno = studno;
        this.name = name;
        this.team = team;
        this.dept = dept;
        this.mobile = mobile;
        this.city = city;
        this.leavedate = leavedate;
        this.session = session;
        this.purpose = purpose;
        this.remarks = remarks;
    }

    public String getStudno() {
        return studno;
    }

    public void setStudno(String studno) {
        this.studno = studno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
