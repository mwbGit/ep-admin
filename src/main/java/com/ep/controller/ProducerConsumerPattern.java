package com.ep.controller;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by MengWeiBo on 2017-08-21
 */
public class ProducerConsumerPattern {
    public static void main(String[] args) {
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
        Thread thread = new Thread(new Product(blockingDeque));
        Thread thread1 = new Thread(new Consumer(blockingDeque));
        thread.start();
        thread1.start();
    }
}


class Product implements Runnable {
    private final BlockingDeque<Integer> blockingDeque;

    public Product(BlockingDeque<Integer> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                blockingDeque.put(i);
                System.out.println("create i=" + i);

            } catch (InterruptedException e) {
                System.out.println("InterruptedException i=" + i);

            }
        }
    }
}

class Consumer implements Runnable {
    private final BlockingDeque<Integer> blockingDeque;

    public Consumer(BlockingDeque<Integer> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("use i=" + blockingDeque.take());
            } catch (InterruptedException e) {
                System.out.println("blockingDeque ");

            }
        }
    }
}