import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to E-learning\nEnter your choice:\n1. College 2. User");
		int n = sc.nextInt();
		
		switch(n){
		  case 1:
			  College college = new College();
			  System.out.println("\n*****College*****\nEnter your choice:\n1. Register 2. Login");
			  int choice1 = sc.nextInt();
			  switch(choice1) {
			  	case 1:
			  		System.out.println("Enter college name:");
			  		String colName1 = sc.next();
			  		System.out.println("Enter college code:");
			  		String colCode1 = sc.next();
			  		System.out.println("Enter password:");
			  		String colPwd1 = sc.next();
				try {
					if(college.register(colName1, colCode1, colPwd1))
			  			System.out.println("Registration Successful");
			  		else
			  			System.out.println("Registration Unsuccessful");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  		break;
			  	case 2:
			  		System.out.println("Enter college code:");
			  		String colCode2 = sc.next();
			  		System.out.println("Enter password:");
			  		String colPwd2 = sc.next();
				try {
					if(college.login(colCode2, colPwd2)) {
			  			System.out.println("Login Successful");
			  			char c1;
						  do {
							System.out.println("\n*****Operations*****\nEnter your choice:\n1. View Courses 2. Add Course 3. Update Course 4. Delete Course");
							int a = sc.nextInt();
							switch(a) {
					    		case 1:
					    			System.out.println("Courses");
					    			college.coursesDisplay();
					    			break;
					    		case 2:
					    			System.out.println("Enter the Course Name to ADD:");
					    			String csName2 = sc.next();
					    			System.out.println("Enter the Course Id to ADD:");
					    			int csId2 = sc.nextInt();
					    			System.out.println(college.addCourse(csName2, csId2));
					    			college.coursesDisplay();
					    			break;
					    		case 3:
					    			college.coursesDisplay();
					    			System.out.println("Enter the Course Id to UPDATE:");
					    			int csId3 = sc.nextInt();
					    			System.out.println("Rename Course Name to UPDATE:");
					    			String csName3 = sc.next();
					    			System.out.println(college.updateCourse(csId3, csName3));
					    			college.coursesDisplay();
					    			break;	
					    		case 4:
					    			college.coursesDisplay();
					    			System.out.println("Enter the Course Id to DELETE:");
					    			int csId4 = sc.nextInt();
					    			System.out.println(college.deleteCourse(csId4));
					    			college.coursesDisplay();
					    			break;
					    		default:
					    			System.out.println("Enter correct choice");
							}
							System.out.println("Do u wish to continue(Y/N):");
							c1 = sc.next().charAt(0);
						}while(c1=='Y' || c1=='y');
			  		}
			  		else
			  			System.out.println("Login Unsuccessful");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  		break;
			  	default:
			  		System.out.println("Enter correct choice");
			  }
			  
			  break;
		  case 2:
			  User user = new User();
			  System.out.println("\n*****User*****\nEnter your choice:\n1. Register 2. Login");
			  int choice2 = sc.nextInt();
			  switch(choice2) {
			  	case 1:
			  		System.out.println("Enter college code:");
			  		String colCode1 = sc.next();
			  		System.out.println("Enter User name:");
			  		String userName1 = sc.next();
			  		System.out.println("Enter User Rno:");
			  		int userrno1 = sc.nextInt();
			  		System.out.println("Enter password:");
			  		String userPwd1 = sc.next();
			  		try {
			  			if(user.register(colCode1, userName1, userrno1, userPwd1)) {
			  				System.out.println("Registration Successful");
			  			}
			  			else
			  				System.out.println("Registration unsuccessful");
			  		} 
			  		catch (ClassNotFoundException | SQLException e1) {
			  			e1.printStackTrace();
			  		}
			  		break;
			  	case 2:
			  		System.out.println("Enter User Rno:");
			  		int userrno2 = sc.nextInt();
			  		System.out.println("Enter password:");
			  		String userPwd2 = sc.next();
			  		try {
			  			if(user.login(userrno2, userPwd2)) {
			  				System.out.println("Login Successful");
			  				System.out.println("Courses");
			    			user.coursesDisplay();
			    			break;
			  			}
			  		}
			  		catch (ClassNotFoundException | SQLException e) {
			  			e.printStackTrace();
			  		}
			  		break;
			  	default:
			  		System.out.println("Enter correct choice");
			  }
			  
			  break;
		  default:
			  System.out.println("Enter correct choice");
		}   
	}

}

