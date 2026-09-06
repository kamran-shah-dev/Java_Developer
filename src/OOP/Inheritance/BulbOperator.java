package OOP.Inheritance;

public class BulbOperator {
    public static void main(String[] args) {
        Switchable[] switchable = new Switchable[3];

        SmartBulb smartBulb = new SmartBulb(76);
        DimmableBulb dimmableBulb = new DimmableBulb(80);
        RegularBulb regularBulb = new RegularBulb();

        switchable[0] = smartBulb;
        switchable[1] = dimmableBulb;
        switchable[2] = regularBulb;

        for (Switchable s : switchable) {
            s.turnOn();
            s.turnOff();
        }

        // When we created array we created it like Switchable[] switchable = new Switchable[3]; even though
        // We put smart, dimmable and regular objects at each reference but java treats all indices of switchable as
        // Switchable reference types. Now because switchable defined the turn (On and Off) functions so
        // It will be able to call both of them but cannot access the functions declared in type Adjustable and  Connectable
        // So we need to cast the types first.
        // Concept in polymorphism.
        smartBulb.increaseBrightness();

        ((Adjustable) switchable[0]).increaseBrightness();
        ((Connectable) switchable[0]).Connect();
    }
}

interface Switchable {
    void turnOn();
    void turnOff();
}

interface Adjustable {
    void increaseBrightness();
    void decreaseBrightness();
}

interface Connectable {
    void Connect();
    void Disconnect();
}

interface Volume {
    void increaseVolume();
    void decreaseVolume();
}

class SmartBulb implements Switchable, Adjustable, Connectable {
    boolean isTurnedOn;
    boolean isConnected;
    int Brightness;

    public SmartBulb(int brightness) {
        if (brightness < 0) {
            System.out.println("Speed cannot be negative");
        } else if (brightness > 100) {
            System.out.println("Speed cannot increase the threshold (100)");
        } else {
            this.Brightness = brightness;
            System.out.println("Smart bulb brightness set to: " + this.Brightness);
        }
    }


    @Override
    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Smart Bulb is Turned On");
    }

    @Override
    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Smart Bulb is Turned Off");
    }

    @Override
    public void increaseBrightness () {
        if (this.Brightness > 100) {
            System.out.println("Speed cannot increase the threshold (100)");
            System.out.println("Current brightness: " + this.Brightness);
        } else {
            this.Brightness += 10;
            System.out.println("Speed Increased! New Speed: " + this.Brightness);
        }
    }

    @Override
    public void decreaseBrightness() {
        if ((Brightness - 10) < 0) {
            System.out.println("Speed is at minimum threshold");
            System.out.println("Current brightness: " + this.Brightness);
        } else {
            this.Brightness -= 10;
            System.out.println("Speed Decreased! New Speed: " + this.Brightness);
        }
    }

    @Override
    public void Connect() {
        isConnected = true;
        System.out.println("Smart Bulb is connected to the network");
    }

    @Override
    public void Disconnect() {
        isConnected = true;
        System.out.println("Smart Bulb is disconnected from the network");
    }
}

class DimmableBulb implements Switchable, Adjustable {
    boolean isTurnedOn;
    int Brightness;

    public DimmableBulb(int brightness) {
        if (brightness < 0) {
            System.out.println("Speed cannot be negative");
        } else if (brightness > 100) {
            System.out.println("Speed cannot increase the threshold (100)");
        } else {
            this.Brightness = brightness;
            System.out.println("DimmableBulb brightness set to: " + this.Brightness);
        }
    }


    @Override
    public void turnOn() {
        isTurnedOn = true;
        System.out.println("DimmableBulb Bulb is Turned On");
    }

    @Override
    public void turnOff() {
        isTurnedOn = false;
        System.out.println("DimmableBulb Bulb is Turned Off");
    }

    @Override
    public void increaseBrightness () {
        if (this.Brightness > 100) {
            System.out.println("Speed cannot increase the threshold (100)");
            System.out.println("Current brightness: " + this.Brightness);
        } else {
            this.Brightness += 10;
        }
    }

    @Override
    public void decreaseBrightness() {
        if ((Brightness - 10) < 0) {
            System.out.println("Speed is at minimum threshold");
            System.out.println("Current brightness: " + this.Brightness);
        } else {
            this.Brightness -= 10;
        }
    }
}

class RegularBulb implements Switchable {
    boolean isTurnedOn;

    @Override
    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Bulb is Turned On");
    }

    @Override
    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Bulb is Turned Off");
    }
}

class Speaker implements Switchable, Connectable, Volume {
    boolean isTurnedOn;
    boolean isConnected;
    int volume = 50;

    @Override
    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Smart Bulb is Turned On");
    }

    @Override
    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Smart Bulb is Turned Off");
    }

    @Override
    public void Connect() {
        isConnected = true;
        System.out.println("Smart Bulb is connected to the network");
    }

    @Override
    public void Disconnect() {
        isConnected = true;
        System.out.println("Smart Bulb is disconnected from the network");
    }

    @Override
    public void increaseVolume () {
        if (this.volume > 100) {
            System.out.println("Speed cannot increase the threshold (100)");
            System.out.println("Current brightness: " + this.volume);
        } else {
            this.volume += 10;
            System.out.println("Speed Increased! New Speed: " + this.volume);
        }
    }

    @Override
    public void decreaseVolume() {
        if ((volume - 10) < 0) {
            System.out.println("Speed is at minimum threshold");
            System.out.println("Current brightness: " + this.volume);
        } else {
            this.volume -= 10;
            System.out.println("Speed Decreased! New Speed: " + this.volume);
        }
    }
}

class Fan implements Switchable, Adjustable, Connectable {
    boolean isTurnedOn;
    boolean isConnected;
    int Speed;

    @Override
    public void turnOn() {
        isTurnedOn = true;
        System.out.println("Smart Bulb is Turned On");
    }

    @Override
    public void turnOff() {
        isTurnedOn = false;
        System.out.println("Smart Bulb is Turned Off");
    }

    @Override
    public void increaseBrightness () {
        if (this.Speed > 100) {
            System.out.println("Speed cannot increase the threshold (100)");
            System.out.println("Current Speed: " + this.Speed);
        } else {
            this.Speed += 10;
            System.out.println("Speed Increased! New Speed: " + this.Speed);
        }
    }

    @Override
    public void decreaseBrightness() {
        if ((Speed - 10) < 0) {
            System.out.println("Speed is at minimum threshold");
            System.out.println("Current brightness: " + this.Speed);
        } else {
            this.Speed -= 10;
            System.out.println("Speed Decreased! New Speed: " + this.Speed);
        }
    }

    @Override
    public void Connect() {
        isConnected = true;
        System.out.println("Smart Bulb is connected to the network");
    }

    @Override
    public void Disconnect() {
        isConnected = true;
        System.out.println("Smart Bulb is disconnected from the network");
    }
}