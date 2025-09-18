package MoneyReceiver;

import exception.BankCardException;

public class BankCardReceiver implements Receiver {
    private int amount;
    private String cardNum;

    public BankCardReceiver(int amount) {
        this.amount = amount;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
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
