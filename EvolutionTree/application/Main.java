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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Main application
 * 
 * @author erica, ben, aj, callan
 *
 */
public class Main extends Application {
  Stage primaryStage; // the one and only stage
  Button logout = new Button("Logout"); // add logout button functionality on each page
  UserDriverApplication currentDriver; // driver application for user/search functionality
  String currentUsername; // the current user, either logging in or registering
  List<User> searchReturn; // the list of users that are returned by the search
  List<String> recommended; // the list of recommended classes to take 
  
  /**
   * The start method that sets the stage and allows the logout button to be displayed on all
   * pages
   */
  @Override
  public void start(Stage primaryStage) {
    try { // create a new instance of the driver application
      currentDriver = new UserDriverApplication();
      
      // logout button functionality
      logout.setOnAction(toLogout -> {
        currentDriver.logout();
        Scene loginScreen = loginScreen();
        primaryStage.setScene(loginScreen);
        primaryStage.show();
      });
      // get it started
      this.primaryStage = primaryStage;
      Scene login = loginScreen();
      primaryStage.setScene(login);
      primaryStage.show();

    } catch (Exception e) {
      // shouldn't get here
      e.printStackTrace();
    }
  }

  /**
   * to create a map of the student information to be able to create a new user (student)
   * @return
   */
  private Map<String, ArrayList<String>> createNewStudentMap(){
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    map.put(Config.USERNAME_FIELD, new ArrayList<String>());
    map.put(Config.NAME_FIELD, new ArrayList<String>());
    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
    map.put(Config.IS_PUBLIC_FIELD, new ArrayList<String>());
    map.put(Config.MAJORS_FIELD, new ArrayList<String>());
    map.put(Config.CERTIFICATES_FIELD, new ArrayList<String>());
    map.put(Config.CLUBS_FIELD, new ArrayList<String>());
    map.put(Config.SCHOLARSHIPS_FIELD, new ArrayList<String>());
    map.put(Config.COURSES_FIELD, new ArrayList<String>());
    map.put(Config.WORK_EXPERIENCES_FIELD, new ArrayList<String>());
    map.put(Config.YEAROFGRAD_FIELD, new ArrayList<String>());
    return map;
  }

  /**
   * to create a map of the student information to be able to create a new user (faculty)
   * @return
   */
  private Map<String, ArrayList<String>> createNewFacultyMap(){
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    map.put(Config.USERNAME_FIELD, new ArrayList<String>());
    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
    map.put(Config.NAME_FIELD, new ArrayList<String>());
    map.put(Config.OFFICELOCATION_FIELD, new ArrayList<String>());
    map.put(Config.COURSESTAUGHT_FILED, new ArrayList<String>());
    map.put(Config.OFFICEHOURS_FIELD, new ArrayList<String>());
    return map;
  }

