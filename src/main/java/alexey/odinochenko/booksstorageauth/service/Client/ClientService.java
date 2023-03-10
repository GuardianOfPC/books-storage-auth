package alexey.odinochenko.booksstorageauth.service.Client;

import alexey.odinochenko.booksstorageauth.exception.RegistrationException;

import javax.security.auth.login.LoginException;

public interface ClientService {
    void register(String clientId, String clientSecret) throws RegistrationException;
    void checkCredentials(String clientId, String clientSecret) throws LoginException;
}
