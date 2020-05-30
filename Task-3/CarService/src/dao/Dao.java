package dao;

public interface Dao <T> {
    T findById(int id);
    T [] findAll();
    void delete(int id);
    void save(T t) ;
}
