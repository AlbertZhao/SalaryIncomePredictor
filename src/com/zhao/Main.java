package com.zhao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static double startingSalary;

    private static double incrementPcent;

    //quarterly, half-yearly, annually
    private static String incrementFrequently;

    private static double deductions;

    //quarterly, half-yearly, annually
    private static String deductionFrequently;

    private static Integer predictionForYears;

    public static void main(String[] args) {

	// write your code here
        System.out.println("Please input startingSalary:");
        startingSalary = Double.parseDouble(System.console().readLine());
        if (startingSalary<1) {
            System.out.println("startingSalary should  >=1");
            return;
        }

        System.out.println("Please input incrementPcent:");
        incrementPcent = Double.parseDouble(System.console().readLine());
        if (incrementPcent<0) {
            System.out.println("incrementPcent should  >0");
            return;
        }


        System.out.println("Please input incrementFrequently:");
        incrementFrequently = System.console().readLine();
        if (!("quarterly".equals(incrementFrequently) || "half-yearly".equals(incrementFrequently) || "annually".equals(incrementFrequently))) {
            System.out.println("incrementFrequently should be quarterly, half-yearly, annually");
            return;
        }

        System.out.println("Please input deductions:");
        deductions = Double.parseDouble(System.console().readLine());
        if (deductions<0) {
            System.out.println("deductions should  >0");
            return;
        }


        System.out.println("Please input deductionFrequently:");
        deductionFrequently = System.console().readLine();
        if (!("quarterly".equals(deductionFrequently) || "half-yearly".equals(deductionFrequently) || "annually".equals(deductionFrequently))) {
            System.out.println("deductionFrequently should be quarterly, half-yearly, annually");
            return;
        }

        System.out.println("Please input predictionForYears:");
        predictionForYears = Integer.valueOf(System.console().readLine());


        List<IncrementReport> incrementReportList = getIncrementReport(predictionForYears);
        incrementReportList.forEach(s -> {
            System.out.println(s.getYear());
            System.out.println(s.getStartingSalary());
            System.out.println(s.getNumofIncrements());
            System.out.println(s.getIncrementPercent());
            System.out.println(s.getIncrementAmount());

        });


        List<DeductionReport> deductionReportList = getDeductionReport(predictionForYears);
        deductionReportList.forEach(s -> {
            System.out.println(s.getYear());
            System.out.println(s.getStartingSalary());
            System.out.println(s.getNumofDeductions());
            System.out.println(s.getDeductionPercent());
            System.out.println(s.getDeductionAmount());
        });



        List<PredictionReport> predictionReportList =  incrementReportList.stream().flatMap(x -> deductionReportList.stream().filter(y -> x.getYear() == y.getYear()).map(y -> {
            PredictionReport predictionReport = new PredictionReport();
            predictionReport.setYear(y.getYear());
            predictionReport.setIncrementAmount(x.getIncrementAmount());
            predictionReport.setDeductionAmount(y.getDeductionAmount());
            predictionReport.setStartingSalary(y.getStartingSalary());
            predictionReport.setSalaryGrowth(String.valueOf(Double.parseDouble(x.getIncrementAmount()) - Double.parseDouble(y.getDeductionAmount())));
            return predictionReport;
        })).collect(Collectors.toList());

        predictionReportList.forEach(s -> {
            System.out.println(s.getYear());
            System.out.println(s.getStartingSalary());
            System.out.println(s.getIncrementAmount());
            System.out.println(s.getDeductionAmount());
            System.out.println(s.getSalaryGrowth());
        });

    }






    private static List<IncrementReport> getIncrementReport(int years) {

        List<IncrementReport> incrementReportList = new ArrayList<IncrementReport>();

        for (int i =0; i< years; i++) {
            IncrementReport incrementReport = new IncrementReport();
            incrementReport.setYear(String.valueOf(i));

            double incrementAmount = 0;
            String numofIncrements = "0";

            incrementReport.setStartingSalary(String.valueOf(startingSalary));

            switch (incrementFrequently)  {
                case "quarterly":
                    incrementAmount = startingSalary*incrementPcent*4;
                    numofIncrements = "4";
                    break;
                case "half-yearly":
                    numofIncrements = "2";
                    incrementAmount = startingSalary*incrementPcent*2;
                    break;
                case "annually":
                    numofIncrements = "1";
                    incrementAmount = startingSalary*incrementPcent*1;
                    break;
                default:
                    break;
            }

            startingSalary = startingSalary + incrementAmount;

            incrementReport.setNumofIncrements(numofIncrements);
            incrementReport.setIncrementAmount(String.valueOf(incrementAmount));
            incrementReport.setIncrementPercent(String.valueOf(incrementPcent));


            incrementReportList.add(incrementReport);

        }

        return incrementReportList;

    }


    private static List<DeductionReport> getDeductionReport(int years) {

        List<DeductionReport> deductionReportList = new ArrayList<DeductionReport>();

        for (int i =0; i< years; i++) {
            DeductionReport deductionReport = new DeductionReport();
            deductionReport.setYear(String.valueOf(i));

            double deductionAmount = 0;
            String numofdeduction = "0";

            deductionReport.setStartingSalary(String.valueOf(startingSalary));

            switch (incrementFrequently)  {
                case "quarterly":
                    deductionAmount = startingSalary*incrementPcent*4;
                    numofdeduction = "4";
                    break;
                case "half-yearly":
                    numofdeduction = "2";
                    deductionAmount = startingSalary*incrementPcent*2;
                    break;
                case "annually":
                    numofdeduction = "1";
                    deductionAmount = startingSalary*incrementPcent*1;
                    break;
                default:
                    break;
            }

            startingSalary = startingSalary + deductionAmount;

            deductionReport.setNumofDeductions(numofdeduction);
            deductionReport.setDeductionAmount(String.valueOf(deductionAmount));
            deductionReport.setDeductionPercent(String.valueOf(incrementPcent));


            deductionReportList.add(deductionReport);

        }

        return deductionReportList;

    }











}
