package org.example.repostitory;

import java.util.List;

public interface CRUDRepository <T, F>{
    void save(T t);
    T update(F f, T t);
    List<T> findAll();
    T findById(F f);
    void deleteById(F f);
}
