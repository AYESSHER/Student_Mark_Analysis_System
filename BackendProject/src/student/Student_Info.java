package student;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student_Info {

	public static void Stud() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); //register driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Faculty?useSSL=false","root","root"); //establish connection
		
		con.setAutoCommit(false);
		
		
		System.out.println("Ensure your details");
		Scanner s=new Scanner(System.in);
		System.out.println("Roll NO");
		int RollNo=s.nextInt();
		
		System.out.println("Update your Phone number:");
		String stud_phone =s.next();
		
		System.out.println("Update your Password:");
		String stud_Password =s.next();
		
		PreparedStatement ps=con.prepareStatement("update Student_info_fac set Stu_phone_number=?, Stu_Password=? where Stu_Roll_no=?" );
		ps.setString(1,stud_phone);
		ps.setString(2,stud_Password);
		ps.setInt(3, RollNo);
		int i=ps.executeUpdate();
		
		System.out.println("Verify your Details is Correct or not[correct/incorrect]");
		String ans=s.next();
		if(ans.equals("correct"))
		{
			con.commit();
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Student?useSSL=false","root","root");
			PreparedStatement p=conn.prepareStatement("insert into Student_info values(?,?,?,?)");
			p.setString(1, stud_phone);
			p.setString(2, stud_Password);
			FileInputStream fin=new FileInputStream("C:\\Users\\ayesh\\Downloads\\Resume-Ayesha.pdf");
			p.setBinaryStream(3, fin,fin.available());
			FileInputStream finn=new FileInputStream("C:\\Users\\ayesh\\Downloads\\student1.jpg");
			p.setBinaryStream(4, finn,finn.available());
			p.executeUpdate();
			conn.close();
		}
		else
		{
			System.out.println("Verify the Student details again");
		}

		
		if(i>0)
		{
			System.out.println("Data Uploaded");
		}
		
		else
		{
			System.out.println("Not Uploaded");
		}
		
		ResultSet rs=ps.executeQuery("Select * from Student_info_fac where Stu_Roll_no='"+RollNo+"'");
		while(rs.next())
		{
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
		}
		
		con.close(); 

		
	}
	
	public static void main(String[] args) throws Exception {
		
		Stud();
		
	}

}
