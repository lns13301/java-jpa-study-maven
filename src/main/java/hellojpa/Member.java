package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private  String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 기본값 ORDINAL 인데, 그냥 STRING 을 쓰도록 하자!

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신 버전 부터는 @Temporal 사용할 필요 없이 LocalDate, LocalDateTime 을 사용하면 됨

    @Lob
    private String description;

    // JPA는 기본적으로 내부적으로 reflection 같은걸 사용하기 때문에 동적으로 객체를 생성할 수 있어야 해서 기본 생성자가 필요함
    public Member() {
    }
}
