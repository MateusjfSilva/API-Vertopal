package projeto.changer.services;

import org.springframework.web.multipart.MultipartFile;

public class Vertopal {
    private String data;
    private MultipartFile file;

    public Vertopal(String data, MultipartFile file) {
        this.data = data;
        this.file = file;
    }
}
