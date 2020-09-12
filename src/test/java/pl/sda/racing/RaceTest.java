package pl.sda.racing;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {


    @Test
    void shouldGiweWinner() throws NoWinerExeption {
        // given

        List<Result> resultList = List.of(createResult("Sowa", 1),
                createResult("Kruk", 3),
                createResult("Koń", 4)
        );

        Race race = new Race(resultList);
        //when
        Pigeon pigeon = race.winPigeon();


        //then
        assertEquals("Sowa", pigeon.getBirdId());
    }
    @Test
    void shouldGiweWinnerAnyway() throws NoWinerExeption {
        // given

        List<Result> resultList = List.of(createResult("Sowa", 8),
                createResult("Kruk", 1),
                createResult("Koń", 4)
        );

        Race race = new Race(resultList);
        //when
        Pigeon pigeon = race.winPigeon();


        //then
        assertEquals("Kruk", pigeon.getBirdId());
    }
    Result createResult(String birdId, int minutes) {
        Pigeon sowa = Pigeon.builder().birdId(birdId).build();
        Result winPigeon = Result.builder()
                .pigeon(sowa)
                .time(Duration.ofMinutes(minutes))
                .build();

        return winPigeon;
    }

}