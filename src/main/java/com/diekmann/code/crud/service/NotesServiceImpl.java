package com.diekmann.code.crud.service;

import com.diekmann.code.crud.dao.NotesRepository;
import com.diekmann.code.crud.domain.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abraun on 23/11/2017.
 */
@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<Notes> findAll() {
        return notesRepository.findAll();
    }

    @Override
    public Notes findOne(final Long id) {
        return notesRepository.findById(id).get();
    }

    @Override
    public Notes saveNotes(final Notes notes) {
        return notesRepository.save(notes);
    }

    @Override
    public void deleteNotes(final Long id) {
        notesRepository.deleteById(id);
    }
}