class College{
	College(){
		
	}
	static String sql;
	static final String url="jdbc:mysql://localhost:3306/exampledb";
	static final String  username="root";
	static final String password="root";
	
	int courseId;
	String collegeName;
	String collegeCode;
	String collegePassword;
	String courseName;
	public int getcourseId() {
		return courseId;
	}
	public String getcollegeName() {
		return collegeName;
	}
	public void setcollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getcollegePassword() {
		return collegePassword;
	}
	public void setcollegePassword(String collegePassword) {
		this.collegePassword = collegePassword;
	}
	public String getcourseName() {
		return collegePassword;
	}
	public void setcourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void coursesDisplay() throws ClassNotFoundException, SQLException  {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("select Course_Id ,Course_Name from course where College_Code=?");
			preparedStatement.setString(1,collegeCode);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
	            //Display values
	            System.out.print("ID: " + rs.getString("Course_Id"));
	            System.out.println(", Name: " + rs.getString("Course_Name"));
	         }
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
	}
	
	public String addCourse(String courseName, int courseId) throws ClassNotFoundException, SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("insert into course values(?,?,?)");
			preparedStatement.setString(1,collegeCode);
			preparedStatement.setString(2,courseName);
			String ia = ""+courseId;
			preparedStatement.setNString(3,ia);
			preparedStatement.executeUpdate();
			return "Course added successfully!";
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return "Course cannot be added";
	}
	
	public String deleteCourse(int courseId) throws ClassNotFoundException, SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("delete from course where Course_Id=?");
			String id = ""+courseId;
			preparedStatement.setString(1,id);
			preparedStatement.executeUpdate();
			return "Course deleted successfully!";
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return "Course cannot be deleted";
	}
	
	public String updateCourse(int courseId, String courseName) throws ClassNotFoundException, SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("update course set Course_Name = ? where Course_Id=?");
			preparedStatement.setString(1,courseName);
			String iu = ""+courseId;
			preparedStatement.setString(2,iu);
			preparedStatement.executeUpdate();
			return "Course updated successfully!";
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return "Course cannot be updated";
	}
	
	public boolean login(String colCode, String colPwd) throws ClassNotFoundException, SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("select * from college where College_Code=? and College_Pwd=?");
			preparedStatement.setString(1,colCode);
			preparedStatement.setString(2,colPwd);
			ResultSet rs=preparedStatement.executeQuery();
			this.collegeCode = colCode;
			return true;
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return false;
	}
	
	public boolean register(String colName, String colCode, String colPwd) throws ClassNotFoundException, SQLException {    
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("insert into college values(?,?,?)");
			preparedStatement.setString(1,colName);
			preparedStatement.setString(2,colCode);
			preparedStatement.setNString(3,colPwd);
			preparedStatement.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return false;
	}
}


class User{
	User(){
		
	}
	static String sql;
	static final String url="jdbc:mysql://localhost:3306/exampledb";
	static final String  username="root";
	static final String password="root";
	
	String collegeCode;
	String userName;
	int userRno;
	String userPassword;

	public String getCollegeCode() {
		return collegeCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserRno() {
		return userRno;
	}
	public void setUserRno(int userRno) {
		this.userRno = userRno;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void coursesDisplay() throws ClassNotFoundException, SQLException  {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("select Course_Id ,Course_Name from course where College_Code=?");
			preparedStatement.setString(1,collegeCode);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
	            //Display values
	            System.out.print("ID: " + rs.getString("Course_Id"));
	            System.out.println(", Name: " + rs.getString("Course_Name"));
	         }
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
	}
	
	public boolean login(int rno, String userPwd) throws ClassNotFoundException, SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("select * from user where User_Roll_No=? and User_Pwd=?");
			String il = ""+rno;
			preparedStatement.setString(1,il);
			preparedStatement.setString(2,userPwd);
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				this.collegeCode = rs.getString("College_Code");
			}	
			return true;
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return false;
	}
	
	public boolean register(String colCode, String userName, int userRno, String userPwd) throws ClassNotFoundException, SQLException {    
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,username,password);
			PreparedStatement preparedStatement=con.prepareStatement("insert into user values(?,?,?,?)");
			preparedStatement.setString(1,colCode);
			preparedStatement.setString(2,userName);
			String ir = ""+userRno;
			preparedStatement.setNString(3,ir);
			preparedStatement.setNString(4,userPwd);
			preparedStatement.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		return false;
	}
}