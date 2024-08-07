package employees;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class EmployeesRepository implements PanacheRepositoryBase<Employee, Long> {

    public Stream<Employee> findEmployeesByPrefix(String prefix) {
        // language=jpaql
        return find("select e from Employee e where e.name like ?1",
                Optional.ofNullable(prefix).orElse("") + "%")
                .stream();
    }

    public Optional<Employee> findEmployeeByName(String name) {
        // language=jpaql
        return find("select e from Employee e where e.name = ?1", name).firstResultOptional();
    }
}
