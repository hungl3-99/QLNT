package ptit.QLKS.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="room")
public class Room extends BaseEntity implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false , unique = true)
    private String id;

    @Column(name = "room_name" , nullable = false)
    private String roomName;

    @Column(name = "price" , nullable = false)
    private long price;

    @Column(name = "electric_price" , nullable = false)
    private long electricPrice;

    @Column(name = "water_price" , nullable = false)
    private long waterPrice;

    @Column(name = "network_price" , nullable = false)
    private long networkPrice;

    @Column(name = "max_number_people" , nullable = false)
    private long maxNumberPeople;

    @Column(name = "type" , nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "location" , nullable = false)
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Account store;

    @Column(name = "is_booking" , nullable = false)
    private boolean isBooking;

    @Column(name = "is_valid" , nullable = false)
    private boolean isValid;

    @Column(name = "images" , nullable = false)
    private String images;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "room")
    List<Order> orders ;

    public void init() {
        this.id = UUID.randomUUID().toString();
    }
}
