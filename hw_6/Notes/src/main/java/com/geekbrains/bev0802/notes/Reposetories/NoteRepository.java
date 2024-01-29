package com.geekbrains.bev0802.notes.Reposetories;

import com.geekbrains.bev0802.notes.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNotesByUserId(Long userId);

    List<Note> findByUserId(Long userId);
}
