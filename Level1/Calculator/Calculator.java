package pro;
import java.util.*;
public class Calculator {
	//methods used inside arithmetic to perform basic operations (+,-,/,*,%)
	public static double addition(double a, double b) {
		return a+b;
	}
	public static double subtraction(double a, double b) {
		return a-b;
	}
	public static double Multiply(double a, double b) {
		return a*b;
	}
	public static double division(double a, double b) {
		return a/b;
	}
	public static double Modulo(double a, double b) {
		return a%b;
	}
	//methods used in scientific operations 
	//calculates a^b
	public static double Power(double a, double b) {
		 if (a < 0 && b != Math.floor(b)) {
		        throw new IllegalArgumentException("Cannot raise a negative number to fractional power.");
		    }
		    return Math.pow(a, b);
	
	}
	//calculates square root of given number
	public static double SquareRoot(double a) {
		if(a<0) {
			throw new IllegalArgumentException("Cannot calculate root of a negative number");
		}else {
			return Math.sqrt(a);
			
		}
	}
	//calculates nth root i.e a^(1/n)
	public static double NthRoot(double a,double b) {
		if(a<0) {
			throw new IllegalArgumentException("Cannot calculate root of a negative number");
		}else if(b==0) 		throw new IllegalArgumentException("Degree of root cannot be zero");
 else {
			return Math.pow(a,(1/b));
			
		}
	}
	//calculates the percentage of given number
	public static double CalculatePercentageOf(double a,double b) {
		return (a/100)*b;
	}
	public static long Factorial(long a) {
		long fact=1;
		if(a<0) {
			throw new IllegalArgumentException("factorial of negative number cannot be calculated");
		}else {
			for (long i=1;i<=a;i++) {
				fact*=i;
			}
		}
		return fact;
	}
// Handles Scientific operations based on user choice and calls corresponding methods.
	public static void scientific(Scanner sc) {
		int choice=0;
		double result=0;
		do {
			System.out.println("=====================================");
			System.out.println("1.Power\n2.SquareRoot\n3.Nthroot\n4.Factorial\n5.Percentagevalue\n6.exit");
			System.out.println("Enter the operation to perform:");
			//validates the input and error handling to make sure the  input is of numeric type 
			try {
				choice=sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("Invalid input! please enter a numeric value.");
					sc.nextLine();
					continue;//used to skip the current iteration
				}
	
		switch(choice) {
		case 1:
			try {
				System.out.println("Enter base:");
				double base=sc.nextDouble();
				System.out.println("Enter exponent:");
				double exponent=sc.nextDouble();
				result=Power(base,exponent);
				System.out.println("result: "+result);
			}catch(InputMismatchException e) { 
				System.out.println("Invalid input! please enter a numeric value.");
			}
		break;
		case 2:
			try{
			System.out.println("Enter number to be Squared:");
			double num1=sc.nextDouble();
			result=SquareRoot(num1);
			System.out.println("result: "+result);
			}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
			}
		break;
		
		case 3:
			try {
			System.out.println("Enter number:");
			double num=sc.nextDouble();
			System.out.println("Enter root degree:");
			double root=sc.nextDouble();
			result=NthRoot(num,root);
			System.out.println("result: "+result);
			}catch(InputMismatchException e) { 
				System.out.println("Invalid input! please enter a numeric value.");
			}
		break;
		case 4:
			try {
			System.out.println("Enter number:");
			long fact=sc.nextLong();
			result=Factorial(fact);
			System.out.println("result: "+result);
			}catch(InputMismatchException e) { 
				System.out.println("Invalid input! please enter a numeric value.");
			}
		break;
		case 5:
			try {
			System.out.println("Enter percent value(e.g. 20 for 20%):");
			double percent=sc.nextDouble();
			System.out.println("Enter number to calculate percent of:");
			double number=sc.nextDouble();
			result=CalculatePercentageOf(percent,number);
			System.out.println("result: "+result);
			}catch(InputMismatchException e) { 
				System.out.println("Invalid input! please enter a numeric value.");
			}
		break;
		case 6: System.out.println("---returning to main menu---");
		break;
		default:System.out.println("Invalid Choice!");
		}
		}while(choice!=6);
		
	
	}
