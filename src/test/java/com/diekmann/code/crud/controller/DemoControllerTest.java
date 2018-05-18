package com.diekmann.code.crud.controller;

import com.diekmann.code.crud.domain.Notes;
import com.diekmann.code.crud.service.NotesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotesService service;

    @Test
    public void notesList() throws Exception {
        final Notes notes1 = new Notes("Test 1", "Content 1");
        final List<Notes> notesList = new ArrayList<>();
        notesList.add(notes1);
        when(service.findAll()).thenReturn(notesList);
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test 1")));
    }

    @Test
    public void notesEditForm() throws Exception {
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        when(service.findOne(1L)).thenReturn(notes1);
        this.mockMvc.perform(get("/notesEdit/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Content 1")));
    }

    @Test
    public void notesViewForm() throws Exception {
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        when(service.findOne(1L)).thenReturn(notes1);
        this.mockMvc.perform(get("/notesView/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Content 1")));
    }

    @Test
    public void notesEdit() throws Exception {
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        final Notes notes2 = new Notes("Test 2", "Content 2");
        notes2.setId(2L);
        final List<Notes> notesList = new ArrayList<>();
        notesList.add(notes1);
        notesList.add(notes2);
        when(service.findAll()).thenReturn(notesList);
        when(service.saveNotes(notes1)).thenReturn(notes1);
        this.mockMvc.perform(post("/notesEdit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test 2")));
    }

    @Test
    public void notesDelete() throws Exception {
        final Notes notes1 = new Notes("Test 1", "Content 1");
        notes1.setId(1L);
        final Notes notes2 = new Notes("Test 2", "Content 2");
        notes2.setId(2L);
        final List<Notes> notesList = new ArrayList<>();
        notesList.add(notes2);
        when(service.findAll()).thenReturn(notesList);
        doNothing().when(service).deleteNotes(notes1.getId());
        this.mockMvc.perform(get("/notesDelete/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test 2")));
    }
}
