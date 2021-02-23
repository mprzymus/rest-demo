package pl.mprzymus.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.apidemo.domain.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
