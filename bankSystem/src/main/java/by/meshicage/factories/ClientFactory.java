package by.meshicage.factories;

import by.meshicage.entity.bank.BankAccount;
import by.meshicage.entity.clients.abstr.Client;
import by.meshicage.entity.clients.impl.SimpleBankClient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientFactory {

    public Client createSimpleClient(Integer id, String name, String cardNumber, BankAccount account) {
        return SimpleBankClient.builder()
                .id(id)
                .name(name)
                .cardNumber(cardNumber)
                .account(account)
                .build();
    }
}