package com.cibertec.blockbusterfake.repository;

import com.cibertec.blockbusterfake.entities.Alquiler;
import com.cibertec.blockbusterfake.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //estereotipos
public interface AlquilerRepository extends JpaRepository<Alquiler,Long> {
}
