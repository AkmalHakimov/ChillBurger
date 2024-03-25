package com.bot.chillburger.entity;

import com.bot.chillburger.enums.ProductSize;
import com.bot.chillburger.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String engName;
    private String uzName;
    private Integer price;
    @ManyToOne
    private Category category;
    private String photoId;
//    @Column(name = "desc")
    private String description;

}
