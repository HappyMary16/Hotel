package ua.nure.borodin.hotel.dao;

import ua.nure.borodin.hotel.model.entity.Entity;

import java.util.List;

public interface IDao<K, T extends Entity> {

    List<T> getAll();

    /**
     * Return element's value with given the key
     *
     * @param key the value of element's key
     * @return element's value with given the key
     */
    <T> T getById(K key);

    /**
     * Update element's value with given the key and the new value
     *
     * @param entity the value of element's value
     */
    void update(T entity);

    /**
     * Delete the element with given the key and return
     * the element's value, which was deleting
     *
     * @param key the value of element's key
     */
    void delete(K key);

    /**
     * Save the element
     *
     * @param entity the value of element
     */
    long insert(T entity);

}
