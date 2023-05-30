package com.app.projectjar.entity.reply;

import com.app.projectjar.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reply extends Period {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String replyContent;

    public Reply(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}


