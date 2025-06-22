package a.b.aaasimplewebapp.repo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.PagingAndSortingRepository; // maybe later

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
