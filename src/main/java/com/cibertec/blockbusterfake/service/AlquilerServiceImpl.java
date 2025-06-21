package com.cibertec.blockbusterfake.service;

import com.cibertec.blockbusterfake.controller.dto.AlquilerForm;
import com.cibertec.blockbusterfake.controller.dto.DetalleDTO;
import com.cibertec.blockbusterfake.entities.Alquiler;
import com.cibertec.blockbusterfake.entities.Cliente;
import com.cibertec.blockbusterfake.entities.DetalleAlquiler;
import com.cibertec.blockbusterfake.entities.Pelicula;
import com.cibertec.blockbusterfake.repository.AlquilerRepository;
import com.cibertec.blockbusterfake.repository.ClienteRepository;
import com.cibertec.blockbusterfake.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public void registrarAlquilerDesdeFormulario(AlquilerForm form) {
        Long idCliente = form.getClienteId();
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setCliente(cliente);
        alquiler.setTotal(0.0);
        alquiler.setDetalles(new ArrayList<>());

        double total = 0.0;

        for (DetalleDTO dto : form.getDetalles()) {
            Pelicula pelicula = peliculaRepository.findById(dto.getPeliculaId()).orElse(null);
            if (pelicula == null) {
                throw new IllegalArgumentException("Película no encontrada");
            }

            DetalleAlquiler detalle = new DetalleAlquiler();
            detalle.setAlquiler(alquiler);
            detalle.setPelicula(pelicula);
            detalle.setCantidad(dto.getCantidad());

            // Precio fijo por simplicidad
            double subtotal = dto.getCantidad() * 10.0;
            total += subtotal;

            alquiler.getDetalles().add(detalle);
        }

        alquiler.setTotal(total);
        alquilerRepository.save(alquiler); // Se guarda la cabecera y los detalles por cascada
    }
}
