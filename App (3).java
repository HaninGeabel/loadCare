
/**
 * App - Write a description here.
 * 
 * @author (Henry, Hanin,Roger) 
 * @version (a version number or a date)
 */
// Standard import for the Scanner class
import java.util.*;
import java.io.*;
public class App {
    public static void main (String [] args) throws IOException {
        ArrayList<Car> myCars = new ArrayList<>();
        Car car;

        // Create first car object, print it, and add it to the list
        // Order of arguments: make, model, year, rate, needsService, isRented, renterName, renterPhone
        //car = new Car("Volkswagen", "Beetle" , 1966, 180, false, false, "", "");
        //myCars.add(car);
        
        // Create second car object, print it, and add it to the list
        //car = new Car("Chevrolet", "Camaro RS", 1970, 225, false, true, "AJ Walker", "587-333-3333");
        //myCars.add(car);
        System.out.println("*** Welcome to Mo's Classic Car Rentals ***");
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter car data filename: ");
        String classicCars = in.nextLine();
        File carsFile = new File (classicCars);
        Scanner inputCarList = new Scanner (carsFile);
        //reading per line and create an arryList with Car objects
        if(carsFile.exists()){
            while(inputCarList.hasNextLine()){
                 String[] line = inputCarList.nextLine().split(",");
                 if(!(line[5].equals("false"))){// check if renteed if_rented 
                   myCars.add(new Car(line[1],line[2],Integer.parseInt(line[0]),Integer.parseInt(line[3]),
                   Boolean.parseBoolean(line[4]),Boolean.parseBoolean(line[5]),line[6],line[7]));
                }else{
                    myCars.add(new Car(line[1],line[2],Integer.parseInt(line[0]),Integer.parseInt(line[3]),
                   Boolean.parseBoolean(line[4]),Boolean.parseBoolean(line[5]),"",""));
                }
            } 
                   //System.out.println("                             *            *            *                               ");

        }
        //int rentals = 0;
        //int revenue = 0;
        char option = ' ';
        
        
        //System.out.println();
        do {
            System.out.printf(" \n                            *            *            *                               \n");

            System.out.print("Cars");
            displayCars(myCars);
            option = getOption(in);
            
            if (option =='A')
                {//Rent Car
                System.out.print("Rent a car. Enter a car selection (by number): ");
                int select = in.nextInt();
                if(myCars.get(select-1).getNeedsService())
                    {
                    System.out.println("The " + myCars.get(select-1).getMake() + " " 
                        + myCars.get(select-1).getModel() + " is not available to rent.");
                        System.out.println("Press [Enter] to continue... ");
                        String space = in.nextLine();
                        //in.next();
                    }
                else if(myCars.get(select-1).getIsRented())
                    {
                    System.out.println("The " + myCars.get(select-1).getMake() + " " 
                        + myCars.get(select-1).getModel() + " is not available to rent.");
                    }
                else
                    {
                    
                    System.out.print("Enter renter's name: ");
                    String name = in.next();
                    String lastN = in.next();
                    String renterName = name +" "+ lastN; 
                    System.out.print("Enter renter's phone  #: ");
                    String renterPhone = in.next();
                        
                    //rentals++;
                    //Car.rentalCount++;
                    //revenue += myCars.get(select-1).getRate();
                    //Car.rateSum +=myCars.get(select-1).getRate();
                        
                    myCars.get(select-1).setRented(renterName, renterPhone);
                    }
                }
            else if(option =='B')
                {//Return a car
                    System.out.print("\nReturn a car. Enter a car selection (by number): ");
                    int select = in.nextInt();
                    
                if(!myCars.get(select-1).getIsRented())
                    {
                    System.out.println("The " + myCars.get(select-1).getMake() 
                        + " " + myCars.get(select-1).getModel() + 
                        " is not rented and cannot be returned.");
                        System.out.print("Press [Enter] to continue... ");
                        in.nextLine();
                      //  System.out.println ();
                    }
                else{
                    myCars.get(select-1).setReturned();
                    //Car.rentalCount--;
                    //Car.rateSum -=myCars.get(select-1).getRate();
                    }
                }
            else if(option =='C')
                {//Flag car for service
                System.out.print("\nFlag car for servicing. Enter a car selection (by number): ");
                int select = in.nextInt();
                if(myCars.get(select-1).getNeedsService())
                        System.out.println("The " + myCars.get(select-1).getMake() + " " 
                        + myCars.get(select-1).getModel() + 
                        " is already in need of service and cannot be flagged.");
                else
                    {
                    myCars.get(select-1).setNeedsService(true);
                    }
                }
            else if(option == 'D')
                {//clear car for sercice
                System.out.print("\nClear car for servicing. Enter a car selection (by number): ");
                int select = in.nextInt();
                    
                if(!myCars.get(select-1).getNeedsService())
                    System.out.println("The " + myCars.get(select-1).getMake() 
                        + " " + myCars.get(select-1).getModel() + 
                        " is not flagged as needing service.");
                else{
                    myCars.get(select-1).setNeedsService(false);
                    }
           
                }
         //Writing in the file    
        File file = new File("c:/temp/classiccars.csv");
        PrintWriter writer = new PrintWriter(file);
        
        String output="";
        
        for(int i = 0; i < myCars.size(); i++) {
            output += myCars.get(i).getYear() + "," + myCars.get(i).getMake() + "," + myCars.get(i).getModel() + ","
                    + myCars.get(i).getRate() + "," + myCars.get(i).getNeedsService() + "," + myCars.get(i).getIsRented() + ","
                    + myCars.get(i).getRenterName() + "," + myCars.get(i).getRenterPhone() + "\n";
        }
        
        writer.print(output);
        writer.close();
        }while(option != 'E');
        System.out.println("Number of rentals initiated in this session: " + Car.rentalCount);
        System.out.println("Total rental rate revenue from this session: $" + Car.rateSum);
        System.out.println();
        System.out.println("Good bye!");
    }
    
    public static void displayCars(ArrayList<Car> car){
        // this method to print the cars 
    System.out.println();
    for (int i = 0; i <car.size(); i++){//for loop for printing the cars 
        
        
        System.out.printf("%d. %d %s %s Rate : %c %d status : ", i+1 ,
        car.get(i).getYear(),car.get(i).getMake(),car.get(i).getModel(),'$',car.get(i).getRate());
        if (car.get(i).getIsRented()){// adding the renter information if it is rented 
           System.out.printf(" Rented (%s %s)",car.get(i).getRenterName(),car.get(i).getRenterPhone());
           
        }
        else 
        System.out.print(" Not Rented");
        
        if(car.get(i).getNeedsService())// if it is not rented and the car needs service
        System.out.println(", Needs service");
        else 
        System.out.println();
        
        
        //else // if it is not rented and the car needs service
        //System.out.println(", Needs Service" );
    
        
    
    }
    System.out.println();
    
    }
    public static char getOption(Scanner input){// method for print the options 
        
        System.out.println("Options");
        System.out.println("A. Rent a car");
        System.out.println("B. Return a car");
        System.out.println("C. Flag car for servicing");
        System.out.println("D. Clear car from servicing");
        System.out.println("E. To exit program");
        System.out.println();
        //Scanner input = new Scanner(System.in);
        System.out.print("Enter your option (by letter): ");
        char option1 = input.next().charAt(0);
        
        char option = Character.toUpperCase(option1);
        return option;
    }
}
