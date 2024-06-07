package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@MappedSuperclass
@SuperBuilder
public class Person extends BaseEntity<Long> {

    String firstname;

    String lastname;

    @Column(unique = true)
    String email;

    String password;
}
