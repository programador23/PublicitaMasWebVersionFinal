package cl.christian.demo.controler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.christian.demo.interfaceService.ICampaniaPublicitariaService;
import cl.christian.demo.interfaceService.ICartelPublicitarioService;
import cl.christian.demo.interfaceService.IPostulacionesService;
import cl.christian.demo.interfaceService.IRedSocialService;
import cl.christian.demo.interfaceService.IRolesService;
import cl.christian.demo.interfaceService.IUsersService;
import cl.christian.demo.modelo.CampaniaPublicitaria;
import cl.christian.demo.modelo.CartelPublicitario;
import cl.christian.demo.modelo.Postulacion;
import cl.christian.demo.modelo.RedSocial;
import cl.christian.demo.modelo.Roles;
import cl.christian.demo.modelo.User;

/**
 * @author Christian
 *
 */

@Controller
@RequestMapping
public class Controlador {
	
	@Autowired
	private ICampaniaPublicitariaService service;
	
	/**
	 * carga los datos de la bd en una lista y luego los manda 
	 * a una vista 
	 */
	@GetMapping("/listar")
	public String listarcampania(Model model) {
		List<CampaniaPublicitaria>campaniapublicitaria=service.listarcampania();
		model.addAttribute("campaniapublicitaria", campaniapublicitaria);
		return "listacampaniapublicitariaAdm";
	}
	
	/**
	 * carga los datos de la bd en una lista y luego los manda 
	 * a una vista 
	 */
	@GetMapping("/listarPublicidad")
	public String listarcampaniaPublidad(Model model) {
		List<CampaniaPublicitaria>campaniapublicitaria=service.listarcampania();
		model.addAttribute("campaniapublicitaria", campaniapublicitaria);
		return "listapublicidadgeneral";
	}

	
	
	
	/**
	 * muestra la vista formulario agregar campaña
	 * cuando lo llamn por "/new"
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/new")
	public String agregarcampania(Model model) {
		model.addAttribute("campaniapublicitaria", new CampaniaPublicitaria());
		model.addAttribute("titulo","Agregar  Campania-Empresa");
		model.addAttribute("titulo","Agregar  Campania-Empresa");
		return "usuario/FormularioAgregarCampaña";
	}

		
	
	/**
	 * @param c
	 * @param model
	 *Guarda los datos del formulario campaña  y la imagen
	 * los guarda en la base de dato
	 * @param attributes
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/savecampania")
	public String savecampania(@Validated CampaniaPublicitaria c, Model model, @RequestParam("file") MultipartFile image, RedirectAttributes attributes, RedirectAttributes redirectAttrs) {
		
		
		if(!image.isEmpty()) {
			
			String rutaAbsoluta = "C://Campania//recursos";
			
			try {
				byte[] bytesImg =image.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ image.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				c.setImage(image.getOriginalFilename());
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		

		
		model.addAttribute("titulo","Agregar  Campania-Empresa");
		service.savecampania(c);
		redirectAttrs
        .addFlashAttribute("mensaje", "Agregado correctamente")
        .addFlashAttribute("clase", "success");
		return "redirect:/listarPublicidad";	
	}
	
	/**
	 * primero busca en la lista el id que le mandamos atraves de un boton
	 * y luego manda los datos para cargalos
	 * para luego poder modificarlo llamando a la clase save de campaña
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("modificarcampania/{id}")
	public String editar(@PathVariable("id") int id,Model model, RedirectAttributes redirectAttrs) {
		
		CampaniaPublicitaria campaniapublicitaria=service.listarId(id);
		
		model.addAttribute("titulo","Modificar Campania-Empresa");
		model.addAttribute("campaniapublicitaria",campaniapublicitaria);
		
		redirectAttrs
        .addFlashAttribute("mensaje", "Modificado correctamente")
        .addFlashAttribute("clase", "success");
		
		return"usuario/FormularioAgregarCampaña";
	}
	

	
	/*Detalle de campaña por id*/
	
