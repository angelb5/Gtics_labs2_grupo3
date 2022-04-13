package pe.edu.pucp.gtics_lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.pucp.gtics_lab2.entity.Marca;
import pe.edu.pucp.gtics_lab2.repository.MarcaRepository;

import java.util.List;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    MarcaRepository marcaRepository;

    @GetMapping({"/lista", ""})
    public String listarMarcas(Model model) {
        List<Marca> marcaList = marcaRepository.findAll();
        model.addAttribute("marcaList", marcaList);
        return "marcas/lista";
    }
}
