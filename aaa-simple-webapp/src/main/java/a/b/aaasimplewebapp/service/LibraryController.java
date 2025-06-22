package a.b.aaasimplewebapp.service;

import a.b.aaasimplewebapp.domain.Book;
import a.b.aaasimplewebapp.domain.ConstRestUri;
import a.b.aaasimplewebapp.domain.ResponseIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RestController()
@RequestMapping(ConstRestUri.LIBRARY)
public class LibraryController {
  private final LibraryServiceImpl libraryService;

  @GetMapping("")
  public ResponseEntity<ResponseIndex> index() {
    var responseIndex =ResponseIndex.builder().segment(ConstRestUri.LIBRARY).status("success").build();
    return ResponseEntity.ok(responseIndex);
  }

  @PostMapping(ConstRestUri.BOOK)
  public ResponseEntity<Book> postBook(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
    Book bookUpserted =this.libraryService.upsert(book);
    var newBookUri =uriComponentsBuilder.path(ConstRestUri.LIBRARY_BOOK+"/{uuid}").build(bookUpserted.getId());

    ResponseEntity<Book> responseEntityBook =new ResponseEntity<>(book, HttpStatus.OK);
    responseEntityBook.getHeaders().setLocation(newBookUri);
    return responseEntityBook;
  }

  @GetMapping
  public ResponseEntity<Book> getBook(@RequestParam UUID uuidBook) {
    Book book =this.libraryService.findById(uuidBook);
    if ( book == null ) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(book);
  }

  @DeleteMapping
  public ResponseEntity<Book> deleteBook(@RequestParam UUID uuidBook) {
    Book book =this.libraryService.findById(uuidBook);
    if ( book != null ) {
      this.libraryService.deleteById(uuidBook);
      return new ResponseEntity<>(book, HttpStatus.OK);
    }else {
      return new ResponseEntity<>(new Book(uuidBook, null, null, 0L), HttpStatus.NOT_FOUND);
    }

  }



}
