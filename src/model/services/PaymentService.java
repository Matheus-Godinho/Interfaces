package model.services;

public interface PaymentService {
	
	Double simpleInterest(Double amount, Integer months);
	Double processingPayment(Double simpleInterest);

}
