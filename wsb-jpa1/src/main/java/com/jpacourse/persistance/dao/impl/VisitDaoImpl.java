package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VisitEntity> getAllPastVisitsForPatient(Long patientId) {
        return entityManager.createQuery(
                        "SELECT v FROM Visits v WHERE v.patient.id = :patientId AND v.date < :today", VisitEntity.class)
                .setParameter("patientId", patientId)
                .setParameter("today", LocalDateTime.now())
                .getResultList();
    }
}
