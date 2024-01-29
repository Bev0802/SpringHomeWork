package com.geekbrains.bev0802.notes.Reposetories;

import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User getUserById(Long id);
}
