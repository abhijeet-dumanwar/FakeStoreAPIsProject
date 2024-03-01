package projects.javasampleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailEventDTO {
    private String to;
    private String from;
    private String subject;
    private String body;
}
