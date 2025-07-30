package com.demo.kite.repo;

import com.demo.kite.entity.KiteSessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KiteSessionRepository extends JpaRepository<KiteSessionInfo, Long> {
}
