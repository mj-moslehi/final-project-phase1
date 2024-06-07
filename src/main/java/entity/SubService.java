package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder

public class SubService extends BaseEntity<Long> {

    @Column(unique = true)
    String name;

    Long basePrice;

    String description;

    @OneToMany(mappedBy = "subService")
    List<Expert_SubService> expert_subServices;

    @ManyToOne
    Service service;

    @OneToMany(mappedBy = "subService")
    List<Orders> orders;

    @Override
    public String toString() {
        return "SubService{" +
                "name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }

}
