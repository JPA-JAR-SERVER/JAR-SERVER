package com.app.projectjar.entity.inquire;

import com.app.projectjar.audit.Period;
import com.app.projectjar.type.AnswerType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "inquire")
@Table(name ="TBL_ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Answer extends Period {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String answerContent;

    @OneToOne(fetch = FetchType.LAZY)
    private Inquire inquire;

    @Builder
    public Answer(Long id, String answerContent, Inquire inquire) {
        this.id = id;
        this.answerContent = answerContent;
        this.inquire = inquire;
    }
}
