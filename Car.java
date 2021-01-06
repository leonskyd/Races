package Races;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable{
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier startline; // добавлен барьер
    
   
    public Car(Race race, int speed, CyclicBarrier startline) {
        this.race = race;
        this.speed = speed;
        this.startline = startline;
        
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    } 
    
    public String getName() { return name; }
    public int getSpeed() { return speed;}
    
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            this.startline.await(); // все машины ждут друг друга и вместе стартуют, хотя в наносекундах видно что не одновременно
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }

}
