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
    private RestTemplate restTemplate;
    @Value("${api.access.token}")
    private String accessToken;

    @PostMapping("/upload")
    public String enviarArquivo(@RequestParam("data") String data, @RequestParam("file") MultipartFile file){
        if (file != null && !file.isEmpty()) {
            String apiUrl = "https://api.vertopal.com/v1/upload/file";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + accessToken);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("data", data);
            body.add("file", new HttpEntity<>(file.getResource(), headers));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
            return response.getBody();
        }
        return "Arquivo não selecionado";
    }

}
