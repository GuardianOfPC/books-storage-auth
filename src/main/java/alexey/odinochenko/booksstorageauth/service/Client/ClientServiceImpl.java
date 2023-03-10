package alexey.odinochenko.booksstorageauth.service.Client;

import alexey.odinochenko.booksstorageauth.exception.RegistrationException;
import alexey.odinochenko.booksstorageauth.data.entity.ClientEntity;
import alexey.odinochenko.booksstorageauth.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public void register(String clientId, String clientSecret) throws RegistrationException {
        if(clientRepository.findById(clientId).isPresent())
            throw new RegistrationException(
                    "Client with id: " + clientId + " already registered");

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        clientRepository.save(new ClientEntity(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) throws LoginException {
        Optional<ClientEntity> optionalUserEntity = clientRepository
                .findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + clientId + " not found");

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
