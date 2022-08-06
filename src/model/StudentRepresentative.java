package model;

public class StudentRepresentative {
    
    private String repId;
    private String repFullName;
    private String repDob;
    private String repMajorMasters;

    public StudentRepresentative() {
    }

    public StudentRepresentative(String repId, String repFullName, String repDob, String repMajorMasters) {
        this.repId = repId;
        this.repFullName = repFullName;
        this.repDob = repDob;
        this.repMajorMasters = repMajorMasters;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getRepFullName() {
        return repFullName;
    }

    public void setRepFullName(String repFullName) {
        this.repFullName = repFullName;
    }

    public String getRepDob() {
        return repDob;
    }

    public void setRepDob(String repDob) {
        this.repDob = repDob;
    }

    public String getRepMajorMasters() {
        return repMajorMasters;
    }

    public void setRepMajorMasters(String repMajorMasters) {
        this.repMajorMasters = repMajorMasters;
    }
    
    
    
}
