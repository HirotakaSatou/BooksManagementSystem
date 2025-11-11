package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Genre;
import com.example.demo.service.GenreService;

@ControllerAdvice
public class GlobalModelAdvice {

    @Autowired
    private GenreService genreService;

    @ModelAttribute
    public void addGenreList(Model model) {
        List<Genre> genreList = genreService.findAll();
        model.addAttribute("genreList", genreList);
    }
}

