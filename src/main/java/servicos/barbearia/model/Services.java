package servicos.barbearia.model;

import jakarta.persistence.*;
import jdk.jfr.Description;

@Entity
@Table(name= "table_services_barbearia")
public class Services {

    private long id;
    private String ServiceName;
    private String ServiceDescription;

    public Services() {

    }

    public Services(String ServiceName, String ServiceDescription) {
        this.ServiceName = ServiceName;
        this.ServiceDescription = ServiceDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ServiceName")
    public String getServiceName() {
        return ServiceName;
    }
    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    @Column(name = "ServiceDescription")
    public String getServiceDescription() {
        return ServiceDescription;
    }
    public void setServiceDescription(String ServiceDescription) {
        this.ServiceDescription = ServiceDescription;
    }

}
