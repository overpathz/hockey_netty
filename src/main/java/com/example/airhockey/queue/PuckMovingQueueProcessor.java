package com.example.airhockey.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
// in general, for different rooms there should be Map here, cuz we're working with many pucks
public class PuckMovingQueueProcessor {

    private final BlockingQueue<Runnable> puckQueue = new ArrayBlockingQueue<>(100);
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public void assignTask(Runnable task) {
        puckQueue.add(task);
    }

    @PostConstruct
    public void init() {
        singleThreadExecutor.submit(() -> {
            while (true) {
                try {
                    Runnable task = puckQueue.take();
                    log.info("Processing task..");
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }
}
