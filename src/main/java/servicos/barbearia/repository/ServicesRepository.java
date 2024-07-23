package servicos.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import servicos.barbearia.model.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

}
