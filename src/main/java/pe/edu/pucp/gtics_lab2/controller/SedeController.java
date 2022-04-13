package pe.edu.pucp.gtics_lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.gtics_lab2.entity.Sede;
import pe.edu.pucp.gtics_lab2.repository.SedeRepository;
import pe.edu.pucp.gtics_lab2.repository.TrabajadorRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("sedes")
public class SedeController {
    @Autowired
    SedeRepository sedeRepository;

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @GetMapping(value={"","/lista"})
    public String listaSedes(Model model){
        List<Sede> sedeList = sedeRepository.findAll();
        model.addAttribute("sedeList",sedeList);
        return "sedes/lista";
    }

    @GetMapping("/editar")
    public String editarSedes(@RequestParam("id") int id,
                              Model model){
        Optional<Sede> optionalSede = sedeRepository.findById(id);
        if(optionalSede.isPresent()){
            Sede sede = optionalSede.get();
            model.addAttribute("sede",sede);
            model.addAttribute("trabajadoresList",trabajadorRepository.returnList(id));
            return "sedes/editar";
        }else{
            return "redirect:/sedes/lista";
        }
    }

    @GetMapping("/nuevo")
    public String nuevaSede(){ return "sedes/nuevo";}

    @PostMapping("/guardar")
    public String guardarSede(Sede sede, RedirectAttributes reddA){
        if(sede.getId() == 0){
            reddA.addFlashAttribute("creada","Sede creada exitosamente");
        }else{
            reddA.addFlashAttribute("editada","Sede editada exitosamente");
        }
        sedeRepository.save(sede);
        return "redirect:/sedes/lista";
    }

    @GetMapping("/borrar")
    public String borrarSede(@RequestParam("id") int id, RedirectAttributes reddA){
        Optional<Sede> optionalSede = sedeRepository.findById(id);
        if(optionalSede.isPresent()){
            sedeRepository.deleteById(id);
            reddA.addFlashAttribute("borrada","Marca borrada exitosamente");
        }
        return "redirect:/sedes/lista";
    }

}
