package org.example.repostitory;

import java.util.List;

public interface CRUDRepository <T, F>{
    void save(T t);
    void deleteById(F f);
    T update(F f, T t);
    List<T> findAll();
    T findById(F f);
}
