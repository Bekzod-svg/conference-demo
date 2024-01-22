package com.bekzod.cenference.repositories;

import com.bekzod.cenference.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
