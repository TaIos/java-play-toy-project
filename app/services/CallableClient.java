package services;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
There is an endless loop if callable throws an exception.
See https://stackoverflow.com/q/72460628/6784881
 */
public class CallableClient {

  public static class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
      throw new Exception();
    }
  }

  public static void main(String[] args) {
    Callable<String> callableTask = new CallableTask();
    ExecutorService executor = Executors.newFixedThreadPool(2);
    Future<String> future = executor.submit(callableTask);
    boolean listen = true;
    while (listen) {
      if (future.isDone()) {
        String result;
        try {
          result = future.get();
          listen = false;
          System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      }
    }
    executor.shutdown();
  }
}
