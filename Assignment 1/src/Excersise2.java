import java.util.*;

public class Excersise2 {

	public static int gameCount = 0;
	public static int p1;
	public static int p2;
	public static int chips;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name1;
		String name2;
		boolean check = false;
		String playerChoice;
		boolean choice = true, nameC=true;
		Scanner input = new Scanner(System.in);


		//Game start
		do {
			System.out.println("********Play Even's *********");
			System.out.println("What is the name of the first player?");
			name1 = input.nextLine();
			System.out.println("What is the name of the second player?");
			name2 =input.nextLine();

			while(nameC){
			if(name1.equals(name2)){
				System.out.println("Players cannot have the same name");
				System.out.println("Please choose another name  for player 2");
				name2=input.nextLine();
				nameC=true;
			}else{
				nameC=false;
				}
			}
			System.out.println("");
			System.out.println("Please select total chips to play with: ");;
			//determine total chips
			do{
				chips = input.nextInt();
				input.nextLine();
				if (chips<3){
					System.out.println("You can't have fewer than 3 chips \n Choose another number");
					chips =0;
				}else if(chips%2==0){
					System.out.println("You must have an odd number of chips");
					System.out.println("Please try again");
					chips =0;
				}else{
					check = true;
				}
			}while(check==false);



			//game start
			do{
				System.out.println("********************************************");
				System.out.println("");
				System.out.println( name1 + " has "+ p1 +" chips.");
				System.out.println(name2 + " has " + p2 + " chips.");

				//Min chip
				int minChip = (chips)/2;

				if (gameCount == 0){
					chips = chipsMoved(name1, minChip, chips, input);
					gameCount++;
				}else{
					chips = chipsMoved(name2, minChip, chips, input);
					gameCount=0;
				}
			}while(chips!=0);

			System.out.println("There are now 0 chips in the pot");
			System.out.println(name1+" has "+ p1+" chips.");
			System.out.println(name2 +" has "+ p2 +" chips.");
			if ((p1%2)== 0){
				System.out.println(name1+" has won the match!");
			}else{
				System.out.println(name2+" has won the match!");
			}
			//playing again
			System.out.println("Would you like to play again y/n?");
			playerChoice = input.nextLine().toString();
			if (playerChoice.equals("Y")|| playerChoice.equals("y")){
				choice =true;
			}else {
				choice =false;
			}
		}while(choice);

		input.close();
	}


	public static int chipsMoved(String name, int minChips, int chips, Scanner in){
		Scanner input = in;
		int totalChips = chips;
		int rem;
		int mC= minChips;
		boolean check = false;
		String nm = name;
		System.out.println("It's your turn "+ nm);
		System.out.println("There are "+ totalChips +" chips remaining");
		System.out.println("You may take any numer of chips from 1 to "+ mC+".");

		if(chips==1){
			if(gameCount == 0){
				p1 +=1;
				totalChips -= 1;
			}else{
				p2 +=1;
				totalChips -= 1;
			};
		}else{
			while(check==false){
				System.out.println("How many will you take "+ nm + "?");
				rem = input.nextInt();
				input.nextLine();
				if (rem == 0){
					System.out.println("Illegal move: you must take at least 1 chip");
				}else if(rem>mC){
					System.out.println("Illegal move: you may not take more than "+ mC + " chip(s)");
				}else{
					totalChips = totalChips-rem;
					if (gameCount ==0){
						p1 += rem;
					}else{
						p2 += rem;
					}

					System.out.println("*******************************");
					check = true;

				}
			}
		}

		return totalChips;
	}

}
