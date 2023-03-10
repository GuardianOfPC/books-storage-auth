package alexey.odinochenko.booksstorageauth.data.model;

import lombok.Value;

@Value
public class Client {
    String clientId;
    String clientSecret;
}
