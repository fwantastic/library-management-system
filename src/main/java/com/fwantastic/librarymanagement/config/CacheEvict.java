package com.fwantastic.librarymanagement.config;

import javax.management.timer.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvict {

  private static final Logger logger = LoggerFactory.getLogger(CacheEvict.class);

  @Autowired
  private CacheManager cacheManager;

  @Scheduled(fixedRate = Timer.ONE_HOUR)
  public void clearCacheSchedule() {
    logger.info("Clearing cache...");
    cacheManager.getCache("books").clear();

  }
}
