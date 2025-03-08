package by.meshicage.entity.bank;

import by.meshicage.service.abstr.AccountComponent;
import by.meshicage.service.abstr.AccountVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount implements AccountComponent {
    private String accountNumber;
    private Double balance;

    @Override
    public void display() {
        System.out.println("Account: " + accountNumber + ", Balance: " + balance);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void accept(AccountVisitor visitor) {
        visitor.visit(this);
    }

    public static BankAccountBuilder builder() {
        return new BankAccountBuilder();
    }

    public static class BankAccountBuilder {
        private String accountNumber;
        private Double balance;

        BankAccountBuilder() {
        }

        public BankAccountBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public BankAccountBuilder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this.accountNumber, this.balance);
        }

        public String toString() {
            return "BankAccount.BankAccountBuilder(accountNumber=" + this.accountNumber + ", balance=" + this.balance + ")";
        }
    }
}