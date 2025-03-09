package by.meshicage;

import by.meshicage.entity.clients.abstr.Client;
import by.meshicage.db.InMemoryDatabase;
import by.meshicage.entity.bank.BankAccount;
import by.meshicage.util.factories.AccountFactory;
import by.meshicage.util.factories.ClientFactory;
import by.meshicage.service.abstr.AccountVisitor;
import by.meshicage.service.abstr.TransactionTemplate;
import by.meshicage.service.facade.BankFacade;
import by.meshicage.service.impl.AccountComposite;
import by.meshicage.service.impl.BalanceChecker;
import by.meshicage.service.impl.BankServiceImpl;
import by.meshicage.service.impl.MoneyMathCounter;
import by.meshicage.service.proxy.BankFacadeProxy;

public class Main {
    public static final String SENDER_CARD = "1234-5678-9012-3456";
    public static final String RECEIVER_CARD = "9876-5432-1098-7654";

    public static void main(String[] args) {
        System.out.println("Creating APP...");

        InMemoryDatabase database = new InMemoryDatabase();

        BankAccount senderAccount = AccountFactory.createBankAccount("123456789", 1000.0);
        BankAccount receiverAccount = AccountFactory.createBankAccount("987654321", 500.0);

        Client senderClient = ClientFactory.createSimpleClient(1, "John Doe", SENDER_CARD, senderAccount);
        Client receiverClient = ClientFactory.createSimpleClient(2, "Jane Doe", RECEIVER_CARD, receiverAccount);

        database.addClients(senderClient, receiverClient);

        BankFacade bankService = new BankFacadeProxy(new BankFacade(new BankServiceImpl(database)));

        System.out.println("App was created: start business logic:");

        boolean transferSuccess = bankService.performTransfer(SENDER_CARD, RECEIVER_CARD, 200.0, "John Doe");
        System.out.println("Transfer successful: " + transferSuccess + "\n");

        System.out.println("Client 1 balance: " + bankService.checkClientBalance(SENDER_CARD));
        System.out.println("Client 2 balance: " + bankService.checkClientBalance(RECEIVER_CARD) + "\n");

        System.out.println("Composite: ");
        AccountComposite composite = new AccountComposite();
        composite.addAccount(senderAccount);
        composite.addAccount(receiverAccount);

        composite.display();
        System.out.println("Total balance: " + composite.getBalance() + "\n");

        System.out.println("Visitor: ");
        AccountVisitor visitor = new BalanceChecker();
        composite.accept(visitor);

        System.out.println("Template: ");
        TransactionTemplate transaction = new MoneyMathCounter(database.getAllClientBalances());
        transaction.execute();
    }
}