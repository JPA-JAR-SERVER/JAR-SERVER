package com.app.projectjar.entity.like;

import com.app.projectjar.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Likes extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
}
