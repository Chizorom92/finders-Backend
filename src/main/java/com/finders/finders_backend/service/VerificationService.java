package com.finders.finders_backend.service;

import com.finders.finders_backend.model.Property;
import com.finders.finders_backend.model.VerificationCheck;
import com.finders.finders_backend.repository.PropertyRepository;
import com.finders.finders_backend.repository.VerificationCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationService {

    @Autowired
    private VerificationCheckRepository verificationCheckRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    // Called by an admin endpoint when they tick/untick any box on the
    // 5-point checklist. Recalculates risk_level every time.
    public VerificationCheck updateChecklist(Long propertyId, VerificationCheck updatedChecks) {

        // Finds the existing check record for this property, or create
        // a new one if this is the first time it's being verified.
        VerificationCheck check = verificationCheckRepository
                .findByPropertyId(propertyId)
                .orElseGet(() -> {
                    VerificationCheck newCheck = new VerificationCheck();
                    Property property = propertyRepository.findById(propertyId)
                            .orElseThrow(() -> new IllegalArgumentException("Property not found"));
                    newCheck.setProperty(property);
                    return newCheck;
                });

        check.setAddressChecked(updatedChecks.getAddressChecked());
        check.setPropertyExistenceChecked(updatedChecks.getPropertyExistenceChecked());
        check.setAgentVerified(updatedChecks.getAgentVerified());
        check.setOwnerAuthorizationChecked(updatedChecks.getOwnerAuthorizationChecked());
        check.setListingReviewed(updatedChecks.getListingReviewed());
        check.setCheckedAt(LocalDateTime.now());

        VerificationCheck saved = verificationCheckRepository.save(check);

        // This is the actual "anti-scam" logic: counts how many boxes
        // are ticked, then set's the property's risk_level accordingly.
        applyRiskLevel(saved);

        return saved;
    }

    private void applyRiskLevel(VerificationCheck check) {
        int checkedCount = countTrue(
                check.getAddressChecked(),
                check.getPropertyExistenceChecked(),
                check.getAgentVerified(),
                check.getOwnerAuthorizationChecked(),
                check.getListingReviewed()
        );

        Property property = check.getProperty();

        if (checkedCount == 5) {
            property.setRiskLevel(Property.RiskLevel.LOW);
        } else if (checkedCount >= 2) {
            property.setRiskLevel(Property.RiskLevel.MEDIUM);
        } else {
            property.setRiskLevel(Property.RiskLevel.HIGH);
        }

        propertyRepository.save(property);
    }

    private int countTrue(Boolean... values) {
        int count = 0;
        for (Boolean value : values) {
            if (Boolean.TRUE.equals(value)) {
                count++;
            }
        }
        return count;
    }
}