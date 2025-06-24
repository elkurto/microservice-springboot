package a.b.aaasimplewebapp.service;


import a.b.aaasimplewebapp.domain.Book;
import a.b.aaasimplewebapp.repo.BookEntity;
import a.b.aaasimplewebapp.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {

  private final BookRepository bookRepository;

  @Override
  public Book upsert(Book book) {

    BookEntity bookEntity =BookMapper.INSTANCE.toEntity(book);
    BookEntity bookEntitySaved =bookRepository.save(bookEntity);
    return BookMapper.INSTANCE.toDomain(bookEntitySaved);
  }

  @Override
  public Book findById(UUID id) {
    BookEntity bookEntitySaved =bookRepository.findById(id).orElse(null);
    Book book =null;
    if (bookEntitySaved != null) {
      book = BookMapper.INSTANCE.toDomain(bookEntitySaved);
    }
    return book;
  }

  @Override
  public void deleteById(UUID id) {
    bookRepository.deleteById(id);
  }

  // todo: make many to many with (book and category) +  (book and author)
}
