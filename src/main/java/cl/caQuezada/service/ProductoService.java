package cl.caQuezada.service;

import cl.caQuezada.model.Producto;
import cl.caQuezada.vo.NumberVO;
import cl.caQuezada.vo.ProductoVO;

public interface ProductoService {

	public ProductoVO getAllProductos();
	public ProductoVO findByNombre(String nombre);
	public ProductoVO findById(Integer idProducto);
	public ProductoVO add(Producto producto);
	public ProductoVO edit(Producto producto);
	public ProductoVO delete(Producto producto);
	public ProductoVO getPage(Integer pagina, Integer cantidad);
	public NumberVO getPageCount(int registrosPorPagina);
}
