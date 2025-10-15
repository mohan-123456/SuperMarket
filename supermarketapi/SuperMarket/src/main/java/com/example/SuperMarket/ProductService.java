package com.example.SuperMarket;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	ProductRepository repo;

	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Optional<Product> getById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		if(repo.existsById(id))
				repo.deleteById(id);
		else
			return "not found";
		return "Deleted successflly";
	}

	public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOpt = repo.findById(id);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            return repo.save(existingProduct);
        }
        return null;
    }

}
