package alexey.odinochenko.booksstorageauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.security.SecureRandom;

@SpringBootApplication
public class BooksStorageAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksStorageAuthApplication.class, args);
    }

}
