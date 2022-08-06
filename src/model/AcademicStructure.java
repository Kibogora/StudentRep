package model;

public class AcademicStructure {
    
    private String acaId;
    private String acaName;
    private String acaLevel;
    private String acaParent;

    public AcademicStructure() {
    }

    public AcademicStructure(String acaId, String acaName, String acaLevel, String acaParent) {
        this.acaId = acaId;
        this.acaName = acaName;
        this.acaLevel = acaLevel;
        this.acaParent = acaParent;
    }

    public String getAcaId() {
        return acaId;
    }

    public void setAcaId(String acaId) {
        this.acaId = acaId;
    }

    public String getAcaName() {
        return acaName;
    }

    public void setAcaName(String acaName) {
        this.acaName = acaName;
    }

    public String getAcaLevel() {
        return acaLevel;
    }

    public void setAcaLevel(String acaLevel) {
        this.acaLevel = acaLevel;
    }

    public String getAcaParent() {
        return acaParent;
    }

    public void setAcaParent(String acaParent) {
        this.acaParent = acaParent;
    }
    
    
    
}
