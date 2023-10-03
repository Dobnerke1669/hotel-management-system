package ro.fortech.bigproject.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String category;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ImageData image;
    private BigDecimal price;
    private String description;

    private boolean available;

    public Product(Long id, String name, String category, ImageData image, BigDecimal price, String description, boolean available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.price = price;
        this.description = description;
        this.available = available;
    }

    public Product(String name, String category, ImageData image, BigDecimal price, String description, boolean available) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.price = price;
        this.description = description;
        this.available = available;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ImageData getImage() {
        return image;
    }

    public void setImage(ImageData image) {
        ImageData imageData = new ImageData(image.getName(), image.getType(), this, image.getImageData());
        this.image = imageData;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}

