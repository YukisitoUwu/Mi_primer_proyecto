package ejerciciosTema_4_5_6.demo;

import ejerciciosTema_4_5_6.demo.entities.Ordenadores;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ejerciciosTema_4_5_6.demo.repository.LaptopRepository;

import java.time.LocalDate;

//Ejercicio 1
//
//		Implementar los métodos CRUD en el API REST de Laptop creada en ejercicios anteriores.
//
//		Los métodos CRUD:
//
//		findAll()
//
//		findOneById()
//
//		create()
//
//		update()
//
//		delete()
//
//		deleteAll()
//
//		Ejercicio 2
//
//		Implementar swagger sobre el API REST de Laptop
//		y verificar que funciona en la URL: http://localhost:8081/swagger-ui/
//
//		Ejercicio 3
//
//		Crear casos de test para el controlador de Laptop desde Spring Boot. Con click derecho
//		dentro del código de la clase LaptopController utilizar la opción Generate > Test para crear la clase
//		con todos los tests unitarios e implementarlos siguiente el proceso visto en clase.
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

	// CRUD
	// crear libro
	Ordenadores ord1 = new Ordenadores(null, "Homo Deus", "Yuval Noah", 450, 29.99, LocalDate.of(2018, 12, 1), true);
	Ordenadores ord2 = new Ordenadores(null, "Homo Sapiens", "Yuval Noah", 450, 19.99, LocalDate.of(2013, 12, 1), true);
	// almacenar un libro
		System.out.println("Num laptos en base de datos: " + repository.findAll().size());

		repository.save(ord1);
		repository.save(ord2);

	// recuperar todos los libros
		System.out.println("Num laptops en base de datos: " + repository.findAll().size());

	// borrar un libro
	// repository.deleteById(1L);

		System.out.println("Num laptos en base de datos: " + repository.findAll().size());
}
}
