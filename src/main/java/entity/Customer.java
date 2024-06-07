package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SoftDelete;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@SuperBuilder

public class Customer extends Person{

    Long validity;

    Date dateOfSingUp;

    @OneToMany(mappedBy = "customer" )
    List<Orders> orders;

    @OneToMany(mappedBy = "customer" )
    List<Comment> comments;

    @Override
    public String toString() {
        return "Customer{" +
                "validity=" + validity +
                ", dateOfSingUp=" + dateOfSingUp +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
