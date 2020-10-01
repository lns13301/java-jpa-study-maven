package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
/*@SequenceGenerator(name = "member_seq_generator",
        sequenceName = "member_seq", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)*/
public class Member {

/*    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private  String username;

    // JPA는 기본적으로 내부적으로 reflection 같은걸 사용하기 때문에 동적으로 객체를 생성할 수 있어야 해서 기본 생성자가 필요함
    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/
/*    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 기본값 ORDINAL 인데, 그냥 STRING 을 쓰도록 하자!

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신 버전 부터는 @Temporal 사용할 필요 없이 LocalDate, LocalDateTime 을 사용하면 됨

    @Lob
    private String description;*/

//    @Column(name = "TEAM_ID")
//    private Long teamId;

/*    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;*/

/*    public void setTeam(Team team) {
        this.team = team;
    }*/

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 해당 옵션을 주면 읽기 전용이 된다.
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<MemberProduct>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

/*    public Team getTeam() {
        return team;
    }

    // 자바의 Getter, Setter 관례 때문에 set 을 쓰기보다는 이름을 다르게하여 중요해보이게 하자
    // 연관관계 편의 메소드는 두 객체에 다 있으면 오류날 수 있으니 한 쪽에만 만들어서 쓰자
    public void changeTeam(Team team) {
        this.team = team;
        // 양방향 세팅 (연관관계 편의 메소드)
        team.getMembers().add(this);
    }*/
}
