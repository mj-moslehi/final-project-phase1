package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder

public class Suggestion extends BaseEntity<Long> {

    Long proposedPrice;

    Date date;

    Integer timeOfJob;

    @ManyToOne
    Expert expert;

    @ManyToOne
    Orders orders;

    @Override
    public String toString() {
        return "Suggestion{" +
                "proposedPrice=" + proposedPrice +
                ", date=" + date +
                ", timeOfJob=" + timeOfJob +
                ", id=" + id +
                '}';
    }
}
