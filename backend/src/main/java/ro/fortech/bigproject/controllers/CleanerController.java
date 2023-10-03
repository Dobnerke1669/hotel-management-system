package ro.fortech.bigproject.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.fortech.bigproject.entities.NotificationCleaning;
import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.payload.request.AssignCleanerRequest;
import ro.fortech.bigproject.payload.response.CleaningTicketResponse;
import ro.fortech.bigproject.payload.response.MessageResponse;
import ro.fortech.bigproject.services.CleaningService;
import ro.fortech.bigproject.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cleaning")
@PreAuthorize("hasRole('CLEANING')")
public class CleanerController {


    @Autowired
    private CleaningService cleaningService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Assign a task", description = "Assign a task to a cleaner")
    @PostMapping("/assign")
    public ResponseEntity<MessageResponse> assignTask(@RequestBody AssignCleanerRequest assignCleanerRequest) {
        cleaningService.markInProgress(assignCleanerRequest.getId(), assignCleanerRequest.getAssignedTo());
        return ResponseEntity.ok(new MessageResponse("Task has now an assigned person"));

    }

    @Operation(summary = "Mark a task as done", description = "Mark a task as done")
    @PutMapping("/markAsDone/{id}")
    public ResponseEntity<MessageResponse> markAsDone(@PathVariable Long id) {
        cleaningService.markDoneNotification(id);
        return ResponseEntity.ok(new MessageResponse("Task marked done successfully"));

    }

    @Operation(summary = "Get all tasks", description = "Get all tasks")
    @GetMapping("/getTasks")
    public List<CleaningTicketResponse> getCleaningNotifications() {
        List<NotificationCleaning> notificationCleanings = cleaningService.getCleaningNotifications();
        List<CleaningTicketResponse> cleaningTicketResponses = new ArrayList<>();

        for(NotificationCleaning notificationCleaning : notificationCleanings){

            Long cleanerId = (notificationCleaning.getCleaner() != null) ? notificationCleaning.getCleaner().getId() : 0L;
            String cleanerName;
            if(!cleanerId.equals(0L)) {
                User cleaner = userService.getUserById(cleanerId);
                cleanerName = cleaner.getFirstName() + " " + cleaner.getLastName();
            } else {
                cleanerName = "Not assigned";
            }
            CleaningTicketResponse cleaningTicketResponse = new CleaningTicketResponse(notificationCleaning.getId(),notificationCleaning.getDetails(),notificationCleaning.getRoom().getNumber(),notificationCleaning.isDone(),cleanerName);
            cleaningTicketResponses.add(cleaningTicketResponse);
        }

        return cleaningTicketResponses;
    }
}
