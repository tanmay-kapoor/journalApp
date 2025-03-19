package org.idk.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {

  @Id
  private ObjectId id;

  @NonNull
  private String title;

  private String content;

  private LocalDateTime date;
}
