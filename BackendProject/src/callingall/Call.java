package callingall;
import java.util.Scanner;

import student.Student_Login;
import faculty.Faculty_Login;
import admin.Faculty_Info;

public class Call {

	public static void main(String[] args) throws Exception {
		
     Scanner s=new Scanner(System.in);
		
		System.out.println("If your are a Student  Please Enter 1 or If you are a Faculty Please Enter 2 or If you are a Admin Please Enter 3");
		System.out.println("1) Student");
		System.out.println("2) Faculty");
		System.out.println("3) Admin");
		int data = s.nextInt();
		switch(data)
		{
			case 1:
				Student_Login.main(null);
				break;
				
			case 2:
				Faculty_Login.main(null);
				break;
				
				
			case 3:
				Faculty_Info.main(null);
				break;
			
			default:
				System.out.println("Cannot Perform");
		}

	}

}
