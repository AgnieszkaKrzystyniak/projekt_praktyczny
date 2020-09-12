package pl.sda.racing.repository;

public interface Repository<T> {

    T save(T entity);

}
