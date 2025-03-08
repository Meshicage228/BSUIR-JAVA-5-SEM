package by.meshicage.service.impl;

import by.meshicage.entity.bank.BankAccount;
import by.meshicage.entity.clients.abstr.Client;
import by.meshicage.db.InMemoryDatabase;
import by.meshicage.service.abstr.BankService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final InMemoryDatabase database;

    @Override
    public Double checkBalance(String cardNumber) {
        return database.getClientByCardNumber(cardNumber)
                .map(Client::getAccount)
                .map(BankAccount::getBalance)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Boolean transferFunds(String senderCardNumber, String receiverAccountNumber, Double amount) {
        return database.getClientByCardNumber(senderCardNumber)
                .filter(sender -> sender.getAccount().getBalance() >= amount)
                .map(sender ->
                        database.getClientByCardNumber(receiverAccountNumber)
                        .map(receiverAccount -> {
                            sender.getAccount().setBalance(sender.getAccount().getBalance() - amount);
                            receiverAccount.getAccount().setBalance(receiverAccount.getAccount().getBalance() + amount);
                            return true;
                        })
                        .orElse(false))
                .orElse(false);
    }

    @Override
    public Boolean validateCredentials(String cardNumber, String name) {
        return database.getClientByCardNumber(cardNumber)
                .map(client -> client.getName().equals(name))
                .orElse(false);
    }
}