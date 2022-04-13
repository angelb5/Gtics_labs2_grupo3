package pe.edu.pucp.gtics_lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.pucp.gtics_lab2.entity.*;
import pe.edu.pucp.gtics_lab2.repository.InventarioRepository;
import pe.edu.pucp.gtics_lab2.repository.MarcaRepository;
import pe.edu.pucp.gtics_lab2.repository.SedeRepository;
import pe.edu.pucp.gtics_lab2.repository.TipoRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    SedeRepository sedeRepository;
    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    TipoRepository tipoRepository;

    @GetMapping(value={"","/lista"})
    public String listaInventario(Model model){
        List<Inventario> inventarioList = inventarioRepository.findAll();
        model.addAttribute("inventarioList",inventarioList);
        return "inventario/lista";
    }

    @GetMapping("/crear")
    public String crearTrabajador(Model model){
        List<Sede> listaSedes = sedeRepository.findAll();
        List<Tipo> listaTipos = tipoRepository.findAll();
        List<Marca> listaMarcas = marcaRepository.findAll();

        model.addAttribute("listaSedes",listaSedes);
        model.addAttribute("listaTipos",listaTipos);
        model.addAttribute("listaMarcas",listaMarcas);
        return "inventario/crear";
    }

    @GetMapping("/editar")
    public String editarTrabajador(@RequestParam("id") int id,
                                   Model model){
        Optional<Inventario> existeInventario = inventarioRepository.findById(id);

        if(existeInventario.isPresent()){

            List<Sede> listaSedes = sedeRepository.findAll();
            List<Tipo> listaTipos = tipoRepository.findAll();
            List<Marca> listaMarcas = marcaRepository.findAll();

            model.addAttribute("listaSedes",listaSedes);
            model.addAttribute("listaTipos",listaTipos);
            model.addAttribute("listaMarcas",listaMarcas);
            model.addAttribute("inventario",existeInventario.get());
            return "inventario/editar";

        }else{
            return "redirect:/inventario/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarTrabajador(Inventario inventario, RedirectAttributes redA){

        try {
            System.out.println("ID Inventario: "+inventario.getId());
            if (inventario.getId() == 0) {
                redA.addFlashAttribute("crear", "Agregado al inventario exitosamente");
            } else {
                redA.addFlashAttribute("editar", "Inventario actualizado exitosamente");
            }
            inventarioRepository.save(inventario);
        } catch(Exception e){
            redA.addFlashAttribute("err", "Hubo un problema, inténtalo nuevamente");
            System.out.println(e.getMessage());
        }

        return "redirect:/inventario/lista";
    }

    @GetMapping("/borrar")
    public String borrarInventario(@RequestParam("id") int id, RedirectAttributes reddA){
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if(optionalInventario.isPresent()){
            inventarioRepository.deleteById(id);
            reddA.addFlashAttribute("borrar","Inventario borrado exitosamente");
        }
        return "redirect:/inventario/lista";
    }


}