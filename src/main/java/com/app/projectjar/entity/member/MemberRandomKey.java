package com.app.projectjar.entity.member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"member"})
@Table(name = "TBL_MEMBER_RANDOM_KEY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRandomKey {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String memberRandomKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";

        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }

        return pwd;
    }

    public MemberRandomKey(Long id, String memberRandomKey, Member member) {
        this.id = id;
        this.memberRandomKey = memberRandomKey;
        this.member = member;
    }

    public MemberRandomKey(Member member) {
        this.id = getId();
        this.memberRandomKey = getTmpPassword();
        this.member = member;
    }


}