//Title:           Badgers Settle Down
//Files:           Sett.java, Badger.java, P9Tests.java, BadgersSettleDown.java
//Course:          COMPSCI300 001, term 1, 2018
//
//Author:          Callan Patel
//Email:           ccpatel2@wisc.edu
//Lecturer's Name: Alexi Brooks
/**
 * This class represents a Badger
 *
 */
public class Badger {
	private Badger leftLowerNeighbor; //left badger of current badger
	private Badger rightLowerNeighbor; //right badger of current badger
	private int size; //size of current badger
	
	//constructs new badger
	//@param size of new badger
	public Badger(int size)
	{
		this.size = size; //sets size to the param size
		leftLowerNeighbor = null; //sets left badger to null
		rightLowerNeighbor = null; //set right badger to null
	}
	
	//@return left badger
	public Badger getLeftLowerNeighbor()
	{
		return leftLowerNeighbor;
	}
	
	//@return right badger
	public Badger getRightLowerNeighbor()
	{
		return rightLowerNeighbor;
	}
	
	//@return size
	public int getSize()
	{
		return size;
	}
	
	//Sets the current badgers left badger to the new badger
	//@param badger to be set
	public void setLeftLowerNeighbor(Badger badger)
	{
		this.leftLowerNeighbor = badger;
	}
	
	//Sets the current badgers right badger to the new badger
	//@param badger to be set
	public void setRightLowerNeighbor(Badger badger)
	{
		this.rightLowerNeighbor = badger;
	}

}
