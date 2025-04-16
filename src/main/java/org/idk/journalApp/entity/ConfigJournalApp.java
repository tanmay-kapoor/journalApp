package org.idk.journalApp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "config_journal_app")
@Data
public class ConfigJournalApp {

//  @Id
//  private ObjectId id;

  @NonNull
  private String key;

  @NonNull
  private String value;
}
