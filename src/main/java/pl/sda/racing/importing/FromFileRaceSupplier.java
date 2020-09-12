package pl.sda.racing.importing;

import lombok.SneakyThrows;
import pl.sda.racing.Pigeon;
import pl.sda.racing.Race;
import pl.sda.racing.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FromFileRaceSupplier implements Supplier<Race> {

    private static final Path FILE_PATH = Paths.get("src/main/resources/pigeon-racing.csv");

    @Override
    public Race get() {
        Race race = new Race();
        race.setResults(readResults());
        return race;
    }

    @SneakyThrows
    private List<Result> readResults() {
        List<Result> resultsWithRelativeTimes = linesWithoutHeader().stream()
                .map(this::splitByComma)
                .map(this::asResult)
                .collect(Collectors.toList());
        return withAdjustedTime(resultsWithRelativeTimes, getWinnersTime());
    }

    private List<Result> withAdjustedTime(List<Result> resultsWithRelativeTimes, Duration winnersDuration) {
        return resultsWithRelativeTimes.stream()
                .map(result -> result.withAbsoluteTime(winnersDuration))
                .collect(Collectors.toList());
    }

    private Duration getWinnersTime() throws IOException {
        String winnersArrival = linesWithoutHeader().get(0).split(",")[7];
        String minutes = winnersArrival.split(":")[0];
        String seconds = winnersArrival.split(":")[1];
        return Duration.parse(String.format("PT%sM%sS", minutes, seconds));
    }

    private List<String> linesWithoutHeader() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        return lines.subList(1, lines.size());
    }

    private Result asResult(String[] splitLine) {
        return Result.builder()
                .pigeon(asPigeon(splitLine))
                .time(parseStringIntoDuration(splitLine[9]))
                .build();
    }

    private static Pigeon asPigeon(String[] splitLine) {
        return Pigeon.builder().birdId(splitLine[2]).name(splitLine[3]).owner(splitLine[1]).build();
    }

    private Duration parseStringIntoDuration(String splitline) {
        String[] fields = splitline.split(":");
        return Duration.parse(String.format("PT%sH%sM%sS", fields[0], fields[1], fields[2]));
    }

    private String[] splitByComma(String line) {
        return line.split(",");
    }

}
