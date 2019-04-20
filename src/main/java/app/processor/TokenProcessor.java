package app.processor;

import app.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TokenProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws IOException {
        String jwtToken = exchange.getIn().getHeader("Authorization", String.class);
        System.out.println("------------ Decode JWT ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];

        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        System.out.println("JWT Header : " + header);


        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        ObjectMapper objectMapper = new ObjectMapper();
        Usuario usuario = objectMapper.readValue(body, Usuario.class);
        exchange.getOut().setBody(usuario);
        System.out.println("JWT Body : " + body);

    }
}
