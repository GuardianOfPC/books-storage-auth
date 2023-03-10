package alexey.odinochenko.booksstorageauth.controller;

import alexey.odinochenko.booksstorageauth.data.model.Client;
import alexey.odinochenko.booksstorageauth.data.response.ErrorResponse;
import alexey.odinochenko.booksstorageauth.data.response.TokenResponse;
import alexey.odinochenko.booksstorageauth.exception.RegistrationException;
import alexey.odinochenko.booksstorageauth.service.Client.ClientService;
import alexey.odinochenko.booksstorageauth.service.Token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody Client client) throws RegistrationException {
        clientService.register(client.getClientId(), client.getClientSecret());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody Client client) throws LoginException {
        clientService.checkCredentials(
                client.getClientId(), client.getClientSecret());
        return new TokenResponse(
                tokenService.generateToken(client.getClientId()));
    }

    @ExceptionHandler({RegistrationException.class, LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
