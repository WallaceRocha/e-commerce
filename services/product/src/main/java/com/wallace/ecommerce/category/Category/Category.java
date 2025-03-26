package com.wallace.ecommerce.category.Category;

import com.wallace.ecommerce.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    /* mappedBy is exactly the same as the name of the field in the Product class
    *  the CascadeType#REMOVE means that if we remove a category, all products associated with it will be removed as well from this list. This is not really necessary but let's keep it for now.
    */
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
