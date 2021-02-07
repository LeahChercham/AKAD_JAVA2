/**
 * DAO
 * Data Access Object Interface
 * API that performs CRUD operations on objects of type T
 * 
 * @author Leah Chercham
 *
 */

import java.util.*;

public interface DAO<T> {
    Optional<T> get(long id);
    
    List<T> getAll();
    
    int save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}
