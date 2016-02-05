/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.csd.services;

import edu.gmu.csd.bean.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Derick Augustine Coutinho
 */
@ManagedBean
@RequestScoped
public class StudentService {

    private ArrayList<Student> studentsSurvey;

    public ArrayList<Student> getStudentsSurvey() {
        return studentsSurvey;
    }

    public void setStudentsSurvey(ArrayList<Student> studentsSurvey) {
        this.studentsSurvey = studentsSurvey;
    }

    public void saveData(Student student) {
        String appendedLikeCamp = "";
        for (String likeCamp : student.getCampusLike()) {
            appendedLikeCamp += likeCamp;
        }

        String data = student.getFirstName() + ", "
                + student.getLastName() + ", "
                + student.getStreetAddress() + ", "
                + student.getCity() + ", "
                + student.getState() + ", "
                + student.getZip() + ", "
                + student.getTelNumber() + ", "
                + student.getEmail() + ", "
                + student.getDateOfSurvey() + ", "
                + appendedLikeCamp + ", "
                + student.getUniversityInterest() + ", "
                + student.getSchoolRecommend() + ", "
                + student.getAddComm();

        try {
            File studentSurveyFile = new File("/opt/bitnami/jboss/studentSurveyFile.txt");
            if (!studentSurveyFile.exists()) {
                studentSurveyFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(studentSurveyFile.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file " + ex.getMessage());
        }
    }

    public String retrieveStudentsSurvey() {
        String fileName = "/opt/bitnami/jboss/studentSurveyFile.txt";
        String oneStudentsSurvey;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ArrayList<Student> studentsSurvey1 = new ArrayList<Student>();
            while ((oneStudentsSurvey = bufferedReader.readLine()) != null) {
                String[] oneStudentsSurveyData = oneStudentsSurvey.split(",");
                if (oneStudentsSurveyData.length > 1) {
                    Student student = new Student();
                    student.setFirstName(oneStudentsSurveyData[0]);
                    student.setLastName(oneStudentsSurveyData[1]);
                    student.setStreetAddress(oneStudentsSurveyData[2]);
                    student.setCity(oneStudentsSurveyData[3]);
                    student.setState(oneStudentsSurveyData[4]);
                    student.setZip(oneStudentsSurveyData[5]);
                    student.setTelNumber(oneStudentsSurveyData[6]);
                    student.setEmail(oneStudentsSurveyData[7]);
                    student.setDateOfSurvey(oneStudentsSurveyData[8]);
                    student.setStringAppendedCampusLike(oneStudentsSurveyData[9]);
                    student.setUniversityInterest(oneStudentsSurveyData[10]);
                    student.setSchoolRecommend(oneStudentsSurveyData[11]);
                    student.setAddComm(oneStudentsSurveyData[12]);

                    studentsSurvey1.add(student);
                }
            }

            setStudentsSurvey(studentsSurvey1);

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        return "listOfStudentSurveys";
    }
}
