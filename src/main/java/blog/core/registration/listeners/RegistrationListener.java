package blog.core.registration.listeners;

import blog.core.registration.events.OnRegistrationCompleteEvent;
import blog.model.entity.User;
import blog.model.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userServiceImpl.createVerificationToken(user, token);

        String recipientAddres = user.getEmail();

        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/userregistrationConfirm?token=" + token;
        String message = messageSource.getMessage("message.regSuccess", null, event.getLocale());

        String domain = messageSource.getMessage("property.domain", null, event.getLocale());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddres);
        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(message + " \r\n " + "http://localhost:8080" + confirmationUrl);
        simpleMailMessage.setText(message + " \r\n " + domain + confirmationUrl);
//        simpleMailMessage.setText("http://localhost:8080" + confirmationUrl);

        javaMailSender.send(simpleMailMessage);
    }
}
