package servicos.barbearia.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicos.barbearia.exception.ResourceNotFoundException;
import servicos.barbearia.model.Services;
import servicos.barbearia.repository.ServicesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/")
public class ServicesController {

    Logger logger= LogManager.getLogger(ServicesController.class);

    @Autowired
    private ServicesRepository servicesRepository;

    //get services
    @GetMapping("/services")
    public List<Services> getAllServices(){
        logger.info(" List<Services>:getAllServices 'Pegar todos os serviços está em execução.' ");
        return this.servicesRepository.findAll();
    }

    //get services by id
    @GetMapping("/services/{id}")
    public ResponseEntity<Services> getServicesById(@PathVariable(value = "id") Long servicesId)
            throws ResourceNotFoundException {
        Services services = servicesRepository.findById(servicesId)
                .orElseThrow(() -> new ResourceNotFoundException("Services not found for this id :: " + servicesId));
        logger.info("getServicesById 'Buscar serviços por ID está em execução.' ", services);
        return ResponseEntity.ok().body(services);
    }

    //save services
    @PostMapping("/services/save")
    public Services createServices(@RequestBody Services services){
    logger.info("createServices 'Criar serviços está em execução.' ", services);
        return this.servicesRepository.save(services);
    }

    //update services
    @PutMapping("/services/update/{id}")
    public ResponseEntity<Services> updateServices(@PathVariable(value = "id") Long servicesId,
                                                   @RequestBody Services ServicesDetails) throws ResourceNotFoundException {
        Services services = servicesRepository.findById(servicesId)
                .orElseThrow(() -> new ResourceNotFoundException("Services not found for this id :: " + servicesId));

        services.setServiceName(ServicesDetails.getServiceName());
        services.setServiceDescription(ServicesDetails.getServiceDescription());
        final Services updatedServices = servicesRepository.save(services);
        logger.info("updateServices 'Atualizar serviços por ID está em execução.' ", services);
        return ResponseEntity.ok(updatedServices);
    }

    //delete services
    @DeleteMapping("/services/delete/{id}")
    public Map<String, Boolean> deleteServices(@PathVariable(value = "id") Long servicesId)
            throws ResourceNotFoundException {
        Services services = servicesRepository.findById(servicesId)
                .orElseThrow(() -> new ResourceNotFoundException("Services not found for this id :: " + servicesId));

        servicesRepository.delete(services);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}