package pl.mprzymus.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.apidemo.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}