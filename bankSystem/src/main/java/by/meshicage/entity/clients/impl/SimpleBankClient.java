package by.meshicage.entity.clients.impl;

import by.meshicage.entity.clients.abstr.Client;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SimpleBankClient extends Client {

    @Override
    public String toString() {
        return "SimpleBankClient" + super.toString();
    }
}
