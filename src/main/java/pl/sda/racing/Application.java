package pl.sda.racing;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.racing.importing.FromFileRaceSupplier;
import pl.sda.racing.importing.Importer;
import pl.sda.racing.repository.RaceRepository;

public class Application {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        RaceRepository raceRepository = new RaceRepository(sessionFactory);
        new Importer(raceRepository, new FromFileRaceSupplier()).importRaceIntoDb();

        raceRepository.getAll().forEach(
                System.out::println
        );

    }
}
