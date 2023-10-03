package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import ro.fortech.bigproject.entities.ImageData;

import java.math.BigDecimal;

public class AddProductRequest {
    @Schema(example = "Coca Cola", required = true)
    private String name;
    @Schema(example = "Drinks", required = true)
    private String category;
    @Schema(example = "A refreshing drink", required = true)
    private String description;
    @Schema(example = "1", required = true)
    private ImageData image;
    @Schema(example = "2.5", required = true)
    private BigDecimal price;

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
        this.image = image;
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
}
