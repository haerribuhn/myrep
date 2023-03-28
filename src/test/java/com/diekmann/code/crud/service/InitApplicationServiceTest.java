package com.diekmann.code.crud.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InitApplicationServiceTest {

    @Mock
    private NotesServiceImpl notesService;

    @InjectMocks
    private InitApplicationService initApplicationService;

    @Test
    public void initializeTestData() {
        //given
        given(notesService.saveNotes(any())).willReturn(any());

        //when
        initApplicationService.initializeTestData();

        //then
        verify(notesService, times(2)).saveNotes(any());

    }
}
