package pl.sda.racing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pigeon {
    @Id
    @GeneratedValue
    private Long id;
    private String birdId;
    private String name;
    private String owner;

    public Pigeon withId(Long id) {
        return new Pigeon(id, birdId, name, owner);
    }
}
