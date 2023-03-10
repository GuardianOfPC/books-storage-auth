package alexey.odinochenko.booksstorageauth.repository;

import alexey.odinochenko.booksstorageauth.data.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String> {
}
