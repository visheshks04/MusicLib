package com.example.musiclibrary.repositories;

import com.example.musiclibrary.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
