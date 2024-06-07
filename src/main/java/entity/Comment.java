package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder

public class Comment extends BaseEntity<Long> {

    Double score;

    String comment;

    @ManyToOne
    Customer customer;

    @ManyToOne
    Expert expert;

    @Override
    public String toString() {
        return "Comment{" +
                "score=" + score +
                ", comment='" + comment + '\'' +
                ", id=" + id +
                '}';
    }
}
