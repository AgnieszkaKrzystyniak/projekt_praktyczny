package pl.sda.racing.importing;

import lombok.RequiredArgsConstructor;
import pl.sda.racing.Race;
import pl.sda.racing.repository.RaceRepository;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class Importer {

    private final RaceRepository raceRepository;
    private final Supplier<Race> raceSupplier;

    public void importRaceIntoDb() {
        Race race = raceSupplier.get();
        raceRepository.save(race);
    }

}
