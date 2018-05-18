package com.diekmann.code.crud.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
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
