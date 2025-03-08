package by.meshicage.service.abstr;

import java.util.List;

public abstract class TransactionTemplate {
    protected final List<Double> amounts;

    public TransactionTemplate(List<Double> amounts) {
        this.amounts = amounts;
    }

    public final void execute() {
        calculateSum();
        findMax();
    }

    protected abstract void calculateSum();
    protected abstract void findMax();
}