package ejerciciosTema_4_5_6.demo.repository;


import ejerciciosTema_4_5_6.demo.entities.Ordenadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface LaptopRepository extends JpaRepository<Ordenadores, Long> {

}
