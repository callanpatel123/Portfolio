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
 * Faculty Class
 * 
 * @author erica, ben, aj, callan
 *
 */

//This class represents a Faculty user. This class extends User
public class Faculty extends User{
	private ArrayList<String> coursesTaught;
	private ArrayList<String> officeHours;
	private ArrayList<String> officeLocation;
	
	//Constructor
	//@param ArrayList<String> coursesTaught, officeHours, officeLocation
	//@param String name, email
	public Faculty(ArrayList<String> coursesTaught, ArrayList<String> officeHours, ArrayList<String> officeLocation,
			String name, String email) {
		super(name, email); //calls the User constructor with name, email, and password
		this.coursesTaught = coursesTaught;
		this.officeHours = officeHours;
		this.officeLocation = officeLocation;
		setType("faculty");
	}
	
	//Constructor
	//@param ArrayList<String> coursesTaught, officeHours, officeLocation
	//@param String name, email, password
	public Faculty(ArrayList<String> coursesTaught, ArrayList<String> officeHours, ArrayList<String> officeLocation,
			String name, String email, String password) {
		super(name, email, password); //calls the User constructor with name, email, and password
		this.coursesTaught = coursesTaught;
		this.officeHours = officeHours;
		this.officeLocation = officeLocation;
		setType("faculty");
	}
	
	//@param ArrayList<String> coursesTaught
	public void setCoursesTaught(ArrayList<String> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}
	
	//@param ArrayList<String> officeHours
	public void setOfficeHours(ArrayList<String> officeHours) {
		this.officeHours = officeHours;
	}
	
	//@param ArrayList<String> officeLocation
	public void setOfficeLocation(ArrayList<String> officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	//@return ArrayList<String> coursesTaught
	public ArrayList<String> getCoursesTaught(){
		return coursesTaught;
	}
	
	//@return Array<String> officeHours
	public ArrayList<String> getOfficeHours(){
		return officeHours;
	}
	
	//@return ArrayList<String> officeLocation
	public ArrayList<String> getOfficeLocation(){
		return officeLocation;
	}
}
