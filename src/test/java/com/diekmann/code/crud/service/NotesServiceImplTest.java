package com.diekmann.code.crud.service;

import com.diekmann.code.crud.dao.NotesRepository;
import com.diekmann.code.crud.domain.Notes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotesServiceImplTest {

    @InjectMocks
    private NotesServiceImpl notesService;

    @Mock
    private NotesRepository notesRepository;

    @Test
    public void findAll() {
        //given
        final Notes notes1 = new Notes("Test 1", "Content 1");
        final List<Notes> notesList = new ArrayList<>();
        notesList.add(notes1);
        given(notesRepository.findAll()).willReturn(notesList);

        //when
        final List<Notes> notesListActua2 = notesService.findAll();

        //then
        verify(notesRepository, times(1)).findAll();
        assertThat(notesListActua2.size()).isEqualTo(1);
    }

    @Test
    public void findOne() {
        //given
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        given(notesRepository.findOne(1L)).willReturn(notes1);

        //when
        final Notes notesActual = notesService.findOne(1L);

        //then
        verify(notesRepository, times(1)).findOne(1L);
        assertThat(notesActual.getId()).isEqualTo(1L);
    }

    @Test
    public void saveNotes() {
        //given
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        given(notesRepository.save(notes1)).willReturn(notes1);

        //when
        final Notes notesActual = notesService.saveNotes(notes1);

        //then
        verify(notesRepository, times(1)).save(notes1);
        assertThat(notesActual.getId()).isEqualTo(1L);
    }

    @Test
    public void deleteNotes() {
        //given
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        doNothing().when(notesRepository).delete(notes1);

        //when
        notesService.deleteNotes(notes1.getId());

        //then
        verify(notesRepository, times(1)).delete(notes1.getId());
    }
}
