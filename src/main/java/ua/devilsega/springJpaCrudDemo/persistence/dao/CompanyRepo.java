package ua.devilsega.springJpaCrudDemo.persistence.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.devilsega.springJpaCrudDemo.persistence.model.Company;

import java.util.Set;

public interface CompanyRepo extends JpaRepository<Company, Long>{
    Set<Company> findByName(String name);
}