  /**
   * Create the log in scene
   * 
   * @return the log in screen to display
   */
  private Scene loginScreen() {
    // log in with valid username
    Text loginPrompt = new Text(20, 30, "Login: ");
    Button submit = new Button("Submit");
    
    // or the user can sign up as either a faculty or student
    Text signupPrompt = new Text(20, 30, "Sign-up: ");
    GridPane grid = new GridPane();
    TextField loginTextField = new TextField();
    TextField signUpTextField = new TextField("Email Address");

    // radio button for the user type when a user wants to sign up
    ToggleGroup type = new ToggleGroup();

    RadioButton userTypeStudent = new RadioButton("Student");
    userTypeStudent.setToggleGroup(type);
    userTypeStudent.setSelected(true);

    RadioButton userTypeFaculty = new RadioButton("Faculty");
    userTypeFaculty.setToggleGroup(type);

    Button signup = new Button("Sign-up!");

    //This is needed to add a blank space between the Radio Button and the Sign-Up block.  If the username is
    //Already taken this space will be filled with a new text field saying that the username has already been chosen.
    Text blankText = new Text("");
    //userNameTakenText will popup if the username is already taken.
    Text userNameTakenText = new Text("This username has already been taken, try again!");
    Text userNameMoreThanOneWord = new Text("Make sure the username email adress is correct.  Must be one word.");

    grid.add(loginPrompt, 0, 1);
    grid.add(loginTextField, 1, 1);
    grid.add(submit, 3, 1);
    grid.add(signupPrompt, 0, 2);
    grid.add(signUpTextField, 1, 2);
    grid.add(userTypeStudent, 1, 3);
    grid.add(userTypeFaculty, 1, 4);
    grid.add(blankText, 1, 5);
    grid.add(signup, 1, 6);

    // button functionality for the signup field to clear when a user clicks on it
    signUpTextField.setOnMouseClicked(event->{
      if (signUpTextField.getText().equals("Email Address")) {
        signUpTextField.clear();
      }
    });

    // button functionality for the signup button, will go to the faculty or student sign up screens
    signup.setOnAction(student -> {
      // display an error if not a 1 word username
      if(signUpTextField.getText().split(" ").length>1) {
        grid.add(userNameMoreThanOneWord, 1, 5);
      }
      else {
    	  this.currentUsername = signUpTextField.getText();
    	  try {
    		  this.currentDriver.register(this.currentUsername);
        	  if (userTypeStudent.isSelected()) {
                  Scene studentSignUp = signupScreenStudent();
                  primaryStage.setScene(studentSignUp);
                }
                  else {
                    Scene facultySignUp = signupScreenFaculty();
                    primaryStage.setScene(facultySignUp);
                  }
          } catch (UserExists e) {
        	  grid.add(userNameTakenText, 1, 5);
          }
      }
    });

    // button functionality for logging in
    submit.setOnAction(toSearch -> {
      try { // try logging in the user with the username provided
        if(currentDriver.login(loginTextField.getText())) {
          // once logged in, display the search screen
          this.currentUsername = loginTextField.getText();
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
        }
        else
          ;
      } // if the user is not registered then display the following message 
      catch (InvalidUsername e) {
        grid.add(new Label("Invalid Username. Please sign up or try again."), 1, 0);
      }
    });

    // align the grid on the screen
    grid.setAlignment(Pos.CENTER);
    BorderPane root = new BorderPane();
    root.setCenter(grid);
    root.setBottom(logout);

    Scene login = new Scene(root, 800, 600);
    login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return login;
  }

