package a.b.aaasimplewebapp.repo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.repository.PagingAndSortingRepository; // maybe later

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
