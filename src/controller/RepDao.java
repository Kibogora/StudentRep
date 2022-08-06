package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AcademicStructure;
import model.StudentRepresentative;

public class RepDao {
    
    String dbUrl = "jdbc:postgresql://localhost:5432/mid?";
    String dbUsername = "postgres";
    String dbPasswd = "postgre";
    Connection conn = null;
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    
    public void saveRepresentative(StudentRepresentative sr){  
        String insertSQL = "INSERT INTO student_representative VALUES(?, ?, ?, ?)";
        Connection myConn = getConnection();
        try {
            ps = myConn.prepareStatement(insertSQL);
            ps.setString(1, sr.getRepId());
            ps.setString(2, sr.getRepFullName());
            ps.setString(3, sr.getRepDob());
            ps.setString(4, sr.getRepMajorMasters());
            ps.executeUpdate();            
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            getDisconnection();
        }
    }
    
    public StudentRepresentative getStud(String repId){
        StudentRepresentative sr = null;
        try {
            String selectSQL = "SELECT * FROM student_representative WHERE id = ?";
            Connection myConn = getConnection();
            ps = myConn.prepareStatement(selectSQL);
            ps.setString(1, repId);
            rs = ps.executeQuery();
            while(rs.next()){
                sr = new StudentRepresentative();
                sr.setRepId(rs.getString("id"));
                sr.setRepFullName(rs.getString("full_name"));
                sr.setRepDob(rs.getString("birth_date"));
                sr.setRepMajorMasters(rs.getString("major_masters"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            getDisconnection();
        }
        return sr;
    }
    
       public int deleteStud(String repId){
        StudentRepresentative sr = null;
        int deleted = 0;
        try {
            String selectSQL = "DELETE FROM student_representative WHERE id = ?";
            Connection myConn = getConnection();
            ps = myConn.prepareStatement(selectSQL);
            ps.setString(1, repId);
            deleted = ps.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            getDisconnection();
        }
        return deleted;
    }
    
    public void updateStud(){
        String updateSQL = "UPDATE student_representative "
                + "SET full_name = ?, birth_date = ?, major_masters = ?"
                + "WHERE id = ?";
    }
    
    
    
    public List<AcademicStructure> getProgrammes(){
        List<AcademicStructure> programmeList = new ArrayList<>();
        try {
            String selectSQL = "SELECT * FROM academic_structure WHERE level = ?";
            Connection myConn = getConnection();            
            ps = myConn.prepareStatement(selectSQL);
            ps.setString(1, "Programme");
            rs = ps.executeQuery();
            while(rs.next()){
                AcademicStructure as = new AcademicStructure();
                as.setAcaId(rs.getString("id"));
                as.setAcaName(rs.getString("name"));
                as.setAcaLevel(rs.getString("level"));
                as.setAcaParent(rs.getString("parent"));
                programmeList.add(as);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            getDisconnection();
        }
        return programmeList;
    }
    
    public List<String> getFacultyMasters(String selectedProgramme){
        List<String> facultyMastersList = new ArrayList<>();
        String selectSQL = "SELECT name FROM academic_structure WHERE level = ?";
        if(selectedProgramme.equals("Undergraduate")){
            try {
                Connection myConn = getConnection();
                ps = myConn.prepareStatement(selectSQL);
                ps.setString(1, "Faculty");
                rs = ps.executeQuery();
                while(rs.next()){
                    facultyMastersList.add(rs.getString("name"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                getDisconnection();
            }
        }else if(selectedProgramme.equals("Graduate")){
            try {
                Connection myConn = getConnection();
                ps = myConn.prepareStatement(selectSQL);
                ps.setString(1, "Masters");
                rs = ps.executeQuery();
                while(rs.next()){
                    facultyMastersList.add(rs.getString("name"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                getDisconnection();
            }
        }
        return facultyMastersList;
    }
    
    public List<String> getMajor(String selectedFacMast){
        Connection myConn = getConnection();
        String parentId = "";
        List<String> majorsList = new ArrayList<>();
        try {
            String selectSQL = "SELECT id FROM academic_structure WHERE name = ?";
            ps = myConn.prepareStatement(selectSQL);
            ps.setString(1, selectedFacMast);
            rs = ps.executeQuery();
            if(rs.next()){
                parentId = rs.getString("id");
            }
            selectSQL = "SELECT name FROM academic_structure WHERE parent = ?";
            ps = myConn.prepareStatement(selectSQL);
            ps.setString(1, parentId);
            rs = ps.executeQuery();
            while(rs.next()){
                majorsList.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            getDisconnection();
        }
        return majorsList;
    }
    
    public Connection getConnection(){
        try {    
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPasswd);
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public void getDisconnection(){
        try {
            if(st != null)
                st.close();
            if(ps != null)
                ps.close();
            if(rs != null)
                rs.close();
            if(conn != null)
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
