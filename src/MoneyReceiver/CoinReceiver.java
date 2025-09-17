package MoneyReceiver;

public class CoinReceiver implements Receiver {
    private int amount;

    public CoinReceiver(int amount) {
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
