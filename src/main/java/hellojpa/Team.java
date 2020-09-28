package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID") // 1 대 다에서 이것을 적지 않으면 조인 테이블 방식(중간 테이블 생성)을 사용하게 되니 꼭 적자
    // 그냥 다대일 양방향 매핑을 쓰는 것이 좋다!!
    private List<Member> members = new ArrayList<Member>();

/*    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }*/

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

}
