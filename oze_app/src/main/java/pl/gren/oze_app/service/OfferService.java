package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.offer.Credit;
import pl.gren.oze_app.model.offer.FinancialProgram;
import pl.gren.oze_app.model.offer.Offer;
import pl.gren.oze_app.repository.OfferRepository;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CreditService creditService; // Assuming CreditService exists


    // Assuming a method to create an Offer object with Credit and FinancialProgram details
//    public Offer createOffer(boolean creditYes, Credit credit, FinancialProgram financialProgram) {
//        Offer offer = new Offer(creditYes, creditService.calculateCreditCost(credit), financialProgram);
//        return offerRepository.save(offer);
//    }

    // Other methods for Offer functionalities can be added here
}