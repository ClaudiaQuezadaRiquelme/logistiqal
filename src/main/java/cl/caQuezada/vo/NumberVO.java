package cl.caQuezada.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // lombok necesita esta notación para constituir el objeto con extensión
public class NumberVO extends GenericVO {

	public int valor; // num de página

	public NumberVO(int valor, String mensaje, String codigo) {
		super(mensaje, codigo);
		this.valor = valor;
	}
	
}
