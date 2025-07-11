package com.backend.reportservice.repository;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Spring Data JPA sẽ tự động cung cấp các hàm như findAll(), findById(), save(),...
    // Sau này bạn có thể thêm các hàm truy vấn phức tạp hơn ở đây.
}