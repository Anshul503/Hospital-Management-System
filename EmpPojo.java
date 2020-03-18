
package sanjeevani.pojo;

import java.util.Objects;

public class EmpPojo {
    private String empid;
    private String empname;
    private String job;
    private double sal;

    
    @Override
    public String toString() {
        return "EmpPojo{" + "empid=" + empid + ", empname=" + empname + ", job=" + job + ", sal=" + sal + '}';
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
      
}
