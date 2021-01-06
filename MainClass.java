package Races;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {

        ExecutorService lap = Executors.newFixedThreadPool(CARS_COUNT);
        CyclicBarrier startline = new CyclicBarrier(CARS_COUNT);
        Semaphore entrance1 = new Semaphore(CARS_COUNT/2);
        Finish finish = new Finish(); // Создан класс для финишного этапа, который наследует Stage
        Race race = new Race(new Road(120), new Tunnel(entrance1), new Road(80),finish);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        for (int i = 0; i < CARS_COUNT; i++) { // используется executor для запуска потоков
           lap.execute(new Car(race, 20 + (int) (Math.random() * 10), startline));
        }
        lap.shutdown();

        try {
            Thread.sleep(30000); // не нашел на данный момент нормального решения кроме как усыпить на предполагаемое время гонки
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println(" Гонка завершилась с таким результатом " + finish.getRaceList().toString()); // Здесь мы получаем результат гонки
    }

}

