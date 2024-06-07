package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;


import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder

public class Orders extends BaseEntity<Long> {

    Long proposedPrice;

    String description;

    Date dateOfOrder;

    String address;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToOne
    SubService subService;

    @ManyToOne
    Customer customer;

    @ManyToOne
    Expert expert;

    @OneToMany(mappedBy = "orders")
    List<Suggestion> suggestions;

    @Override
    public String toString() {
        return "Orders{" +
                "proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", dateOfOrder=" + dateOfOrder +
                ", address='" + address + '\'' +
                ", orderStatus=" + orderStatus +
                ", id=" + id +
                '}';
    }
}
