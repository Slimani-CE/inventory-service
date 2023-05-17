package ma.enset.distributedsystems.inventoryservice;

import ma.enset.distributedsystems.inventoryservice.entities.Product;
import ma.enset.distributedsystems.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {

	private ProductRepository productRepository;
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public InventoryServiceApplication(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
		this.productRepository = productRepository;
		this.repositoryRestConfiguration = repositoryRestConfiguration;
	}


	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Expose Products Id
		repositoryRestConfiguration.exposeIdsFor(Product.class);

		productRepository.save(new Product(null, "LAPTOP HP", 3500., 5.));
		productRepository.save(new Product(null, "HyperX Headphones", 1500., 10.));
		productRepository.save(new Product(null, "MACBOOK PRO", 9000., 5.));
		productRepository.save(new Product(null, "DELL DESKTOP", 5000., 5.));
		productRepository.findAll().forEach(product -> {
			System.out.println("Product name: " + product.getName());
		});
	}
}
