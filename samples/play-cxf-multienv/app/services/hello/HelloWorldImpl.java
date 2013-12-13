package services.hello;

import javax.jws.WebService;

@WebService(endpointInterface = "services.hello.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHi(String text) {
        return "Hello " + text;
    }

    @Override
    public String abc(String word) {
        return word.toLowerCase();
    }
}
