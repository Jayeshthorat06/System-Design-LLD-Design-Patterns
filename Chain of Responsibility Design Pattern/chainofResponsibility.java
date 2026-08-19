abstract class moneyHandler {
    moneyHandler handler;

    public void setHandler(moneyHandler handler) {
        this.handler = handler;
    }

    abstract public void dispense(int money);
}

class thousandHandler extends moneyHandler {
    int amount;

    public thousandHandler(int amt) {
        this.amount = amt;
    }

    public void dispense(int money) {
        int notesneeded = money / 1000;
        if (amount >= notesneeded) {
            amount -= notesneeded;
        } else {
            notesneeded = amount;
            amount = 0;
        }

        if (notesneeded > 0) {
            System.out.println("Dispensing amount 1000 *\t" + notesneeded);
        }

        int requiredamount = money - (notesneeded * 1000);

        if (requiredamount > 0) {
            if (handler != null) {
                handler.dispense(requiredamount);
            } else {
                System.out.println("Dispensing amount  " + money + "  is not fulfilled due to insufficient balance");
            }
        }

    }
}

class fiveHundredHandler extends moneyHandler {
    int amount;

    public fiveHundredHandler(int amt) {
        this.amount = amt;
    }

    public void dispense(int money) {
        int notesneeded = money / 500;

        if (amount >= notesneeded) {
            amount -= notesneeded;
        } else {
            notesneeded = amount;
            amount = 0;
        }

        if (notesneeded > 0) {
            System.out.println("Dispensing amount 500 *\t" + notesneeded);
        }

        int requiredamount = money - (notesneeded * 500);

        if (requiredamount > 0) {
            if (handler != null) {
                handler.dispense(requiredamount);
            } else {
                System.out.println("Dispensing amount  " + money + "  is not fulfilled due to insufficient balance");
            }
        }
    }
}

class twoHundredHandler extends moneyHandler {
    int amount;

    public twoHundredHandler(int amt) {
        this.amount = amt;
    }

    public void dispense(int money) {
        int notesneeded = money / 200;

        if (amount >= notesneeded) {
            amount -= notesneeded;
        } else {
            notesneeded = amount;
            amount = 0;
        }

        if (notesneeded > 0) {
            System.out.println("Dispensing amount 200 *\t" + notesneeded);
        }

        int requiredamount = money - (notesneeded * 200);

        if (requiredamount > 0) {
            if (handler != null) {
                handler.dispense(requiredamount);
            } else {
                System.out.println("Dispensing amount  " + money + "  is not fulfilled due to insufficient balance");
            }
        }
    }
}

class oneHundredHandler extends moneyHandler {
    int amount;

    public oneHundredHandler(int amt) {
        this.amount = amt;
    }

    public void dispense(int money) {
        int notesneeded = money / 100;

        if (amount >= notesneeded) {
            amount -= notesneeded;
        } else {
            notesneeded = amount;
            amount = 0;
        }

        if (notesneeded > 0) {
            System.out.println("Dispensing amount 100 *\t" + notesneeded);
        }

        int requiredamount = money - (notesneeded * 100);

        if (requiredamount > 0) {
            if (handler != null) {
                handler.dispense(requiredamount);
            } else {
                System.out.println("Dispensing amount  " + money + "  is not fulfilled due to insufficient balance");
            }
        }
    }
}

class chainofResponsibility {
    public static void main(String[] args) {
        moneyHandler thousand = new thousandHandler(5);
        moneyHandler five = new fiveHundredHandler(10);
        moneyHandler two = new twoHundredHandler(15);
        moneyHandler one = new oneHundredHandler(20);

        thousand.setHandler(five);
        five.setHandler(two);
        two.setHandler(one);

        int amount = 541000;

        System.out.println("Dispensing amount \t " + amount);
        thousand.dispense(amount);
    }

}