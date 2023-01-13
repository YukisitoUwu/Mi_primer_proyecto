package ejerciciosTema_4_5_6.demo.controllers;


import ejerciciosTema_4_5_6.demo.entities.Ordenadores;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ejerciciosTema_4_5_6.demo.repository.LaptopRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;


@RestController
public class LaptopController {


    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository laptopRepository;




    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/ordenadores")
    public List<Ordenadores> findAll(){

        return laptopRepository.findAll();
    }

    @GetMapping("/api/ordenadores/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")
    public ResponseEntity<Ordenadores> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id){

        Optional<Ordenadores> ordOpt = laptopRepository.findById(id); // 3456546456435
        // opcion 1
        if(ordOpt.isPresent())
            return ResponseEntity.ok(ordOpt.get());
        else
            return ResponseEntity.notFound().build();

        // opcion 2
//        return bookOpt.orElse(null);
        // return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/ordenadores")
    public ResponseEntity<Ordenadores> create(@RequestBody Ordenadores ordenadores, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        // guardar el libro recibido por parámetro en la base de datos
        if(ordenadores.getId() != null){ // quiere decir que existe el id y por tanto no es una creación
            log.warn("trying to create a ord with id");
            System.out.println("trying to create a ord with id");
            return ResponseEntity.badRequest().build();
        }
        Ordenadores result = laptopRepository.save(ordenadores);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    /**
     * actualizar un libro existente en base de datos
     */
    @PutMapping("/api/ordenadores")
    public ResponseEntity<Ordenadores> update(@RequestBody Ordenadores ordenadores){
        if(ordenadores.getId() == null ){ // si no tiene id quiere decir que sí es una creación
            log.warn("Trying to update a non existent ord");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(ordenadores.getId())){
            log.warn("Trying to update a non existent ord");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización
        Ordenadores result = laptopRepository.save(ordenadores);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }

    @DeleteMapping("/api/ordenadores/{id}")
    public ResponseEntity<Ordenadores> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/ordenadores")
    public ResponseEntity<Ordenadores> deleteAll(){
        log.info("REST Request for delete all books");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    /**
    * Crear un nuevo ordenador en base de datos
     * Método POST, no colisiona con findAll porque son diferentes métodos HTTP: GET vs. POST
     * @param ordenadores
     * @param headers
     * @return
             */

}
