package com.fourbooks.hellowebflux.repository;

import com.fourbooks.hellowebflux.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
