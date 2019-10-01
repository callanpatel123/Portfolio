///////////////////////////////////////////////////////////////////////////////
//
// Title:           Final Project: EvolutionTree
// Due Date:        Milestone 2: 4/25/2019 10pm
//                  Milestone 3: 5/3/2019 11:59pm
//                  
// Course:          CS400, Spring 2018, Lecture 002
//
// Authors:         Erica Heying, Ben Procknow, Ajman Naqab, Callan Patel
// A team:          63
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons:         NONE
// Online Sources:  https://docs.oracle.com/javafx/2/ui_controls/radio-button.htm
//
///////////////////////////// KNOWN BUGS///////////////////////////////////////
// Known Bugs:      NONE     
///////////////////////////////////////////////////////////////////////////////
package application;

import java.util.ArrayList;
/**
 * Student Class
 * 
 * @author erica, ben, aj, callan
 *
 */

//This class represents a Student. This class extends User
public class Student extends User{
	private int yearOfGrad;
	private ArrayList<String> major;
	private ArrayList<String> certificate;
	private ArrayList<String> clubs;
	private ArrayList<String> scholership; 
	private ArrayList<String> courses;
	private ArrayList<String> workExperience;
	
	   public Student(int yearOfGrad, ArrayList<String> major, ArrayList<String> certificate, ArrayList<String> clubs,
         ArrayList<String> scholership, ArrayList<String> courses, ArrayList<String> workExperience, String name, String email){            
     super(name, email); //calls User constructor with name, email, password
     this.yearOfGrad = yearOfGrad;
     this.major = major;
     this.certificate = certificate;
     this.clubs = clubs;
     this.scholership = scholership;
     this.courses = courses;
     this.workExperience = workExperience; 
     setType("student");
 }
	
	//Constructor
	//@param int yearOfGrad
	//@param ArrayList<String> major, certificate, clubs, scholership,courses,workExperience
	//@param name, email, password
	public Student(int yearOfGrad, ArrayList<String> major, ArrayList<String> certificate, ArrayList<String> clubs,
			ArrayList<String> scholership, ArrayList<String> courses, ArrayList<String> workExperience, String name, String email, String password){			
		super(name, email, password); //calls User constructor with name, email, password
		this.yearOfGrad = yearOfGrad;
		this.major = major;
		this.certificate = certificate;
		this.clubs = clubs;
		this.scholership = scholership;
		this.courses = courses;
		this.workExperience = workExperience;
		setType("student");
	}
	
	//@return int yearOfGrad
	public int getYearOfGrad() {
		return yearOfGrad;
	}
	
	//@return ArrayList<String> major
	public ArrayList<String> getMajor(){
		return major;
	}
	
	//@return ArrayList<String> certificate
	public ArrayList<String> getCertificate(){
		return certificate;
	}
	 //@return ArrayList<String> clubs
	public ArrayList<String> getClubs(){
		return clubs;
	}
	
	//@return ArrayList<String> scholership
	public ArrayList<String> getScholership(){
		return scholership;
	}
	 
	//@return ArrayList<String> courses
	public ArrayList<String> getCourses(){
		return courses;
	}
	 
	//@return ArrayList<String> workExperience
	public ArrayList<String> getWorkExperience(){
		return workExperience;
	}
	
	//@param ArrayList<String> major
	public void setMajor(ArrayList<String> major) {
		this.major = major;
	}
	
	//@param ArrayList<String> certificate
	public void setCertificate(ArrayList<String> certificate) {
		this.certificate = certificate;
	}
	
	//@param ArrayList<Sting> clubs
	public void setClubs(ArrayList<String> clubs) {
		this.clubs = clubs;
	}
	
	//@param ArrayList<String> scholership
	public void setScholership(ArrayList<String> scholership) {
		this.scholership = scholership;
	}
	
	//@param ArrayList<String> courses
	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	//@param ArrayList<String> workExperience
	public void setWorkExperience(ArrayList<String> workExperience) {
		this.workExperience = workExperience;
	}
	
	   //@param ArrayList<String> workExperience
    public void setYearOfGrad(int yearOfGrad) {
        this.yearOfGrad = yearOfGrad;
    }
	
}

