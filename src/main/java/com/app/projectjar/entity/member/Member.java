package com.app.projectjar.entity.member;

import com.app.projectjar.audit.Period;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.type.BadgeType;
import com.app.projectjar.type.MemberType;
import com.app.projectjar.type.Role;
import com.app.projectjar.type.UserType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Member extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Column(unique = true)
    @NotNull private String memberEmail;
    @NotNull private String memberPassword;
    @Column(unique = true)
    @NotNull private String memberPhoneNumber;
    @NotNull private String memberName;
    @Column(unique = true)
    @NotNull private String memberNickname;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ENABLE'")
    private MemberType memberStatus;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ZERO'")
    private BadgeType badgeType;
    @Enumerated(EnumType.STRING)
    private Role memberType;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public Member(Long id, String memberEmail, String memberPassword, String memberPhoneNumber, String memberName, String memberNickname, MemberType memberStatus, BadgeType badgeType, Role memberType, UserType userType, MemberFile memberFile) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.badgeType = badgeType;
        this.memberType = memberType;
        this.userType = userType;
        this.memberFile = memberFile;
    }


    public Member update(String memberEmail, String memberName, String memberPhoneNumber, UserType userType) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPhoneNumber = memberPhoneNumber;
        this.userType = userType;

        return this;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private MemberFile memberFile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberRandomKey> memberRandomKeys;

    public void updatePassword(String memberPassword){
        this.memberPassword = memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public void setMemberStatus(MemberType memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void setBadgeType(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    public void setMemberFile(MemberFile memberFile) {
        this.memberFile = memberFile;
    }

    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }
}
