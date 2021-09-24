package faculty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import student.Student_Info;

public class Faculty_Login {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver"); //register driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin_info?useSSL=false","root","root"); //establish connection
		
		
		System.out.println("Login with your registered mail id and password");
		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter your Email Id:");
		String Email_ID =s.nextLine();
		System.out.println("Enter your Password:");
		String Password =s.nextLine();
		
		boolean status=false;
		

		  PreparedStatement login =con.prepareStatement("select * from Faculty_info where Email_ID=? and Password=?");
		  
		
		login.setString(1,Email_ID );
		login.setString(2, Password);
		
		ResultSet rs=login.executeQuery();
		
		status=rs.next();
		
		
		
		
		if(status==true)
		{
			System.out.println("Login Successfull");
			
			System.out.println("EmailId : "+rs.getString(1));
			System.out.println("Password : "+rs.getString(2));
			
			
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Faculty?useSSL=false","root","root"); 	
			PreparedStatement ps=conn.prepareStatement("insert into Faculty_Login values(?,?)");
			ps.setString(1,Email_ID );
			ps.setString(2, Password);
			ps.executeUpdate();

			
			conn.close();
			System.out.println("If you want to Insert a Student Detail [Yes/No]");
			String ans=s.nextLine();
			if(ans.equals("Yes"))
			{
				Faculty_StudentInfo.fac_stu_info();
				
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

