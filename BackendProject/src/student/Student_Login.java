package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import faculty.Faculty_StudentInfo;

public class Student_Login {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Faculty?useSSL=false","root","root"); 	
		con.setAutoCommit(false);
		
		System.out.println("Login with your registered mail id and password");
		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter your Email Id:");
		String Email_ID =s.nextLine();
		System.out.println("Enter your Password:");
		String Password =s.nextLine();
		
		boolean status=false;
		

		  PreparedStatement login =con.prepareStatement("select * from Student_info_fac where Stu_Email_ID=? and Stu_Password=?");
		  
		
		login.setString(1,Email_ID );
		login.setString(2, Password);
		
		ResultSet rs=login.executeQuery();
		
		status=rs.next();
		
		if(status==true)
		{
			System.out.println("Login Successfull");
			System.out.println("Roll.Num : "+rs.getInt(1));
			System.out.println("Name : "+rs.getString(2));
			System.out.println("EmailId : "+rs.getString(3));
			System.out.println("Password : "+rs.getString(4));
			System.out.println("PhoneNum : "+rs.getString(5));
			System.out.println("Sem1 : "+rs.getFloat(6));
			System.out.println("Sem2 : "+rs.getFloat(7));
			System.out.println("Sem3 : "+rs.getFloat(8));
			System.out.println("Sem4 : "+rs.getFloat(9));
			System.out.println("Year : "+rs.getLong(10));
			System.out.println("Percentage : "+rs.getFloat(11));
			System.out.println("Grade : "+rs.getString(12));
			
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Student?useSSL=false","root","root"); 	
			PreparedStatement ps=conn.prepareStatement("insert into Login values(?,?)");
			ps.setString(1,Email_ID );
			ps.setString(2, Password);
			ps.executeUpdate();

			
			conn.close();
			
			System.out.println("If you want to Update Your Details [Yes/No]");
			String ans=s.nextLine();
			if(ans.equals("Yes"))
			{
				Student_Info.Stud();
				
			}
			else
			{
				System.out.println("Verify the Student detail again");
			
				con.commit();
			
			}	
			
		}
		
		else {
			System.out.println("Check your emailId and Password");
		}
		
	
		
		con.close(); 
		
		
		
		
	}

}
