package at.htl.recap.control;

import at.htl.recap.entity.Register;
import at.htl.recap.entity.Vehicle;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class RegisterRepositoryTest {

    @Inject
    RegisterRepository registerRepository;

    @Inject
    AgroalDataSource ds;

    @Test
    void t010_simpleInsert_Ok() {
        Vehicle opel = new Vehicle("Opel", "Kadett", 1974);
        Register bugsBunny = new Register("Bugs Bunny", "LL-123ABC", opel);

        registerRepository.persist(bugsBunny);

        Table registerTable = new Table(ds, "V_REGISTER");
        output(registerTable).toConsole();

        assertThat(registerTable).row()
                .column("R_ID").value().isGreaterThanOrEqualTo(5000)
                .column("R_LICENSE_NO").value().isEqualTo("LL-123ABC")
                .column("R_OWNER").value().isEqualTo("Bugs Bunny")
                .column("R_REGISTER_DATE").value().isEqualTo(
                        LocalDate.now().format(
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        )
                )
                .column("R_SIGN_OFF_DATE").value().isNull();

        Request vehicleRequest = new Request(ds, "select V_ID, V_BRAND, V_CONSTRUCTION_YEAR, V_MODEL from V_VEHICLE where V_ID = " + opel.getId());
        output(vehicleRequest).toConsole();
    }
}