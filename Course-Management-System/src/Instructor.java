import dbmsconnector.Dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Instructor {
    Dbms dbms = new Dbms();


    public void instructorHome(){
        Main main = new Main();
   //     Admin admin = new Admin();
    //    Student student = new Student();
        Instructor instructor = new Instructor();

        System.out.println("\n***** Instructor Home*****");
        System.out.println("1. View modules");
        System.out.println("2. Add Marks");
        System.out.println("3. Back to Main Home");
        System.out.println("4. Exit");
        System.out.print("Enter your answer-> ");
        Scanner input2 = new Scanner(System.in);
        int select2 = input2.nextInt();

        if(select2==1){
            instructor.viewModules();
        }
        else if(select2==2){
            instructor.addMarks();
        }
        else if(select2==3){
            main.homePage();
        }
        else if(select2==4){
            main.exit();
        }
        else{
            System.out.println("\nInvalid choice!!! Please enter again.");
            instructorHome();
        }


    }

    public void addMarks() {
        Scanner siD = new Scanner(System.in);
        Scanner miD = new Scanner(System.in);
        Scanner mN = new Scanner(System.in);
        Scanner mM = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh != 'n') {
            System.out.print("Enter Student Id:");
            int studentId = siD.nextInt();
            System.out.print("Enter Module Id:");
            int moduleId = miD.nextInt();
            System.out.print("Enter Module Name:");
            String moduleName = mN.nextLine();
            System.out.print("Enter Module Mark:");
            int moduleMark  = mM.nextInt();
            dbms.insertMarks(studentId, moduleId, moduleName,moduleMark);

            System.out.print("Add more Marks?([y]/[n]) ");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);
        }
        instructorHome();
    }

    public void viewModules() {
        try {
            ResultSet result = dbms.getModule();
            while (result.next()) {
                Integer ModuleId = result.getInt("moduleId");
                String ModuleName = result.getString("moduleName");
                Integer CourseId = result.getInt("courseId");
                String CourseName = result.getString("courseName");
                System.out.format("moduleId: %d, moduleName: %s, courseId: %d, courseName: %s\n", ModuleId, ModuleName, CourseId, CourseName);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        instructorHome();
    }
}
