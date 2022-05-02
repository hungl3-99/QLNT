package ptit.QLKS.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="system_order")
public class Order extends BaseEntity implements Serializable{

    @Id
    @Column(name = "id", updatable = false, nullable = false , unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName = "id", nullable=false)
    private Account account;

    @ManyToOne
    @JoinColumn(name="room_id" ,referencedColumnName = "id", nullable = false)
    private Room room;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name ="store_id")
    private String storeId;

    public void init() {
        this.id = UUID.randomUUID().toString();
    }
}
