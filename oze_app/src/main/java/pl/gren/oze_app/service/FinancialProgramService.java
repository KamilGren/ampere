package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.offer.FinancialProgram;
import pl.gren.oze_app.repository.FinancialProgramRepository;

@Service
public class FinancialProgramService {

    @Autowired
    private FinancialProgramRepository financialProgramRepository;

    public FinancialProgram getFinancialProgramByName(String name) {
        return financialProgramRepository.findByName(name);
    }

    // Additional methods for FinancialProgram functionalities can be added here (e.g., create, update, delete)
}
