package repository;

public interface Repository<T> {
    T findById(int id);
    T [] findAll();
    void delete(int id);
    void save(T t) ;
}
