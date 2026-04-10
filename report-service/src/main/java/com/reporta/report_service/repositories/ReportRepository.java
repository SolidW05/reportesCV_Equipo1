package com.reporta.report_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reporta.report_service.models.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
    List<Report> findByIdUser(Long idUser);

    List<Report> findByIdLocationIn(List<Long> idsUbicacion);
}
