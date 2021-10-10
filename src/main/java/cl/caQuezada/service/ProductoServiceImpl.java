package cl.caQuezada.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.caQuezada.model.Producto;
import cl.caQuezada.repository.ProductoRepository;
import cl.caQuezada.vo.NumberVO;
import cl.caQuezada.vo.ProductoVO;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;
	
	ProductoVO respuestaVO;

	@Override
	@Transactional(readOnly=true)
	public ProductoVO getAllProductos() {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "101");
		try {
			respuestaVO.setProductos( (List<Producto>) productoRepository.findAll() );
			respuestaVO.setMensaje(String.format("Se ha/n encontrado %d registros", respuestaVO.getProductos().size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

	@Override
	@Transactional(readOnly=true)
	public ProductoVO findByNombre(String nombre) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "102");
		try {
			List<Producto> productos = productoRepository.findByNombre(nombre);
			if (productos.size() > 0) {
				respuestaVO.setProductos(productos);
				respuestaVO.setMensaje("Producto encontrado correctamente");
				respuestaVO.setCodigo("0");
			} else {
				respuestaVO.setMensaje("Producto no encontrado");
				respuestaVO.setCodigo("0");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

	@Override
	@Transactional
	public ProductoVO add(Producto producto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "103");
		try {
			productoRepository.save(producto);
			respuestaVO.setMensaje(String.format("Se ha guardado el producto %s", producto.getNombreProducto() ));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

	@Override
	@Transactional
	public ProductoVO edit(Producto producto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "104");
		try {
//			ProductoVO productoVO = findById(producto.getIdProducto());
			
//			if (productoVO.getProductos() != null) {
//				for (Producto productoTemp : productoVO.getProductos()) {
//					productoTemp.setIdProducto(producto.getIdProducto());
//					productoTemp.setNombreProducto(producto.getNombreProducto());
//					productoTemp.setCodigoUnico(producto.getCodigoUnico());
//					productoTemp.setStockProducto(producto.getStockProducto());
//				}
//			}
			productoRepository.save(producto);
			respuestaVO.setMensaje(String.format("Se ha actualizado correctamente el producto %s", producto.getNombreProducto() ));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

	@Override
	@Transactional
	public ProductoVO delete(Producto producto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "105");
		try {
			productoRepository.delete(producto);
			respuestaVO.setMensaje(String.format("Se ha eliminado correctamente el producto %s", producto.getNombreProducto() ));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

	@Override
	@Transactional(readOnly=true)
	public ProductoVO getPage(Integer pagina, Integer cantidad) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "106");
		try {
			Pageable pageable = PageRequest.of(pagina, cantidad);
			Page<Producto> responsePage = productoRepository.findAll(pageable);
			respuestaVO.setProductos(responsePage.getContent());
			respuestaVO.setMensaje(String.format("Se ha/n encontrado %d producto/s", respuestaVO.getProductos().size() ));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

	@Override
	@Transactional(readOnly=true)
	public NumberVO getPageCount(int registrosPorPagina) {
		NumberVO respuesta = new NumberVO(0, "Ha ocurrido un error", "107");
		try {
			int registros = (int)productoRepository.count();
			if (registrosPorPagina == 0 && registros == 0) {
				respuesta.setValor(1);
			} else {
				respuesta.setValor( (registros/registrosPorPagina) + (registros % registrosPorPagina == 0 ? 0 : 1) );
			}
			
			respuestaVO.setMensaje(String.format("Hay %d pagina/s", respuesta.getValor() ));
			respuestaVO.setCodigo("201");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	@Override
	@Transactional(readOnly=true)
	public ProductoVO findById(Integer idProducto) {
		respuestaVO = new ProductoVO(new ArrayList<Producto>(), "Ha ocurrido un error", "108");
		try {
			Producto producto = productoRepository.findById(idProducto).get();
			respuestaVO.getProductos().add(producto);
			respuestaVO.setMensaje(String.format("Se ha/n encontrado %d producto/s", null != producto ? 1 : 0 ));
			respuestaVO.setCodigo("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuestaVO;
	}

}
