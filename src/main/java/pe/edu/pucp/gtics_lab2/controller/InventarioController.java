package pe.edu.pucp.gtics_lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.gtics_lab2.entity.Inventario;
import pe.edu.pucp.gtics_lab2.entity.Sede;
import pe.edu.pucp.gtics_lab2.repository.InventarioRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioRepository inventarioRepository;

    @GetMapping(value={"","/lista"})
    public String listaInventario(Model model){
        List<Inventario> inventarioList = inventarioRepository.findAll();
        model.addAttribute("inventarioList",inventarioList);
        return "inventario/lista";
    }

    @GetMapping("/borrar")
    public String borrarInventario(@RequestParam("id") int id, RedirectAttributes reddA){
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if(optionalInventario.isPresent()){
            inventarioRepository.deleteById(id);
            reddA.addFlashAttribute("borrada","Inventario borrado exitosamente");
        }
        return "redirect:/inventario/lista";
    }


}
