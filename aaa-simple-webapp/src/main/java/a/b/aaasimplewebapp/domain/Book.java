package a.b.aaasimplewebapp.domain;

import a.b.aaasimplewebapp.repo.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Book {
  private UUID id;
  private String name;
  private String author;
  private long publishedDateMillis;

  @Override
  public boolean equals(Object o) {
    if ( this == o ) {
      return true;
    }else if ( o instanceof Book other) {
      return Objects.equals( this.id, other.id);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