	/**
	 * Buscar el id en la lista
	 * Buscar si existe o no 
	 * Luego de encontrarlo lo manda a una vista que detalla la campaña y sus datos
	 * 
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("detalle/{id}")
	public String detalleCampania(@PathVariable("id") int id,Model model,RedirectAttributes attribute) {
		
		 CampaniaPublicitaria campaniapublicitaria= null;
		
		
		  if(id > 0) {
			  campaniapublicitaria = service.listarId(id);
		  
		 if(campaniapublicitaria==null) {
			 attribute.addFlashAttribute("error","El ID del la Campania no existe");
			 return "DetalleCampania";
		 }
		  }else {
			  attribute.addFlashAttribute("error","error con el id de la campania");
			  return "DetalleCampania";
		  }
		model.addAttribute("titulo","Detalle de  "+ campaniapublicitaria.getNombre());
		  
		model.addAttribute("campaniapublicitaria", campaniapublicitaria);
		return"DetalleCampania";
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/nombre/detalle/{id}")
	public String detalleCampaniaNombre(@PathVariable("id") int id,Model model,RedirectAttributes attribute) {
		
		 CampaniaPublicitaria campaniapublicitaria= null;
		
		
		  if(id > 0) {
			  campaniapublicitaria = service.listarId(id);
		  
		 if(campaniapublicitaria==null) {
			 attribute.addFlashAttribute("error","El ID del la Campania no existe");
			 return "DetalleCampania";
		 }
		  }else {
			  attribute.addFlashAttribute("error","error con el id de la campania");
			  return "DetalleCampania";
		  }
		model.addAttribute("titulo","Detalle de  "+ campaniapublicitaria.getNombre());
		  
		model.addAttribute("campaniapublicitaria", campaniapublicitaria);
		return"DetalleCampania";
	}
	
	
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/idusuario/detalle/{id}")
	public String detalleCampaniaUsuario(@PathVariable("id") int id,Model model,RedirectAttributes attribute) {
		
		 CampaniaPublicitaria campaniapublicitaria= null;
		
		
		  if(id > 0) {
			  campaniapublicitaria = service.listarId(id);
		  
		 if(campaniapublicitaria==null) {
			 attribute.addFlashAttribute("error","El ID del la Campania no existe");
			 return "DetalleCampania";
		 }
		  }else {
			  attribute.addFlashAttribute("error","error con el id de la campania");
			  return "DetalleCampania";
		  }
		model.addAttribute("titulo","Detalle de  "+ campaniapublicitaria.getNombre());
		  
		model.addAttribute("campaniapublicitaria", campaniapublicitaria);
		return"DetalleCampania";
	}
	
	
	
	
	
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/listausuario")
	public String listaCampaniasUsuarios(Model model) {

	
		model.addAttribute("campaniapublicitaria", new CampaniaPublicitaria());
		return "usuario/ListaDeCampaniaUsuario";
		
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/listanombre")
	public String listaCampaniasNombre(Model model) {

	
		model.addAttribute("campaniapublicitaria", new CampaniaPublicitaria());
		return "ListaDeCampaniaNombre";
		
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/nombre/")
	public String BuscarPorNombre(@RequestParam String nombre,Model model,@ModelAttribute("campaniapublicitaria") CampaniaPublicitaria campaniaPublicitaria) {
		
		model.addAttribute("PublicidadPorNombre", service.BuscarPornombre(nombre));
		
		
		return "ListaDeCampaniaNombre";
	}
	
	/*
	 * Este metodo busca por el id del usuario todas las campañas que tenga
	 * vinculadas o que allá creado
	 */
	/**
	 * Buscar en toda la lista el idusuario que sea igual al indicado
	 * para luego agregarlos a una lista, para mostrarlos en una vista
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/idusuario/")
	public String BuscarPorIdusuario(@RequestParam String idusuario,Model model,@ModelAttribute("campaniapublicitaria") CampaniaPublicitaria campaniaPublicitaria ) {

		model.addAttribute("campaniapublicitaria", service.BuscarPordusuario(idusuario));
		return "usuario/ListaDeCampaniaUsuario";
	}
	
	
	/*lista por idusuario de carteles ingresados*/
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/cartelidusuario")
	public String listarcarteles_usuarios(@RequestParam String idusuario,Model model,@ModelAttribute("cartelpublicitarios") CartelPublicitario cartelPublicitario) {
		
		 
		
		model.addAttribute("CartelPorUsuario", servicecartel.buscarPorIdusuario(idusuario));

		return "carteles/ListaDeCartelesUsuario";
	}
	
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/cartel")
	public String listacartelesusuario(Model model) {
		model.addAttribute("cartelpublicitarios", new CartelPublicitario());
	return "carteles/ListaDeCartelesUsuario";
	}
	
	
	
