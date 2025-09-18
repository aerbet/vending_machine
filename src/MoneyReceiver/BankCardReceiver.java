package MoneyReceiver;

import exception.BankCardException;

import java.util.Scanner;

public class BankCardReceiver implements Receiver {
    private int amount;

    public BankCardReceiver(int amount) {
        this.amount = amount;

        String num = checkCardNumber();
        String pin = checkCardPassword();
        print(String.format("Карта %s успешно зарегистрирована с паролем '%s'%n", num, pin));
    }

    public String checkCardPassword() {
        while (true) {
            try {
                print("Введите пароль карты из 4 цифр: ");
                String pin = new Scanner(System.in).nextLine();

                if (pin.length() != 4 || !pin.matches("\\d+")) {
                    throw new BankCardException("Неправильный пароль карты, он должен содержать только 4 цифры");
                }

                return pin;
            } catch (BankCardException e) {
                print(e.getMessage());
            }
        }
    }

    public String checkCardNumber() {
        while (true) {
            try {
                print("Введите номер карты из 16 цифр: ");
                String card = new Scanner(System.in).nextLine();

                if (card.length() != 16 || !card.matches("\\d+")) {
                    throw new BankCardException("Неправильный номер карты, может быть только 16 цифр");
                }

                return card;
            } catch (BankCardException e) {
                print(e.getMessage());
            }
        }
    }

    private void print(String msg) {
        System.out.println(msg);
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
