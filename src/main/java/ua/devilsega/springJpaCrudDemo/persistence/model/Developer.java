package ua.devilsega.springJpaCrudDemo.persistence.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developers", schema = "public")
public class Developer {
    private Developer(){}

    public Developer(String name, double salary, String city, Timestamp dateAdded){
        setName(name);
        setSalary(salary);
        setCity(city);
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
    @Column(name = "salary")
    private double salary;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "date_added")
    private Timestamp dateAdded;

    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "companies_developers", schema = "public",
            joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("developers")
    private Set<Company> companies = new HashSet<>();

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

    public double getSalary(){
        return salary;
    }

    public String getCity(){
        return city;
    }

    public Set<Company>getCompanies(){
        return companies;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setDateAdded (Timestamp dateAdded){
        this.dateAdded = dateAdded;
    }

    private void setSalary (double salary){
        this.salary = salary;
    }

    private void setCity (String city){
        this.city = city;
    }

    public void setCompanies(Set<Company>comps){
        companies.addAll(comps);
    }
}