package com.cibertec.blockbusterfake.service;

import com.cibertec.blockbusterfake.entities.Pelicula;
import com.cibertec.blockbusterfake.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }
}