	@GetMapping("/inicioAdmin")
	//llama
	public String inicioAdmin(Model model) {
		
		return "inicioAdmin";
	}
	
	
	
		
	/**
	 * Busca el id en la lista para luego para luego tomar el objeto e
	 * elimina una campaña por su id 
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
		@GetMapping("/eliminarcampania/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/listarPublicidad";
	}
	
		
	/*Desde Esta parte comienzan las clases de los carteles publicitarios*/
	@Autowired
	private ICartelPublicitarioService servicecartel;
	
	/**
	 * Toma los datos de la bd 
	 * y crea una lista  de tipo CartelPublicitario para luego  mostrarlos en una vista
	 * llamada listacartelesAdmin
	 */
	
	@GetMapping("/listacartelesAdmin")
	public String listarcartelesadmin(Model model) {
		List<CartelPublicitario> cartelpublicitarios = servicecartel.listarcarteles();
		model.addAttribute("cartelpublicitarios", cartelpublicitarios);
		return "carteles/listacartelesAdmin";
	} 
	/**
	 * Toma los datos de la bd 
	 * y crea una lista  de tipo CartelPublicitario para luego  mostrarlos en una vista
	 * llamada listacarteles
	 */
	@GetMapping("/listacarteles")
	public String listarcarteles(Model model) {
		List<CartelPublicitario> cartelpublicitarios = servicecartel.listarcarteles();
		model.addAttribute("cartelpublicitarios", cartelpublicitarios);
		return "carteles/listacarteles";
	} 
	
	
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/nuevocartel")
	public String nuevocartel(Model model) {
		model.addAttribute("titulo","Agregar Cartel Publicitario");
		
		model.addAttribute("cartelpublicitario", new CartelPublicitario());
		return "carteles/FormularioAgregarCartel";
	}
	

	
	/**
	 * Esta Clase guarda el formulario de cartel
	 * Ademas guarda las imagenes
	 *
	 * @return
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/savecartel")
	public String savecartel(@Validated CartelPublicitario c, Model model, @RequestParam("file") MultipartFile image, @RequestParam("document") MultipartFile documentos,RedirectAttributes atrAttributes,RedirectAttributes redirectAttrs) {
		
		if(!image.isEmpty() && ! documentos.isEmpty()) {
			
			String rutaAbsoluta = "C://Campania//recursos";
			
			String rutaAbsolutaDoc = "C://Campania//recursos";
			
			try {
				byte[] bytesImg = image.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
				byte[] bytesDoc = documentos.getBytes();
				Path rutaCompletaDoc = Paths.get(rutaAbsolutaDoc + "//" + documentos.getOriginalFilename());
			
				Files.write(rutaCompleta, bytesImg);
				Files.write(rutaCompletaDoc, bytesDoc);
				
				c.setImage(image.getOriginalFilename());
				c.setDocumentacion(documentos.getOriginalFilename());
				
					
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	    redirectAttrs
        .addFlashAttribute("mensaje", "Agregado correctamente")
        .addFlashAttribute("clase", "success");
		
		servicecartel.savecartel(c);
		return "redirect:/listacarteles";
	}
	
	/**
	 * Buscar en la lista de carteles por el id entregado
	 * luego entrega el id con el objeto asociado
	 * y manda los datos a una vista para cargarlos 
	 */
	
	@GetMapping("modificarcartel/{id}")
	public String editarcartel(@PathVariable int id,Model model, RedirectAttributes redirectAttrs) {
		CartelPublicitario cartelpublicitario=servicecartel.listarIdcartel(id);
		
		model.addAttribute("titulo","Modificar-Cartel  "+ cartelpublicitario.getId());
		model.addAttribute("cartelpublicitario",cartelpublicitario);
		
		
	    redirectAttrs
        .addFlashAttribute("mensaje", "Modificado correctamente")
        .addFlashAttribute("clase", "success");
		
		return"carteles/FormularioModificarCartel";
	}

	

	
	
	/**
	 * Buscar en la lista de carteles el id entregado y luego de encontralo elimina todo el objeto o la fila de la bd
	 */
	@GetMapping("/eliminarcartel/{id}")
	public String deletecartel(Model del,@PathVariable int id) {
		servicecartel.deletecartel(id);
		return"redirect:/index";
	}
	
