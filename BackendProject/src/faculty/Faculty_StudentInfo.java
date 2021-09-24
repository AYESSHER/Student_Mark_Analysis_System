package faculty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Faculty_StudentInfo {
	
	public static void fac_stu_info() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); //register driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Faculty?useSSL=false","root","root"); //establish connection	
		
		con.setAutoCommit(false);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Scanner s=new Scanner(System.in);
		
		
		while(true)
		{
		System.out.println("Enter Stu_Roll_no:");
		String Stu_Roll_no=br.readLine();
		
		System.out.println("Enter Stu_name:");
		String Stu_name=br.readLine();
		
		System.out.println("Enter Stu_Email_ID:");
		String Stu_Email_ID=br.readLine();
		
		boolean email=Stu_Email_ID.matches("[a-zA-Z0-9]+@[a-zA-Z0-9.]+");
		if(email)
		{
			System.out.println("Email Id Valid");
		}
		else
		{
			System.out.println("please enter valid mail id Format");
			br.readLine();
		}
		
		System.out.println("Enter Stu_Password:");
		String Stu_Password=br.readLine();
		
		System.out.println("Enter Stu_phone_number:");
		String Stu_phone_number=br.readLine();
		
		System.out.println("Semester_1 Percentage:");
		float  Semester_1=s.nextFloat();
		System.out.println("Semester_2 Percentage:");
		float  Semester_2=s.nextFloat();
		System.out.println("Semester_3 Percentage:");
		float  Semester_3=s.nextFloat();;
		System.out.println("Semester_4 Percentage:");
		float  Semester_4=s.nextFloat();
		System.out.println("Academic Year:");
		int Year=br.read();
		
//		System.out.println("Percentage:");
//		br.readLine();
		//float Percentage=Float.valueOf(br.readLine()).floatValue();
		float Percentage = (float)((Semester_1 + Semester_2 + Semester_3 + Semester_4)/4);
	      System.out.println("Percentage ::"+ Percentage);
//		float Percentage=((Semester_1+Semester_2+Semester_3+Semester_4)/4);
//		System.out.println(Percentage);
		
//		System.out.println("Grade:");
//		br.readLine();
//		String Grade=br.readLine();
		if(Percentage>=90)
		{
			System.out.println("Grade A+");
		}
			else if(Percentage>=80 && Percentage<90)
			{
				System.out.println("Grade A");
			}
			else if(Percentage>=70 && Percentage<80)
			{
				System.out.println("Grade B+");
			}
			else if(Percentage>=60 && Percentage<70)
			{
				System.out.println("Grade B");
			}
			else if(Percentage>=50 && Percentage<60)
			{
				System.out.println("Grade C+");
			}
		else
		{
			System.out.println("Grade F");
		}
		
		
		
		
	
		PreparedStatement login = con.prepareStatement("insert into  Student_info_fac values(?,?,?,?,?,?,?,?,?,?,?)");
		
		login.setString(1,Stu_Roll_no );
		login.setString(2,Stu_name);
		login.setString(3,Stu_Email_ID);
		login.setString(4,Stu_Password);
		login.setString(5, Stu_phone_number);
		login.setFloat(6,Semester_1 );
		login.setFloat(7,Semester_2);
		login.setFloat(8,Semester_3);
		login.setFloat(9,Semester_4);
		login.setLong(10,Year);
		login.setFloat(11,Percentage);
	
		
		int data=login.executeUpdate();
		
		System.out.println("Verify Student Detail is Correct or not[correct/incorrect]");
		br.readLine();
		String ans=br.readLine();
		if(ans.equals("correct"))
		{
			con.commit();
		}
		else
		{
			System.out.println("Verify the Student detail again");
		}
		
		if(data>0)
		{
			System.out.println("Record Inserted");
		}
		else
		{
			System.out.println("Not Inserted");
		}
		
		con.close(); 
	}
	}
	public static void main(String[] args) throws Exception {
		

		fac_stu_info();
		
		
	}	

}
