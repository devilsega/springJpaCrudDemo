package ua.devilsega.springJpaCrudDemo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.devilsega.springJpaCrudDemo.persistence.model.Developer;

import java.util.Set;

public interface DeveloperRepo extends JpaRepository<Developer, Long>{
    Set<Developer> findByName(String name);
}