// Handles basic arithmetic operations based on user choice and calls corresponding methods.
public static void arithmetic(Scanner sc) {
		int choice=0;
		double result=0;
		double num1=0,num2=0;
		do {
			System.out.println("=====================================");
			System.out.println("1.Addition\n2.Subtraction\n3.Multiplication\n4.Division\n5.modulo\n6.exit");
			System.out.println("Enter the operation to perform:");
			try {
				choice=sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("Invalid input! please enter a numeric value.");
					sc.nextLine();
					continue;
				}
			if(choice!=6) {
				System.out.println("Enter the numbers:");
			try {
				
			 num1=sc.nextDouble();
			 num2=sc.nextDouble();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! please enter a numeric value.");
				sc.nextLine();
				continue;
			}
			}	
		switch(choice) {
		case 1: result=addition(num1,num2);
		System.out.println("result: "+result);
		break;
		case 2: result=subtraction(num1,num2);
		System.out.println("result: "+result);
		break;
		case 3: result=Multiply(num1,num2);
		System.out.println("result: "+result);
		break;
		case 4:
			if(num2==0) {
				System.out.println("number cannot be divided by zero");
			}
			else{
				result=division(num1,num2);
				System.out.println("result: "+result);

			}
		break;
		case 5:if(num2==0) {
			System.out.println("number cannot be divided by zero");
		}
		else{
			result=Modulo(num1,num2);
			System.out.println("result: "+result);

		}
		break;
		case 6: System.out.println("---returning to main menu---");
		break;
		default:System.out.println("Invalid Choice!");
		}
		}while(choice!=6);
		
	
	}
//methods used in temperature conversions
//Celsius to fahrenhiet conversion
public static double Celsius_to_fahrenhiet(double celsius) {
	double fahrenhiet=(celsius*(9.0/5))+32;
	return fahrenhiet;
}
//Celsius to Kelvin conversion

public static double Celsius_to_Kelvin(double celsius) {
	double Kelvin=(celsius + 273.15);
	return Kelvin;
}
//Kelvin to fahrenhiet conversion

public static double Kelvin_to_Fahrenhiet(double Kelvin) {
	double fahrenhiet=((Kelvin-273.15)*(9.0/5.0))+32 ;
	return fahrenhiet;
}
//Kelvin to Celsius conversion

public static double Kelvin_to_Celsius(double Kelvin) {
	double Celsius=(Kelvin-273.15) ;
	return Celsius;
}
//fahrenhiet to Celsius conversion

public static double Fahrenhiet_to_Celsius(double Fahrenhiet) {
	double Celsius=(Fahrenhiet-32)*(5.0/9.0) ;//5.0/9.0 ensures floating-point division; 5/9 would be integer division and result in 0

	return Celsius;
}
//fahrenhiet to kelvin conversion

public static double Fahrenhiet_to_Kelvin(double Fahrenhiet) {
	double Kelvin=((Fahrenhiet-32)*(5.0/9.0)) + 273.15;
	return Kelvin;
}

