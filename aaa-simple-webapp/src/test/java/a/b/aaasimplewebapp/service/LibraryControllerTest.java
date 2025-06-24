package a.b.aaasimplewebapp.service;


import a.b.aaasimplewebapp.domain.Book;
import a.b.aaasimplewebapp.domain.ConstRestUri;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {
  @Autowired
  private MockMvc mockMvc;
  private final ObjectMapper mapper = new ObjectMapper();

  @MockitoBean
  private LibraryService libraryService;

  @Test
  void testIndex() throws Exception {
    String expected_body = "{\"segment\":\"/library\",\"status\":\"success\"}";
    mockMvc.perform(MockMvcRequestBuilders.get(ConstRestUri.LIBRARY))
            .andExpect(status().isOk())
            .andExpect(content().json( expected_body, JsonCompareMode.LENIENT))
            .andExpect(content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON));

  }

  @Test
  void testGetBookById() throws Exception {
    // given
    UUID uuid = UUID.randomUUID();
    Book book = new Book(uuid, "foo","bar", 123456);

    when(libraryService.findById(uuid)).thenReturn(book);

    // when
    mockMvc.perform(MockMvcRequestBuilders.get(ConstRestUri.LIBRARY_BOOK + "/"+ uuid.toString()))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("foo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("bar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.publishedDateMillis").value(123456))

    ;

  }

  @Test
  void testGetBookByIdShouldReturn404WhenNotFound() throws Exception {
    UUID uuid = UUID.randomUUID();
    when(libraryService.findById(uuid)).thenReturn(null);

    mockMvc.perform(MockMvcRequestBuilders.get(ConstRestUri.LIBRARY_BOOK +"/"+ uuid.toString()))
            .andExpect(status().isNotFound());
  }

  @Test
  void testDelete() throws Exception {
    UUID uuid = UUID.randomUUID();
    Book book = new Book(uuid, "foo","bar", 123456);
    when(libraryService.findById(uuid)).thenReturn(book);

    mockMvc.perform(MockMvcRequestBuilders.delete(ConstRestUri.LIBRARY_BOOK + "/" + uuid.toString()))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("foo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("bar"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.publishedDateMillis").value(123456));
  }

  @Test
  void testDeleteShouldReturn404WhenNotFound() throws Exception {
    UUID uuid = UUID.randomUUID();
    when(libraryService.findById(uuid)).thenReturn(null);

    mockMvc.perform(MockMvcRequestBuilders.delete(ConstRestUri.LIBRARY_BOOK + "/" + uuid.toString()))
            .andExpect(status().isNotFound())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$.author").doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$.publishedDateMillis").value(0));

  }
}
