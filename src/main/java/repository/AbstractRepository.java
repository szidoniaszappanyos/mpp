package repository;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepository<ID, E extends HasID<ID>> implements IRepository<ID, E> {
    protected Map<ID, E> entities;

    public AbstractRepository(){
        entities=new HashMap<>();
    }
    @Override
    public int size() {
        return entities.size();
    }

    @Override
    public void update(ID id, E entity) {
        if (!(entities.get(id)==null)) {
            if (!id.equals(entity.getId()))
                if (entities.get(entity.getId())!=null)
                    throw new RuntimeException("Id "+entity.getId()+" already exists!!");
            entities.put(entity.getId(), entity);
            System.out.println("Entitate modificata " + entity);
        }else
            throw new RuntimeException("Id "+id +" does not  exists");
    }

    @Override
    public void save(E entity) {
        ID id=entity.getId();
        if (entities.get(id)==null) {
            entities.put(id, entity);
        }else
            throw new RuntimeException("Id already exists"+id);

    }

    @Override
    public void delete(ID id) {
        entities.remove(id);
        System.out.println("Entity deleted "+id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

}

