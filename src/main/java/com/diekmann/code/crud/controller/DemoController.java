package com.diekmann.code.crud.controller;

import com.diekmann.code.crud.domain.Notes;
import com.diekmann.code.crud.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by abraun on 10/11/2017.
 */
@Controller
public class DemoController {

    private static final String NOTES_EDIT = "notesEdit";
    private static final String NOTES_LIST = "notesList";
    private static final String NOTES = "notes";

    @Autowired
    NotesService notesService;

    @RequestMapping(value="/")
    public String notesList(final Model model) {
        model.addAttribute(NOTES_LIST, notesService.findAll());
        return NOTES_LIST;
    }

    @RequestMapping(value = {"/" + NOTES_EDIT, "/" + NOTES_EDIT + "/{id}"}, method = RequestMethod.GET)
    public String notesEditForm(final Model model, @PathVariable(required = false, name = "id") final Long id) {
        if (null != id) {
            model.addAttribute(NOTES, notesService.findOne(id));
        } else {
            model.addAttribute(NOTES, new Notes());
        }
        return NOTES_EDIT;
    }

    @RequestMapping(value={"/notesView","/notesView/{id}"}, method = RequestMethod.GET)
    public String notesViewForm(final Model model, @PathVariable(required = false, name = "id") final Long id) {
        if (null != id) {
            model.addAttribute(NOTES, notesService.findOne(id));
        } else {
            model.addAttribute(NOTES, new Notes());
        }
        return "notesView";
    }

    @RequestMapping(value = "/" + NOTES_EDIT, method = RequestMethod.POST)
    public String notesEdit(final Model model, final Notes notes) {
        notesService.saveNotes(notes);
        model.addAttribute(NOTES_LIST, notesService.findAll());
        return NOTES_LIST;
    }

    @RequestMapping(value="/notesDelete/{id}", method = RequestMethod.GET)
    public String notesDelete(final Model model, @PathVariable(name = "id") final Long id) {
        notesService.deleteNotes(id);
        model.addAttribute(NOTES_LIST, notesService.findAll());
        return NOTES_LIST;
    }

}
