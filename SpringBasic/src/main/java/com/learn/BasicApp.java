package com.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.learn.bean.Person;


public class BasicApp {

	public static void main(String[] args) {
		//created the application context
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ApplicationContext classApplicationContext = new ClassPathXmlApplicationContext("config.xml");
		
		ApplicationContext fileSystemApplicationContext = new FileSystemXmlApplicationContext("config.xml");

		
		Person person = (Person) ac.getBean("person");
		Person person2 = ac.getBean("person",Person.class);
		
		person.setName("Vrunda");
		System.out.println(person);
	}
	
}
