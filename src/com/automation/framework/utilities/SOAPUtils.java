package com.automation.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jayway.jsonpath.JsonPath;

public class SOAPUtils {

	public static void saveResponse(String fileName, String response) throws TransformerException, SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(response)));
		// Use a Transformer for output
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("responseXml//"+fileName+".xml"));
		transformer.transform(source, result);

	}


	public static List<String> getNodeListValuesByNodeName(String nodeName,String fileName) throws SAXException, IOException, ParserConfigurationException{
		File fXmlFile = new File("responseXml//"+fileName+".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(fXmlFile);
		NodeList nodeList = document.getElementsByTagName(nodeName);
		List<String> list =  new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println(node.getNodeName());
			list.add(node.getTextContent());
		}
		return list;
	}

	public static String getAttibuteValueByAttributeName(String attributeName,String fileName) throws SAXException, IOException, ParserConfigurationException{
		File fXmlFile = new File("responseXml//"+fileName+".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse("C:\\Users\\nagarjun.veerapu\\Downloads\\ECMBaseFeb09\\ECMBaseFeb09\\responseXml\\VIGenericOutgoingWebService1457338821449.xml");
		NodeList nodeList = document.getElementsByTagName(attributeName);
		int length = nodeList.getLength();
		String attribute = "AttributeName";
		String attributeValue = "AttributeValue";
		if(length==0){
			nodeList = document.getElementsByTagName("doc:Attribute");
			length = nodeList.getLength();
			attribute = "doc:AttributeName";
			attributeValue = "doc:AttributeValue";
		}
		boolean found = false;
		for (int i = 0; i < length; i++) {
			Node node = nodeList.item(i);
			NodeList childList = node.getChildNodes();
			int childNodeLength = childList.getLength();
			for(int j = 0; j < childNodeLength; j++){
				Node childNode = childList.item(j);
				String nodeName = childNode.getNodeName();
				String nodeValue = childNode.getTextContent();
				if(nodeName.equals(attribute)&&nodeValue.equals(attributeName)){
					found  = true;
				}
				if(found && nodeName.equals(attributeValue)){
					return nodeValue;
				}
			}
		}
		return null;
	}

	public boolean validateXMLSchema(String xsdPath, String xmlPath){

		try {
			SchemaFactory factory = 
					SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: "+e.getMessage());
			return false;
		}
		return true;	
	}
	

	public String getTextValueForANode(String xmlPath,String xpath) throws IOException, JSONException{
		String xml = FileUtils.readFileToString(new File(xmlPath));
		JSONObject json = XML.toJSONObject(xml);
		return JsonPath.read(json.toString(), xpath);
	}
	
	

}
