package assignment2;

import java.util.*;

public class SetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a set called mySet
	    Set mySet = new HashSet();

	    // Ensure that this set contains an interesting selection of fruit 
	    String fruit1 = "pear", fruit2 = "banana", fruit3 = "tangerine",
	                            fruit4 = "strawberry", fruit5 = "blackberry";
	    mySet.add( fruit1 );
	    mySet.add( fruit2 );
	    mySet.add( fruit3 );
	    mySet.add( fruit2 );
	    mySet.add( fruit4 );
	    mySet.add( fruit5 );

	    // Display contents of mySet
	    System.out.println( "mySet now contains:" );
	    System.out.println( mySet );
	    
	    //Display the contents of mySet
	    System.out.println("Number of elements in HashSet are: "+ mySet.size());
	    
	    //Removing blackberry and strawberry
	    mySet.remove(fruit4);
	    mySet.remove(fruit5);
	   
	    // display contents of mySet for confirmation
	    System.out.println("mySet now contains: ");
	    System.out.println(mySet);
	    
	    //remove the remaining fruit using single method incovation
	    mySet.clear();
	    //show that the set is empty with isEmpty method
	    if(mySet.isEmpty()){
	    	System.out.println("HashSet is empty");
	    }else{
	    	System.out.println("there are still elements in HashSet");
	    }

	}

}
