package at.htl.recap.control;

import at.htl.recap.entity.Vehicle;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VehicleRepository implements PanacheRepository<Vehicle> {
    public Vehicle save(Vehicle vehicle) {
        return getEntityManager().merge(vehicle);

    }
}
