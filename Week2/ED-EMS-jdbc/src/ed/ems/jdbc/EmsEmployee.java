/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.ems.jdbc;

/**
 *
 * @author iOSDev
 */
public class EmsEmployee {

    private String empid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String secqn;
    private String secans;
    private String bsbid;
    private String accid;
    private double salary;
    private String appgroup;
    private boolean active;
    private String memo;

    public EmsEmployee() {
        
    }
    
    
    
    public EmsEmployee(String empid, String name, String password, String email, String phone, String address, String secqn, String secans, String bsbid, String accid, double salary, String appgroup, boolean active, String memo) {
        this.empid = empid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.secqn = secqn;
        this.secans = secans;
        this.bsbid = bsbid;
        this.accid = accid;
        this.salary = salary;
        this.appgroup = appgroup;
        this.active = active;
        this.memo = memo;
    }
    
    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecqn() {
        return secqn;
    }

    public void setSecqn(String secqn) {
        this.secqn = secqn;
    }

    public String getSecans() {
        return secans;
    }

    public void setSecans(String secans) {
        this.secans = secans;
    }

    public String getBsbid() {
        return bsbid;
    }

    public void setBsbid(String bsbid) {
        this.bsbid = bsbid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAppgroup() {
        return appgroup;
    }

    public void setAppgroup(String appgroup) {
        this.appgroup = appgroup;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return String.format("EmployeeID: %s, name: %s", this.empid, this.name);//To change body of generated methods, choose Tools | Templates.
    }
    
}
