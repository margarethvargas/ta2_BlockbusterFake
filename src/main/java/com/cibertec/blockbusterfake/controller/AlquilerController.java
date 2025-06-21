package com.cibertec.blockbusterfake.controller;

import com.cibertec.blockbusterfake.controller.dto.AlquilerForm;
import com.cibertec.blockbusterfake.controller.dto.DetalleDTO;
import com.cibertec.blockbusterfake.service.AlquilerService;
import com.cibertec.blockbusterfake.service.ClienteService;
import com.cibertec.blockbusterfake.service.PeliculaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alquiler")
public class AlquilerController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private AlquilerService alquilerService;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("peliculas", peliculaService.listarPeliculas());

        // Formulario con un detalle vacío por defecto
        AlquilerForm form = new AlquilerForm();

        model.addAttribute("alquilerForm", form);
        return "alquiler";
    }

    @PostMapping
    public String registrar(@ModelAttribute AlquilerForm alquilerForm, RedirectAttributes redirectAttrs) {
        try {
            alquilerService.registrarAlquilerDesdeFormulario(alquilerForm);
            redirectAttrs.addFlashAttribute("mensajeExito", "Alquiler registrado exitosamente.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensajeError", "Error al registrar el alquiler: " + e.getMessage());
        }
        return "redirect:/alquiler";
    }
}
