/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.csd.bean;

import edu.gmu.csd.processor.DataProcessor;
import edu.gmu.csd.services.StudentService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Derick Augustine Coutinho
 */
@ManagedBean
@RequestScoped
public class Student {

    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String telNumber;
    private String email;
    private String dateOfSurvey;
    private String[] campusLike;
    private String stringAppendedCampusLike;
    private String universityInterest;
    private String schoolRecommend;
    private String raffle;
    private String addComm;

    private final String schoolRecommendString = "Very Likely,Likely,Unlikely";
    private final String[] schoolRecommendArray = schoolRecommendString.split(",");

    private WinningResult winningResult;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfSurvey() {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(String dateOfSurvey) {
        this.dateOfSurvey = dateOfSurvey;
    }

    public String[] getCampusLike() {
        return campusLike;
    }

    public void setCampusLike(String[] campusLike) {
        this.campusLike = campusLike;
    }

    public String getStringAppendedCampusLike() {
        return stringAppendedCampusLike;
    }

    public void setStringAppendedCampusLike(String stringAppendedCampusLike) {
        this.stringAppendedCampusLike = stringAppendedCampusLike;
    }

    public String getUniversityInterest() {
        return universityInterest;
    }

    public void setUniversityInterest(String universityInterest) {
        this.universityInterest = universityInterest;
    }

    public String getSchoolRecommend() {
        return schoolRecommend;
    }

    public void setSchoolRecommend(String schoolRecommend) {
        this.schoolRecommend = schoolRecommend;
    }

    public String getRaffle() {
        return raffle;
    }

    public void setRaffle(String raffle) {
        this.raffle = raffle;
    }

    public String getAddComm() {
        return addComm;
    }

    public void setAddComm(String addComm) {
        this.addComm = addComm;
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }

    public void setWinningResult(WinningResult winningResult) {
        this.winningResult = winningResult;
    }

    public String validateForm() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getMessages();

        Student student = new Student();
        student.setFirstName(getFirstName());
        student.setLastName(getLastName());
        student.setStreetAddress(getStreetAddress());
        student.setCity(getCity());
        student.setState(getState());
        student.setZip(getZip());
        student.setTelNumber(getTelNumber());
        student.setEmail(getEmail());
        student.setDateOfSurvey(getDateOfSurvey());
        student.setCampusLike(getCampusLike());
        student.setUniversityInterest(getUniversityInterest());
        student.setSchoolRecommend(getSchoolRecommend());
        student.setRaffle(getRaffle());
        student.setAddComm(getAddComm());

        StudentService studentService = new StudentService();
        studentService.saveData(student);

        WinningResult result = DataProcessor.calculateMeanStandardDeviation(getRaffle());
        setWinningResult(result);

        String fwdToJsp;
        if (null != result && result.getMean() > 90) {
            // WinnerAcknowledgement JSP
            fwdToJsp = "winnerAcknowledgement";
        } else {
            // SimpleAcknowledgement JSP
            fwdToJsp = "simpleAcknowledgement";
        }

        return fwdToJsp;
    }

    public List<String> completeRecommend(String recommendPrefix) {
        List<String> matches = new ArrayList<String>();
        for (String possibleRecommend : schoolRecommendArray) {
            if (possibleRecommend.toUpperCase()
                    .startsWith(recommendPrefix.toUpperCase())) {
                matches.add(possibleRecommend);
            }
        }
        return (matches);
    }
}
