package a.b.aaasimplewebapp.repo;

import a.b.aaasimplewebapp.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

// contradict the docs
// a. u don't need  TestEntityManager
// b. using an embedded database (h2) does not test JPA and postgres
// c. spring is so daft that dev tells spring not replace the working database config with a broken one.
//    see :class_annotation: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  //@Autowired
  //TestEntityManager testEntityManager;

  @Test
  public void testSaveBook() {
    BookEntity book = new BookEntity(null, "foo","bar", System.currentTimeMillis());

    BookEntity bookSaved01 =bookRepository.save(book);

    Assertions.assertEquals(book.getId(), bookSaved01.getId());
    Assertions.assertEquals(book.getName(), bookSaved01.getName());
    Assertions.assertEquals(book.getAuthor(), bookSaved01.getAuthor());
    Assertions.assertEquals(book.getPublishedDateMillis(), bookSaved01.getPublishedDateMillis());


  }
}




