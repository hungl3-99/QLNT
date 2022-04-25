package ptit.QLKS.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="account")
public class Account extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "username" , nullable = false , updatable = false)
    private String username;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "role" , nullable = false)
    private String role;

    @Column(name = "active" , nullable = false)
    private boolean isActive;

    @Column(name = "full_name" , nullable = false)
    private String fullName;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "tel" , nullable = false)
    private String tel;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_request")
    private boolean isRequest;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "account")
    List<Order> order;
}
