package cl.caQuezada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.caQuezada.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer>, PagingAndSortingRepository<Producto, Integer> {

	// @Query(value = "SELECT FROM PRODUCTO WHERE nombre_producto LIKE '%?1%'", nativeQuery=true) // forma NATIVE
	@Query("FROM Producto WHERE nombre_producto LIKE %?1%") // forma JPQL. No es necesario poner SELECT * antes de FROM porque va por defecto
	public List<Producto> findByNombre(String nombre);
}
