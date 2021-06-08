package at.htl.recap.control;

import at.htl.recap.entity.Register;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RegisterRepository implements PanacheRepository<Register> {


}
