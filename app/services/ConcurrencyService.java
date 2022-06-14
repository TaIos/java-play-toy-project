package services;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrencyService {

    private static final int DEFAULT_POOL_SIZE = 3;


    public void submitTasks(List<Runnable> tasks) {
        ExecutorService executor = createFixedThreadPool();
        tasks.forEach(executor::submit);
        executor.shutdown();
    }

    public void runCallable(Callable<String> callable) {
        ExecutorService executor = createFixedThreadPool();
        Future<String> future = executor.submit(callable);

        boolean listen = true;
        while (listen) {
            if (future.isDone()) { // isDone is a non-blocking method
                String result;
                try {
                    // blocking method and it blocks the main thread until a response arrives
                    result = future.get();
                    listen = false;
                    System.out.println(result);
                    // if the current thread was interrupted while waiting
                    // if the computation threw an exception
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        executor.shutdown();
    }


    private ExecutorService createFixedThreadPool(int poolSize) {
        return Executors.newFixedThreadPool(poolSize);
    }

    private ExecutorService createFixedThreadPool() {
        return createFixedThreadPool(DEFAULT_POOL_SIZE);
    }

}


