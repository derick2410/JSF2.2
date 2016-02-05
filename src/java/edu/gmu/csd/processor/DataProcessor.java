/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gmu.csd.processor;

import edu.gmu.csd.bean.WinningResult;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Derick Augustine Coutinho
 */
public class DataProcessor {

    public static WinningResult calculateMeanStandardDeviation(String raffle) {
        String[] dataFieldsArr = null;
        if (StringUtils.isNotEmpty(raffle)) {
            dataFieldsArr = StringUtils.split(raffle, ",");
        }
        WinningResult winningResult = null;

        if (null != dataFieldsArr) {
            double mean;
            double sum = 0;
            double squareSum = 0;
            double stndrdDeviation;

            // First for loop to calculate the mean.
            for (String meanData : dataFieldsArr) {
                sum = sum + Integer.parseInt(meanData);
            }

            mean = sum / dataFieldsArr.length;

            // Second for loop to calculate the standard deviation.
            for (String sdData : dataFieldsArr) {
                double square = Math.pow((Integer.parseInt(sdData) - mean), 2);

                squareSum = squareSum + square;
            }

            stndrdDeviation = Math.sqrt(squareSum / (dataFieldsArr.length - 1));

            winningResult = new WinningResult();
            winningResult.setMean(mean);
            winningResult.setStandardDeviation(stndrdDeviation);
        }

        return winningResult;
    }
}
