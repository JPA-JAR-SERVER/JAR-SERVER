package com.app.projectjar.domain.calendar;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data @ToString
@NoArgsConstructor
public class CalendarDTO {
    private String id;
    private String title;
    private String start;
    private String end;


    @Builder
    public CalendarDTO(Long id, String title, LocalDateTime start, LocalDateTime end) {
        this.id = id+"";
        this.title = title;
        this.start = formatDate(start);
        this.end = formatDate(end);
    }

    private String formatDate(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.format(formatter);
    }
}
