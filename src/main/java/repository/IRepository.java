package repository;

public interface IRepository<ID, E> {
    int size();
    void save(E entity);
    void delete(ID id);
    void update(ID id, E entity);
    Iterable<E> findAll();
}