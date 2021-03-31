package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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
			
			Course theCourse = new Course("Pacman - How To Score One Million Points");
			
			System.out.println("\nSaving the course...");
			session.save(theCourse);
			System.out.println("Saved the course: " + theCourse);
			
			Student theStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student theStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
			
			theCourse.addStudent(theStudent1);
			theCourse.addStudent(theStudent2);
			
			System.out.println("\nSaving students...");
			session.save(theStudent1);
			session.save(theStudent2);
			System.out.println("Saved students: " + theCourse.getStudents());
			
			session.getTransaction().commit();
			
			System.out.println("\nDone!!!");			
		}
		finally {
			session.close();
			factory.close();
		}
	}
}