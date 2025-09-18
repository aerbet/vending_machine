import MoneyReceiver.BankCardReceiver;
import MoneyReceiver.CoinReceiver;
import MoneyReceiver.Receiver;
import enums.ActionLetter;
import exception.BankCardException;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {
    private final UniversalArray<Product> products = new UniversalArrayImpl<>();
    private static Receiver receiver;
    private static boolean isExit = true;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        receiver = chooseReceiver();
    }

    public static void run() {
        AppRunner app = new AppRunner();

        while (isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Монет на сумму: " + receiver.getAmount());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());

        try {
            chooseAction(allowProducts);
        } catch (StringIndexOutOfBoundsException s) {
            print("Вы ввели пустую строку, попробуйте еще раз");
        }

    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (receiver.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print("a - Пополнить баланс");
        showActions(products);
        print("h - Выйти");
        String action = fromConsole().substring(0, 1);


        if ("a".equalsIgnoreCase(action)) {
            receiver.setAmount(receiver.getAmount() + 10);
            print("Вы пополнили баланс на 10");
            return;
        }

        if ("h".equalsIgnoreCase(action)) {
            isExit = false;
        } else {
            try {
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                        receiver.setAmount(receiver.getAmount() - products.get(i).getPrice());
                        print("Вы купили " + products.get(i).getName());
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                print("Недопустимая буква. Попрбуйте еще раз.");
                chooseAction(products);
            }
        }
    }

    private Long checkCardNumber() {
        print("Введите номер карты из 16 цифр: ");
        String card = fromConsole();
        long number = Long.parseLong(card);

        if (card.length() == 16) {
            return number;
        } else {
            throw new BankCardException("Неправильный номер карты, он должен содержать 16 цифр");
        }
    }

    private int checkCardPassword() {
        print("Введите пин-код карты из 4 цифр: ");
        String card = fromConsole();
        int number = Integer.parseInt(card);

        if (card.length() == 4) {
            return number;
        } else {
            throw new BankCardException("Неправильный пин-код карты, он должен содержать только 4 цифры");
        }
    }

    private Receiver chooseReceiver() {
        print("Выберите способ оплаты: " + "\n" + "1. Монеты 2. Банковская карта");
        String choice = fromConsole().substring(0, 1);

        switch (Integer.parseInt(choice)) {
            case 1:
                return new CoinReceiver(100);
            case 2:
                return new BankCardReceiver(120);
            default:
                return null;
        }
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
