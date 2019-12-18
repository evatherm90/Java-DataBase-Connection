/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentpartb;

import Classes.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;

public class AssignmentPartB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        DatabaseConnection dbcon = new DatabaseConnection();

        String keyWord = "cont";
        while (keyWord.equalsIgnoreCase("cont")) {
            System.out.println("Hello! if you want to make an entry");
            System.out.println("press 1");
            System.out.println("if you want to print");
            System.out.println("press 2");
            System.out.println("if you want to leave");
            System.out.println("press any other key");

            Scanner e = new Scanner(System.in);
            String endoli = e.nextLine();

            switch (endoli) {
                case "1"://register something

                    System.out.println("if you want to register a trainer press 1");
                    System.out.println("if you want to register a course press 2");
                    System.out.println("if you want to register a student press 3");
                    System.out.println("if you want to register an assignment press 4");
                    System.out.println("to register a student in a course press 5");
                    System.out.println("to assigne a trainer to a course press 6");
                    Scanner r = new Scanner(System.in);
                    String register = r.nextLine();
                    switch (register) {
                        case "1"://trainer
                            dbcon.selectTrainers();
                            System.out.println("insert id that does not exist");
                            Scanner t1 = new Scanner(System.in);
                            int tid = t1.nextInt();
                            System.out.println("insert last name");
                            Scanner t2 = new Scanner(System.in);
                            String tlast_name = t2.nextLine();
                            System.out.println("insert first name");
                            Scanner t3 = new Scanner(System.in);
                            String tfirst_name = t3.nextLine();
                            System.out.println("insert subject");
                            Scanner t4 = new Scanner(System.in);
                            String tsubject = t4.nextLine();
                            dbcon.insertTrainer(tid, tlast_name, tfirst_name, tsubject);
                            break;
                        case "2"://course
                            dbcon.selectCourses();
                            System.out.println("insert id that does not exist");
                            Scanner c1 = new Scanner(System.in);
                            int cid = c1.nextInt();
                            System.out.println("insert title");
                            Scanner c2 = new Scanner(System.in);
                            String ctitle = c2.nextLine();
                            System.out.println("insert stream");
                            Scanner c3 = new Scanner(System.in);
                            String cstream = c3.nextLine();
                            System.out.println("insert start date like yyyy-mm-dd");
                            Scanner c4 = new Scanner(System.in);
                            String cstart_date = c4.nextLine();
                            System.out.println("insert end date like yyyy-mm-dd");
                            Scanner c5 = new Scanner(System.in);
                            String cend_date = c5.nextLine();
                            System.out.println("insert type");
                            Scanner c6 = new Scanner(System.in);
                            String ctype = c6.nextLine();
                            dbcon.insertCourse(cid, ctitle, cstream, cstart_date, cend_date, ctype);
                            break;
                        case "3"://student

                            dbcon.selectStudents();
                            System.out.println("insert id that does not exist");
                            Scanner s1 = new Scanner(System.in);
                            int sid = s1.nextInt();
                            System.out.println("insert last name");
                            Scanner s2 = new Scanner(System.in);
                            String slast_name = s2.nextLine();
                            System.out.println("insert first name");
                            Scanner s3 = new Scanner(System.in);
                            String sfirst_name = s3.nextLine();
                            System.out.println("insert date of birth like: yyyy-mm-dd");
                            Scanner s4 = new Scanner(System.in);
                            String sdate_of_birth = s4.nextLine();
                            System.out.println("insert tuition fees decimal amount");
                            Scanner s5 = new Scanner(System.in);
                            double stuition_fees = s5.nextDouble();
                            dbcon.insertStudent(sid, slast_name, sfirst_name, sdate_of_birth, stuition_fees);

                            break;
                        case "4"://assignment
                            dbcon.selectAssignments();
                            System.out.println("insert id that does not exist");
                            Scanner a1 = new Scanner(System.in);
                            int aid = a1.nextInt();
                            System.out.println("insert title");
                            Scanner a2 = new Scanner(System.in);
                            String atitle = a2.nextLine();
                            System.out.println("insert description");
                            Scanner a3 = new Scanner(System.in);
                            String adescription = a3.nextLine();
                            System.out.println("insert sub date");
                            Scanner a4 = new Scanner(System.in);
                            String asub_date = a4.nextLine();
                            System.out.println("insert oral mark ");
                            Scanner a5 = new Scanner(System.in);
                            int oral_mark = a5.nextInt();
                            System.out.println("insert total mark");
                            Scanner a6 = new Scanner(System.in);
                            int total_mark = a6.nextInt();
                            dbcon.selectCourses();
                            System.out.println("insert course id ");
                            Scanner a7 = new Scanner(System.in);
                            int acourse_id = a7.nextInt();

                            dbcon.insertAssignments(aid, atitle, adescription, asub_date, oral_mark, total_mark, acourse_id);
                            break;
                        case "5"://students per course

                            dbcon.selectStudents();
                            System.out.println("select student id");
                            Scanner s = new Scanner(System.in);
                            int stid = s.nextInt();
                            dbcon.selectCourses();
                            System.out.println("select course id");
                            Scanner c = new Scanner(System.in);
                            int coid = c.nextInt();
                            dbcon.setStudentInACourse(stid, coid);
                            break;

                        case "6"://trainers per course
                            dbcon.selectTrainers();
                            System.out.println("select trainer id");
                            Scanner t = new Scanner(System.in);
                            int trid = t.nextInt();
                            dbcon.selectCourses();
                            Scanner co = new Scanner(System.in);
                            int corid = co.nextInt();
                            dbcon.setTrainerInACourse(trid, corid);
                            break;

                        default:
                            System.out.println("Not a valid request!");
                            break;

                    }

                    break;

                case "2"://print smth

                    System.out.println("to print all trainers press 1");
                    System.out.println("to print all courses press 2");
                    System.out.println("to print all trainers per course press 3");
                    System.out.println("to print all students press 4");
                    System.out.println("to print all students per course press 5");
                    System.out.println("to print all assignments press 6");
                    System.out.println("to print all assignments per course press 7");
                    System.out.println("to print all assignments per student press 8");
                    System.out.println("to print all students assignmed to more than one course press 9");
                    Scanner p = new Scanner(System.in);

                    String print = p.nextLine();

                    switch (print) {
                        case "1":
                            System.out.println("trainer's id, last name,  first name,   subject:");
                            dbcon.selectTrainers();
                            break;
                        case "2":
                            System.out.println("course's id,  title,  stream, start date, end date, type:");
                            dbcon.selectCourses();
                            break;
                        case "3":
                            System.out.println("course, trainer's last name, first name:");
                            dbcon.selectTrainersPerCourse();
                            break;
                        case "4":
                            System.out.println("student's id, last name, first name, date of birth. tuition fees:");
                            dbcon.selectStudents();
                            break;
                        case "5":
                            System.out.println("course, student's name:");
                            dbcon.selectStudentsPerCourse();
                            break;
                        case "6":
                            System.out.println("id, title, description, sub date, oral mark, total mark");
                            dbcon.selectAssignments();
                            break;
                        case "7":
                            System.out.println("course, assignment's title:");
                            dbcon.selectAssignmentsPerCourses();
                            break;
                        case "8":
                            System.out.println("student's name, assignment's title:");
                            dbcon.selectAssignmentsPerStudents();
                            break;
                        case "9":
                            System.out.println("student;s id and name:");
                            dbcon.selectStudentsInMoreThanOneCourse();
                            break;
                        default:
                            System.out.println("Not a valid request!");

                    }

                    break;
                default:
                    System.out.println("Not a valid request!");
                    break;

            }

            System.out.println("to continue type cont");
            System.out.println("press anything else to leave");
            Scanner kw = new Scanner(System.in);
            keyWord = kw.nextLine();

        }
    }

}
