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
public class CompanyCrud {
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private DeveloperRepo developerRepo;

    public boolean createNewCompany(String name){
        try {
            Company company = new Company(name,new Timestamp(System.currentTimeMillis()));
            companyRepo.saveAndFlush(company);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean setDeveloper (long companyId, long developerId){
        try {
            Developer developer = developerRepo.findOne(developerId);
            Company company = companyRepo.findOne(companyId);
            if (developer==null||company==null){
                return false;
            }
            Set<Developer> devs = new HashSet<>();
            devs.add(developer);
            company.setDevelopers(devs);
            companyRepo.saveAndFlush(company);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean deleteCompany(long companyId){
        try {
            Company company = companyRepo.findOne(companyId);
            companyRepo.delete(company);
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Company getCompany(long companyId){
        return companyRepo.findOne(companyId);
    }

    public Map<Long, String> getAll(){
        List<Company> companies = companyRepo.findAll();
        LinkedHashMap<Long,String> result = new LinkedHashMap<>();
        for (Company item: companies) {
            result.put(item.getId(),item.getName());
        }
        return result;
    }
}