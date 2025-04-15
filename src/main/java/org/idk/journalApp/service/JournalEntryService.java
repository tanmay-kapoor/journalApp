package org.idk.journalApp.service;

import org.bson.types.ObjectId;
import org.idk.journalApp.entity.JournalEntry;
import org.idk.journalApp.entity.User;
import org.idk.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepository;

  @Autowired
  private UserService userService;

  public List<JournalEntry> getAll() {
    return journalEntryRepository.findAll();
  }

  @Transactional
  public void saveEntry(JournalEntry entry, String username) throws Exception {
    User user = userService.findByUsername(username);
    if (user == null) {
      throw new Exception("User not found");
    }

    entry.setDate(LocalDateTime.now());
    JournalEntry saved = journalEntryRepository.save(entry);
    user.getJournalEntries().add(saved);
    userService.saveUser(user);
  }

  public void saveEntry(JournalEntry entry) {
    journalEntryRepository.save(entry);
  }

  public Optional<JournalEntry> findByid(ObjectId id) {
    return journalEntryRepository.findById(id);
  }

  @Transactional
  public void deleteById(ObjectId id, String username) throws Exception {
    try {
      User user = userService.findByUsername(username);
      if (user == null) {
        throw new Exception("User not found");
      }

      boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
      if (removed) {
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
      }
    } catch (Exception e) {
      log.error("Error deleting entry", e);
      throw e;
    }
  }
}
