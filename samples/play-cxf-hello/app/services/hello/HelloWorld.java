package services.hello;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
    String abc(String word);
}
