package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

public class Service extends BaseEntity<Long> {

    @Column(unique = true)
    String name;

    @OneToMany(mappedBy = "service")
    List<SubService> subServices;

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
