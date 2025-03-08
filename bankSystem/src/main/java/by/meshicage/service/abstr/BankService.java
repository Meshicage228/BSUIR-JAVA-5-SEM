package by.meshicage.service.abstr;

public interface BankService {
    Double checkBalance(String cardNumber);
    Boolean transferFunds(String senderCardNumber, String receiverAccountNumber, Double amount);
    Boolean validateCredentials(String cardNumber, String name);
}
