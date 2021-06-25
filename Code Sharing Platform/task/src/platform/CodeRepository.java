package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {

    Code findByUniqueId(String uuid);

    List<Code> findAll();
}
