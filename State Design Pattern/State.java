interface VendingState {
    public VendingState insertCoin(VendingMachineState machine, int coin);

    public VendingState selectItem(VendingMachineState machine);

    public VendingState dispense(VendingMachineState machine);

    public VendingState returnCoin(VendingMachineState machine);

    public VendingState refill(VendingMachineState machine, int quantity);

    public String getStateName();
}

class VendingMachineState {
    public int itemCount;
    public int itemPrice;
    public int insertedCoin;

    VendingState currState;
    NoCoinState nocoinstate;
    HasCoinState hascoinstate;
    DispenseState dispensestate;
    SoldOutState soldoutstate;

    public VendingMachineState(int itemCount, int itemPrice) {
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;

        this.nocoinstate = new NoCoinState();
        this.hascoinstate = new HasCoinState();
        this.dispensestate = new DispenseState();
        this.soldoutstate = new SoldOutState();

        if (itemCount > 0) {
            currState = nocoinstate;
        } else {
            currState = soldoutstate;
        }
    }

    public void insertCoin(int coin) {
        currState = currState.insertCoin(this, coin);
    }

    public void selectItem() {
        currState = currState.selectItem(this);
    }

    public void dispense() {
        currState = currState.dispense(this);
    }

    public void returnCoin() {
        currState = currState.returnCoin(this);
    }

    public void refill(int qty) {
        currState = currState.refill(this, qty);
    }

    public void printStatus() {
        System.out.println("Current Status of Vendor Machine");
        System.out.println("Price Of Item -------> \t" + this.itemPrice);
        System.out.println("Count of Item -------> \t" + this.itemCount);
        System.out.println("Current State ------> \t" + currState.getStateName());
    }

    public VendingState getNoCoinState() {
        return this.nocoinstate;
    }

    public VendingState getHasCoinState() {
        return this.hascoinstate;
    }

    public VendingState getDispenseState() {
        return this.dispensestate;
    }

    public VendingState getSoldOutState() {
        return this.soldoutstate;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void incrementItemCount(int count) {
        this.itemCount += count;
    }

    public void decrementItemCount() {
        this.itemCount--;
    }

    public int getInsertedCoin() {
        return this.insertedCoin;
    }

    public void setInsertedCoin(int coin) {
        this.insertedCoin = coin;
    }

    public void addCoin(int coin) {
        this.insertedCoin += coin;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(int price) {
        this.itemPrice = price;
    }

}

class NoCoinState implements VendingState {
    public VendingState insertCoin(VendingMachineState machine, int coin) {
        machine.setInsertedCoin(coin);
        System.out.println("Coin Inserted. Current Balace : ----> \t" + coin);
        return machine.getHasCoinState();
    }

    public VendingState selectItem(VendingMachineState machine) {
        System.out.println("Please insert the Coin First");
        return machine.getNoCoinState();
    }

    public VendingState dispense(VendingMachineState machine) {
        System.out.println("Please insert the coin first and select the Item");
        return machine.getNoCoinState();
    }

    public VendingState returnCoin(VendingMachineState machine) {
        System.out.println("No coin to Return");
        return machine.getNoCoinState();
    }

    public VendingState refill(VendingMachineState machine, int quantity) {
        System.out.println("Refilling the Items");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }

    public String getStateName() {
        return "No Coin State";
    }
}

class HasCoinState implements VendingState {
    public VendingState insertCoin(VendingMachineState machine, int coin) {
        machine.addCoin(coin);
        System.out.println("Coins Added. Curremt Balance is ----->\t" + machine.getInsertedCoin());
        return machine.getHasCoinState();
    }

    public VendingState selectItem(VendingMachineState machine) {
        if (machine.getInsertedCoin() >= machine.getItemPrice()) {
            System.out.println("Item Dispencing Started");

            int change = machine.getInsertedCoin() - machine.getItemPrice();
            if (change > 0) {
                System.out.println("Inserted extra money. Returning Change: \t" + change);
            }
            machine.setInsertedCoin(0);
            return machine.getDispenseState();
        } else {
            int need = machine.getItemPrice() - machine.getInsertedCoin();
            System.out.println("Item price is more. Please insert extra need: \t" + need);
            return machine.getHasCoinState();
        }
    }

    public VendingState dispense(VendingMachineState machine) {
        System.out.println("Please Select the Item First");
        return machine.getHasCoinState();
    }

    public VendingState returnCoin(VendingMachineState machine) {
        System.out.println("Coin Returning: ---->\t" + machine.getInsertedCoin());
        machine.setInsertedCoin(0);
        return machine.getNoCoinState();
    }

    public VendingState refill(VendingMachineState machine, int quantity) {
        System.out.println("Can't Refill in this State");
        return machine.getHasCoinState();
    }

    public String getStateName() {
        return "Has Coin State";
    }
}

class DispenseState implements VendingState {
    public VendingState insertCoin(VendingMachineState machine, int coin) {
        System.out.println("Can't Insert this coins now. Returning ---->\t" + coin);
        return machine.getDispenseState();
    }

    public VendingState selectItem(VendingMachineState machine) {
        System.out.println("Already under Dispensing can't slelect new item");
        return machine.getDispenseState();
    }

    public VendingState dispense(VendingMachineState machine) {
        System.out.println("Item Dispensed");
        machine.decrementItemCount();

        if (machine.getItemCount() > 0) {
            return machine.getNoCoinState();
        } else {
            System.out.println("Machine Items are not Sold Out");
            return machine.getSoldOutState();
        }
    }

    public VendingState returnCoin(VendingMachineState machine) {
        System.out.println("Can't return on this state");
        return machine.getDispenseState();
    }

    public VendingState refill(VendingMachineState machine, int quantity) {
        System.out.println("Can't Refill on this State");
        return machine.getDispenseState();
    }

    public String getStateName() {
        return "Dispense State";
    }
}

class SoldOutState implements VendingState {
    public VendingState insertCoin(VendingMachineState machine, int coin) {
        System.out.println("All Items are Sold Out can't insert coin");
        return machine.getSoldOutState();
    }

    public VendingState selectItem(VendingMachineState machine) {
        System.out.println("All Items are sold out can't Select Item");
        return machine.getSoldOutState();
    }

    public VendingState dispense(VendingMachineState machine) {
        System.out.println("All Items are sold out can't dispense");
        return machine.getSoldOutState();
    }

    public VendingState returnCoin(VendingMachineState machine) {
        System.out.println("Nothing to return all Items are sold out");
        return machine.getSoldOutState();
    }

    public VendingState refill(VendingMachineState machine, int quantity) {
        System.out.println("Refilling Items");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }

    public String getStateName() {
        return "Sold Out State";
    }
}

class State {
    public static void main(String[] args) {
        int itemCount = 2;
        int itemPrice = 20;
        VendingMachineState machine = new VendingMachineState(itemCount, itemPrice);
        machine.printStatus();

        machine.insertCoin(10);
        machine.printStatus();

        machine.selectItem();
        machine.printStatus();

        machine.insertCoin(10);
        machine.printStatus();

        machine.selectItem();
        machine.printStatus();

        machine.dispense();
        machine.printStatus();


        machine.insertCoin(20);
        machine.selectItem();
        machine.dispense();
        machine.printStatus();

        machine.insertCoin(30);

        machine.refill(3);
        machine.printStatus();
        machine.insertCoin(30);

        machine .selectItem();

    }
}