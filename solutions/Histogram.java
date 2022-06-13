/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import java.util.Scanner;
class Histogram{

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        
        for(int a=0; a<1; a++){
            try{
                int NumOfData = in.nextInt();
                int NumOfBins = in.nextInt();
                
                int[] DataPoints = new int[NumOfData];
                
                for(int b=0; b<NumOfData; b++){
                    DataPoints[b] =in.nextInt();
                }
                
                int max = DataPoints[0];
                int min = DataPoints[0];
                
                for(int i=0;i<DataPoints.length;i++){
                    if(DataPoints[i] > max){
                        max = DataPoints[i];
                    }
                    if(DataPoints[i] < min){
                        min = DataPoints[i];
                    }
                }

                int CutoffsValue = (max-min)/NumOfBins;
                int sum = 0;
                int[] interval = new int[NumOfBins+1];
                int[] count = new int[NumOfBins];

                for(int i=0; i<interval.length; i++){
                    if(i==0){
                        System.out.print(min + " ");
                        sum += min;
                        interval[i] = min;
                    }

                    else{
                        System.out.print(sum + " ");
                        interval[i] = sum;
                    }
                    sum += CutoffsValue;
                }
                System.out.println();

                for(int i=0; i<DataPoints.length; i++){
                    for(int j=0; j<interval.length-1; j++){
                        if(DataPoints[i] >= interval[j] && DataPoints[i] < interval[j+1]){
                            count[j]++;
                        }


                    }
                    if(DataPoints[i] == interval[interval.length-1]){
                        count[interval.length-2]++;
                    }
                }

                for(int i=0; i<count.length; i++){
                    System.out.print(count[i] + " ");
                }
                }
            catch(java.util.InputMismatchException e){
                return;
            }  
        }
    }
}
