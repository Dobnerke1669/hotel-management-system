package ro.fortech.bigproject.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class AssignCleanerRequest {
    @Schema(example = "1", required = true)
    private Long id;
    @Schema(example = "1", required = true)
    private Long assignedTo;

    public Long getId() {
        return id;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }
}
