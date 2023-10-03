package ro.fortech.bigproject.payload.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class AddOrderRequest {

    @Schema(example = "1", required = true)
    private Long roomNumber;

    @Schema(example = "[{\"quantity\": 2, \"id\": 1}]", required = true)
    private List<AddOrderedProductRequest> productList;

    public AddOrderRequest(Long roomNumber, List<AddOrderedProductRequest> productList) {
        this.roomNumber = roomNumber;
        this.productList = productList;
    }

    public AddOrderRequest() {

    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<AddOrderedProductRequest> getProductList() {
        return productList;
    }

    public void setProductList(List<AddOrderedProductRequest> productList) {
        this.productList = productList;
    }
}
