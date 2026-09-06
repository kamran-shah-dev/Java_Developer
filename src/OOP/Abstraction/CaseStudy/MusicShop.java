package OOP.Abstraction.CaseStudy;

import com.sun.tools.javac.Main;

import javax.lang.model.element.Name;

public class MusicShop {
    public static void main(String[] args) {
        Instrument[] instruments = new Instrument[3];
        instruments[0] = new Guitar("Fender Stratocaster", 2020, 6, "electric");
        instruments[1] = new Piano("Steinway", 2015, true);
        instruments[2] = new Guitar("Martin", 2018, 12, "acoustic");

        for (Instrument instrument : instruments) {
            System.out.println(instrument.play());
        }

        for (Instrument instrument : instruments) {
            System.out.println("Working with :" +  instrument.getName());

            if(instrument instanceof Tunable tunableInstrument) {
                System.out.println(tunableInstrument.tune());
                System.out.println(tunableInstrument.adjustPitch(true));
                System.out.println(tunableInstrument.adjustPitch(false));
            }

            if(instrument instanceof Maintanable maintanableInstrument) {
                System.out.println(maintanableInstrument.clean());
                System.out.println(maintanableInstrument.inspect());
            }
        }
    }
}

abstract class Instrument {
    private final String name;
    private final int yearOfManufacture;

    public Instrument(String name, int yearOfManufacture) {
        this.name = name;
        this.yearOfManufacture = yearOfManufacture;
    }

    abstract String play();

    public String getInstrumentDetails() {
        return "Instrument Name: " + this.name + ", Year of Manufacture: " + this.yearOfManufacture;
    }

    public String getName() {
        return name;
    }
}

interface Tunable {
    String tune();
    String adjustPitch(boolean up);
}

interface Maintanable {
    String clean();
    String inspect();
}

class StringedInstrument extends Instrument {
    int numberOfStrings;

    public StringedInstrument (String name, int manufactureYear, int numberOfStrings) {
        super(name, manufactureYear);
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    String play() {
        return "Playing the Stringed Instrument";
    }

    @Override
    public String getInstrumentDetails() {
        return this.getInstrumentDetails() + " Strings: " + this.numberOfStrings;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }
}

class Guitar extends StringedInstrument implements Tunable, Maintanable {
    private String guitarType;

    public Guitar(String Name, int manufactureYear, int numberOfStrings, String guitarType) {
        super(Name , manufactureYear , numberOfStrings);
        this.guitarType = guitarType;
    }

    @Override
    String play() {
        return "Playing the: " + guitarType + " Guitar, with number of strings: " + getNumberOfStrings();
    }

    @Override
    public String getInstrumentDetails() {
        return this.getInstrumentDetails() + " Type: " + guitarType;
    }

    @Override
    public String tune() {
        return "Tuning " + guitarType + " Guitar";
    }

    @Override
    public String adjustPitch(boolean up) {
        return up ? "Increasing pitch of "+ guitarType + " Guitar" :
                "Decreasing pitch of "+ guitarType + " Guitar";
    }

    @Override
    public String clean() {
        return "Cleaning " + guitarType + " Guitar";
    }

    @Override
    public String inspect() {
        return "Inspecting " + guitarType + " Guitar";
    }
}

class Piano extends Instrument implements Tunable, Maintanable {
    private final boolean isGrand;

    public Piano(String Name, int manufactureYear, boolean isGrand) {
        super(Name , manufactureYear);
        this.isGrand = isGrand;
    }

    @Override
    String play() {
        return "Playing the: " + (isGrand? "Grand Piano" : "Upright Piano");
    }

    @Override
    public String getInstrumentDetails() {
        return this.getInstrumentDetails() + " Type: " + (isGrand? "Grand Piano" : "Upright Piano");
    }

    @Override
    public String tune() {
        return "Tuning " + (isGrand? "Grand Piano" : "Upright Piano");
    }

    @Override
    public String adjustPitch(boolean up) {
        return up ? "Increasing pitch of "+ (isGrand? "Grand Piano" : "Upright Piano") :
                "Decreasing pitch of "+ (isGrand? "Grand Piano" : "Upright Piano");
    }

    @Override
    public String clean() {
        return "Cleaning " + (isGrand? "Grand Piano" : "Upright Piano");
    }

    @Override
    public String inspect() {
        return "Inspecting " + (isGrand? "Grand Piano" : "Upright Piano");
    }
}