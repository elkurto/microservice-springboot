package a.b.aaasimplewebapp.service;

import a.b.aaasimplewebapp.domain.ConstRestUri;
import a.b.aaasimplewebapp.domain.ResponseIndex;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(ConstRestUri.LIBRARY)
public class LibraryController {

  public LibraryController() {

  }

  @GetMapping("")
  public ResponseEntity<ResponseIndex> index() {
    var responseIndex =ResponseIndex.builder().segment(ConstRestUri.LIBRARY).status("success").build();
    return ResponseEntity.ok(responseIndex);
  }
}
