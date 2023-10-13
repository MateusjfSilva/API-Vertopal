package projeto.changer.services;

import java.time.LocalDateTime;

public class Output {
    private String connector;
    private String name;
    private Long size;
    private String expiresAt;
    private String status;
    private int duration;
    private String Url;

    public Output(String connector, String name, Long size, String expiresAt) {
        this.connector = connector;
        this.name = name;
        this.size = size;
        this.expiresAt = expiresAt;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
