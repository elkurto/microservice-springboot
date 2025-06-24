package a.b.aaasimplewebapp.service;

import a.b.aaasimplewebapp.domain.Book;
import a.b.aaasimplewebapp.domain.ConstRestUri;
import a.b.aaasimplewebapp.domain.ResponseIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
  public ResponseEntity<Book> postBook(@RequestBody Book book) {
    Book bookUpserted =this.libraryService.upsert(book);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{uuid}")
            .buildAndExpand(bookUpserted.getId())
            .toUri();
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Location", location.toString());

    return ResponseEntity.ok()
            .headers(responseHeaders)
            .body(bookUpserted);

  }

  @GetMapping(ConstRestUri.BOOK+"/{uuidBook}")
  public ResponseEntity<Book> getBook(@PathVariable UUID uuidBook) {
    Book book =this.libraryService.findById(uuidBook);
    if ( book == null ) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(book);
  }

  @DeleteMapping(ConstRestUri.BOOK+"/{uuidBook}")
  public ResponseEntity<Book> deleteBook(@PathVariable UUID uuidBook) {
    Book book =this.libraryService.findById(uuidBook);
    if ( book != null ) {
      this.libraryService.deleteById(uuidBook);
      return new ResponseEntity<>(book, HttpStatus.OK);
    }else {
      return new ResponseEntity<>(new Book(uuidBook, null, null, 0L), HttpStatus.NOT_FOUND);
    }

  }



}
