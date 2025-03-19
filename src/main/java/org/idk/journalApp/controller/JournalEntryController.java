package org.idk.journalApp.controller;

import org.bson.types.ObjectId;
import org.idk.journalApp.entity.JournalEntry;
import org.idk.journalApp.entity.User;
import org.idk.journalApp.service.JournalEntryService;
import org.idk.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/journal")
@Slf4j
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    User user = userService.findByUsername(username);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    List<JournalEntry> all = user.getJournalEntries();
    if (all != null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/id/{myId}")
  public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    User user = userService.findByUsername(username);
    Optional<JournalEntry> journalEntryOptional = user.getJournalEntries().stream().filter(entry -> entry.getId().equals(myId)).findFirst();

    if (journalEntryOptional.isPresent()) {
      return new ResponseEntity<>(journalEntryOptional.get(), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();

      journalEntryService.saveEntry(entry, username);
      return new ResponseEntity<>(entry, HttpStatus.CREATED);
    } catch (Exception e) {
      log.error("Exception ", e);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/id/{myId}")
  public ResponseEntity<JournalEntry> updateJournalEntryById(
          @PathVariable ObjectId myId,
          @RequestBody JournalEntry newEntry) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    User userInDb = userService.findByUsername(username);
    Optional<JournalEntry> journalEntryOptional = userInDb.getJournalEntries().stream().filter(entry -> entry.getId().equals(myId)).findFirst();

    if (!journalEntryOptional.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    JournalEntry old = journalEntryOptional.get();

    String newTitle = newEntry.getTitle();
    if (!newTitle.isEmpty()) {
      old.setTitle(newTitle);
    }

    String newContent = newEntry.getContent();
    if (newContent != null && !newContent.isEmpty()) {
      old.setContent(newContent);
    }

    journalEntryService.saveEntry(old);
    return new ResponseEntity<>(old, HttpStatus.OK);
  }

  @DeleteMapping("/id/{myId}")
  public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();

      journalEntryService.deleteById(myId, username);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      log.error("Exception ", e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}


