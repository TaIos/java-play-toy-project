package services;

import akka.actor.ActorSystem;
import java.time.Duration;
import javax.inject.Inject;
import scala.concurrent.ExecutionContext;

public class AkkaSchedulingSerivce {

  private final ActorSystem actorSystem;
  private final ExecutionContext executionContext;

  @Inject
  public AkkaSchedulingSerivce(ActorSystem actorSystem, ExecutionContext executionContext) {
    this.actorSystem = actorSystem;
    this.executionContext = executionContext;
  }

  public void scheduleTask() {
    actorSystem.log().info("Staring scheduling of the task");
    actorSystem
        .scheduler()
        .scheduleAtFixedRate(
            Duration.ofSeconds(30),
            Duration.ofSeconds(10),
            () ->
                actorSystem
                    .log()
                    .info(String.format("Time in millis is [%d]", System.currentTimeMillis())),
            executionContext);
    actorSystem.log().info("Scheduling of the task done");
  }
}
