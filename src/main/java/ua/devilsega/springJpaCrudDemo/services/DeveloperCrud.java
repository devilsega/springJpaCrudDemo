package ua.devilsega.springJpaCrudDemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.devilsega.springJpaCrudDemo.persistence.model.Company;
import ua.devilsega.springJpaCrudDemo.persistence.dao.CompanyRepo;
import ua.devilsega.springJpaCrudDemo.persistence.dao.DeveloperRepo;
import ua.devilsega.springJpaCrudDemo.persistence.model.Developer;

import java.sql.Timestamp;
import java.util.*;

@Service
public class DeveloperCrud {
    @Autowired
    private DeveloperRepo developerRepo;
    @Autowired
    private CompanyRepo companyRepo;

    public boolean createNewDeveloper(String name, double salary,String city){
        try {
            Developer developer = new Developer(name,salary,city,new Timestamp(System.currentTimeMillis()));
            developerRepo.saveAndFlush(developer);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean setCompany (long developerId, long companyId){
        try {
            Company company = companyRepo.findOne(companyId);
            Developer developer = developerRepo.findOne(developerId);
            if (company==null||developer==null){
                return false;
            }
            Set<Company> comps = new HashSet<>();
            comps.add(company);
            developer.setCompanies(comps);
            developerRepo.saveAndFlush(developer);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean deleteDeveloper(long developerId){
        try {
            Developer developer = developerRepo.findOne(developerId);
            developerRepo.delete(developer);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public Developer getDeveloper(long developerId){
        return developerRepo.findOne(developerId);
    }

    public Map<Long, String> getAll(){
        List<Developer> developers = developerRepo.findAll();
        LinkedHashMap<Long,String> result = new LinkedHashMap<>();
        for (Developer item: developers) {
            result.put(item.getId(),item.getName());
        }
        return result;
    }
}
