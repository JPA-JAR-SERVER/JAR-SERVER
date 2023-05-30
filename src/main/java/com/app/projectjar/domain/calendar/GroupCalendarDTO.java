package com.app.projectjar.domain.calendar;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Component
@NoArgsConstructor
public class GroupCalendarDTO {

    private String id;
    private String title;
    private String start;
    private String end;
    private boolean isAllday;
    private String category;
    private String calendarId;

    @Builder
    public GroupCalendarDTO(Long id, String title, LocalDate start, LocalDate end) {
        this.id = id + "";
        this.title = title;
        this.start = formatter(start);
        this.end = formatter(end);
        this.isAllday = true;
        this.category = "allday";
        this.calendarId = "cal" + id % 10 ;
    }

    private String formatter(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }
}
