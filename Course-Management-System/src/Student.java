import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbmsconnector.Dbms;

public class Student {
    Dbms dbms = new Dbms();



    public void studentHome() {
        Main mains = new Main();
        Admin admin = new Admin();
        Student student = new Student();
        Instructor instructor = new Instructor();

        System.out.println("\n***** Student Home*****");
        System.out.println("1. New Student Registration");
        System.out.println("2. View Instructor/Module they are on");
        System.out.println("3. Back to Main Home");
        System.out.println("4. Exit");
        System.out.print("Enter your answer-> ");
        Scanner input3 = new Scanner(System.in);
        int select3 = input3.nextInt();

        if(select3==1){
            registerStudent();
        }
        else if(select3==2){
            viewIorM();
        }
        else if(select3==3){
            mains.homePage();
        }
        else if(select3==4){
            mains.exit();
        }
        else{
            System.out.println("\nInvalid choice!!! Please enter again.");
            studentHome();
        }

    }
// View registered student detail
    public void viewIorM() {
        System.out.print("Enter your name to view Registered student: ");
        Scanner snm = new Scanner(System.in);
        String studentName = snm.next();

        registeredStudent(studentName);
    }
    public void registeredStudent(String stdName) {
        try {
            ResultSet result = dbms.getStudent();
            while (result.next()) {
                Integer StudentID = result.getInt("StudentID");
                String StudentName = result.getString("studentName");
                String CourseName = result.getString("CourseName");
                String courseLevel = result.getString("CourseLevel");
                if(stdName.equals(StudentName)) {
                    System.out.format("StudentID: %s\nFirstName: %s\nCourseEnrolled: %s\nCourseLevel: %s",StudentID, StudentName, CourseName, courseLevel);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        studentHome();
    }

    public void registerStudent(){
        System.out.println("\n*** Student Registration Page ***");
        System.out.println("Courses available are: ");
        viewCourse();
        System.out.println("Fill the details below:");
        System.out.print("Enter new Student ID: ");
        Scanner sd = new Scanner(System.in);
        int studentId = sd.nextInt();
        System.out.print("Enter Student name: ");
        Scanner sn = new Scanner(System.in);
        String studentName = sn.next();
        System.out.print("Enter the course name: ");
        Scanner cn = new Scanner(System.in);
        String courseName = cn.next();
        System.out.print("Enter Level: ");
        Scanner lvl = new Scanner(System.in);
        int courseLevel = lvl.nextInt();

        if (courseLevel == 4){
            System.out.println("*** LEVEL 4 MODULES ***");
            System.out.println("1)Personal Development\n2)Web Technologies\n3)Data Structures\n4)Introduction To Programming-I\n5)Introduction To DatabaseSystems\n");
            System.out.println("===================================================");
            dbms.insertIntoStudent(studentId,studentName, courseName, courseLevel);
        }
        else if(courseLevel == 5)
        {
            System.out.println("*** LEVEL 5 MODULES ***");
            System.out.println("1)Object Oriented Programming\n2)Collaborative Development\n3)Software Engineering\n4)Numerical Methods and Concurrency\n5)Cloud System Programming");
            System.out.println("===================================================");
            dbms.insertIntoStudent(studentId,studentName, courseName, courseLevel);
        }
       else if(courseLevel == 6)
        {
            System.out.println("1)Optional Modules\n2)Compulsory Modules");
            System.out.println("Enter your choice:");
            Scanner co = new Scanner(System.in);
            int opt = co.nextInt();
            if (opt == 1)
            {
                System.out.println("*** LEVEL 6 OPTIONAL MODULES ***");
                System.out.println("1)Artificial Intelligence and Machine Learning\n2)Complex Systems");
                System.out.println("===============================================");
                dbms.insertIntoStudent(studentId,studentName, courseName, courseLevel);
            }
            else if (opt == 2)
            {
                System.out.println("*** LEVEL 6 COMPULSORY MODULES ***");
                System.out.println("1)Big Data\n2)Project Professionalism\n3)High Performance Computing");
                System.out.println("===============================================");
                dbms.insertIntoStudent(studentId,studentName, courseName, courseLevel);
            }
            else{
                System.out.println("\nInvalid choice!!! Please enter again.");
                registerStudent();
            }
        }
       else{
            System.out.println("\nInvalid choice!!! Please enter again.");
            registerStudent();
        }
       studentHome();
    }



    public void viewCourse(){
        try {
            ResultSet result = dbms.getCourse();
            while (result.next()) {
                Integer CourseId = result.getInt("CourseId");
                String CourseName = result.getString("CourseName");
                String status = result.getString("courseStatus");
                int stats = Integer.parseInt(status);
                if(stats == 1){
                    status = "Available";
                }
                if(stats == 0){
                    status = "Canceled";
                }
                System.out.format("CourseId: %s, CourseName: %s, courseStatus: %s\n", CourseId, CourseName, status);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


}