	@GetMapping("/eliminarPostulacion/{id}")
	public String deletePostulacion(@PathVariable int id,RedirectAttributes rediAttributes) {
		servicePostulaciones.deletePostulacion(id);
		//rediAttributes.addFlashAttribute("mensaje", "Postulacion Eliminada correctamente");
		return"redirect:/index";
	}
	
	
	
	@GetMapping("/agregarCartel")
	//llama
	public String agregarcartel(Model model) {
		model.addAttribute("cartelpublicitario", new CartelPublicitario());
		model.addAttribute("titulo","Agregar Cartel Publicitario");
		return "carteles/FormularioAgregarCartel";
	}
	
	/**
	 * iniciamos un objeto en null
	 * luego preguntamos el id entregado es mayor a 0 si es asi 
	 * buscamos en la lista de carteles el id y si lo encuentra por carga en el objeto vacio iniciado anteriormente
	 * 
	 */
	@GetMapping("detallecartel/{id}")
	public String detalleCartel(@PathVariable("id") int id,Model model,RedirectAttributes attribute) {
		
		 CartelPublicitario cartelPublicitario = null;
		
		
		  if(id > 0) {
			  cartelPublicitario = servicecartel.listarIdcartel(id);
		  
		 if(cartelPublicitario==null) {
			 attribute.addFlashAttribute("error","El ID del la Cartel no existe");
			 return "DetalleCampania";
		 }
		  }else {
			  attribute.addFlashAttribute("error","error con el id del cartel");
			  return "DetalleCampania";
		  }
		model.addAttribute("titulo","Detalle de  "+ cartelPublicitario.getTitulo());
		  
		model.addAttribute("cartelpublicitario", cartelPublicitario);
		return"carteles/DetalleCartel";
	}
	
	
	@GetMapping("/cartelidusuario/detallecartel/{id}")
	public String detalleCartelUsuario(@PathVariable("id") int id,Model model,RedirectAttributes attribute) {
		
		 CartelPublicitario cartelPublicitario = null;
		
		
		  if(id > 0) {
			  cartelPublicitario = servicecartel.listarIdcartel(id);
		  
		 if(cartelPublicitario==null) {
			 attribute.addFlashAttribute("error","El ID del la Cartel no existe");
			 return "DetalleCampania";
		 }
		  }else {
			  attribute.addFlashAttribute("error","error con el id del cartel");
			  return "DetalleCampania";
		  }
		model.addAttribute("mensaje","Detalle de  "+ cartelPublicitario.getTitulo());
		  
		model.addAttribute("cartelpublicitario", cartelPublicitario);
		return"carteles/DetalleCartel";
	}
	

	
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private IUsersService serviceUsuario;
	@Autowired
	private IRolesService serviceRoles;
	
