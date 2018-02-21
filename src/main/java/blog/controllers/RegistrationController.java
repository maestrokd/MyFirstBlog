package blog.controllers;

import blog.core.registration.events.OnRegistrationCompleteEvent;
import blog.model.dto.UserDto;
import blog.model.entity.User;
import blog.model.entity.VerificationToken;
import blog.model.service.UserServiceImpl;
import blog.model.service.VerificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@RestController
public class RegistrationController {

    // Fields
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    VerificationTokenServiceImpl verificationTokenService;

    @Autowired
    private MessageSource messageSource;


    // Methods
    @RequestMapping(value = "/userregistration", method = RequestMethod.GET)
    public ModelAndView getUserRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = new UserDto();
        modelAndView.addObject("userDto", userDto);
        modelAndView.setViewName("all/users/userregistrationform");
        return modelAndView;
    }


    @RequestMapping(value = "/userregistration", method = RequestMethod.POST)
    public ModelAndView doUserRegistration(
            @ModelAttribute("userDto")
            @Valid
            UserDto userDto
            , BindingResult bindingResult
            , WebRequest webRequest
            , Locale locale
    ) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("all/users/userregistrationform", "userDto", userDto);
        }

        User registeredUser = userServiceImpl.registerNewUserAccount(userDto);

        if (registeredUser == null) {
            bindingResult.rejectValue("email", "message.regError");
        }

        try {
            String appUrl = webRequest.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, locale, appUrl));
        } catch (Exception e) {
            return new ModelAndView("newlogin", "customMessage", "Email registration was failed with " + userDto.getEmail());
        }
        return new ModelAndView("newlogin", "customMessage", "Please confirm registration of " + userDto.getEmail());
    }


    @RequestMapping(value = "/userregistrationConfirm", method = RequestMethod.GET)
    public ModelAndView doConfirmUserRegistration(
            WebRequest webRequest
            , @RequestParam("token") String token
    ){
        Locale locale = webRequest.getLocale();
        VerificationToken verificationToken = userServiceImpl.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messageSource.getMessage("auth.message.invalidToken", null, locale);
            return new ModelAndView("newlogin", "customMessage", message);
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            String messageValue = messageSource.getMessage("auth.message.expired", null, locale);
            verificationTokenService.deleteVerificationToken(verificationToken);
            return new ModelAndView("newlogin", "customMessage", messageValue);
        }
        user.setEnabled(true);
        userServiceImpl.updateUser(user);
        verificationTokenService.deleteVerificationToken(verificationToken);
        return new ModelAndView("newlogin", "customMessage", "User was registered");
    }

}
