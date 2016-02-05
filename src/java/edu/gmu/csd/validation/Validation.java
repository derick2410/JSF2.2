/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.csd.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Derick Augustine Coutinho
 */
@ManagedBean
@RequestScoped
public class Validation {

    public void validateName(FacesContext context, UIComponent componentToValidate,
            Object value) throws ValidationException {
        String nameValue = (String) value;
        String onlyAlpha = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(onlyAlpha);
        Matcher matcher = pattern.matcher(nameValue);
        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Your first name should contain only alphabets.");
            throw new ValidatorException(message);
        }
    }

    public void validateTelephoneNumber(FacesContext context, UIComponent componentToValidate,
            Object value) throws ValidationException {
        String telPhoneNum = (String) value;
        //String usTelPhonePattern = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$"; // (xxx)-xxx-xxxx
        String usTelPhonePattern = "\\([1-9][\\d]{2}\\)-[\\d]{3}-[\\d]{4}";

        Pattern pattern = Pattern.compile(usTelPhonePattern);
        Matcher matcher = pattern.matcher(telPhoneNum);

        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Your phone number should be of the following format: (xxx)-xxx-xxxx.");
            throw new ValidatorException(message);
        }
    }

    public void validateEmailAddress(FacesContext context, UIComponent componentToValidate,
            Object value) throws ValidationException {
        boolean isEmailValid = false;
        String emailAddress = (String) value;

        if (emailAddress == null) {
            isEmailValid = false;
        }

        try {
            InternetAddress emailAddr = new InternetAddress(emailAddress);
            emailAddr.validate();
            isEmailValid = true;
        } catch (AddressException ex) {
            isEmailValid = false;
        }

        if (!isEmailValid) {
            FacesMessage message = new FacesMessage("Your email address should be of valid format.");
            throw new ValidatorException(message);
        }
    }

    public void validateDateOfSurvey(FacesContext context, UIComponent componentToValidate,
            Object value) throws ValidationException {
        boolean isDateValid = false;
        String dateOfSurvey = (String) value;

        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        sdf.setLenient(false);

        try {
            sdf.parse(dateOfSurvey);
            isDateValid = true;
        } catch (ParseException pexp) {
            pexp.printStackTrace();
            isDateValid = false;
        }

        if (!isDateValid && StringUtils.isNotEmpty(dateOfSurvey)) {
            FacesMessage message = new FacesMessage("Date of survey should be of valid format.");
            throw new ValidatorException(message);
        }
    }
}
