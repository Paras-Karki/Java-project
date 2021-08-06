import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
import dbmsconnector.Dbms;

public class Admin {
    Dbms dbms = new Dbms();

    public void adminHome(){
        Main main = new Main();
        Admin admin = new Admin();
        Student student = new Student();
        Instructor instructor = new Instructor();

        System.out.println("\n*** Admin Home ***");
        System.out.println("1. Manage Modules");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Instructor");
        System.out.println("4. Generate Result Slip");
        System.out.println("5. Back to Main Home");
        System.out.println("6. Exit");
        System.out.print("Enter your answer-> ");
        Scanner input1 = new Scanner(System.in);
        int select1 = input1.nextInt();

        if(select1 == 1){
             moduleManager();
        }
        else if(select1 == 2){
            courseManager();
        }
        else if(select1 == 3){
            instructorOptions();
        }
        else if(select1 == 4){
            try {
                printReport();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(select1 == 5){
            main.homePage();
        }
        else if(select1 == 6){
            main.exit();
        }
        else {
            System.out.println("\nInvalid choice!!! Please enter again.");
            admin.adminHome();
        }
    }

    public void printReport() throws FileNotFoundException {
        File file = new File("src/Report.txt");
        Scanner sr = new Scanner(file);
        while (sr.hasNextLine()) {
            System.out.println(sr.nextLine());
        }
        adminHome();
    }


    //Instructor related methods below
    public void instructorOptions() {
        System.out.println("\n *** Instructor Manager For Admin *** ");
        System.out.println("1. Add Instructor");
        System.out.println("2. Remove/Change Instructor");
        System.out.println("3. Go Back to Admin Home");
        System.out.print("Enter your answer-> ");
        Scanner options = new Scanner(System.in);
        int optionsC = options.nextInt();

        if (optionsC == 1 ){
             addInstructor();
        }
        else if (optionsC == 2){
            removeInstructor();
        }
        else if (optionsC == 3){
            adminHome();
        }
        else {
            System.out.println("\nInvalid choice!!! Please enter again.");
            instructorOptions();
        }
    }
// remove instructor and replace with changes
    public void removeInstructor() {
        Scanner delI = new Scanner(System.in);
        Scanner nameI = new Scanner(System.in);
        Scanner idi = new Scanner(System.in);

        System.out.print("Enter module id the instructor is current;y on:");
        int ModuleId = delI.nextInt();
        System.out.print("Enter instructor ID:");
        int InstructorId = nameI.nextInt();
        System.out.print("Enter instructor name:");
        String InstructorName = idi.nextLine();
        dbms.deleteInstructor(ModuleId, InstructorId, InstructorName);

        instructorOptions();
    }

    public void addInstructor() {
        Scanner idVar = new Scanner(System.in);
        Scanner instructName = new Scanner(System.in);
        Scanner instructModule = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh != 'n') {
            System.out.print("Enter ID of Instructor:");
            int InstructorId = idVar.nextInt();
            System.out.print("Enter name of Instructor:");
            String InstructorName = instructName.nextLine();
            System.out.print("Enter Module Id relating to instructor:");
            int ModuleId = instructModule.nextInt();
            dbms.insertInstructor(ModuleId,InstructorId,InstructorName);
            System.out.print("Add more Instructor?([y]/[n]) ");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);
        }
        instructorOptions();
    }

    // Course Manager 3 methods expect this one
    public void courseManager() {
        //Add course - Cancel - Delete
        System.out.println("\n ***Course Manager For Admin*** ");
        System.out.println("1. Add Course");
        System.out.println("2. Cancel Course");
        System.out.println("3. Delete Course");
        System.out.println("4. Go Back to Admin Home");
        System.out.print("Enter your answer-> ");
        Scanner options = new Scanner(System.in);
        int optionsC = options.nextInt();

        if (optionsC == 1 ){
            addCourse();
        }
        else if (optionsC == 2){
            cancelCourse();
        }
        else if (optionsC == 3){
             deleteCourse();
        }
        else if (optionsC == 4){
            adminHome();
        }
        else {
            System.out.println("\nInvalid choice!!! Please enter again.");
            courseManager();
        }


    }

    public void deleteCourse() {
        Scanner dCourse = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh !='n')
        {
            System.out.print("Enter Course Id to delete the Course: ");
            int CourseId = dCourse.nextInt();
            dbms.removeCourse(CourseId);
            System.out.print("Delete more Course?([y]/[n])");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);
        }
        courseManager();
    }

    public void cancelCourse() {
        Scanner cn = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh != 'n')
        {
            System.out.print("Enter Course Id to cancel a Course: ");
            int CourseId = cn.nextInt();
            dbms.cancelACourse(CourseId,0);
            System.out.println("Will you cancel more?([y]/[n]) ");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);

        }
        courseManager();
    }

    public void addCourse() {
        Scanner corseId = new Scanner(System.in);
        Scanner corseName = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh != 'n') {
            System.out.print("Enter Course Id you like to add: ");
            int CourseId = corseName.nextInt();
            System.out.print("Enter Course Name you like to add: ");
            String CourseName = corseId.nextLine();
            dbms.insertCourse(CourseId,CourseName,1);
            System.out.println("Will you add more?([y]/[n]) ");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);
        }
        courseManager();
    }


    // Module Manager 2 methods below - add and edit modules

    public void moduleManager() {
        System.out.println("\n ***Module Manager For Admin*** ");
        System.out.println("1. Add Module");
        System.out.println("2. Edit Module");
        System.out.println("3. Go Back to Admin Home");
        System.out.print("Enter your answer-> ");
        Scanner inputM = new Scanner(System.in);
        int choiceM = inputM.nextInt();
        if (choiceM == 1 ){
            addModule();
        }
        else if (choiceM == 2){
            editModule();
        }
        else if (choiceM == 3){
            adminHome();
        }
        else {
            System.out.println("\nInvalid choice!!! Please enter again.");
            moduleManager();
        }
    }

    public void editModule() {
        Scanner mId1 = new Scanner(System.in);
        Scanner newM = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh != 'n') {
            System.out.print("Enter Module Id to rename:");
            int ModuleId = mId1.nextInt();
            System.out.print("Enter New Module Name:");
            String ModuleName = newM.nextLine();
            dbms.renameModule(ModuleId,ModuleName);
            System.out.println("Rename a module again?([y]/[n])");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);
        }
        moduleManager();
    }

    public void addModule() {
        Scanner mId = new Scanner(System.in);
        Scanner mName = new Scanner(System.in);
        Scanner cId = new Scanner(System.in);
        Scanner cName = new Scanner(System.in);
        char smallCh = 'y';
        char var;
        while (smallCh != 'n') {
            System.out.print("Enter Module Id: ");
            int ModuleId = mId.nextInt();
            System.out.print("Enter Module Name: ");
            String ModuleName = mName.nextLine();
            System.out.print("Enter Course Id:");
            int CourseId = cId.nextInt();
            System.out.print("Enter Course Name:");
            String CourseName = cName.nextLine();
            dbms.insertModule(ModuleId, ModuleName, CourseId,CourseName);

            System.out.print("\nAdd more Modules?([y]/[n]) ");
            Scanner s = new Scanner(System.in);
            var = s.next().charAt(0);
            smallCh = Character.toLowerCase(var);
        }
        moduleManager();
    }
    //Module related methods end
}
