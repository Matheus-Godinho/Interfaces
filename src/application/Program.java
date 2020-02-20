package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractProcessingService;
import model.services.PaypalPaymentService;

public class Program {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Contract contract;
			int number;
			Date date;
			double totalValue;
			
		try {
			System.out.printf("Enter contract data%n");
			System.out.printf("Number: ");
			number = sc.nextInt();
			System.out.printf("Date (dd/MM/yyyy): ");
			date = sdf.parse(sc.next());
			System.out.printf("Contract value: ");
			totalValue = sc.nextDouble();
			contract = new Contract(number, date, totalValue);
			
			ContractProcessingService cps;
				int months;
				
			System.out.printf("Enter number of installments: ");
			months = sc.nextInt();
			cps = new ContractProcessingService(months, new PaypalPaymentService());
			cps.processingContract(contract);
			
			System.out.printf("Installments:%n");
			for (Installment i: contract.getInstallments())
				System.out.printf("%s%n", i.toString());
			
		}
		catch (IllegalArgumentException e) {
			System.out.printf("%s%n", e.getMessage());
		}
		catch (ParseException e) {
			System.out.printf("Error in date. You must enter a date with the dd/MM/yyyy format%n");
		}
		catch (RuntimeException e) {
			System.out.printf("Unexpected error%n");
		}
		
		sc.close();

	}

}
