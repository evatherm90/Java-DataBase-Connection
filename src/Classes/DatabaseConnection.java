/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonis
 */
public class DatabaseConnection {

    public Connection conn = null;
    public ResultSet rs = null;
    String url = "jdbc:mysql://localhost/school_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String username = "debian-sys-maint";
    String password = "idXhnuSfpnx4bzT5";

    public void selectStudents() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("SELECT * FROM Students");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getDouble(5));
        }

        rs.close();
        stm.close();
        conn.close();

    }

    public void selectTrainers() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("SELECT * FROM Trainers");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }

        rs.close();
        stm.close();
        conn.close();
    }

    public void selectAssignments() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("SELECT * FROM Assignments");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getInt(5) + " " + rs.getInt(6));
        }

        rs.close();
        stm.close();
        conn.close();
    }

    public void selectCourses() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("SELECT * FROM Courses");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " " + rs.getDate(5) + " " + rs.getString(6));
        }

        rs.close();
        stm.close();
        conn.close();

    }

    public void selectStudentsPerCourse() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("select c.title, s.last_name, s.first_name\n"
                + "from Students s, Courses c, Students_has_Courses shc\n"
                + "where c.course_id=shc.Courses_course_id\n"
                + "and s.student_id=shc.Students_student_id\n"
                + "order by c.course_id;");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ": " + rs.getString(2) + " " + rs.getString(3));
        }

        rs.close();
        stm.close();
        conn.close();
    }

    public void selectAssignmentsPerCourses() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("select c.title, a.title\n"
                + "from Assignments a, Courses c\n"
                + "where a.Courses_course_id=c.course_id\n"
                + "order by c.title;");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ": " + rs.getString(2));
        }

        rs.close();
        stm.close();
        conn.close();

    }

    public void selectTrainersPerCourse() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("select c.title, t.last_name, t.fisrt_name\n"
                + "from Trainers t, Courses c, Trainers_has_Courses thc\n"
                + "where t.trainer_id=thc.Trainers_trainer_id\n"
                + "and c.course_id=thc.Courses_course_id\n"
                + "order by c.title;");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ": " + rs.getString(2) + " " + rs.getString(3));
        }

        rs.close();
        stm.close();
        conn.close();

    }

    public void selectAssignmentsPerStudents() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("select s.last_name, s.first_name, a.title\n"
                + "from Assignments a, Students s, Students_has_Courses shc\n"
                + "where a.Courses_course_id=shc.Courses_course_id\n"
                + "and shc.Students_student_id=s.student_id\n"
                + "order by s.last_name;");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + ", " + rs.getString(3));
        }

        rs.close();
        stm.close();
        conn.close();

    }

    public void selectStudentsInMoreThanOneCourse() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        Statement stm = conn.createStatement();
        rs = stm.executeQuery("SELECT \n"
                + "    s.student_id, s.first_name, s.last_name\n"
                + "FROM\n"
                + "    Students s\n"
                + "WHERE\n"
                + "    (SELECT \n"
                + "            COUNT(shc.Students_student_id) AS Number_of_courses\n"
                + "        FROM\n"
                + "            Students_has_Courses shc\n"
                + "        WHERE\n"
                + "            shc.Students_student_id = s.student_id) > 1;");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }

        rs.close();
        stm.close();
        conn.close();

    }

    public void insertStudent(int id, String last_name, String first_name, String date_of_birth, double tuition_fees) throws SQLException {

        String sql = "INSERT INTO Students VALUES (?,?,?,?,?)";
        conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(1, last_name);
        pstmt.setString(2, first_name);
        pstmt.setString(3, date_of_birth);
        pstmt.setDouble(4, tuition_fees);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public void insertTrainer(int id, String last_name, String first_name, String subject) throws SQLException {

        String sql = "INSERT INTO Trainers VALUES (?,?,?,?)";
        conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, last_name);
        pstmt.setString(3, first_name);
        pstmt.setString(4, subject);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public void insertCourse(int id, String title, String stream, String start_date, String end_date, String type) throws SQLException {

        String sql = "INSERT INTO Courses values (?,?,?,?,?,?)";
        conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, title);
        pstmt.setString(3, stream);
        pstmt.setString(4, start_date);
        pstmt.setString(5, end_date);
        pstmt.setString(6, type);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public void insertAssignments(int id, String title, String description, String sub_date, int oral_mark, int total_mark, int course_id) throws SQLException {

        String sql = "INSERT INTO Assignments values (?,?,?,?,?,?,?)";
        conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, title);
        pstmt.setString(3, description);
        pstmt.setString(4, sub_date);
        pstmt.setInt(5, oral_mark);
        pstmt.setInt(6, total_mark);
        pstmt.setInt(7, course_id);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public void setStudentInACourse(int student_id, int course_id) throws SQLException {

        String sql = "INSERT INTO Students_has_Courses values (?,?)";
        conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, student_id);
        pstmt.setInt(2, course_id);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public void setTrainerInACourse(int trainer_id, int course_id) throws SQLException {

        String sql = "INSERT INTO Trainers_has_Courses values (?,?)";
        conn = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, trainer_id);
        pstmt.setInt(2, course_id);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

}
