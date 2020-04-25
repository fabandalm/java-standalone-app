package com.falmeida.tech.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author falmeida
 *
 */
public class TreeDataNode {

	Logger logger = LoggerFactory.getLogger(TreeDataNode.class);
	
	private TreeDataNode parent;
	
	private int population;
	
	private String value;
	
	private List<TreeDataNode> children = new ArrayList<TreeDataNode>();
	
	public TreeDataNode(TreeDataNode parent,String value,int population) { 
		this.parent = parent;
		this.value = value;
		this.population = population;
	}

	public TreeDataNode getParent() {
		return parent;
	}

	public void setParent(TreeDataNode parent) {
		this.parent = parent;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population += population;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<TreeDataNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeDataNode> children) {
		this.children = children;
	} 
	
}
