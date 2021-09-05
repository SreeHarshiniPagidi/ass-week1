import java.util.ArrayList;
import java.util.Iterator;

// Implemented by Coin data to communicate with observers
interface Subject {
    public void registerObserver(Observer o);

    public void unregisterObserver(Observer o);

    public void notifyObservers();
}

class CoinData implements Subject {
    private int quarters = 0; // Number of quarters, to be input by the user.
    private int dimes = 0; // Number of dimes, to be input by the user.
    private int nickles = 0; // Number of nickles, to be input by the user.
    private int cents = 0; // Number of cents, to be input by the user.

    private double dollars = 0.0; // Total value of all the coins, in dollars.

    ArrayList<Observer> observerList;

    public CoinData() {
        observerList = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(observerList.indexOf(o));
    }

    @Override
    public void notifyObservers() {
        for (Iterator<Observer> it = observerList.iterator(); it.hasNext();) {
            Observer o = it.next();
            o.update(quarters, dimes, nickles, cents, dollars);
        }
    }

    // get each coin Count
    private int getQuarters() {
        return quarters;
    }

    private int getDimes() {
        return dimes;
    }

    private int getNickles() {
        return nickles;
    }

    private int getCents() {
        return cents;
    }

    // setter
    private void setQuarters(int n) {
        quarters += n;
    }

    private void setDimes(int n) {
        dimes += n;
    }

    private void setNickles(int n) {
        nickles += n;
    }

    private void setCents
    }

    private double setDollars() {
        dollars = (25 * getQuarters()) + (10 * getDimes()) + (5 * getNickles()) + (1 * getCents());
        return dollars;
    }

    // This method is used update displays
    // when data changes
    public void dataChanged(int quarters, int dimes, int nickles, int cents) {
        // get latest data
        setQuarters(quarters);
        setDimes(dimes);
        setNickles(nickles);
        setPennies(cents);
        setDollars();

        notifyObservers();
    }
}

// This interface is implemented by all those
// classes that are to be updated whenever there
// is an update from CoinData
interface Observer {
    public void update(int quarters, int dimes, int nickles, int cents, double dollars);
}

class TotalAmountDisplay implements Observer {
    private int totalCoins;
    private double totalAmount;
    int quarters, dimes, nickles, cents;

    public void update(int quarters, int dimes, int nickles, int cents, double dollars) {
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickles = nickles;
        this.pennies = cents;
        this.totalCoins = quarters + dimes + nickles + pennies;
        this.totalAmount = dollars / 100;
        display();
    }

    public void display() {
        System.out.println("\n__________Total Coins Display___________ \n" + "\nquarters: " + quarters + "\ndimes: "
                + dimes + "\nnickles: " + nickles + "\ncents " + cents + "\nTotal Coins: " + totalCoins
                + "\nTotal Amount: $ " + totalAmount);
    }
}

class Main {
    public static void main(String args[]) {
        // create objects for testing
        TotalAmountDisplay totalAmountDisplay = new TotalAmountDisplay();
        // CurrentCoinAdded currentCoinAdded = new CurrentCoinAdded();

        CoinData coinData = new CoinData();

        // register display elements
        coinData.registerObserver(totalAmountDisplay);

        // in real app you would have some logic to
        // call this function when data changes
        coinData.dataChanged(1, 2, 3, 4);
        coinData.dataChanged(0, 0, 0, 1);

        // remove an observer
        // coinData.unregisterObserver(totalAmountDisplay);

    }
}
