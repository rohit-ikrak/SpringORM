package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDaoImpl;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" ); 
    	ApplicationContext con = new ClassPathXmlApplicationContext("config.xml");
    	StudentDaoImpl sdao = con.getBean("studentDao", StudentDaoImpl.class);
//    	Student stud = new Student(2, "Kamal Kumar", "Kerala");
//    	int r = sdao.insert(stud);
//    	System.out.println("done" + r);
    	
    	Boolean go = true;
    	while(go) {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		System.out.println("Press 1 for new student.");
    		System.out.println("Press 2 to display all students.");
    		System.out.println("Press 3 to get details of a single student.");
    		System.out.println("Press 4 to update an existing student.");
    		System.out.println("Press 5 for deleting a details of a student.");
    		System.out.println("Press 6 to Exist.");
    	
    	
    		try {
        		int input = Integer.parseInt(br.readLine());
        		switch(input) {
        		case 1://add student details
        			
        			//taking input from user
        			System.out.println("Enter Student Id : ");
        			int sid = Integer.parseInt(br.readLine());
        			System.out.println("Enter Student Name : ");
        			String sname = (br.readLine());
        			System.out.println("Enter Student city : ");
        			String scity = (br.readLine());
        			
        			//taking student values in object through constructor
        			Student s = new Student(sid, sname, scity);
        			
        			//saving object in database by calling insert method.
        			int r = sdao.insert(s);
        			
        			System.out.println(r + " students added.");
        			System.out.println("#############################");
        			System.out.println();
        			break;
        		case 2://display all student details
        			
        			//loading student details from db in a list
        			List<Student> studs = sdao.getAllStudents();
        			System.out.println("Student Details ( Id, Name, City)");
        			for(Student st : studs) {
        				System.out.println(" ID   : " + st.getSid() + "\n Name : " + st.getSname() + "\n City : " + st.getScity());
        				System.out.println("_________________________________________");
        			}
        			
        			System.out.println("#############################");
        			System.out.println();
        			break;
        		case 3://get detail of a student
        			
        			//giving id to fetch details from table in db
        			System.out.println("Enter student Id to get details: ");
        			int userId= Integer.parseInt(br.readLine());
        			Student st = sdao.getStudent(userId);
    				System.out.println(" ID   : " + st.getSid() + "\n Name : " + st.getSname() + "\n City : " + st.getScity());
    				System.out.println("_________________________________________");

    				break;
        		case 4://update detail of a student
        			
        			//taking input from user
        			System.out.println("Enter Student Id : ");
        			int stid = Integer.parseInt(br.readLine());
        			System.out.println("Enter Student Name : ");
        			String stname = (br.readLine());
        			System.out.println("Enter Student city : ");
        			String stcity = (br.readLine());
        			
        			//taking student values in object through constructor
        			Student nstudent = new Student(stid, stname, stcity);
        			
        			//saving object in database by calling insert method.
        			sdao.update(nstudent);
        			
        			System.out.println(" student Updated.");
        			System.out.println("#############################");
        			System.out.println();
        			
        			break;
        		case 5://delete a student detail
        			System.out.println("Enter user Id to delete : ");
        			int id= Integer.parseInt(br.readLine());
        			sdao.deleteStudent(id);
        			System.out.println("Student Deleted.");
    				System.out.println("_________________________________________");

        			break;
        		default://Exit
        			go= false;
        			
        		}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    	System.out.println("Thank You for using this application.");
    }
}
