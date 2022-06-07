package com.example.testimageupload.Dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ImageDaoImpl implements ImageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void addImageOnDd() {
    }
}
