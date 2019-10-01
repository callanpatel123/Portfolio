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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDriverTest {
  UserDriverApplication userDriver;
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    userDriver = new UserDriverApplication();
  }

  @AfterEach
  void tearDown() throws Exception {
    userDriver = null;
  }

  /**
   * Tests to be sure the InvalidUsername exception is thrown as expected in login()
   */
  @Test
  void test001_InvalidUsername_login() {
    try {
      userDriver.login("eheying");
      fail("InvalidUsername not thrown in test001.");
    } catch (InvalidUsername e) {
      // do nothing, this is expected
    } catch (Exception e) {
      fail("Unexpected exception in test001.");
    }
  }
  
  /**
   * Tests to be sure the login works as expected with a valid username
   */
  @Test
  void test002_valid_login() {
    try {
      if(!userDriver.login("ben@wisc.edu")) // should return !true
        fail("Should be able to log in a vaild user (test002).");
    } catch (InvalidUsername e) {
      fail("InvalidUsername thrown in test002.");
    } catch (Exception e) {
      fail("Unexpected exception in test002.");
    }
  }
  
  /**
   * Tests to be sure the login fails if another user is already logged in
   */
  @Test
  void test003_valid_login_but_already_logged_in() {
    try {
      if(userDriver.login("ben@wisc.edu")) {
        if(userDriver.login("ccpatel2@wisc.edu"))
          fail("Should not be able to log in 2 users at the same time (test003");
      }
    } catch (InvalidUsername e) {
      fail("InvalidUsername thrown in test003.");
    } catch (Exception e) {
      fail("Unexpected exception in test003.");
    }
  }

  /**
   * Tests to be sure the UserExists exception is thrown as expected in register()
   */
  @Test
  void test004_UserExists_register() {
    try {
      userDriver.register("ben@wisc.edu");
      fail("UserExists not thrown in test004.");
    } catch (UserExists e) {
      // do nothing, this is expected
    } catch (Exception e) {
      fail("Unexpected exception in test004.");
    }
  }

  /**
   * Tests to be sure the user is logged out properly by checking if a new user can log in
   */
  @Test
  void test005_valid_logout() {
    try {
      userDriver.login("ben@wisc.edu");
      userDriver.logout();
      if(!userDriver.login("ccpatel2@wisc.edu")) // should return !true
        fail("Unable to log in new user after one was logged out in test005.");
    } catch(InvalidUsername e) {
      fail("InvalidUsername thrown inproperly at test005.");
    }catch (Exception e) {
      fail("Unexpected exception in test005.");
    }
  }

  /**
   * Tests to be sure a new user can be added
   */
  @Test
  void test006_user_type() {
    try {
      userDriver.login("ben@wisc.edu");
      if(userDriver.getUser() == null)
        fail("User should be type student in test006.");
    }catch(Exception e) {
      e.printStackTrace();
      fail("Unexpected exception in test006.");
    }
  }

  /**
   * Tests to be sure the username is correct
   */
  @Test
  void test007_user_type() {
    try {
      userDriver.login("ben@wisc.edu");
      if(!userDriver.getUsername().equals("ben@wisc.edu"))
        fail("Username should match what was used to login in test007");
    }catch(Exception e) {
      fail("Unexpected exception in test007.");
    }
  }
  
  /**
   * Tests to ensure properly writing to JSON file
   */
//  @Test
  void test008_write_to_JSON() {
    try {
      userDriver.register("test");
     
      userDriver.addUserToJSON();
      
      
    }catch(Exception e) {
      fail("Unexpected exception in test008.");
    }
  }
}
