package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class UpdateProductRequest {
    @Schema(example = "1", required = true)
    private Long id;
    @Schema(example = "Bepis", required = true)
    private String name;
    @Schema(example = "Drinkies", required = true)
    private String category;
    @Schema(example = "2.5", required = true)
    private BigDecimal price;
    @Schema(example = "A very good drink", required = true)
    private String description;
    @Schema(example = "true", required = true)
    private boolean available;

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
