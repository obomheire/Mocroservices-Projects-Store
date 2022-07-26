package com.zackoverflow.product.service;

import com.zackoverflow.product.ProductRepository;
import com.zackoverflow.product.dto.Product;
import com.zackoverflow.product.exception.OfferNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    public ProductService(ProductRepository productRepository) { // NB: this is achieved by using @Autowired as shown above
//        this.productRepository = productRepository;
//    }

    public String addProduct(Product product) {

        if (product.getPrice() == 0 && product.getDiscount() > 0) {
            throw  new OfferNotValidException("Discount is not available at 0 product price");
        }

        log.info("Adding product");
        productRepository.save(product);
        return "Product Successfully Added";
    }

    public List<Product> listAllproducts() {
        return productRepository.findAll();
    }

    public List<Product> productCategoryList(String category) {
        return productRepository.findByCategory(category);
    }

    public Product productById(Integer id) {
        return productRepository.findById(id).get();
    }

    public String updateProduct(Product product) {

        productRepository.save(product);
        return "Product updated successfully";
    }

    public String deleteProductById(Integer id) {

        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}


/*
INITIAL CODE BEFORE MONGODB IS INCLUDED
@Service
public class ProductService {

    List<Product> products = new ArrayList<>();
    public String addProduct(Product product) {

        products.add(product);

        return "Product Successfully Added";
    }

    public List<Product> listAllproducts() {
        return products;
    }

    public List<Product> productCategoryList(String category) {
        return products.stream()
                .filter(product -> product.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Product productById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findAny().get();
    }

    public String updateProduct(Product product) {
        for (Product prod: products) {
            if (prod.getId().equals(product.getId())) {
                prod.setName(product.getName());
                prod.setCategory(product.getCategory());
                prod.setPrice(product.getPrice());
                prod.setDiscount(product.getDiscount());
                prod.setDiscountDescription(product.getDiscountDescription());

                return "Product updated successfully";
            }
        }
        return "Product updated failed";
    }

    public String deleteProductById(Integer id) {
        for (Product product: products) {
            if (product.getId().equals(id)) {
                products.remove(product);

                return "Product deleted successfully";
            }
        }

        return "Fail to delete product";
    }
}*/
