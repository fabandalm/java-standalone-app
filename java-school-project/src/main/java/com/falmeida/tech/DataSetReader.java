package com.falmeida.tech;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.falmeida.tech.domain.StudentPopulation;
import com.falmeida.tech.exception.DataAggregatorException;
import com.falmeida.tech.service.DataAggregator;
import com.falmeida.tech.service.TreeDataAggregator;

import java.io.FileNotFoundException;

/**
 * 
 * @author falmeida
 */
public class DataSetReader {
	
	private String line = "";
	
	private String lineSeparator = "\t";
	
	Logger logger = LoggerFactory.getLogger(DataSetReader.class);

	// read an input file and the algorithm to be used to aggregate
	public int readDataSet(String inputFile, DataAggregator dataAggregator) {
		
		logger.debug("readDataSet");
		
		BufferedReader br = null;
		
		StudentPopulation studentPopulation = null;
		
		int lineCounter = 1;
		
		try {
			
			long startTime = System.nanoTime();
		      
			br = new BufferedReader(new FileReader(inputFile));
  
			while ((line = br.readLine()) != null) {
	  
				//System.out.println("");
				
				//System.out.println("Processing Data Line: "+ lineCounter +" ...");  
				
				//System.out.println("--- Reports ---");
				
				try {
					
					studentPopulation = inputValidator(line, lineSeparator);
					
					dataAggregator.aggregationByStudentPopulation(studentPopulation);
					
				}catch(DataAggregatorException e) {
					logger.error("Processing Data Line " + lineCounter + " Error " + e.getMessage());
				}
				
				lineCounter ++;
				
			}
			
			// Tracks the time elapsed in milliseconds and seconds
			
			printExecutionTime(startTime);
			
		} catch (FileNotFoundException e) {
			logger.error("Processing Data Line " + lineCounter + " Error " + e.getMessage());
        } catch (IOException e) {
        	logger.error("Processing Data Line " + lineCounter + " Error " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return (lineCounter - 1);
		
	}
	
	// validates each line of the input file
	public StudentPopulation inputValidator(String line, String lineSeparator) throws DataAggregatorException {
		
		logger.debug("inputValidator");
		
		StudentPopulation studentPopulation = null;
		
		String[] populationBySchool = line.split(lineSeparator);
		
		//This Try and Catch avoids the application of stopping processing when it come across some wrong data.
		
		try {
			
			int population = Integer.parseInt(populationBySchool[3].substring(1));
			
			studentPopulation = new StudentPopulation(
					populationBySchool[0],
					populationBySchool[1],
					populationBySchool[2],
					population);
			
		}catch(Exception e) {
			throw new DataAggregatorException(e.toString());
		}
		
		// Uncomment the lines below to see the progress in a lower pace
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
		return studentPopulation;
		
	}
	
	// prints the total execution time
	public void printExecutionTime(long startTime) {
		
		logger.debug("printExecutionTime");
		
		long elapsedTime = System.nanoTime() - startTime;
		
        long miliseconds = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

        long seconds = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        
        logger.info("--- --- --- --- ---- ---");
        
        logger.info("--- Performance Time ---");
        
        logger.info("Total Data Processing Time: " + miliseconds + " miliseconds.");
        
        logger.info("Total Data Processing Time: " + seconds + " seconds.");
        
	}
	
	// It can used in order to see the application working with Kofax Sample Data
	public static void main(String[] args) {
		
		String inputFile = "src/main/resources/Ontario_Sample.csv";
		
		DataSetReader dataSetReader = new DataSetReader();
		
		dataSetReader.readDataSet(inputFile, new TreeDataAggregator());
		
	}
	
}
