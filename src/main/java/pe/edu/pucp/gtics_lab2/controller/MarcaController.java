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
import pe.edu.pucp.gtics_lab2.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/nueva")
    public String nuevaMarca() {
        return "marcas/nueva";
    }

    @GetMapping("/editar")
    public String editarMarca(@RequestParam("id") int id, Model model){
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        if(optionalMarca.isPresent()){
            Marca marca = optionalMarca.get();
            model.addAttribute("marca",marca);
            return "marcas/editar";
        }else{
            return "redirect:/marcas/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarMarca(Marca marca, RedirectAttributes attr){
        if (marca.getId() == 0) {
            attr.addFlashAttribute("creada", "Marca creada exitosamente");
        } else {
            attr.addFlashAttribute("editada", "Marca editada exitosamente");
        }
        marcaRepository.save(marca);
        return "redirect:/marcas/lista";
    }

    @GetMapping("/borrar")
    public String borrarMarca(@RequestParam("id") int id, RedirectAttributes attr){
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        if(optionalMarca.isPresent()){
            marcaRepository.deleteById(id);
            attr.addFlashAttribute("borrada","Marca borrada exitosamente");
        }
        return "redirect:/marcas/lista";
    }

}
