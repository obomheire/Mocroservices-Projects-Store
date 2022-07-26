package com.zackoverflow.product.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
/*
WE GET ACCESS TO THESE LOMBOK BY USING @Data ANNOTATION FROM LOMBOK
@Getter
@Setter
@ToString
*/

@AllArgsConstructor
@NoArgsConstructor
@Data

@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @NotNull(message = "Product name cannot be Null!")
    private String name;
    @NotNull(message = "Category cannot be Null!")
    private Category category;
    @Min(0)
    private double price;
    private String currency;
    @Max(100)
    private double discount;
    private String discountDescription;
    private List<String> imageURLs;

}


/*
ORIGINAL CONFIGURATION WITH GETTERS AND SETTER OR WITHOUT LOMBOK

@Document(collection = "product")
public class Product {
    @Id
    private Integer id;
    private String name;
    private Category category;
    private double price;
    private String currency;
    private double discount;
    private String discountDescription;
    private List<String> imageURLs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }
}
*/

