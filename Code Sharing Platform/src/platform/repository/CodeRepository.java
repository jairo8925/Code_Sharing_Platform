package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.Code;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<Code, String> {

    Code findByCodeId(String codeId);

    List<Code> findAll();

    boolean existsByCodeId(String codeId);

    List<Code> findByTimeRestrictedFalseAndViewsRestrictedFalse();
}
