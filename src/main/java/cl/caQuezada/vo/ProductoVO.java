package cl.caQuezada.vo;

import java.util.List;

import cl.caQuezada.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // lombok necesita esta notación para constituir el objeto con extensión
public class ProductoVO extends GenericVO {

	List<Producto> productos;

	public ProductoVO(List<Producto> productos, String mensaje, String codigo) {
		super(mensaje, codigo); // pasa parámetros a GenericVO
		this.productos = productos;
	}
	
}
