/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.csd.bean;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Derick Augustine Coutinho
 */
@ManagedBean
public class WinningResult {

    private double mean;
    private double standardDeviation;

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
