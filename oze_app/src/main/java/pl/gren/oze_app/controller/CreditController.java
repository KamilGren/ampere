package pl.gren.oze_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gren.oze_app.model.offer.Credit;
import pl.gren.oze_app.model.offer.FinancialProgram;
import pl.gren.oze_app.repository.CreditRepository;
import pl.gren.oze_app.service.CreditService;
import pl.gren.oze_app.service.FinancialProgramService;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/credits")
public class CreditController {

    private CreditService creditService;

    private CreditRepository creditRepository;

    @Autowired
    public CreditController(CreditService creditService, CreditRepository creditRepository) {
        this.creditService = creditService;
        this.creditRepository = creditRepository;
    }

    @PostMapping("/calculate-credit")
    public String calculateCredit(@ModelAttribute Credit credit, Model model) {

        credit.setNumberOfLoanInstallmentsInYear(12.0);
        creditService.calculateCreditCost(credit);
        creditRepository.save(credit);

        model.addAttribute("creditForm", credit);

        // wszelkie obliczenia oraz zapisanie kredytu w repozytorium
        System.out.println("Wielkość kredytu: " + credit.getCreditAmount());
        System.out.println("RRSO: " + credit.getRRSO());
        System.out.println("Koszt kredytu: " + credit.getCreditCost());
        System.out.println("WIelkość raty: " + credit.getLoanInstallmentAmount());

      return "offer/credit-informations";
    }

    @GetMapping("/get/{id}")
    public String getCreditInformation(@PathVariable Long id, Model model) {

        Credit credit = creditRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Brak kredytu z takimi ID"));

        model.addAttribute("creditForm", credit);

        return "offer/credit-informations";
    }

    @GetMapping("/show")
    public String showCreditInformations(Model model) {
        model.addAttribute("creditForm", new Credit());

        return "offer/credit-informations";
    }

}
