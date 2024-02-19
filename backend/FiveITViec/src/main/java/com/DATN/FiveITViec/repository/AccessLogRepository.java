package com.DATN.FiveITViec.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DATN.FiveITViec.model.AccessLog;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    @Query(value = "SELECT COALESCE(COUNT(a.timestamp), 0) AS logCount, MONTHS.month AS month "
            + "FROM (SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 "
            + "UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 "
            + "UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12) AS MONTHS "
            + "LEFT JOIN Access_Log a ON MONTH(a.timestamp) = MONTHS.month AND YEAR(a.timestamp) = :year "
            + "GROUP BY MONTHS.month " + "ORDER BY MONTHS.month", nativeQuery = true)
    List<Long> findAccessCountByYear(@Param("year") int year);

}
