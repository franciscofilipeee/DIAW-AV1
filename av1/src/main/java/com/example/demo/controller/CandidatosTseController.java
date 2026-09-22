package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.services.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidatosTseController {
    private final CandidatosTseService candidatosTseService;

    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String index(
        @RequestParam(required = false) String cargo,
        @RequestParam(required = false) String partido,
        @RequestParam(required = false) String texto,
        Model model
    ) {
        List<Candidato> candidatos = candidatosTseService.filtrar(cargo, partido, texto);

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("cargos", candidatosTseService.listarCargos());
        model.addAttribute("partidos", candidatosTseService.listarPartidos());
        model.addAttribute("cargoSelecionado", cargo);
        model.addAttribute("partidoSelecionado", partido);
        model.addAttribute("texto", texto);

        return "index";
    }
}
