package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractProcessingService {
	
	private static Calendar calendar = Calendar.getInstance();
	
	private Integer months;
	
	private PaymentService paymentService;

	public ContractProcessingService() {
	}
	public ContractProcessingService(Integer months, PaymentService paymentService) {
		this.months = months;
		this.paymentService = paymentService;
	}
	
	public Integer getMonths() {
		return months;
	}
	
	public PaymentService getPaymentService() {
		return paymentService;
	}
	
	private Date processingDate(Date date, Integer months) {
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	private Double processingValue(Double value, Integer months) {
		Double simpleInterest;
		
		simpleInterest = getPaymentService().simpleInterest(value, months);
		return getPaymentService().processingPayment(simpleInterest);
	}
	public void processingContract(Contract contract) {
		Date initialDate, finalDate;
		Double initialValue, finalValue;
		
		initialDate = contract.getDate();	
		initialValue = contract.getTotalValue() / getMonths();
		for (int i = 1; i <= getMonths(); i++) {
			finalDate = processingDate(initialDate, i);
			finalValue = processingValue(initialValue, i);
			contract.addInstallments(new Installment(finalDate, finalValue));
		}
	}

}
