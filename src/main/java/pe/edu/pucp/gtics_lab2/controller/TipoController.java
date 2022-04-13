package pe.edu.pucp.gtics_lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.gtics_lab2.entity.Marca;
import pe.edu.pucp.gtics_lab2.entity.Tipo;
import pe.edu.pucp.gtics_lab2.repository.MarcaRepository;
import pe.edu.pucp.gtics_lab2.repository.TipoRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tipos")
public class TipoController {
    @Autowired
    TipoRepository tipoRepository;

    @GetMapping({"/lista", ""})
    public String listaTipos(Model model) {
        List<Tipo> tipoList = tipoRepository.findAll();
        model.addAttribute("tipoList", tipoList);
        return "tipos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoTipo() {
        return "tipos/nuevo";
    }

    @GetMapping("/editar")
    public String editarTipo(@RequestParam("id") int id, Model model){
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);
        if(optionalTipo.isPresent()){
            Tipo tipo = optionalTipo.get();
            model.addAttribute("tipo",tipo);
            return "tipos/editar";
        }else{
            return "redirect:/tipos/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarTipo(Tipo tipo, RedirectAttributes attr){
        if (tipo.getId() == 0) {
            attr.addFlashAttribute("creado", "Tipo creado exitosamente");
        } else {
            attr.addFlashAttribute("editado", "Tipo editado exitosamente");
        }
        tipoRepository.save(tipo);
        return "redirect:/tipos/lista";
    }

    @GetMapping("/borrar")
    public String borrarTipo(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<Tipo> optionalTipo = tipoRepository.findById(id);
        if(optionalTipo.isPresent()){
            tipoRepository.deleteById(id);
            attr.addFlashAttribute("borrado","Tipo borrado exitosamente");
        }
        return "redirect:/tipos/lista";
    }
}
