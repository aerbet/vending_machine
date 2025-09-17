package MoneyReceiver;

public class BankCardReceiver implements Receiver {
    private int amount;

    public BankCardReceiver(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
