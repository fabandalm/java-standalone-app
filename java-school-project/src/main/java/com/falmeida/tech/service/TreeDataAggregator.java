package com.falmeida.tech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.falmeida.tech.domain.StudentPopulation;

/**
 * 
 * @author falmeida
 *
 */
public class TreeDataAggregator implements DataAggregator {

	Logger logger = LoggerFactory.getLogger(TreeDataAggregator.class);
	
	TreeDataSet treeDataSet = new TreeDataSet();
	
	@Override
	public void aggregationByStudentPopulation(StudentPopulation studentPopulation) {
		
		logger.debug("aggregationByStudentPopulation");
		
		logger.debug("StudentPopulation: " + studentPopulation);
		
		treeDataSet.addTreeDataNode(studentPopulation);
		
		//treeDataSet.printTreeNode();
		
	}

}
