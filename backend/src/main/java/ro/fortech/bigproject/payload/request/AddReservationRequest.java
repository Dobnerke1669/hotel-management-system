package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class AddReservationRequest {
    @Schema(example = "Popescu", required = true)
    private String firstName;
    @Schema(example = "1", required = true)
    private Long clientId;
    @Schema(example = "Ion", required = true)
    private String lastName;
    @Schema(example = "2024-05-05", required = true)
    private Date startDate;
    @Schema(example = "2024-05-10", required = true)
    private Date endDate;
    @Schema(example = "Single", required = true)
    private String roomType;
    @Schema(example = "popescuion@yahoo.com", required = true)
    private String email;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
