package cl.caQuezada.model;

// JPA
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
// @SequenceGenerator(name="SQ_PRODUCTO", initialValue=1, allocationSize=1, sequenceName="SQ_PRODUCTO") // ORACLE
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_PRODUCTO") // ORACLE
	private Integer idProducto;
	private String codigoUnico;
	private String nombreProducto;
	private Integer precioProducto;
	private Integer stockProducto;
}
