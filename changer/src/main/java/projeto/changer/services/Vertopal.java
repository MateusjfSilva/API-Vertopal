package projeto.changer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Vertopal {

    @Autowired
    private RestTemplate restTemplate;
    private String data;
    private MultipartFile file;

    public Vertopal(String data, MultipartFile file) {
        this.data = data;
        this.file = file;
    }

    public String enviarArquivo(Vertopal vertopal){
        String url = "https://api.vertopal.com/v1/upload/file";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, vertopal, String.class);
        return responseEntity.getBody();
    }
}
