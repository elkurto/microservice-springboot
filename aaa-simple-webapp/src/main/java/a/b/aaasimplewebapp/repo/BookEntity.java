package a.b.aaasimplewebapp.repo;

import a.b.aaasimplewebapp.domain.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column
  private String name;
  @Column
  private String author;
  @Column
  private long publishedDateMillis;

  @Override
  public boolean equals(Object o) {
    if ( this == o ) {
      return true;
    }else if ( o instanceof BookEntity other) {
      return Objects.equals( this.id, other.id);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
