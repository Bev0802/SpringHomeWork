package com.geekbrains.bev0802.notes.servises;

import com.geekbrains.bev0802.notes.Entities.Note;
import com.geekbrains.bev0802.notes.Entities.User;
import com.geekbrains.bev0802.notes.Reposetories.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Класс тестирования для NoteService.
 * Проверяет основную логику работы сервиса по управлению заметками.
 */
@ExtendWith(MockitoExtension.class)
public class NoteServiceTestSpringTests {

    @Mock
    private NoteRepository noteRepository; // Мок репозитория заметок для изоляции от базы данных

    @InjectMocks
    private NoteService noteService; // Тестируемый сервис заметок с инжектированным моком репозитория

    private Note note; // Объект заметки, используемый в тестах

    /**
     * Инициализация тестовых данных перед каждым тестом.
     */
    @BeforeEach
    public void setup() {
        // Создание тестовой заметки с базовыми свойствами
        note = new Note();
        note.setId(1L);
        note.setTitle("Test Note");
        note.setContent("This is a test note.");
        note.setCreationDate(LocalDateTime.now());
        note.setLastModifiedDate(LocalDateTime.now());
    }

    /**
     * Тест поиска заметки по идентификатору.
     */
    @Test
    public void testFindNoteById() {
        // Настройка поведения мока для возвращения тестовой заметки
        Mockito.when(noteRepository.findById(eq(1L))).thenReturn(Optional.of(note));

        // Выполнение операции поиска
        Note found = noteService.findNoteById(1L);

        // Проверка вызова метода findById и корректности результата
        verify(noteRepository).findById(eq(1L));
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(note.getId());
    }

    /**
     * Тест сохранения заметки.
     */
    @Test
    public void testSaveNote() {
        // Настройка поведения мока для метода save
        Mockito.when(noteRepository.save(any(Note.class))).thenReturn(note);

        // Выполнение операции сохранения
        Note saved = noteService.saveNote(note);

        // Проверка вызова метода save и корректности сохранённых данных
        verify(noteRepository).save(any(Note.class));
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(note.getId());

    }

    /**
     * Тест получения всех заметок.
     */
    @Test
    public void testFindAllNotes() {
        // Настройка поведения мока для возвращения списка заметок
        Mockito.when(noteRepository.findAll()).thenReturn(Collections.singletonList(note));

        // Выполнение операции получения всех заметок
        List<Note> notes = noteService.findAllNotes();

        // Проверка вызова метода findAll и корректности полученных данных
        verify(noteRepository).findAll();
        assertThat(notes).isNotEmpty();
        assertThat(notes).hasSize(1);
        assertThat(notes.get(0).getId()).isEqualTo(note.getId());
    }

    /**
     * Тест удаления заметки по идентификатору.
     */
    @Test
    public void testDeleteNote() {
        // Выполнение операции удаления
        noteService.deleteNote(1L);

        // Проверка вызова метода deleteById
        verify(noteRepository).deleteById(eq(1L));
        // Здесь assertThat не используется, так как мы проверяем вызов метода без возвращаемого результата
    }

    /**
     * Тест поиска заметок по идентификатору пользователя.
     */
    @Test
    public void testFindNotesByUserId() {
        // Подготовка: создание мока пользователя и заметки
        User mockUser = new User();
        mockUser.setId(1L); // Устанавливаем ID для пользователя для проверки

        Note mockNote = new Note();
        mockNote.setId(2L); // Устанавливаем ID для заметки для проверки
        mockNote.setUser(mockUser);
        mockNote.setTitle("Test Note");
        mockNote.setContent("Test content");

        // Настройка поведения мока: при вызове findByUserId возвращаем список с одной заметкой
        Mockito.when(noteRepository.findByUserId(eq(1L))).thenReturn(Collections.singletonList(mockNote));

        // Выполнение: поиск заметок по ID пользователя
        List<Note> notes = noteService.findNotesByUserId(1L);

        // Проверка: метод findByUserId был вызван, и возвращенный список заметок не пуст
        verify(noteRepository).findByUserId(eq(1L)); // Подтверждаем, что метод findByUserId был вызван с правильным ID
        assertThat(notes).isNotNull(); // Проверяем, что результат не null
        assertThat(notes).isNotEmpty(); // Проверяем, что список не пуст
        assertThat(notes.get(0).getUser().getId()).isEqualTo(1L); // Проверяем, что ID пользователя в заметке соответствует ожидаемому
    }


}
