package at.htl.recap;

import at.htl.recap.entity.Vehicle;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class VehicleRepositoryTest {

    @Inject
    VehicleRepository vehicleRepository;

    @Inject
    AgroalDataSource ds;

    @BeforeEach
    void setUp() {
        vehicleRepository.deleteAll();
    }

    @Test
    void t010_insertSimpleVehicle() {

        Vehicle vehicle = new Vehicle("Opel", "Kapitän", 1972);
        vehicleRepository.persist(vehicle);

        Table table = new Table(ds, "V_VEHICLE");
        output(table).toConsole();
        assertThat(table).hasNumberOfRows(1)
                .row()
                .column("V_ID").value().isGreaterThanOrEqualTo(1000)
                .column("V_BRAND").value().isEqualTo("Opel")
                .column("V_MODEL").value().isEqualTo("Kapitän");
    }
}