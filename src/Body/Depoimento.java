package Body;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Depoimento {
    
    private String depoimento;
    private LocalDateTime dateTimeSent;


    public Depoimento(){
        this.dateTimeSent = LocalDateTime.now();
    }


    public String getDepoimento() {
            return depoimento;
        }

    public void setDepoimento(String depoimento) {
        this.depoimento = depoimento;
    }

    public LocalDateTime getDateTimeSent() {
        return dateTimeSent;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.dateTimeSent.format(formatter);
    }

}
