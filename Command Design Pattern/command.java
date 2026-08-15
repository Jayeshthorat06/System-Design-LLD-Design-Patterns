class light {
    public void on() {
        System.out.println("Light turn ON");
    }

    public void off() {
        System.out.println("Light turn OFF");
    }
}

class fan {
    public void on() {
        System.out.println("Fan Turn ON");
    }

    public void off() {
        System.out.println("Fan turn OFF");
    }
}

interface Icommand {
    public void execute();

    public void undo();
}

class lightCommand implements Icommand {
    light light;

    public lightCommand(light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class fanCommand implements Icommand {
    fan fan;

    public fanCommand(fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.on();
    }

    public void undo() {
        fan.off();
    }
}

class remoteControl {
    public static int numsize = 4;
    Icommand[] command = new Icommand[numsize];
    boolean[] curr = new boolean[numsize];

    public remoteControl() {
        for (int i = 0; i < numsize; i++) {
            this.command[i] = null;
            this.curr[i] = false;
        }
    }

    public void setCommand(Icommand command, int index) {
        if (index >= 0 && index < 4) {
            if (this.command[index] != null) {
                this.command[index] = null;
            }

            this.command[index] = command;

        }

    }

    public void pressButton(int index) {
        if (this.command[index] != null && index >= 0 && index < 4) {
            if (curr[index] == false) {
                command[index].execute();
            } else {
                command[index].undo();
            }
            curr[index] = !curr[index];
        } else {
            System.out.println("No command found on this index");
        }
    }
}

class command {
    public static void main(String[] args) {

        lightCommand lc = new lightCommand(new light());
        fanCommand fc = new fanCommand(new fan());

        remoteControl remote = new remoteControl();

        System.out.println("Light simulation");

        remote.setCommand(lc, 0);

        remote.pressButton(0);
        remote.pressButton(0);

        remote.setCommand(fc, 1);

        remote.pressButton(1);
        remote.pressButton(1);

    }
}