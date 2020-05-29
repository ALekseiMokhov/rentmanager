package dao;

public interface Dao {
    Object findById(int id);
    Object [] findAll();
    void delete(int id);
    void save(Object o) ;
}