public static void Temperature_converter(Scanner sc) {
	int choice=0;
	double result=0;
	do {
		System.out.println("=====================================");
		System.out.println("1.Celsius_to_Fahrenhiet\n2.Celsius_to_kelvin\n3.Fahrenhiet_to_celsius\n4.Fahrenhiet_to_kelvin\n5.Kelvin_to_celsius\n6.Kelvin_to_Fahrenhiet\n7.exit\n");
		System.out.println("Enter the conversion to perform:");
		try {
			choice=sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! please enter a numeric value.");
				sc.nextLine();
				continue;
			}		
	switch(choice) {
	case 1:
		try{
			System.out.println("Enter Celsius:");
			double Celsius=sc.nextDouble();
			result=Celsius_to_fahrenhiet(Celsius);
			System.out.printf("result: %.2f\n", result);// Using printf to format the output, showing result up to 2 decimal places
			}catch(InputMismatchException e) { 
				System.out.println("Invalid input! please enter a numeric value.");
			}
			break;
	case 2: try{
		System.out.println("Enter Celsius:");
		double Celsius=sc.nextDouble();
		result=Celsius_to_Kelvin(Celsius);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 3: try{
		System.out.println("Enter Farenhiet:");
		double Fahrenhiet=sc.nextDouble();
		result=Fahrenhiet_to_Celsius(Fahrenhiet);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 4:try{
		System.out.println("Enter Farenhiet:");
		double Fahrenhiet=sc.nextDouble();
		result=Fahrenhiet_to_Kelvin(Fahrenhiet);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 5:try{
		System.out.println("Enter Kelvin:");
		double Kelvin=sc.nextDouble();
		result=Kelvin_to_Celsius(Kelvin);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 6:try{
		System.out.println("Enter Kelvin:");
		double Kelvin=sc.nextDouble();
		result=Kelvin_to_Fahrenhiet(Kelvin);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;

	case 7: System.out.println("---returning to main menu---");
	break;
	default:System.out.println("Invalid Choice!");
	}
	}while(choice!=7);
}
// methods used in currency_converter
//Converts Rupees to US Dollars (approximate rate: 1 INR = 0.011 USD)

public static double Rupee_to_USD(double Rupee) {

	return (Rupee*0.011);
	
}
// Converts Rupees to Euros (approximate rate: 1 INR = 0.0098 EUR)

public static double Rupee_to_EURO(double Rupee) {
	return Rupee*(0.0098);
	
}
//Converts Euros to Rupees (approximate rate: 1 EUR = 101.80 INR)

public static double EURO_to_Rupee(double EURO) {
	
	return (EURO*101.80);
}
//Converts Euros to US Dollars (approximate rate: 1 EUR = 1.16 USD)

public static double EURO_to_USD(double EURO) {
	return (EURO*1.16);
}
//Converts US Dollars to Rupees (approximate rate: 1 USD = 87.49 INR)

public static double USD_to_Rupee(double USD) {
	return (USD*87.49);
}
//Converts US Dollars to Euros (approximate rate: 1 USD = 0.86 EUR)

public static double USD_to_EURO(double USD) {
	return (USD*0.86);
}
//currency converter for usd,euro and rupee 
public static void Currency_converter(Scanner sc) {
	int choice=0;
	double result=0;
	do {
		System.out.println("=====================================");
		System.out.println("1.Rupee_to_USD\n2.Rupee_to_EURO\n3.EURO_to_Rupee\n4.EURO_to_USD\n5.USD_to_Rupee\n6.USD_to_EURO\n7.exit\n");
		System.out.println("Enter the conversion to perform:");
		try {
			choice=sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! please enter a numeric value.");
				sc.nextLine();
				continue;
			}
	
	switch(choice) {
	case 1:
		try{
			System.out.println("Enter Rupee:");
			double Rupee=sc.nextDouble();
			result=Rupee_to_USD(Rupee);
			System.out.printf("result: %.2f\n", result);
			}catch(InputMismatchException e) { 
				System.out.println("Invalid input! please enter a numeric value.");
			}
			break;
	case 2: try{
		System.out.println("Enter Rupee:");
		double Rupee=sc.nextDouble();
		result=Rupee_to_EURO(Rupee);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 3: try{
		System.out.println("Enter EURO:");
		double EURO=sc.nextDouble();
		result=EURO_to_Rupee(EURO);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 4:try{
		System.out.println("Enter EURO:");
		double EURO=sc.nextDouble();
		result=EURO_to_USD(EURO);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 5:try{
		System.out.println("Enter USD:");
		double USD=sc.nextDouble();
		result=USD_to_Rupee(USD);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;
	case 6:try{
		System.out.println("Enter USD:");
		double USD=sc.nextDouble();
		result=USD_to_EURO(USD);
		System.out.printf("result: %.2f\n", result);
		}catch(InputMismatchException e) { 
			System.out.println("Invalid input! please enter a numeric value.");
		}
		break;

	case 7: System.out.println("---returning to main menu---");
	break;
	default:System.out.println("Invalid Choice!");
	}
	}while(choice!=7);
}
// Handles Conversion operations based on user choice and calls corresponding methods.

public static void conversions(Scanner sc) {
	int choice=0;
	do {
		System.out.println("=====================================");
		System.out.println("1.Temperature conversion\n2.Currency conversion\n3.exit");
		System.out.println("Enter the choice for conversion to perform:");
		try {
			choice=sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! please enter a numeric value.");
				sc.nextLine();
				continue;
			}	
	switch(choice) {
	case 1: Temperature_converter(sc);
	break;
	case 2: Currency_converter(sc);
	break;
	case 3: System.out.println("---returning to main menu---");
	break;
	default:System.out.println("Invalid Choice!");
	}
	}while(choice!=3);
	
}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int choice=0;
		
		do {
			System.out.println("-----------------------------------");
			System.out.println("CONSOLE BASED CALCULATOR");
			System.out.println("-----------------------------------");
			System.out.println("1.Arithmetic operations\n2.Scientific operations\n3.conversions\n4.Exit");
			System.out.print("Enter your choice:");
			try {
				choice=sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("Invalid input! please enter a numeric value.");
					sc.nextLine();
					continue;
				}
			switch(choice) {
			case 1:arithmetic(sc);
			break;
			case 2:scientific(sc);
			break;
			case 3:conversions(sc);
			break;
			case 4:System.out.println("-Exiting-");
				System.out.println("-----------------------------------");
				sc.close();
				break;
			default:
				System.out.println("Invalid Choice! Try Again.");
			}
			}while(choice!=4);
			
		}
}
	
	


