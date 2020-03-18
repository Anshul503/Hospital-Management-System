
package sanjeevani.pojo;

public class DoctorPojo {
    private String userid;
    private String doctorid;
    private String qualification;
    private String specialist;
    private String isActive;

    public DoctorPojo() {
    }

    public DoctorPojo(String userid, String doctorid, String qualification, String specialist, String isActive) {
        this.userid = userid;
        this.doctorid = doctorid;
        this.qualification = qualification;
        this.specialist = specialist;
        this.isActive = isActive;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "DoctorPojo{" + "userid=" + userid + ", doctorid=" + doctorid + ", qualification=" + qualification + ", specialist=" + specialist + ", isActive=" + isActive + '}';
    }
  
}
   
