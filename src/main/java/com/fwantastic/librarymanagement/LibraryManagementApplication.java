package com.fwantastic.librarymanagement;

import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// @EnableAsync annotation switches on Spring’s ability to run @Async methods in a background thread pool
@EnableAsync
@SpringBootApplication
public class LibraryManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryManagementApplication.class, args);
  }

  // /*- will turn off the code formatting
  /*-
   * Reference: https://spring.io/guides/gs/async-method/ If Executor bean is not found, Spring
   * creates SimpleAsyncTaskExecutor and uses that. Best practice is to define your own settings.
   * How to determine the right size:
   * - The actual constraint is that a logical CPU core can only run a single thread at the same time
   * - If you have 8 logical cores, you can safely put 8 threads
   * - Queue size: as many as you can wait on. The queue size is the number of items your threadPool will accept before rejecting them
   * - It is business logic that depends on what behavior you expect
   */
  @Bean
  public Executor taskExecutor() {
    // ThreadPoolTaskExecutor javadoc: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/concurrent/ThreadPoolTaskExecutor.html#fields.inherited.from.class.org.springframework.scheduling.concurrent.ExecutorConfigurationSupport
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(1);
    executor.setMaxPoolSize(3);   // Default is Integer.MAX_VALUE
    executor.setQueueCapacity(5); // Default is Integer.MAX_VALUE.
    executor.setThreadNamePrefix("LMS-");
    executor.initialize();
    return executor;
  }
}
