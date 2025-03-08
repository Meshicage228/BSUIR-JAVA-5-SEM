package by.meshicage.service.facade;

import by.meshicage.service.abstr.BankService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BankFacade {
    private final BankService bankService;

    public boolean performTransfer(String senderCardNumber, String receiverAccountNumber, Double amount, String senderName) {
        return bankService.validateCredentials(senderCardNumber, senderName)
                && bankService.transferFunds(senderCardNumber, receiverAccountNumber, amount);
    }

    public Double checkClientBalance(String cardNumber) {
        return bankService.checkBalance(cardNumber);
    }
}
