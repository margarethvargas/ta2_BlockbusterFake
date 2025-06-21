package com.cibertec.blockbusterfake.repository;

import com.cibertec.blockbusterfake.entities.Cliente;
import com.cibertec.blockbusterfake.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //estereotipos
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {
}
