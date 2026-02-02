    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
/**
 *
 * @author lenovo
 * @param <T>
 */
public interface GenericDAO<T> {
    void create(T obj);
    void update(T obj);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}
