package source;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Driver {

	public static void main(String[] args) {
		
		LinkedList<Food> x = new LinkedList<Food>();
		
		try{
			FileReader fr = new FileReader("food.txt");
			BufferedReader br = new BufferedReader(fr);
			
			Scanner scan = new Scanner(br);
			String[] str = new String[4];
	        while(scan.hasNextLine()){
				String line = scan.nextLine();
		        if((line.length() > 0)){
		        	str = line.split(" ");
			        String name = str[0];
			        String group = str[1];
			        int calories = Integer.parseInt(str[2]);
			        double percentage = Double.parseDouble(str[3]);
			        Food f = new Food(name, group, calories, percentage);
			        x.addAtTail(f);
		        }
	        }
			br.close();
			scan.close();
		}
		catch(IOException e){
			System.out.println("Not working");
		}
		
		System.out.println("---------------------------------");
		System.out.println("Welcome to Parkland Meal Selector");
		System.out.println("---------------------------------");
		System.out.println("Please select from the following");
		System.out.println("(Press enter twice after each submission)");
		System.out.println("1 - List food database");
		System.out.println("2 - Create meal by manual selection");
		System.out.println("3 - Create meal by random selection");
		System.out.println("4 - Remove foods high in calorie");
		System.out.println("5 - Exit");
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			int choice = scan.nextInt();
			switch(choice) {
				case 1:
					System.out.println("============================================================================");
					System.out.println("Name                Food Group          Calories            Daily percentage   ");
					System.out.println("============================================================================");
					
					x.roll();
					break;
				case 2:
					String foodname[] = new String[3];
					int caloriesum = 0;
					double dailypercentage = 0;
					int i = 0;
					while(i < 3) {
						boolean flag = false;
						System.out.print("Enter food name: ");
						foodname[i] = scan.next();
						for(int k = 0; k < x.getCurrentSize(); k++) {
							if(foodname[i].equals(x.getAtIndex(k).getName())) {
								i++;
								flag = true;
								caloriesum = caloriesum + x.getAtIndex(k).getCalories();
								dailypercentage = dailypercentage + x.getAtIndex(k).getPercentage();
								break;
							}
						}
						if(flag == false) {
							System.out.println("Food " + foodname[i] + " not in database, try again");
						}
					}
					
					System.out.println("===============================");
					System.out.println("Your selected meal");
					System.out.println("===============================");
					System.out.println("Foods: " + foodname[0] + " " + foodname[1] + " " + foodname[2]);
					System.out.println("Total calorie count: " + caloriesum);
					System.out.println("Total daily percentage: " + dailypercentage);
					System.out.println("===============================");
					break;
				case 3:
					
					int num1, num2, num3;
					
					while(true) {
						Random a = new Random();
						num1 = a.nextInt(x.getCurrentSize());
						Random b = new Random();
						num2 = b.nextInt(x.getCurrentSize());
						Random c = new Random();
						num3 = c.nextInt(x.getCurrentSize());
						
						if(num1 != num2 && num1 != num3 && num2 != num3) {
							break;
						}
					}
					
					System.out.println("===============================");
					System.out.println("Your selected meal");
					System.out.println("===============================");
					System.out.println("Foods: " + x.getAtIndex(num1).getName() + " " + x.getAtIndex(num2).getName() + " " + x.getAtIndex(num3).getName());
					System.out.println("Total calorie count: " + (x.getAtIndex(num1).getCalories() + x.getAtIndex(num2).getCalories() 
							+ x.getAtIndex(num3).getCalories()));
					System.out.println("Total daily percentage: " + (x.getAtIndex(num1).getPercentage() + x.getAtIndex(num2).getPercentage() 
							+ x.getAtIndex(num3).getPercentage()));
					System.out.println("===============================");
					break;
				case 4:
					System.out.print("Enter calorie limit: ");
					int calorielimit = scan.nextInt();
					for(int index = 0; index < x.getCurrentSize(); index++) {
						if(x.getAtIndex(index).getCalories() > calorielimit) {
							x.removeAtIndex(index);
							index--;
						}
					}
					System.out.println("============================================================================");
					System.out.println("Name                Food Group          Calories            Daily percentage   ");
					System.out.println("============================================================================");
					x.roll();
					break;
				default:
					scan.close();
					System.out.println("Done");
                	return;
			}
		}
		
	}

}
