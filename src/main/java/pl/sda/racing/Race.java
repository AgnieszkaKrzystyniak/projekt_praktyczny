package pl.sda.racing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {


    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Result> results;

    public Race(List<Result> results) {
        this.results = results;
    }

    Pigeon winPigeon ()throws NoWinerExeption {
        Optional<Pigeon> pigeon = results.stream()
                .sorted()
                .findFirst()
                .map(Result::getPigeon);
        if(pigeon.isEmpty()){
            throw new NoWinerExeption();
        }
        return pigeon.get();
//                .map(Result::getPigeon)
//                .findFirst();

//                .findFirst().get().getPigeon(); - inne zastosowanie stream
//                .findFirst().map(Result::getPigeon);


    }

    public Race withId(Long id) {
        return new Race(id, results);
    }
}