  /**
   * Create a map out of the user input from the ArrayList of TextFields.  
   * The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return a map to add the student user
   */
  private Map<String,ArrayList<String>> addStudentUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput){
      map.get(Config.USERNAME_FIELD).add(this.currentUsername);
      
      map.get(Config.PROFILE_TYPE_FIELD).add("student");
      
      String[] nameArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<nameArray.length;curIndex++) {
          nameArray[curIndex] = nameArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.NAME_FIELD).addAll(Arrays.asList(nameArray));
      
      String[] yearOfGradArray = userInput.get(1).getText().split(",");
      for (int curIndex=0;curIndex<yearOfGradArray.length;curIndex++) {
          yearOfGradArray[curIndex] = yearOfGradArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.YEAROFGRAD_FIELD).addAll(Arrays.asList(yearOfGradArray));
      
      String[] majorArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<majorArray.length;curIndex++) {
          majorArray[curIndex] = majorArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.MAJORS_FIELD).addAll(Arrays.asList(majorArray));
      
      String[] clubArray = userInput.get(3).getText().split(",");
      for (int curIndex=0;curIndex<clubArray.length;curIndex++) {
          clubArray[curIndex] = clubArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.CLUBS_FIELD).addAll(Arrays.asList(clubArray));
      
      String[] scholarshipArray = userInput.get(4).getText().split(",");
      for (int curIndex=0;curIndex<scholarshipArray.length;curIndex++) {
          scholarshipArray[curIndex] = scholarshipArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.SCHOLARSHIPS_FIELD).addAll(Arrays.asList(scholarshipArray));
      
      String[] coursesArray = userInput.get(5).getText().split(",");
      for (int curIndex=0;curIndex<coursesArray.length;curIndex++) {
          coursesArray[curIndex] = coursesArray[curIndex].trim().toUpperCase();
      }
      map.get(Config.COURSES_FIELD).addAll(Arrays.asList(coursesArray));
      
      String[] workExperienceArray = userInput.get(6).getText().split(",");
      for (int curIndex=0;curIndex<workExperienceArray.length;curIndex++) {
          workExperienceArray[curIndex] = workExperienceArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.WORK_EXPERIENCES_FIELD).addAll(Arrays.asList(workExperienceArray));
      return map;
  }
  

  /**
   * Create Sign-up scene for students
   */
  private Scene signupScreenStudent() {
      ArrayList<Text> fields = new ArrayList<Text>();
      fields.add(new Text("Name: "));
      fields.add(new Text("Year of graduation: "));
      fields.add(new Text("Major: "));
      fields.add(new Text("Clubs: "));
      fields.add(new Text("Scholarships: "));
      fields.add(new Text("Courses: "));
      fields.add(new Text("Work Experience: "));

      TextField nameTextField = new TextField();
      TextField yearOfGraduationTextField = new TextField();
      TextField majorTextField = new TextField();
      TextField clubsTextField = new TextField();
      TextField scholarshipsTextField = new TextField();
      TextField coursesTextField = new TextField();
      TextField workExperienceTextField = new TextField();
      ArrayList<TextField> signUpStudentTextFieldList = new ArrayList<TextField>() {
          {
              add(nameTextField);
              add(yearOfGraduationTextField);
              add(majorTextField);
              add(clubsTextField);
              add(scholarshipsTextField);
              add(coursesTextField);
              add(workExperienceTextField);
          }
      };
      
      // add the features to a grid
      GridPane grid = new GridPane();
      for (int i = 0; i < fields.size(); i++) {
          grid.add(fields.get(i), 0, i);
          grid.add(signUpStudentTextFieldList.get(i), 1, i);
      }
      Button signup = new Button("Sign-up!");
      grid.add(signup, 1, fields.size());

      // button functionality to go to the search screen once signed up
      signup.setOnAction(toSearch -> {
          Map<String,ArrayList<String>> studentMap = this.createNewStudentMap();
          this.addStudentUserText(studentMap, signUpStudentTextFieldList);
          try {
        	  this.currentDriver.addUser(this.currentUsername, studentMap);
          } catch(UserExists e) {
        	  ;
          }
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
      });

      grid.setAlignment(Pos.CENTER);
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(grid);
      borderPane.setBottom(logout);

      Scene signupScreen = new Scene(borderPane, 800, 600);
      signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return signupScreen;
  }
  
  /**
   * Create a map out of the user input from the ArrayList of TextFields.  
   * The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addFacultyUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput){
      map.get(Config.USERNAME_FIELD).add(this.currentUsername);
      
      map.get(Config.PROFILE_TYPE_FIELD).add("faculty");
      
      String[] nameArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<nameArray.length;curIndex++) {
          nameArray[curIndex] = nameArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.NAME_FIELD).addAll(Arrays.asList(nameArray));
      
      String[] officeBuildingArray = userInput.get(1).getText().split(",");
      for (int curIndex=0;curIndex<officeBuildingArray.length;curIndex++) {
          officeBuildingArray[curIndex] = officeBuildingArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.OFFICELOCATION_FIELD).addAll(Arrays.asList(officeBuildingArray));
      
      String[] classesTaughtArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<classesTaughtArray.length;curIndex++) {
          classesTaughtArray[curIndex] = classesTaughtArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.COURSESTAUGHT_FILED).addAll(Arrays.asList(classesTaughtArray));
      
      String[] officeHoursArray = userInput.get(3).getText().split(",");
      for (int curIndex=0;curIndex<officeHoursArray.length;curIndex++) {
          officeHoursArray[curIndex] = officeHoursArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.OFFICEHOURS_FIELD).addAll(Arrays.asList(officeHoursArray));
      
      return map;
  }
  
  /**
   * Create Sign-up scene for Faculty
   */
  private Scene signupScreenFaculty() {
      ArrayList<Text> fields = new ArrayList<Text>();
      // gather all the features needed to gather information for the user
      fields.add(new Text("Name: "));
      fields.add(new Text("Office building: "));
      fields.add(new Text("Classes taught: "));
      fields.add(new Text("Office Hours: "));
      
      TextField nameTextField = new TextField();
      TextField officeBuildingTextField = new TextField();
      TextField classesTaughtTextField = new TextField();
      TextField officeHoursTextField = new TextField();
      ArrayList<TextField> signUpFacultyTextFieldList = new ArrayList<TextField>() {
          {
              add(nameTextField);
              add(officeBuildingTextField);
              add(classesTaughtTextField);
              add(officeHoursTextField);
          }
      };
      // add to a grid
      GridPane grid = new GridPane();
      for (int i = 0; i < fields.size(); i++) {
          grid.add(fields.get(i), 0, i);
          grid.add(signUpFacultyTextFieldList.get(i), 1, i);
      }
      Button signup = new Button("Sign-up!");
      grid.add(signup, 1, fields.size());

      // button functionality to go to the search screen and save the user
      signup.setOnAction(toSearch -> {
          Map<String, ArrayList<String>> facultyMap = this.createNewFacultyMap();
          try {
        	  this.currentDriver.addUser(this.currentUsername, this.addFacultyUserText(facultyMap, signUpFacultyTextFieldList));
          } catch(UserExists e) {
        	  ;
          }
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
      });

      grid.setAlignment(Pos.CENTER);
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(grid);
      borderPane.setBottom(logout);

      Scene signupScreen = new Scene(borderPane, 800, 600);
      signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return signupScreen;
  }

  /**
   * this will be able to make the map to search for a student
   * @return
   */
  private Map<String, ArrayList<String>> createNewSearchStudentMap(){
	    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
	    map.put(Config.COURSES_FIELD, new ArrayList<String>());
	    map.put(Config.YEAROFGRAD_FIELD, new ArrayList<String>());
	    map.put(Config.MAJORS_FIELD, new ArrayList<String>());
	    map.put(Config.CERTIFICATES_FIELD, new ArrayList<String>());
	    map.put(Config.CLUBS_FIELD, new ArrayList<String>());
	    map.put(Config.SCHOLARSHIPS_FIELD, new ArrayList<String>());
	    map.put(Config.WORK_EXPERIENCES_FIELD, new ArrayList<String>());
	    return map;
	  }
  
  /**
   * this will be able to make the map to search for faculty
   * @return
   */
  private Map<String, ArrayList<String>> createNewSearchFacultyMap(){
	    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
	    map.put(Config.OFFICELOCATION_FIELD, new ArrayList<String>());
	    map.put(Config.COURSESTAUGHT_FILED, new ArrayList<String>());
	    map.put(Config.OFFICEHOURS_FIELD, new ArrayList<String>());
	    return map;
	  }
  
  /**
   * Create a map out of the user input from the ArrayList of TextFields.  
   * The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addSearchStudentUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput, String userType){
	  
	  map.get(Config.PROFILE_TYPE_FIELD).add(userType);
	  
      String[] courseArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<courseArray.length;curIndex++) {
          courseArray[curIndex] = courseArray[curIndex].trim().toUpperCase();
      }
      map.get(Config.COURSES_FIELD).addAll(Arrays.asList(courseArray));
      
      String[] yearOfGradArray = userInput.get(1).getText().split(",");
      for (int curIndex=0;curIndex<yearOfGradArray.length;curIndex++) {
          yearOfGradArray[curIndex] = yearOfGradArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.YEAROFGRAD_FIELD).addAll(Arrays.asList(yearOfGradArray));
      
      String[] majorArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<majorArray.length;curIndex++) {
    	  majorArray[curIndex] = majorArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.MAJORS_FIELD).addAll(Arrays.asList(majorArray));
      
      String[] certificatesArray = userInput.get(3).getText().split(",");
      for (int curIndex=0;curIndex<majorArray.length;curIndex++) {
    	  certificatesArray[curIndex] = certificatesArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.CERTIFICATES_FIELD).addAll(Arrays.asList(certificatesArray));
      
      String[] clubsArray = userInput.get(4).getText().split(",");
      for (int curIndex=0;curIndex<clubsArray.length;curIndex++) {
          clubsArray[curIndex] = clubsArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.CLUBS_FIELD).addAll(Arrays.asList(clubsArray));
     
      String[] scholarshipArray = userInput.get(5).getText().split(",");
      for (int curIndex=0;curIndex<scholarshipArray.length;curIndex++) {
          scholarshipArray[curIndex] = scholarshipArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.SCHOLARSHIPS_FIELD).addAll(Arrays.asList(scholarshipArray));
      
      String[] workExperienceArray = userInput.get(6).getText().split(",");
      for (int curIndex=0;curIndex<workExperienceArray.length;curIndex++) {
         	workExperienceArray[curIndex] = workExperienceArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.WORK_EXPERIENCES_FIELD).addAll(Arrays.asList(workExperienceArray));
      
      return map;
  }
  
  /**
   * Create a map out of the user input from the ArrayList of TextFields.  
   * The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addSearchFacultyUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput, String userType){
     
	  map.get(Config.PROFILE_TYPE_FIELD).add(userType);
	  
      String[] officeBuildingArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<officeBuildingArray.length;curIndex++) {
          officeBuildingArray[curIndex] = officeBuildingArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.OFFICELOCATION_FIELD).addAll(Arrays.asList(officeBuildingArray));
      
      String[] classesTaughtArray = userInput.get(1).getText().split(",");
      for (int curIndex=0;curIndex<classesTaughtArray.length;curIndex++) {
          classesTaughtArray[curIndex] = classesTaughtArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.COURSESTAUGHT_FILED).addAll(Arrays.asList(classesTaughtArray));
      
      String[] officeHoursArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<officeHoursArray.length;curIndex++) {
          officeHoursArray[curIndex] = officeHoursArray[curIndex].trim().toLowerCase();
      }
      map.get(Config.OFFICEHOURS_FIELD).addAll(Arrays.asList(officeHoursArray));
      
      return map;
  }
  
  /**
   * Create search screen
   */
  private Scene search() {
    // radio button for which type of user to search for
    ToggleGroup type = new ToggleGroup();

    RadioButton userTypeStudent = new RadioButton("Student");
    userTypeStudent.setToggleGroup(type);
    userTypeStudent.setSelected(true);

    RadioButton userTypeFaculty = new RadioButton("Faculty");
    userTypeFaculty.setToggleGroup(type);

    HBox userType = new HBox();
    userType.getChildren().addAll(userTypeStudent, userTypeFaculty);

    // for all the features you can search for
    ArrayList<Text> fields = new ArrayList<Text>();
    fields.add(new Text("Courses: "));
    fields.add(new Text("Year of graduation: "));
    fields.add(new Text("Major: "));
    fields.add(new Text("Certificates:  "));
    fields.add(new Text("Clubs: "));
    fields.add(new Text("Scholarships: "));
    fields.add(new Text("Work Experience: "));
    fields.add(new Text("Office building: "));
    fields.add(new Text("Classes taught: "));
    fields.add(new Text("Office Hours: "));

    TextField coursesTextField = new TextField();
    TextField yearsGraduationTextField = new TextField();
    TextField majorTextField = new TextField();
    TextField certificatesTextField = new TextField();
    TextField clubsTextField = new TextField();
    TextField scholarshipsTextField = new TextField();
    TextField workExperienceTextField = new TextField();
    TextField officeBuildingTextField = new TextField();
    TextField classesTaughtTextField = new TextField();
    TextField officeHoursTextField = new TextField();
    ArrayList<TextField> studentSearchTextFieldList = new ArrayList<TextField>() {
      {
        add(coursesTextField);
        add(yearsGraduationTextField);
        add(majorTextField);
        add(certificatesTextField);
        add(clubsTextField);
        add(scholarshipsTextField);
        add(workExperienceTextField);
      }
    };
    ArrayList<TextField> facultySearchTextFieldList = new ArrayList<TextField>() {
    	{
    		add(officeBuildingTextField);
    	    add(classesTaughtTextField);
    	    add(officeHoursTextField);
    	}
    };
    
    // add to a grid
    GridPane grid = new GridPane();
    grid.add(userType, 0, 0);
    for (int i = 1; i < fields.size() + 1; i++) {
      grid.add(fields.get(i - 1), 0, i);
    }

    for (int i = 0;i<studentSearchTextFieldList.size();i++) {
    	grid.add(studentSearchTextFieldList.get(i), 1, i+1);
    }
    
    for (int i = studentSearchTextFieldList.size();i<(studentSearchTextFieldList.size()+facultySearchTextFieldList.size());i++) {
    	grid.add(facultySearchTextFieldList.get(i-studentSearchTextFieldList.size()), 1, i+1);
    }

    Button searchButton = new Button("Search");
    grid.add(searchButton, 1, fields.size() + 1);
    

    // button functionality for searching, will then display the search results screen
    searchButton.setOnAction(toSearch -> {
      if (userTypeStudent.isSelected()) {
    	  Map<String,ArrayList<String>> studentSearchMap = this.createNewSearchStudentMap();
    	  this.addSearchStudentUserText(studentSearchMap, studentSearchTextFieldList, "student");
    	  searchReturn = this.currentDriver.searchUser(studentSearchMap);
    	  
      } else {
    	  Map<String, ArrayList<String>> facultySearchMap = this.createNewSearchFacultyMap();
    	  this.addSearchFacultyUserText(facultySearchMap, facultySearchTextFieldList, "faculty");
    	  searchReturn = this.currentDriver.searchUser(facultySearchMap);
      }
      
      Scene searchResults = searchResults();
      primaryStage.setScene(searchResults);
    });
    
    Button editButton = new Button("Edit Profile");
    
    // goes to the edit user screen
    editButton.setOnAction(event->{
    	//Check for type of user
    	if (this.currentDriver.getType().equals("student")) {
    		this.primaryStage.setScene(this.editStudentScene());
    	}
    	else {
    		this.primaryStage.setScene(this.editFacultyScene());
    	}
    });
    
    grid.setAlignment(Pos.CENTER);
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setTop(editButton);
    borderPane.setBottom(logout);

    Scene searchScreen = new Scene(borderPane, 800, 600);
    searchScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return searchScreen;
  }

  /**
   * makes the list into one string
   * @param list
   * @return a single string
   */
  private String listToString(ArrayList<String> list) {
	  String output = "";
	  for (String i:list) {
		  output+=i;
		  output+=", ";
	  }
	  return output;
  }
  
  /**
   * the screen where a student can edit their profile, is very similar to the sign up 
   * student page, but pre-fills the options already chosen
   * @return the scene to display
   */
  private Scene editStudentScene() {
	  ArrayList<Text> fields = new ArrayList<Text>();
      fields.add(new Text("Name: "));
      fields.add(new Text("Email: "));
      fields.add(new Text("Year of graduation: "));
      fields.add(new Text("Major: "));
      fields.add(new Text("Clubs: "));
      fields.add(new Text("Scholarships: "));
      fields.add(new Text("Courses: "));
      fields.add(new Text("Work Experience: "));
    
      Student studentUser = (Student) this.currentDriver.getUser();
      TextField nameTextField = new TextField(studentUser.getName());
      TextField emailTextField = new TextField(this.currentUsername);
      TextField yearOfGraduationTextField = new TextField(String.valueOf(studentUser.getYearOfGrad()));
      TextField majorTextField = new TextField(studentUser.getMajor().get(0));
      TextField clubsTextField = new TextField(this.listToString(studentUser.getClubs()));
      TextField scholarshipsTextField = new TextField(this.listToString(studentUser.getScholership()));
      TextField coursesTextField = new TextField(this.listToString(studentUser.getCourses()));
      TextField workExperienceTextField = new TextField(this.listToString(studentUser.getWorkExperience()));
      ArrayList<TextField> editStudentTextFieldList = new ArrayList<TextField>() {
          {
              add(nameTextField);
              add(emailTextField);
              add(yearOfGraduationTextField);
              add(majorTextField);
              add(clubsTextField);
              add(scholarshipsTextField);
              add(coursesTextField);
              add(workExperienceTextField);
          }
      };
      
      GridPane grid = new GridPane();
      for (int i = 0; i < fields.size(); i++) {
          grid.add(fields.get(i), 0, i);
          grid.add(editStudentTextFieldList.get(i), 1, i);
      }
      Button confirmChangesButton = new Button("Confirm changes");
      grid.add(confirmChangesButton, 1, fields.size());

      // button functionality, saves profile and goes to search screen
      confirmChangesButton.setOnAction(toSearch -> {
    	  Map<String,ArrayList<String>> studentMap = this.createNewStudentMap();
    	  this.addStudentUserText(studentMap, editStudentTextFieldList);
    	  this.currentDriver.editUser(studentMap);
          Scene search = this.search();
          primaryStage.setScene(search);
          primaryStage.show();
      });

      grid.setAlignment(Pos.CENTER);
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(grid);
      borderPane.setBottom(logout);

      Scene signupScreen = new Scene(borderPane, 800, 600);
      signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return signupScreen;
  }
  
  
  /**
   * a screen to edit the faculty user profile, similar to the add faculty user screen
   * but is prefilled with the already choosen options
   * @return the scene to displa
   */
  private Scene editFacultyScene() {
	  ArrayList<Text> fields = new ArrayList<Text>();
      fields.add(new Text("Name: "));
      fields.add(new Text("Email: "));
      fields.add(new Text("Office building: "));
      fields.add(new Text("Classes taught: "));
      fields.add(new Text("Office Hours: "));
      
      Faculty facultyUser = (Faculty) this.currentDriver.getUser();
      TextField nameTextField = new TextField(facultyUser.getName());
      TextField emailGraduationTextField = new TextField(facultyUser.getEmail());
      TextField officeBuildingTextField = new TextField(this.listToString(facultyUser.getOfficeLocation()));
      TextField classesTaughtTextField = new TextField(this.listToString(facultyUser.getCoursesTaught()));
      TextField officeHoursTextField = new TextField(this.listToString(facultyUser.getOfficeHours()));
      ArrayList<TextField> editFacultyTextFieldList = new ArrayList<TextField>() {
          {
              add(nameTextField);
              add(emailGraduationTextField);
              add(officeBuildingTextField);
              add(classesTaughtTextField);
              add(officeHoursTextField);
          }
      };

      GridPane grid = new GridPane();
      for (int i = 0; i < fields.size(); i++) {
          grid.add(fields.get(i), 0, i);
          grid.add(editFacultyTextFieldList.get(i), 1, i);
      }
      Button editUserButton = new Button("Confirm");
      grid.add(editUserButton, 1, fields.size());

      // button functionality
      editUserButton.setOnAction(toSearch -> {
          Map<String, ArrayList<String>> facultyMap = this.createNewFacultyMap();
          this.addFacultyUserText(facultyMap, editFacultyTextFieldList);
          this.currentDriver.editUser(facultyMap);
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
      });

      grid.setAlignment(Pos.CENTER);
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(grid);
      borderPane.setBottom(logout);

      Scene signupScreen = new Scene(borderPane, 800, 600);
      signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return signupScreen;
  }
  
  /**
   * search results screen
   */
  private Scene searchResults() {
    Text users = new Text("Users with your search criteria: ");
    ListView<String> toDisplay = new ListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList();
    if(searchReturn != null) {
    for(int i=0; i<searchReturn.size(); i++) 
      items.add(searchReturn.get(i).getName() + ": " + searchReturn.get(i).getEmail());
    
    toDisplay.setItems(items);
    }
    else {
      items.add("Search returned 0 users.");
      toDisplay.setItems(items);
    }
    
    Text otherInfo = new Text("More information about your search: ");
    ListView<String> reco = new ListView<String>();
    ObservableList<String> recoItems = FXCollections.observableArrayList();
    if(recommended != null) {
    for(int i=0; i<recommended.size(); i++) 
      recoItems.add(recommended.get(i));
    reco.setItems(recoItems); 
    }
    else {
      recoItems.add("No recommended classes.");
      reco.setItems(recoItems);
    }
    double totalUser = this.currentDriver.getTotalUser();
    double searchedUser = 0;
    try {
    	searchedUser = this.searchReturn.size();
    } catch (NullPointerException e) {
    	;
    }
    double percentUser = searchedUser/totalUser*100;
    percentUser = Math.round(percentUser);
    Text otherInfo2 = new Text("This search returned "+percentUser+"% of users.");
    GridPane grid = new GridPane();
    grid.add(users, 0, 0);
    grid.add(toDisplay,1,1);
    grid.add(otherInfo, 0, 2);
    grid.add(otherInfo2, 1, 3);

    // set button functionality, will return to the search screen
    Button searchAgain = new Button("Search again");
    searchAgain.setOnAction(toSearch -> {
      Scene search = search();
      primaryStage.setScene(search);
      primaryStage.show();
    });
    

    Button saveUser = new Button("Save user to database"); // to be able to choose whether or not to save the user
    saveUser.setOnAction(save -> {
      try {
        currentDriver.addUserToJSON();
      } catch (FileNotFoundException e) {

        e.printStackTrace();

      }
    });
    
    HBox upperRight = new HBox();
    upperRight.getChildren().addAll(searchAgain, saveUser);
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);
    borderPane.setRight(upperRight);

    Scene searchResults = new Scene(borderPane, 800, 600);
    searchResults.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return searchResults;
  }

  /**
   * main that launches the program
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}