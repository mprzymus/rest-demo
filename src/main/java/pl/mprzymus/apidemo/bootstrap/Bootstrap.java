package pl.mprzymus.apidemo.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mprzymus.apidemo.domain.Category;
import pl.mprzymus.apidemo.domain.Customer;
import pl.mprzymus.apidemo.domain.Vendor;
import pl.mprzymus.apidemo.repositories.CategoryRepository;
import pl.mprzymus.apidemo.repositories.CustomerRepository;
import pl.mprzymus.apidemo.repositories.VendorRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Override
    public void run(String... args) {
        generateCategories();
        generateCustomers();
        generateVendors();
    }

    private void generateVendors() {
        var vendor1 = new Vendor();
        vendor1.setName("Vendor1");

        var vendor2 = new Vendor();
        vendor2.setName("Vendor2");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);

        log.info("Vendors loaded = " + vendorRepository.count());
    }

    private void generateCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerRepository.save(customer2);

        log.info("Customers loaded = " + customerRepository.count());

    }

    private void generateCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info("Categories Loaded = " + categoryRepository.count());
    }
}