/**
 * This sample program is provided AS IS and may be used, executed, copied and modified without royalty payment by customer (a) for its own
 * instruction and study, (b) in order to develop applications designed to run with an IBM WebSphere product, either for customer's own internal use
 * or for redistribution by customer, as part of such an application, in customer's own products.
 * 
 * Product 5724-V21,  (C) COPYRIGHT International Business Machines Corp., 2008
 * All Rights Reserved * Licensed Materials - Property of IBM
 */
package com.ibm.security.appscan.altoromutual.util;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is a utility class used by servlet classes and JSP pages
 * @author Alexei
 */
public class ServletUtil {

	public static final String SESSION_ATTR_USER = "user";
	public static final String SESSION_ATTR_ADMIN_VALUE = "altoroadmin";
	public static final String SESSION_ATTR_ADMIN_KEY = "admin";
	
	public static final String ALTORO_COOKIE = "AltoroAccounts";
	
	public static final String EMAIL_REGEXP = "^..*@..*\\...*$";
	
	public static final String LEGAL_EMAIL_REGEXP = "^[A-Za-z0-9_\\-\\.\\+]+@[A-Za-z0-9\\-\\.]+.[A-Za-z]+$";
	
	/**
	 * This method searches for the provided query in the specified news XML file
	 * @param query search query
	 * @param path news XML file to search
	 * @return search results
	 */
	public static String[] searchArticles(String query, String path){
		ArrayList<String> results = new ArrayList<String>();
	
		File file = new File(path);
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			//root node
			NodeList nodes = document.getElementsByTagName("news");
			
			if (nodes.getLength() == 1){
				nodes = nodes.item(0).getChildNodes();
				for (int i=0; i<nodes.getLength(); i++){
					Node node = nodes.item(i);
					if ("publication".equals(node.getNodeName())){
						NodeList innerNodes = node.getChildNodes();
						String title = null;
						Boolean isPublic = null;
						for (int x=0; x<innerNodes.getLength(); x++){
							try {
								if ("title".equals(innerNodes.item(x).getNodeName())){
									title = innerNodes.item(x).getFirstChild().getNodeValue();
								}
								else if ("isPublic".equals(innerNodes.item(x).getNodeName())){
									isPublic = Boolean.parseBoolean(innerNodes.item(x).getFirstChild().getNodeValue());
								}
								
								if (title != null && isPublic != null){
									if (isPublic && title.contains(query)){
										results.add(title);
									}
									
									break;
								}
							} catch (Exception e){}
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (results.size() == 0)
			return null;
		
		return results.toArray(new String[results.size()]);
	}

	/**
	 * This class _pretends_ to search the site for the specified search query
	 * @param query query
	 * @param rootDir directory, to search the contents of (e.g. "static" pages)
	 * @return
	 */
	public static String[] searchSite(String query, String rootDir){
		ArrayList<File> matches = searchFiles(query, new File(rootDir));
		String[] results = new String[matches.size()];
			
		for (int i=0; i<results.length; i++){
			String absolutePath = matches.get(i).getAbsolutePath();
			results[i] = absolutePath.substring(rootDir.length());
		}
		
		return results;
	}

	/*
	 * Recursive method to search a files in a directory
	 */
	private static ArrayList<File> searchFiles(String query, File rootFile) {
		// error checking
		if (!rootFile.canRead())
			return new ArrayList<File>();
		
		//if directory - recurse
		if (rootFile.isDirectory()){
			ArrayList<File> results = new ArrayList<File>();
			File[] files = rootFile.listFiles();
			for (File file: files){
				results.addAll(searchFiles(query, file));
			}
			return results;
		}
		
		if (rootFile.isFile()){
			//!!! do nothing for this demo site
			// we would search file contents otherwise
			return new ArrayList<File>();
		}
		
		return new ArrayList<File>();
	}
	
	/*
	 * Returns all bank usernames
	 */
	public static String[] getBankUsers(){
		return DBUtil.getBankUsernames();
	}
	
	/*Basic Web Sanitizer*/
	public static String sanitizeBasic(String str){
	    StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '<':
				buf.append("&lt;");
				break;
			case '>':
				buf.append("&gt;");
				break;
			case '"':
				buf.append("&ldquo;");
				break;
			default:
				buf.append(ch);
			}
		}
		return buf.toString();
	}
	
	/* Web output sanitizer */
	public static String sanitizeWeb(String data){
		return StringEscapeUtils.escapeHtml(data);
	}
}