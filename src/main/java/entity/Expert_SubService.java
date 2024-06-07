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

public class Expert_SubService extends BaseEntity<Long> {

    @ManyToOne
    Expert expert;

    @ManyToOne
    SubService subService;

}
