package cl.caQuezada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.caQuezada.model.Producto;
import cl.caQuezada.service.ProductoService;
import cl.caQuezada.util.Util;
import cl.caQuezada.vo.ProductoVO;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService psv;
	private Integer persisteCantidadRegistros = 6;
	
	@GetMapping({"/", "/home"})
	public String home(ModelMap modelMap, @RequestParam(defaultValue="1") Integer p, @RequestParam(defaultValue="6") Integer cantidadRegistros ) {
		persisteCantidadRegistros = cantidadRegistros;
		Integer totalPaginas = psv.getPageCount(persisteCantidadRegistros).getValor(); // número de productos por página
		modelMap.addAttribute("paginas", Util.getArregloPaginas(p, totalPaginas));
		modelMap.addAttribute("paginaActual", p);
		modelMap.addAttribute("VO", psv.getPage(p-1, persisteCantidadRegistros));
		
		return "home";
	}
	
	@PostMapping("/findByProduct")
	public String findByTituloAndAutor(
			ModelMap modelMap, 
			@RequestParam String nombre) {
		ProductoVO productoVO = psv.findByNombre(nombre);
		modelMap.addAttribute("VO", productoVO);
		return "home";
	}
	
	@GetMapping("/editarForm")
	public ModelAndView editarForm(ModelMap modelMap, @RequestParam String idProducto, RedirectAttributes ra) {
		ProductoVO respuestaVO = new ProductoVO();
		respuestaVO.setMensaje("No se pudo agregar la vista Edición. Intente nuevamente");
		try {
			respuestaVO = psv.findById(Integer.parseInt(idProducto));
			modelMap.addAttribute("mensaje", respuestaVO.getMensaje());
			modelMap.addAttribute("VO", respuestaVO.getProductos().get(0));
			return new ModelAndView("editar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ra.addFlashAttribute("mensaje", respuestaVO.getMensaje());
		respuestaVO = psv.getAllProductos();
		
		return new ModelAndView("redirect:/home");
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(@ModelAttribute Producto producto, RedirectAttributes ra) {
		
		ProductoVO respuestaVO = psv.edit(producto);
		ra.addFlashAttribute("mensaje", respuestaVO.getMensaje());
		
		if(respuestaVO.getCodigo().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/editarForm");
		}
	}
	
	@GetMapping("/agregarForm")
	public String agregarForm(ModelMap modelMap) {
		return "agregar";
	}
	
	@PostMapping("/agregar")
	public ModelAndView agregar(@ModelAttribute Producto producto, RedirectAttributes ra) {
		ProductoVO respuestaVO = psv.add(producto);
		ra.addFlashAttribute("mensaje", respuestaVO.getMensaje());
		
		if(respuestaVO.getCodigo().equals("0")) {
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/editarForm");
		}
	}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(ModelMap modelMap,  @RequestParam Integer idProducto, RedirectAttributes ra) {
		ProductoVO respuestaVO = new ProductoVO();
		respuestaVO.setMensaje("No se pudo eliminar el usuario. Intente nuevamente");
		try {
			Producto productoEliminado = new Producto();
			productoEliminado.setIdProducto(idProducto);
			respuestaVO = psv.delete(productoEliminado);
			ra.addFlashAttribute("mensaje", respuestaVO.getMensaje());
			return new ModelAndView("redirect:/home");
		} catch(Exception e) {
			e.printStackTrace();
		}
		ra.addFlashAttribute("mensaje", respuestaVO.getMensaje());
		respuestaVO = psv.getAllProductos();
		ra.addAttribute("VO", respuestaVO);
		return new ModelAndView("redirect:/home");
	}
}
