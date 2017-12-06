/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.dao;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuvane
 */
public class GenericDAO<T> implements iGenericDAO<T> {

    EntityManager manager = ConexaoHibernate.getInstance();

    private Queue<String> params;
    private Queue<Object> values;

    public GenericDAO() {
        this.params =  new LinkedList();
        this.values = new LinkedList();
    }

    
    
    public void commit() {
        manager.getTransaction().commit();
    }

    @Override
    public void save(T object) {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " salvo som sucesso!");
    }

    @Override
    public T listOne(String pkName, int pkValue, Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t where t." + pkName + " = " + pkValue;
        Query query = manager.createQuery(jpql);
        Object obj = query.getSingleResult();
        return (T) obj;
    }

    public T listOne(String pkName, String pkValue, Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t where t." + pkName + " = " + pkValue;
        Query query = manager.createQuery(jpql);
        Object obj = query.getSingleResult();
        return (T) obj;
    }

    @Override
    public List listAll(int first, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(T object) {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " atualizado som sucesso!");

    }

    @Override
    public void delete(T object) {
        manager.getTransaction().begin();
        manager.remove(object);
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " excluído som sucesso!");
    }

    @Override
    public List listAll(Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t";
        Query query = manager.createQuery(jpql);
        List<T> objects = query.getResultList();
        return objects;
    }

    @Override
    public List newQueryBuilder(Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t where ";
        while (params.size() > 0) {
            jpql += "t." + params.poll() + " = " + values.poll();
            jpql += params.size() > 0 ? " and " : "";
        }
        Query query = manager.createQuery(jpql);
        List<T> objects = query.getResultList();
        return objects;
    }
    
    public void addParams(String parametro, Object valor) {
        params.add(parametro);
        values.add(valor);
    }
    
    public List newQueryNamedSingleParam(String namedQuery) {
        
        List<T> objects= manager.createNamedQuery(namedQuery)
                .setParameter(params.poll(), values.poll())
                .getResultList();
        
        return objects;
    }
    
    public List newQueryNamedDoubleParam(String namedQuery) {
        
        List<T> objects= manager.createNamedQuery(namedQuery)
                .setParameter(params.poll(), values.poll())
                .setParameter(params.poll(), values.poll())
                .getResultList();
        
        return objects;
    }
    
}
