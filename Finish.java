package Races;

import java.util.ArrayList;

public class Finish extends Stage{ 
    final private int FIN = 1;
    private int counter;
    private ArrayList<String> raceList;

    public Finish() {
        this.counter = FIN;
        this.raceList = new ArrayList<>();
    }

    public ArrayList<String> getRaceList() {
        return raceList;
    }

    @Override
    public void go(Car c) {
        System.out.println(c.getName() + " закончил гонку и занял " + this.counter + " -е место !");
        this.raceList.add(c.getName() + " - место  № " + this.counter);
        this.counter++;
    }
}
