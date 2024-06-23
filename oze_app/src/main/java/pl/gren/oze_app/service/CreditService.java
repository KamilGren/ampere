package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.offer.Credit;
import pl.gren.oze_app.oldrepository.CreditRepository;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    public void calculateCreditCost(Credit credit) {
        // Calculate RRSO if it's not set
        if (credit.getRRSO() == null) {
            credit.setRRSO(getRRSOByBank(credit.getBank())); // Implement logic to fetch RRSO based on bank name (replace with your implementation)
        }

        System.out.println("Co jest grane: ");
        System.out.println("rrso: " + credit.getRRSO());
        System.out.println("ile rat w roku: " + credit.getNumberOfLoanInstallmentsInYear());
        System.out.println("Potegi: " + Math.pow(credit.getNumberOfLoanInstallmentsInYear(), credit.getNumberOfLoanInstallmentsAll()));
        System.out.println("obliczenia nr1: " + credit.getCreditAmount() * (credit.getRRSO() / 100.0));
        System.out.println("Potegi: " + Math.pow(credit.getNumberOfLoanInstallmentsInYear(), credit.getNumberOfLoanInstallmentsAll()) );

        // Calculate loan installment amount
        credit.setLoanInstallmentAmount(Math.round((credit.getCreditAmount() * (credit.getRRSO() / 100.0)) / (credit.getNumberOfLoanInstallmentsInYear() * (1 - (Math.pow(credit.getNumberOfLoanInstallmentsInYear(), credit.getNumberOfLoanInstallmentsAll()) / (Math.pow((credit.getNumberOfLoanInstallmentsInYear() + (credit.getRRSO() / 100.0)), credit.getNumberOfLoanInstallmentsAll())))) * 100.0)) / 100.0);

        // Calculate credit cost
        credit.setCreditCost(Math.round(((credit.getLoanInstallmentAmount() * credit.getNumberOfLoanInstallmentsAll()) - credit.getCreditAmount()) * 100.0) / 100.0);

    }

    private Double getRRSOByBank(String bank) {
        // Implement your logic to fetch RRSO based on bank name (replace with your actual implementation)
        // This example uses a switch statement for demonstration purposes
        switch (bank.toUpperCase()) {
            case "ALIOR BANK":
                return 10.01;
            case "BOŚ BANK":
                return 10.49;
            case "CREDIT AGRICOLE":
                return 10.91;
            case "PKO BP":
                return 12.46;
            case "BNP PARIBAS":
                return 13.23;
            default:
                System.out.println("Invalid bank name.");
                return 0.0;
        }
    }
}



