package projeto.changer.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import projeto.changer.services.Response;

@RestController
@RequestMapping("/teste")
public class VertopalController {
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${api.access.token}")
    private String accessToken;
    @Value("${api.data.app}")
    private String app;

    Gson gson = new Gson();
    private Response respostaJson;

    @PostMapping("/upload")
    public String enviar(@RequestParam("file") MultipartFile file){
        if (file != null && !file.isEmpty()) {
            String apiUrl = "https://api.vertopal.com/v1/upload/file";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + accessToken);

            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            data.add("data", ("{\"app\":\"%s\"}").formatted(app));
            data.add("file", file.getResource());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            respostaJson = gson.fromJson(responseEntity.getBody(), Response.class);
            System.out.println(respostaJson.getResult().getSaida().getConnector());
            return responseEntity.getBody();
        } else {
            return "Arquivo não selecionado";
        }
    }

    @PostMapping("/converter")
    public String converter(@RequestParam("ext") String ext) {
        if (ext != null && !ext.isEmpty()) {
            String apiUrl = "https://api.vertopal.com/v1/convert/file";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Authorization", "Bearer " + accessToken);

            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            data.add("data", ("{\n" +
                    "\"app\":\"%s\", \n" +
                    "\"connector\":\"%s\",\n" +
                    "\"parameters\": {\n" +
                    "        \"output\": \"%s\"\n" +
                    "    }\n" +
                    "}").formatted(app, respostaJson.getResult().getSaida().getConnector(), ext));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            respostaJson = gson.fromJson(responseEntity.getBody(), Response.class);
            System.out.println(respostaJson.getResult().getSaida().getConnector());
            return responseEntity.getBody();
        } else {
            return "Arquivo não selecionado";
        }
    }
}
