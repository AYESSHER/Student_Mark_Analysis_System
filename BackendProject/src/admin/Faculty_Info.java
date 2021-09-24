package admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Faculty_Info {

	public static void main(String[] args) throws Exception {
		

		Class.forName("com.mysql.cj.jdbc.Driver"); //register driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin_info?useSSL=false","root","root"); //establish connection	
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Scanner s=new Scanner(System.in);
		while(true)
		{
		System.out.println("1) Insert");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		
		int data = s.nextInt();
		
		
		switch(data)
		{
			case 1:
				
					System.out.println("Enter your Fac_Email_ID:");
					String Fac_Email_ID=br.readLine();
					
					boolean email=Fac_Email_ID.matches("[a-zA-Z0-9]+@[a-zA-Z0-9.]+");
					if(email)
					{
						System.out.println("Email Id Valid");
					}
					else
					{
						System.out.println("please enter valid mail id Format");
						br.readLine();
					}
					
					System.out.println("Enter your Fac_Password:");
					String Fac_Password=br.readLine();
				
					PreparedStatement login = con.prepareStatement("insert into  Faculty_info (Email_ID,Password) values(?,?)");
					login.setString(1,Fac_Email_ID);
					login.setString(2,Fac_Password);
					login.executeUpdate();
					System.out.println("Record Inserted");
					break;
			
			case 2:
				
				System.out.println("Enter Faculty Mail_ID");
				String fac_mail=br.readLine();
				System.out.println("Enter Password");
				String pass=br.readLine();
				System.out.println("Enter ID to Update");
				int id=s.nextInt();

				String update=("update Faculty_info set Email_ID=?, Password=? where ID=?");
				PreparedStatement ps1=con.prepareStatement(update);
					ps1.setString(1,fac_mail);
					ps1.setString(2,pass);
					ps1.setInt(3, id);
				    ps1.executeUpdate();
				System.out.println("Record Updated");
				break;
				
			case 3:
				System.out.println("Enter Faculty Mail_ID to delete");
				String fac_mail_del=br.readLine();
				String delete=("delete from Faculty_info where ID=?");
				PreparedStatement ps2=con.prepareStatement(delete);
				System.out.println("Enter ID to Delete");
				int id1=s.nextInt();
				ps2.setInt(1, id1);
				
				ps2.executeUpdate();
				
				System.out.println("Record Deleted");
				break;
			
			default:
				System.out.println("Cannot Perform");
		

	}

}
}
}
