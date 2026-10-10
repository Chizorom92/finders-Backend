package com.finders.finders_backend.repository;

import com.finders.finders_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // Get all reports with a given status - e.g. all OPEN ones for
    // an admin's "pending reports" dashboard view.
    List<Report> findByStatus(Report.ReportStatus status);

    // Get all reports filed against a specific property
    List<Report> findByPropertyId(Long propertyId);
}