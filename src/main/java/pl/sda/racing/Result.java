package pl.sda.racing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Comparable <Result> {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Pigeon pigeon;
    private Duration time;

    public Result withAbsoluteTime(Duration base) {
        return new Result(id, pigeon, time.plus(base));
    }

    @Override
    public int compareTo(Result result) {
       return time.compareTo(result.getTime());
    }
}
