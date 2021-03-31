package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Student theStudent = session.get(Student.class, 2);
			System.out.println("\nLoaded student: " + theStudent);
			System.out.println("Courses: " + theStudent.getCourses());
			
			Course theCourse1 = new Course("Rubik's Cube - How To Speed Cube");
			Course theCourse2 = new Course("Atari 2600 - Game Development");
			
			theCourse1.addStudent(theStudent);
			theCourse2.addStudent(theStudent);
			
			System.out.println("\nSaving courses...");
			
			session.save(theCourse1);
			session.save(theCourse2);
			
			session.getTransaction().commit();
			
			System.out.println("\nDone!!!");			
		}
		finally {
			session.close();
			factory.close();
		}
	}
}