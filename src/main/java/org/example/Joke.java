package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Joke {

    @Id
    @GeneratedValue
    long id ;
    private String category ;
    private String type ;
    @Column(columnDefinition = "CLOB") // Use CLOB data type for the_joke column
    private String theJoke ;
    @Column(columnDefinition = "CLOB") // Use CLOB data type for the_joke column
    private String setup ;
    @Column(columnDefinition = "CLOB") // Use CLOB data type for the_joke column
    private String delivery ;
    private boolean religious ;
    private boolean political ;
    private boolean racist ;
    private boolean sexist ;
    private boolean explicit ;
    private String language ;
    private long apiId ;

    public Joke(String category, String type, String theJoke, String setup, String delivery, boolean religious, boolean political, boolean racist, boolean sexist, boolean explicit, String language, long apiId) {
        this.category = category;
        this.type = type;
        this.theJoke = theJoke;
        this.setup = setup;
        this.delivery = delivery;
        this.religious = religious;
        this.political = political;
        this.racist = racist;
        this.sexist = sexist;
        this.explicit = explicit;
        this.language = language;
        this.apiId = apiId;
    }

    public Joke(){}
}
