package by.meshicage.service.proxy;

import by.meshicage.service.facade.BankFacade;

import java.time.LocalDateTime;

public class BankFacadeProxy extends BankFacade {
    private final BankFacade bankFacade;

    public BankFacadeProxy(BankFacade bankFacade) {
        super(null);
        this.bankFacade = bankFacade;
    }

    @Override
    public boolean performTransfer(String senderCardNumber, String receiverAccountNumber, Double amount, String senderName) {
        String logMessage = String.format(
                "[%s] Started performing transfer: Sender=%s, Receiver=%s, Amount=%.2f, SenderName=%s",
                LocalDateTime.now(), senderCardNumber, receiverAccountNumber, amount, senderName
        );
        System.out.println(logMessage);

        boolean status = bankFacade.performTransfer(senderCardNumber, receiverAccountNumber, amount, senderName);

        logMessage = String.format(
                "[%s] Ended performing transfer: Sender=%s, Receiver=%s, Amount=%.2f, SenderName=%s, Status=%s",
                LocalDateTime.now(), senderCardNumber, receiverAccountNumber, amount, senderName, status
        );
        System.out.println(logMessage);

        return status;
    }

    @Override
    public Double checkClientBalance(String cardNumber) {
        String logMessage = String.format(
                "[%s] Started checking client balance: CardNumber=%s",
                LocalDateTime.now(), cardNumber
        );
        System.out.println(logMessage);

        double balance = bankFacade.checkClientBalance(cardNumber);

        logMessage = String.format(
                "[%s] Ended checking client balance: CardNumber=%s, Balance=%.2f",
                LocalDateTime.now(), cardNumber, balance
        );
        System.out.println(logMessage);

        return balance;
    }
}
