package com.diekmann.code.crud.service;

import com.diekmann.code.crud.dao.NotesRepository;
import com.diekmann.code.crud.domain.Notes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotesServiceImplTest {

    @InjectMocks
    private NotesServiceImpl notesService;

    @Mock
    private NotesRepository notesRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAll() {
        //given
        Notes notes1 = new Notes("Test 1", "Content 1");
        List<Notes> notesList = new ArrayList<>();
        notesList.add(notes1);
        given(notesRepository.findAll()).willReturn(notesList);

        //when
        List<Notes> notesListActual = notesService.findAll();

        //then
        verify(notesRepository, times(1)).findAll();
        assertThat(notesListActual.size()).isEqualTo(1);

    }

    @Test
    public void findOne() {
    }

    @Test
    public void saveNotes() {
    }

    @Test
    public void deleteNotes() {
    }
}