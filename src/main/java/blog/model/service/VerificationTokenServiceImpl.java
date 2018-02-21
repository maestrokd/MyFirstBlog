package blog.model.service;

import blog.model.entity.VerificationToken;
import blog.model.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl {

    // Fields
    private VerificationTokenRepository verificationTokenRepository;


    // Setters
    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }


    // Methods
    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
