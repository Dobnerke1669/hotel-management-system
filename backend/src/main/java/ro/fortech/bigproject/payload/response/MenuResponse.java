package ro.fortech.bigproject.payload.response;

import java.math.BigDecimal;

public class MenuResponse {
    private Long id;
    private String name;

    private String category;
    private BigDecimal price;

    private Long imageId;
    private String description;

    private boolean available;

    public MenuResponse(Long id, String name, String category, BigDecimal price, Long imageId, String description, boolean available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageId = imageId;
        this.description = description;
        this.available = available;
    }

    public MenuResponse()
    {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
