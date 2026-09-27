package model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private PRIORITY priority;
    private CATEGORY category;
    private STATUS status;


    public enum PRIORITY{
        LOW,
        MEDIUM,
        HIGH
    }
    public enum CATEGORY{
        WORK,
        PERSONAL,
        STUDY,
        OTHER
    }
    public enum STATUS{
        PENDING,
        IN_PROGRESS,
        COMPLETED,
    }
}
