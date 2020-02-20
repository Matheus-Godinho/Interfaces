package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;

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
			number = sc.nextInt();
			date = sdf.parse(sc.next());
			totalValue = sc.nextDouble();
			contract = new Contract(number, date, totalValue);
			
			contract.addInstallments(new Installment(sdf.parse("25/07/2018"), 206.04));
			contract.addInstallments(new Installment(sdf.parse("25/08/2018"), 208.08));
			contract.addInstallments(new Installment(sdf.parse("25/09/2018"), 210.12));
			
			for (Installment i: contract.getInstallments())
				System.out.printf("%s%n", i.toString());
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		sc.close();

	}

}
