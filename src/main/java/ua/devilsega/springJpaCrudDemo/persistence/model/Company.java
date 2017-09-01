package ua.devilsega.springJpaCrudDemo.persistence.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies", schema = "public")
public class Company {
    private Company(){}

    public Company(String name, Timestamp dateAdded){
        setName(name);
        setDateAdded(dateAdded);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date_added")
    private Timestamp dateAdded;

    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "companies_developers", schema = "public",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("companies")
    private Set<Developer> developers = new HashSet<>();

    //-------------setters/getters-----------------

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Timestamp getDateAdded(){
        return dateAdded;
    }

    public Set<Developer> getDevelopers(){
        return developers;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setDateAdded(Timestamp dateAdded){
        this.dateAdded = dateAdded;
    }

    public void setDevelopers(Set<Developer>devs){
        developers.addAll(devs);
    }
}