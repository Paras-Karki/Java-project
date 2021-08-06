

import java.util.Scanner;

public class Main {

    public void homePage(){
        Admin admin = new Admin();
        Student student = new Student();
        Instructor instructor = new Instructor();

        System.out.println("\n***** Java Course Management System *****");
        System.out.println("Choose from the following options!");
        System.out.println("1. Admin");
        System.out.println("2. Student");
        System.out.println("3. Instructor");
        System.out.println("4. Exit");
        System.out.print("Enter your answer-> ");
        Scanner input = new Scanner(System.in);
        int select = input.nextInt();

        if(select==1){
            admin.adminHome();
        }
        else if(select==2){
            student.studentHome();
        }
        else if(select==3){
            instructor.instructorHome();
        }
        else if(select==4){
            exit();
        }
        else{
            System.out.println("\nInvalid choice!!! Please enter again.");
            homePage();
        }

    }



    public void exit(){
        System.out.println("\n*** You have exit the program. Thank you! ***");
        System.exit(0);
    }

    public static void main(String[] args) {
        Main main = new Main();


        // write your code here

        main.homePage();


    }



}
