package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Course theCourse = new Course("Pacman - How To Score One Million Points");
			
			theCourse.addReview(new Review("Greate course ... loved it!"));
			theCourse.addReview(new Review("Cool course, job well done!"));
			theCourse.addReview(new Review("What a dumb course, you are an idiot!"));
			
			System.out.println("Saving the course");
			System.out.println(theCourse);
			System.out.println(theCourse.getReviews());
			
			//	saving theCourse we save all its reviews as well because we have
			//	cascadeType.all
			session.save(theCourse);
			
			session.getTransaction().commit();
			
			System.out.println("\nDone!!!");			
		}
		finally {
			session.close();
			factory.close();
		}
	}
}