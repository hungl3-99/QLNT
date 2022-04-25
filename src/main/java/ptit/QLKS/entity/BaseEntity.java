package ptit.QLKS.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntity {

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    @JsonProperty("updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "created_by")
    @JsonProperty("created_by")
    private String createdBy;
}
