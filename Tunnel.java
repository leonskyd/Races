package Races;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{

    private Semaphore entrance; // добавлен семафор

    public Tunnel(Semaphore entrance) {
        this.length = 150;
        this.description = "Тоннель " + length + " метров";
        this.entrance = entrance;


    }
    @Override
    public void go(Races.Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                this.entrance.acquire(); // добавлена проверка на семафоре
                System.out.println(c.getName() + " начал этап: " + description + " в " + System.currentTimeMillis());
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description + " в " + System.currentTimeMillis());
                this.entrance.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
