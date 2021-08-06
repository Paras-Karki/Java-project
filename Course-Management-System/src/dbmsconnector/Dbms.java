package dbmsconnector;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class Dbms {
    private Connection con;

    public Dbms(){
        try{
            con = DBUtils.getDbConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getCourse(){
        try {
            String selectData = "SELECT * FROM course";
            PreparedStatement statement = con.prepareStatement(selectData);
            return statement.executeQuery();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void insertModule(int ModuleId, String ModuleName, int CourseID, String CourseName) {
        try {
            String addToSql = "INSERT INTO module ( ModuleId, ModuleName, CourseId, CourseName) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(addToSql);
            statement.setInt(1, ModuleId);
            statement.setString(2, ModuleName);
            statement.setInt(3, CourseID);
            statement.setString(4, CourseName);

            statement.executeUpdate();
            statement.close();
            System.out.println("Modules has been added Successfully.\n");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void renameModule(int moduleId, String moduleName) {
        try {
            String cancelData = "UPDATE module SET ModuleName = ? WHERE ModuleId = ?";
            PreparedStatement statement = con.prepareStatement(cancelData);

            statement.setString(1, moduleName);
            statement.setInt(2, moduleId);

            statement.executeUpdate();
            statement.close();
            System.out.println("Module name has been changed successfully.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertCourse(int courseId, String courseName, int courseStatus) {
        try {
            String addToSql = "INSERT INTO course ( CourseId, CourseName, courseStatus) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(addToSql);
            statement.setInt(1, courseId);
            statement.setString(2, courseName);
            statement.setInt(3, courseStatus);


            statement.executeUpdate();
            statement.close();
            System.out.println("New Course has been added Successfully.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cancelACourse(int courseId, int courseStatus) {
        try {
            String cancelData = "UPDATE course SET courseStatus = ? WHERE CourseId = ?";
            PreparedStatement statement = con.prepareStatement(cancelData);

            statement.setInt(1, courseStatus);
            statement.setInt(2, courseId);

            statement.executeUpdate();
            statement.close();
            System.out.println("The course status has been changed to Cancel.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeCourse(int courseId) {
        try {
            String remData = "DELETE FROM course WHERE courseId = ?";
            PreparedStatement statement = con.prepareStatement(remData);

            statement.setInt(1, courseId);
            statement.executeUpdate();
            statement.close();
            System.out.println("The given course has been removed successfully..");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertInstructor(int moduleId, int instructorId, String instructorName) {
        try {
            String addToSql = "INSERT INTO instructor( moduleId, instructorId, instructorName) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(addToSql);
            statement.setInt(1, moduleId);
            statement.setInt(2, instructorId);
            statement.setString(3, instructorName);

            statement.executeUpdate();
            statement.close();
            System.out.println("Instructor has been added successfully.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteInstructor(int moduleId, int instructorId, String instructorName) {
        try {
            String cancelData = "UPDATE instructor SET instructorName = ?, InstructorId=?  WHERE ModuleId = ?";
            PreparedStatement statement = con.prepareStatement(cancelData);

            statement.setString(1, instructorName);
            statement.setInt(2, instructorId);
            statement.setInt(3, moduleId);

            statement.executeUpdate();
            statement.close();
            System.out.println("Instructor has been removed from DataBase successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertIntoStudent(int studentId, String studentName, String courseName, int courseLevel) {
        try {
            String addToSql = "INSERT INTO student(studentID, studentName, courseName, courseLevel) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(addToSql);
            statement.setInt(1,studentId);
            statement.setString(2,studentName);
            statement.setString(3, courseName);
            statement.setInt(4,courseLevel);

            statement.executeUpdate();
            statement.close();
            System.out.println("New Student Data has been added successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public ResultSet getStudent() {
        try {
            String selectData = "SELECT * FROM student";
            PreparedStatement statement = con.prepareStatement(selectData);
            return statement.executeQuery();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void insertMarks(int studentId, int moduleId, String moduleName, int moduleMark) {
        try {
            String addToSql = "INSERT INTO modulemark ( studentId, moduleId, moduleName, moduleMark) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(addToSql);
            statement.setInt(1, studentId);
            statement.setInt(2, moduleId);
            statement.setString(3, moduleName);
            statement.setInt(4, moduleMark);

            statement.executeUpdate();
            statement.close();
            System.out.println("Mark has been Added to DataBase successfully");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getModule() {
        try {
            String selectData = "SELECT * FROM module";
            PreparedStatement statement = con.prepareStatement(selectData);
            return statement.executeQuery();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
