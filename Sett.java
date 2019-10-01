//Title:           Badgers Settle Down
//Files:           Sett.java, Badger.java, P9Tests.java, BadgersSettleDown.java
//Course:          COMPSCI300 001, term 1, 2018
//
//Author:          Callan Patel
//Email:           ccpatel2@wisc.edu
//Lecturer's Name: Alexi Brooks
/**
 * This class represents a Sett of badgers in the form of a binary search tree
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Sett {
	private Badger topBadger; //The root badger
	
	//Constructs a new Sett
	public Sett()
	{
		topBadger = null; //Sets the topBadger to null--empty sett
	}
	
	//Creates new Badger
	//@param size of new badger
	public void settleBadger(int size) throws java.lang.IllegalArgumentException
	{
		if(topBadger == null) //If the topBadger is null, the topBadger is set to the new badger
			topBadger = new Badger(size);
		else
			settleHelper(topBadger, new Badger(size)); //If the topBadger isn't null, the settleHelper method is called
	}
	
	//Recursive method that adds the new badger to the set
	//@param current badger
	//@param newBadger is the new badger to be added to the set
	private void settleHelper(Badger current, Badger newBadger) throws java.lang.IllegalArgumentException
	{
		if(current.getLeftLowerNeighbor() == null || current.getRightLowerNeighbor() == null) //if the current node is a leaf
		{
			if(newBadger.getSize() < current.getSize() && current.getLeftLowerNeighbor() == null) //if new badgers size is less than current size and left badger is null
			{
				current.setLeftLowerNeighbor(newBadger); //current badgers left is set to the new badger
			}
			else if(newBadger.getSize() > current.getSize() && current.getRightLowerNeighbor() == null) ////if new badgers size is greater than current size and right badger is null
			{
				current.setRightLowerNeighbor(newBadger); //current badgers right is set to the new badger
			}
		}
		
		else if(newBadger.getSize() < current.getSize()) //if the newBadger size is less than the current badger size
		{
			settleHelper(current.getLeftLowerNeighbor(), newBadger); //recursive call with the currents left badger
		}
		
		else if(newBadger.getSize() > current.getSize()) //if the newBadger size is greater than the current badger size
		{
			settleHelper(current.getRightLowerNeighbor(), newBadger); //recursive call with the currents right badger
		}
		
		else //if the newBadger size is the same as the current size, throw an IllegalArgumentException because the badger already exists
			throw new IllegalArgumentException("WARNING: failed to settle the badger with size "+ newBadger.getSize()+", as there is already a badger with the same size in this sett");
	}
	
	//counts the number of badgers in the sett
	//@return number of badgers in the sett
	public int countBadger()
	{
		return countBadgerHelper(topBadger); //calls the recursive helper method
	}
	
	//recursive method that counts the badgers in the sett
	//@param current badger
	//@return number of badgers
	private int countBadgerHelper(Badger current)
	{
		if(current == null) //if the current badger is null
			return 0;
		else //recursive call adding 1 to the recursive call with the left badger first and then the right badger of the current badger
			return 1 + countBadgerHelper(current.getLeftLowerNeighbor()) + countBadgerHelper(current.getRightLowerNeighbor());
	}
	
	//find the badger with the size asked for
	//@param size of badger
	//@return badger with the size
	public Badger findBadger(int size) throws java.util.NoSuchElementException
	{
		return findHelper(topBadger, size); //calls the recursive helper method
	}
	
	//find the badger with the size asked for
	//@param current badger
	//@param size
	//@return badger
	private Badger findHelper(Badger current, int size) throws java.util.NoSuchElementException
	{
		if(current != null) //if the badger isn't null
		{
			if(current.getSize() == size)
				return current; //returns current if the current badgers size is equal to the size that is being looked for
			else if(current.getSize() < size) { //if the current size is less than the size that is being looked for
				return findHelper(current.getRightLowerNeighbor(), size); //recursive call with the currents right badger and the same size being looked for
			}
			else if(current.getSize() > size) { //if the current size is greater than the size that is being looked for
				return findHelper(current.getLeftLowerNeighbor(), size); //recursive call with the currents left badger and the same size being looked for
			}
		}
		throw new NoSuchElementException("WARNING: failed to find a badger with size "+ size+" in the sett"); //if the badger doesn't exist,
	}																										  //a NoSuchElementExcpetion is thrown
	
	//turns the sett into a list of badgers in ascending order
	//@return list of badgers
	public java.util.List<Badger> getAllBadgers()
	{
		List<Badger>list = new ArrayList<Badger>(); //creates new list that is empty at the start
		getAllHelper(topBadger, list); //calls the recursive helper method
		return list; //returns the list after the recursive call is done
	}
	
	//turns the sett into a list of badgers in ascending order
	//@param current badger
	//@param allBadger list
	private void getAllHelper(Badger current, java.util.List<Badger> allBadgers)
	{
		if(current != null) //if the current badger is not null
		{
			getAllHelper(current.getLeftLowerNeighbor(), allBadgers); //recursive call with currents left badger and same list
			allBadgers.add(current); //adds the current badger
			getAllHelper(current.getRightLowerNeighbor(), allBadgers); //recursive call with the currents right badger and same list
		}
	}
	
	//gets the largest badger in the sett
	//@returns largest badger
	public Badger getLargestBadger()
	{
		Badger current = topBadger; //creates copy of root badger
		while(current.getRightLowerNeighbor() != null) //loop while the currents right badger isn't null
		{
			current = current.getRightLowerNeighbor(); //sets current badger to the current badgers right badger
		}
		
		return current; //returns the largest badger
	}
	
	//gets the height of the sett tree of badgers
	//@return height of tree
	public int getHeight() 
	{
		return getHeightHelper(topBadger); //calls recursive helper method
	}
	
	//get the height of the sett tree of badgers
	//@param current badger
	//@return height of tree
	private int getHeightHelper(Badger current)
	{
		if(current == null) //if the current is null, returns 1
			return 1;
		
		int left = getHeightHelper(current.getLeftLowerNeighbor()); //creates an int for the height of all the left badgers
		int right = getHeightHelper(current.getRightLowerNeighbor()); //creates an int for the height of all the right badgers
		
		if(left > right) //if the left int is larger, the left int height is returned plus 1, taking into account the current node
			return left + 1;
		else //of the right int is larger, the right int height is return plus 1, taking into account the current node
			return right + 1; 
	}
	
	//checks if the sett tree of badgers is empty
	//@return true if the tree is empty and false if not
	public boolean isEmpty()
	{
		return topBadger == null;
	}
	
	//@return topBadger
	public Badger getTopBadger()
	{
		return topBadger;
	}
	
	//clears the sett tree of badgers
	public void clear()
	{
		topBadger = null; //setts the root badger to null
	}
	
	

}
