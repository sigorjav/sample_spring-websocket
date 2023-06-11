package com.sigorjav.sample_springwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    /**
     * @MessageMapping
     * 위 어노테이션을 선언하면 WebsocketConfiguration에 선언된
     * setApplicationDestinationPrefixes 뒤에 붙기 때문에
     * 외부에서는 /app/hello 엔트리 포인트를 사용하여 통신을 할수 있게 된다.
     */
    @MessageMapping("/hello")
    /**
     * @SendTo
     * 여기에서 사용되는 /topic은 WebsocketConfiguration에서 enableSimpleBroker에 정의된 토픽으로 통신을 하게 된다.
     * 따라서 greet 메소드는 /app/hello로 받은 메세지를 /topic/greetings 로 전달해주는 브로커 컨트롤러 메소드를 생성한 것이다.
     */
    @SendTo("/topic/greetings")
    public Greeting greet(HelloMessage message) throws InterruptedException {
//        Thread.sleep(2000);
        return new Greeting("Hello " + HtmlUtils.htmlEscape(message.getName()));
    }
}
