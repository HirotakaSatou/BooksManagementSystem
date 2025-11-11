package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Genre;
import com.example.demo.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    /**
     * ジャンル一覧を取得
     */
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    /**
     * ジャンルIDで1件取得
     */
    public Genre findById(Integer id) {
        return genreRepository.findById(id);
    }
}
