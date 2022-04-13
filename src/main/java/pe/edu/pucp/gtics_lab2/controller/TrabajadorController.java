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
import pe.edu.pucp.gtics_lab2.entity.Trabajador;
import pe.edu.pucp.gtics_lab2.repository.SedeRepository;
import pe.edu.pucp.gtics_lab2.repository.TrabajadorRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

    @Autowired
    TrabajadorRepository trabajadorRepository;
    @Autowired
    SedeRepository sedeRepository;

    @GetMapping(value={"","/lista"})
    public String listaTrabajadores(Model model){
        List<Trabajador> listaTrabajadores = trabajadorRepository.findAll();

        model.addAttribute("listaTrabajadores",listaTrabajadores);
        return "trabajadores/lista";
    }

    @GetMapping("/crear")
    public String crearTrabajador(Model model){
        List<Sede> listaSedes = sedeRepository.findAll();

        model.addAttribute("listaSedes",listaSedes);
        return "trabajadores/crear";
    }

    @GetMapping("/editar")
    public String editarTrabajador(@RequestParam("id") String dni,
                              Model model){
        Optional<Trabajador> existeTrabajador = trabajadorRepository.findByDni(dni);

        if(existeTrabajador.isPresent()){
            Trabajador trabajador = existeTrabajador.get();
            List<Sede> listaSedes = sedeRepository.findAll();

            model.addAttribute("listaSedes",listaSedes);
            model.addAttribute("trabajador",trabajador);
            return "trabajadores/editar";

        }else{
            return "redirect:/trabajadores/lista";
        }
    }

    @PostMapping("/guardar")
    public String guardarTrabajador(@RequestParam("tipo") int tipo,
            Trabajador trabajador, RedirectAttributes redA){

        try {
            trabajadorRepository.save(trabajador);
            if (tipo == 0) {
                redA.addFlashAttribute("msg", "Trabajador agregado exitosamente");
            } else {
                redA.addFlashAttribute("msg", "Datos del Trabajador actualizados exitosamente");
            }
        } catch(Exception e){
            redA.addFlashAttribute("err", "Hubo un problema, inténtalo nuevamente");
        }

        return "redirect:/trabajadores/lista";
    }

    @GetMapping("/borrar")
    public String borrarTrabajador(@RequestParam("id") String dni, RedirectAttributes redA){

        Optional<Trabajador> existeTrabajador = trabajadorRepository.findByDni(dni);

        if(existeTrabajador.isPresent()){
            trabajadorRepository.delete(existeTrabajador.get());
            redA.addFlashAttribute("msg","Trabajador eliminado exitosamente");
        } else{
            redA.addFlashAttribute("err","Hubo un problema al eliminar al trabajador. Inténtelo nuevamente");
        }
        return "redirect:/trabajadores/lista";
    }
}
