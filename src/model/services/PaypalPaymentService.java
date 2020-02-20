package model.services;

public class PaypalPaymentService implements PaymentService {
	
	public static final Double INTEREST_RATE = 0.01;
	public static final Double PAYMENT_RATE = 0.02;

	@Override
	public Double simpleInterest(Double amount, Integer months) {
		return amount * (1 + (INTEREST_RATE * months));
	}

	@Override
	public Double processingPayment(Double simpleInterest) {
		return simpleInterest * (1 + PAYMENT_RATE);
	}

}
