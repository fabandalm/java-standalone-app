package com.falmeida.tech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.falmeida.tech.domain.StudentPopulation;

/**
 * 
 * @author falmeida
 *
 */
public class TreeDataSet {
	
	private TreeDataNode root;
	
	Logger logger = LoggerFactory.getLogger(TreeDataSet.class);
	
	public void addTreeDataNode(StudentPopulation pop) {
		
		if(root.getChildren().isEmpty()) {
		
			TreeDataNode treeDataNodeProvince = new TreeDataNode(root,pop.getProvince(),pop.getPopulation());
					
			root.getChildren().add(treeDataNodeProvince);
			
			TreeDataNode treeDataNodeCity = new TreeDataNode(treeDataNodeProvince,pop.getCity(),pop.getPopulation());
			
			treeDataNodeProvince.getChildren().add(treeDataNodeCity);
			
			TreeDataNode treeDataNodeSchool = new TreeDataNode(treeDataNodeCity,pop.getSchool(),pop.getPopulation());
			
			treeDataNodeCity.getChildren().add(treeDataNodeSchool);
			
		}else {
			
			boolean isProvinceChild = false;
			
			TreeDataNode province = null;
			
			boolean isCityChild = false;
			
			TreeDataNode city = null;
			
			boolean isSchoolChild = false;
			
			for (TreeDataNode treeDataNodeProvince : root.getChildren()) {
				if(pop.getProvince().contentEquals(treeDataNodeProvince.getValue())) {
					province = treeDataNodeProvince;
					treeDataNodeProvince.setPopulation(pop.getPopulation());
					isProvinceChild = true;
					for (TreeDataNode treeDataNodeCity : treeDataNodeProvince.getChildren()) {
						if(pop.getCity().contentEquals(treeDataNodeCity.getValue())) {
							city = treeDataNodeCity;
							treeDataNodeCity.setPopulation(pop.getPopulation());
							isCityChild = true;
							for (TreeDataNode treeDataNodeSchool : treeDataNodeCity.getChildren()) {
								if(pop.getSchool().contentEquals(treeDataNodeSchool.getValue())) {
									treeDataNodeSchool.setPopulation(pop.getPopulation());
									isSchoolChild = true;
									break;
								}
							}
							break;
						}
					}
					break;
				}
			}
			
			if(!isProvinceChild) {
				
				TreeDataNode treeDataNodeProvince = new TreeDataNode(root,pop.getProvince(),pop.getPopulation());
				
				province = treeDataNodeProvince;

				root.getChildren().add(province);
				
			}
			
			if(!isCityChild) {
				
				TreeDataNode treeDataNodeCity = new TreeDataNode(province,pop.getCity(),pop.getPopulation());
				
				city = treeDataNodeCity;

				province.getChildren().add(city);
				
			}
			
			if(!isSchoolChild) {
				
				TreeDataNode treeDataNodeSchool = new TreeDataNode(city,pop.getSchool(),pop.getPopulation());

				city.getChildren().add(treeDataNodeSchool);
				
			}
			
		}
		
	}
	
	public void printTreeNode() {
	
		for (TreeDataNode province : root.getChildren()) {
			
			for (TreeDataNode city : province.getChildren()) {
								
				for (TreeDataNode school : city.getChildren()) {
					
					logger.info(school.getValue() + "\t\t\t" + school.getPopulation());
					
				}
				
				logger.info(city.getValue() + "\t\t\t" + city.getPopulation());
				
			}
			
			logger.info(province.getValue() + "\t\t\t" + province.getPopulation());
			
		}
		
	}
	
	public TreeDataSet() {
		root = new TreeDataNode(null,null,0);
	}
	
}
