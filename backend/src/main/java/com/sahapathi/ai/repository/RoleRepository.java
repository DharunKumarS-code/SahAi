package com.sahapathi.ai.repository;

import com.sahapathi.ai.entity.Role;
import com.sahapathi.ai.enums.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(RoleName name);
    boolean existsByName(RoleName name);
}
