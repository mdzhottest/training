package assignment2;

import java.util.*;


public class List {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] f1 = {"pear", "banana", "tangerine", "strawberry", "blackberry"};

		//adding elements to list
		ArrayList<String> fruits = new ArrayList<String>(Arrays.asList(f1));

		
		//display elements in order of insertion 
		//with iterator
		Iterator<String> itr = fruits.iterator();
		while(itr.hasNext()){
			Object element = itr.next();
			System.out.println(element);
		}
		
		screenBreak();
		
		//display the contents in reverse order using a listiterator
		ListIterator<String> lIterator = fruits.listIterator(fruits.size());
		while(lIterator.hasPrevious()){
			System.out.println(lIterator.previous());
		}
		
		screenBreak();
		
		//Insert a second banana between tangerine and strawberry
		fruits.add(4, "banana");
		
		//display contents
		for(String s : fruits){
			System.out.println(s);
		}
		
		screenBreak();
	}
	
	public static void screenBreak(){
		System.out.println("***************************");
	}

}
