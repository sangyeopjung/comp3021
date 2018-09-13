import java.util.Arrays;
import java.util.Objects;

public class BusCompany {
    private static int numCompanies = 0;

    private String name;
    private int numBuses;
    private int arraySize;
    private Bus[] buses;

    BusCompany(String name) {
        this.name = name;
        this.numBuses = 0;
        this.arraySize = 5;
        this.buses = new Bus[arraySize];

        numCompanies++;
    }

    public String getName() {
        return name;
    }

    public boolean createAndAddBus(int id, String model) {
        if (numBuses == arraySize) {
            return false;
        }

        for (int i = 0; i < numBuses; i++) {
            if (id == buses[i].getID()) {
                return false;
            }
        }

        buses[numBuses++] = new Bus(id, model);
        return true;
    }

    public void removeAllBuses() {
        numBuses = 0;
    }

    public static int getNumCompanies() {
        return numCompanies;
    }
}