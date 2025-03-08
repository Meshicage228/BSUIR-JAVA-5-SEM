package by.meshicage.service.abstr;

public interface AccountComponent {
    void display();
    double getBalance();
    void accept(AccountVisitor visitor);
}