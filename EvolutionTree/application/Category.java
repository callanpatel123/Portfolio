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
import java.util.List;

/**
 * Category Class
 * 
 * @author erica, ben, aj, callan
 *
 */

//this class represents a category in the form of a hashtable using linked list buckets
public class Category{
	//Name of the category
	private String name;
	
	//inner node class
	private class Node{
		private User user;
		private Node next; //next node reference
		
		//constructor
		//@param User user
		private Node(User user) {
			this.user = user;
			next = null;
		}
		
		//returns the User for this node
		private User getUser() {
			return user;
		}
	}
	
	//inner linkedList class
	private class linkedList{
		private Node head;
		private int size; //size of the list
		
		//constructor
		private linkedList() {
			head = null;
			size = 0;
		}
		
		//insert method to insert a user
		//@param User user
		private void insert(User user) {
			if(head == null) { //checks if the list is empty and makes the user the head if so
				head = new Node(user);
				size++; //increments the size
			}
			else { //if list isn't empty, the list is iterated through till the end, and then is added
				Node temp = head;
				while(temp.next != null) {
					temp = temp.next;
				}
				temp.next = new Node (user);
				size++;
			}
		}
		
		//removes a user
		//@param User user
		//@return true if the user was removed, false if not
		private boolean remove(User user) {
			if(!contains(user)) { //checks if the user exists in this category
				return false; //returns false if doesn't exist
			}
			else { //if user exists in this category, checks head node first
				if(head.user.getEmail().equals(user.getEmail())) { //if head node is to be deleted, head reference is set to the next node
					head = head.next;                              //and then returns true
					size--;
					return true;
				}
				else { //iterates through list until node is found, and then removed, and returns true
					Node temp = head;
					while(!(temp.next.user.getEmail().equals(user.getEmail()))) {
						temp = temp.next;
					}
					temp.next = temp.next.next;
					size--;
					return true;
				}
			}
		}
		
		
		//@param User user
		//@return true if the category contains the user, false if not
		private boolean contains(User user) {
			Node temp = head;
			while(temp != null) { //iterates through the list and returns true of the node is found
				if(temp.user.getEmail().equals(user.getEmail())) {
					return true;
				}
				temp = temp.next;
			}
			return false; //node was not found
		}
		
		
		//gets the email(username) of the user
		//@param User user
		//@return String email
		private String get(User user) {
			if(!contains(user)) {
				return null; //returns nothing if the category doesn't contain the user
			}
			Node temp = head;
			while(!(temp.user.getEmail().equals(user.getEmail()))) { //iterates through until the user is found
				temp = temp.next;
			}
			return temp.user.getEmail(); //returns the email of the user
		}
		
		//@return User 
		private User getHead() {
			return head.user;
		}
		
	}
	
	private int capacity; //table size
	private double loadFactorThreshold; //loadfactor value that starts resizing
	int numKeys; //number of keys in HashTable
	private linkedList[]table; //the HashTable of type linkedList
	
	//Constructor
	//@param String name of the category
	public Category(String name) {
		table = new linkedList[11]; //creates table with initial size of 11
		capacity = 11;
		loadFactorThreshold = 0.75; //sets LoadFactor threshold to 0.75
		this.name = name;
	}
	
	//Constructor
	//@param intinitialCapacity
	//@param double loadFactorThreshold
	//@param String name
	public Category(int initialCapacity, double loadFactorThreshold, String name) {
		this.name = name;
		capacity = initialCapacity;
		table = new linkedList[initialCapacity]; //sets initial hashtable size
		this.loadFactorThreshold = loadFactorThreshold;

	}
	
	//inserts a new user into the hastable
	//@param User user
	public void insert(User user) {
		
		if(table[Math.abs(user.hashCode())%capacity] == null) { //if hashIndex of Hashtable is null, create new linkedList at index
			table[Math.abs(user.hashCode())%capacity] = new linkedList();
		}
		table[Math.abs(user.hashCode())%capacity].insert(user); //call linkedList insert method
		numKeys++; //increment numKeys
		
		if(getLoadFactor() >= loadFactorThreshold) { //if loadFactor is greater than or equal to the loadFactorThreshold
			linkedList[]temp = new linkedList[(2*capacity) + 1]; //create temp hashTable with 2 time the size of the original hashTable + 1
			int capTemp = 2*capacity + 1;
			
			for(int count = 0; count < table.length; count++) { //loops through original hashTable
				if(table[count] != null) {  //rehasheshes the non-null values from the original hashTable and places them in new table
					temp[Math.abs(table[count].getHead().hashCode())%capTemp] = table[count];
				}
			}
			table = temp; //sets HashTable to temp
			capacity = capTemp; //reestablishes the capacity 
		} 
	}
	
	//removes a user from the hashtable
	//@param User user
	//@return true if the user was removes, false if not
	public boolean remove(User user) {
		if(table[Math.abs(user.hashCode())%capacity] != null && table[Math.abs(user.hashCode())%capacity].contains(user)) {
			//if hashIndex of key in hashTable isn't empty and linkedList bucket at index contains the key, call linkedList remove method
			numKeys--; //decrease numKeys
			return table[Math.abs(user.hashCode())%capacity].remove(user); 
		}
		return false; //returns false if key doesn't exist
		
	}
	
	
	//gets the user that corresponds with the email(username) given
	//@param String username
	//@return User
	public User get(String username) {
		//Iterates through whole hashtable array
		for(int count = 0; count < table.length; count++) {
			if(table[count] != null) {
				if(table[count].size > 0) { //iterates through the linkedlist bucket at each index that has a bucket
					Node temp = table[count].head;
					while(temp != null) {
						if(temp.user.getEmail().equals(username)) {
							return temp.user; //returns the user if user if found
						}
						temp = temp.next;
					}
				}
			}
		}
		return null; //User was not found with the username given
	}
	
	
	//gets all the users in this category
	//@return List<User> list
	public List<User> getAll(){
		List<User> list = new ArrayList<User>(); //local list
		for(int count = 0; count < table.length; count++) { //iterates through whole hashtable array
			if(table[count] != null) {
				if(table[count].size > 0) { //iterates through the linkedListbucket at each index that has a bucket
					Node temp = table[count].head;
					while(temp != null) {
						list.add(temp.user);
						temp = temp.next; //adds the user to the list
					}
				}
			}
		}
		return list; //returns list
	}
	
	//@return numKeys
	public int numKeys() {
		return numKeys;
	}

	//@return loadFactorThreshold
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	//@return loadFactor (numKeys/capacity)
	public double getLoadFactor() {
		return ((double)numKeys/capacity);
	}

	//@return capacity
	public int getCapacity() {
		return capacity;
	}
	
	//returns the hashtable
	public linkedList[] getTable() {
		return table;
	}
}
