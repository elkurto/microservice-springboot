package a.b.aaasimplewebapp.service;

import a.b.aaasimplewebapp.domain.Book;

import java.util.UUID;

public interface LibraryService {
  Book upsert(Book book);

  Book findById(UUID id);

  void deleteById(UUID id);
}
