package projeto.changer.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import projeto.changer.services.Vertopal;

@RestController
@RequestMapping("/teste")
public class VertopalController {

    @Autowired
    private Vertopal vertopal;
    @Value("${api.access.token}")
    private String accessToken;

    @PostMapping("/upload")
    public ResponseEntity enviarArquivo(@RequestParam("data") String data, @RequestParam("file") MultipartFile file){
        if (file != null && !file.isEmpty()) {
            String apiUrl = "https://api.vertopal.com/v1/upload/file";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer" + accessToken);

            Vertopal vertopal = new Vertopal(data, file);
            vertopal.enviarArquivo(this.vertopal);
            HttpEntity<Vertopal> requestEntity = new HttpEntity<>(vertopal, headers);

            return ResponseEntity.status(200).body(vertopal);
        }
        return ResponseEntity.status(400).build();
    }

}