	@GetMapping("/create")
	public String Crear(@ModelAttribute User user) {
		return "registro";
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/saveUsuario")
	public String Guardar(@ModelAttribute User user,Model model,RedirectAttributes attributes) {
	String tmPass = user.getPassword();
	String encriptado = encoder.encode(tmPass);
	
	user.setPassword(encriptado);
	user.setEnabled(1);
	
	serviceUsuario.guardar(user);
	int idcopia = user.getIdusuario();
	
	
	Roles roles = new Roles();
	roles.setUser_id(idcopia);
	roles.setRol("ROLE_USER");
	
	serviceRoles.guardar(roles);
	
	attributes.addFlashAttribute("mensaje","Usuario Registrado Exitosamente "+ user.getUsername());
	
		
		return"redirect:/index";
	}
	@Autowired
	private IPostulacionesService servicePostulaciones;
	
	@GetMapping("/postular/{id}")
	public String Postular(@ModelAttribute Postulacion postulacion,@PathVariable("id") int id,Model model) {
		
	model.addAttribute("mensaje", id);
		
		return "postular";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/guardarPostulacion/")
	public String GuardarPostulacion(@ModelAttribute Postulacion postulacion, Model model,RedirectAttributes attributes) {
		
		try {
			postulacion.setMensajeIns(null);
			postulacion.setEstado("Postulacion Pendiente");
			servicePostulaciones.guardar(postulacion);
			attributes.addFlashAttribute("mensaje","Postulacion Exitosa ");
		}catch (Exception e) {
			
			attributes.addFlashAttribute("mensaje","Ya Postulaste a esta Publicidad...");
			return"redirect:/index";
		}
		
		
		return"redirect:/index";
	}
	
	//guardar aprobacion de  postulacion
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/guardarPostulacionAprobado")
	public String GuardarPostulacionAprobado(@ModelAttribute Postulacion postulacion, Model model,RedirectAttributes attributes) {
		
			
			postulacion.setEstado("Postulacion Aprobada");
			servicePostulaciones.guardar(postulacion);
			attributes.addFlashAttribute("mensaje","Postulacion Aprobada ");
	
		
		
		return"redirect:/index";
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/postulaciones")
	public String postulacionusuario(Model model) {
		model.addAttribute("postulaciones", new Postulacion());
		return"ListaDePostulacionesUsuario";
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/postulacionUsuario")
	public String listaPostulacionesUsuario(@RequestParam String idusuario,Model model,@ModelAttribute("postulaciones")Postulacion postulacion) {
		
		model.addAttribute("PostulacionPorUsuario", servicePostulaciones.buscarPorIdusuario(idusuario));
		return"ListaDePostulacionesUsuario";
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/postulacionesCam/")
	public String postulacionesCampanias(Model model) {
		model.addAttribute("postulaciones", new Postulacion());
		return"ListaDePostulacionesCampanias";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/postulacionesCampanias/")
	public String listaPostulacionesCampanias(@RequestParam(value="idCampania")  Integer idcampania,Model model,@ModelAttribute("postulaciones")Postulacion postulacion) {
		model.addAttribute("PostulacionesPorIdCampania", servicePostulaciones.buscarPorIdCampania(idcampania));
		return "ListaDePostulacionesCampanias";
				
	}
	@GetMapping("perfilUsuario/{username}")
	public String perfilUsuario(@PathVariable("username") String username,Model model,RedirectAttributes attribute) {
		
		User user = null;
		
		user = serviceUsuario.listaPorUser(username);
		
		if(user==null) {
			attribute.addFlashAttribute("error","El Usuario no existe");
			return "/index";
		}
		 model.addAttribute("perfilUsuario", user);
		 //model.addAttribute("redessociales", new RedSocial());
		return"PerfilUsuario";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/redesSociales/")
	public String listaRedesSociales(@RequestParam(value="username")String username,Model model,@ModelAttribute("redessociales")RedSocial redSocial) {
		User user=null;
		user = serviceUsuario.listaPorUser(username);
		
		model.addAttribute("RedesSocialesPorUsername", redSocialService.listaPorRedSocial(username));
		model.addAttribute("perfilUsuario", user);
		return"perfilUsuario";
		
		
	}
	
	public String redesSociales(Model model) {
		model.addAttribute("redessociales", new RedSocial());
		return "perfilUsuario";
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("postulacionesCampanias/aprobarPostulacion/{id}")
	public String editarPostulacion(@PathVariable("id") int id,Model model) {
		
		Postulacion postulacion=servicePostulaciones.listarId(id);
		
		
		model.addAttribute("postulaciones",postulacion);
		
		
		return"FormularioAprobarPostulacion";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/savePr/{id}")
	public String FinalizarPostulacion(@PathVariable("id") int id,Model model,RedirectAttributes attributes) {
		
		Postulacion postulacion=servicePostulaciones.listarId(id);
		
		postulacion.setEstado("Proceso finalizado");
		
		servicePostulaciones.guardar(postulacion);
		
		
		attributes.addFlashAttribute("mensaje", "Proceso Finalizado");
		return"redirect:/pago";
	}
	
	
	
	@Autowired
	private IRedSocialService redSocialService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/saveRedSocial")
	public String GuardarRedSocial(@ModelAttribute RedSocial redSocial,Model model,RedirectAttributes attributes) {
		redSocialService.guardar(redSocial);
		attributes.addFlashAttribute("mensaje", "Red Social  Agregada");
		
		return"redirect:/index";
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/nuevaRedSocial")
	public String agregaRedSocial(Model model) {
		
	model.addAttribute("redessociales", new RedSocial());
	return"FormularioAgregarRedSocial";
	}
	
	
	@GetMapping("/index")
	//llama de inicio 
	public String incio(Model model) {
		
		return "inicio";
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/pago")
	//llama a pago
	public String pago(Model model) {
		
		return "pago";
	}
	
	
	
	@GetMapping("/registro")
	//llama de inicio 
	public String Registro(Model model) {
		
		return "registro";
	}
	
	

	
}
