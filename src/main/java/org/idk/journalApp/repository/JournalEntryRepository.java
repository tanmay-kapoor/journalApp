package org.idk.journalApp.repository;

import org.bson.types.ObjectId;
import org.idk.journalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
