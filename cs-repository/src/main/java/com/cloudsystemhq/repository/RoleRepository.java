package com.cloudsystemhq.repository;

import com.cloudsystemhq.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
