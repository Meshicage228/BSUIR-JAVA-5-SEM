package by.meshicage.entity.clients.abstr;

import by.meshicage.entity.bank.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public abstract class Client {
    private Integer id;
    private String name;
    private String cardNumber;
    private BankAccount account;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
