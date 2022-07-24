package com.zackoverflow.product.service;

import com.zackoverflow.product.dto.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}