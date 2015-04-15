import java.util.*;

public class Exercise1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Variable declaration
		int userNum;
		Scanner check = new Scanner(System.in);
		int compGen;
		Random rand = new Random();


		compGen = rand.nextInt(50)+1;
		compGen = 50;
		int compGenMax = compGen + 10;
		int compGenMin = compGen - 10;
		boolean close = false;
		int gameCount = 0;

		//calculations
		do{
			System.out.println("Guess a number: ");
			userNum = check.nextInt();
			if (gameCount>3){
				close = true;
				System.out.println("The computer chose: "+ compGen);
				System.out.println("Sorry");
			}else{
				
				if (compGenMin<userNum && userNum < compGenMax){
					System.out.println("The computer chose: "+ compGen);
					System.out.println("Great Job");
					close = true;

				}else{
					System.out.println("Please try again: ");
					close = false;
					gameCount++;
				}
			}
		}while(close == false);
		check.close();
	}

}
