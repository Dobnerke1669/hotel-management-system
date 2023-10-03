package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class AddOrderedProductRequest {

    @Schema(example = "2", required = true)
    private int quantity;
    @Schema(example = "1", required = true)
    private Long id;

    public AddOrderedProductRequest(int quantity, Long id) {
        this.quantity = quantity;
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
