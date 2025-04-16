package org.idk.journalApp.cache;

import org.idk.journalApp.entity.ConfigJournalApp;
import org.idk.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {

  @Autowired
  private ConfigJournalAppRepository configJournalAppRepository;

  public Map<String, String> APP_CACHE;

  @PostConstruct
  public void init() {
    APP_CACHE = new HashMap<>();
    List<ConfigJournalApp> all = configJournalAppRepository.findAll();
    all.forEach(configJournalApp -> APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue()));
  }
}
