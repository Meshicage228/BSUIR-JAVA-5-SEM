package by.meshicage.db;

import by.meshicage.entity.bank.BankAccount;
import by.meshicage.entity.clients.abstr.Client;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDatabase {
    private final Map<Integer, Client> clients = new HashMap<>();

    public void addClients(Client... client) {
        Arrays.stream(client)
                .forEach(cl -> clients.put(cl.getId(), cl));
    }

    public Optional<Client> getClientByCardNumber(String cardNumber) {
        return clients.values()
                .stream()
                .filter(client -> client.getCardNumber().equals(cardNumber))
                .findFirst();
    }

    public List<Double> getAllClientBalances() {
        return clients.values()
                .stream()
                .map(Client::getAccount)
                .map(BankAccount::getBalance)
                .collect(Collectors.toList());
    }
}