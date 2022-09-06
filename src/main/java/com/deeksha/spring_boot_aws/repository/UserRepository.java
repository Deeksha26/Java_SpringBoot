package com.deeksha.spring_boot_aws.repository;

import com.deeksha.spring_boot_aws.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
