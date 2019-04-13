package com.unochapeco.dogssereleps.repositories;

import com.unochapeco.dogssereleps.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
