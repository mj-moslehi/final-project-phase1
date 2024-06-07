package entity;

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

public class Expert extends Person {

    @Lob
    byte[] image;

    Double score;

    @Enumerated(EnumType.STRING)
    ExpertStatus expertStatus;

    Long validity;

    Date dateOfSingUp;

    @OneToMany(mappedBy = "expert")
    List<Orders> orders;

    @OneToMany(mappedBy = "expert" )
    List<Expert_SubService> expert_subServices;

    @OneToMany(mappedBy = "expert")
    List<Comment> comments;

    @OneToMany(mappedBy = "expert")
    List<Suggestion> suggestions;

    @Override
    public String toString() {
        return "Expert{" +
                "score=" + score +
                ", expertStatus=" + expertStatus +
                ", validity=" + validity +
                ", dateOfSingUp=" + dateOfSingUp +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
