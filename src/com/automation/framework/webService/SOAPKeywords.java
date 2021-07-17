package com.automation.framework.webService;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.XmlException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.automation.framework.utilities.SOAPUtils;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.iface.MessageExchange;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestProperty;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;

public class SOAPKeywords {

	public String callAndSaveMacroService(Hashtable<String, String> testData)throws XmlException, IOException, SoapUIException, SAXException, ParserConfigurationException, XPathExpressionException, TransformerException{

		WsdlProject project = new WsdlProject(testData.get("requestWSDL")+".xml");

		String  fileName = testData.get("fileName");
		// retrieve all test suite from project
		List<TestSuite> testSuiteList = project.getTestSuiteList();
		testData.remove("fileName");
		// Iterate all TestSuites of project
		for (TestSuite ts : testSuiteList) {
			System.out.println("****Running Test suite " + ts.getName() + "********");

			// Retrieve all TestCases from a particular TestSuite
			List<TestCase> testCaseList = ts.getTestCaseList();

			for (TestCase testcase : testCaseList) {

				for(TestStep step : testcase.getTestStepList()){
					System.out.println("****Running Test Case " + step.getName()+ "*****");
					if(step.getName().equals(testData.get("macro"))){
						List<TestProperty> testpropList = step.getPropertyList();
						for (TestProperty testProperty : testpropList) {
							if(testProperty.getName().equals("Request")){
								DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
								DocumentBuilder builder = factory.newDocumentBuilder();
								Document doc1 = builder.parse(new InputSource(new StringReader(testProperty.getValue())));
								addElement(doc1, "gen:system",testData.get("system"));
								addElement(doc1, "gen:inputData",testData.get("inputData"));
								modifyRequest(testProperty, doc1);
							}
						}
						return extractResponse(testcase, fileName);
					}
				}
			}
		}
		return null;

	}

	private static void modifyRequest(TestProperty testProperty, Document doc1)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		DOMSource domSource = new DOMSource(doc1);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		String str = writer.toString().replace("RequestHeader xmlns=\"\"", "RequestHeader").replace("Document xmlns=\"\"", "Document");
		testProperty.setValue(str);
	}

	private static String extractResponse(TestCase testcase,String fileName) throws TransformerException, SAXException, IOException, ParserConfigurationException {
		TestCaseRunner testRunner = testcase.run(new PropertiesMap(), false);
		System.out.println("****Runner " + testRunner.getRunContext()+ "*****");
		String response = null;
		//verify where test case pass or not
		for (TestStepResult tcr :  testRunner.getResults()) {
			response = ((MessageExchange)tcr).getResponseContentAsXml();
			System.out.println("************************************");
			System.out.println(response);
			System.out.println("************************************");
			response = response.trim();
			response = response.split("<m:return>")[1].split("</m:return>")[0];


			if (response.startsWith("<![CDATA[")) {
				response = response.substring(9);
				int i = response.indexOf("]]>");
				if (i == -1) {
					throw new IllegalStateException(
							"argument starts with <![CDATA[ but cannot find pairing ]]>;");
				}
				response = response.substring(0, i);
			}

			SOAPUtils.saveResponse(fileName,response);
			System.out.println("Resposne "+response);
			
		}
		return response;
	}

	@SuppressWarnings("unused")
	private static WsdlProject getProjectXML(String pathToXML,String endpoint)
			throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException, XmlException, SoapUIException {
		File fXmlFile = new File(pathToXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(fXmlFile);
		NodeList endPointList = document.getElementsByTagName("con:endpoint");
		for (int i = 0; i < endPointList.getLength(); i++) {
			Node node = endPointList.item(i);
			node.setTextContent(endpoint);
		}
		DOMSource domSource1 = new DOMSource(document);
		StreamResult result1= new StreamResult(new File("soaprequest\\dummyxml.xml"));
		TransformerFactory tf1 = TransformerFactory.newInstance();
		Transformer transformer1 = tf1.newTransformer();
		transformer1.transform(domSource1, result1);
		WsdlProject project = new WsdlProject("soaprequest\\dummyxml.xml");
		return project;
	}



	private static void addElement(Document xmlDocument,String tagName , String tagValue) {
		Node attriNode = xmlDocument.getElementsByTagName(tagName).item(0);
		attriNode.setTextContent(tagValue);
	}



	@SuppressWarnings("unused")
	private static void addAttributes(Document doc1, Element element, String attriName, String attriValue,String attribute,String attributeName,String attributeValue) {
		Node attriNode = doc1.createElement(attribute);
		Element nAttribute= (Element)element.appendChild(attriNode);
		Node attriNameNode = doc1.createElement(attributeName);
		attriNameNode.setTextContent(attriName);
		nAttribute.appendChild(attriNameNode);

		Node attriValueNode = doc1.createElement(attributeValue);
		attriValueNode.setTextContent(attriValue);
		nAttribute.appendChild(attriValueNode);
	}


	@SuppressWarnings("unused")
	private void addElementAndPassValue(Document doc1, Element element, String attributeName,String attriName) {
		Node attriNameNode = doc1.createElement(attributeName);
		attriNameNode.setTextContent(attriName);
		element.appendChild(attriNameNode);

	}

	@SuppressWarnings("unused")
	private void addElementByRelation(Document xmlDocument,Node element,Hashtable<String, String> testData, String tagName , String tagValue) {
		if(testData.get(tagValue) != null){
			element.setTextContent(testData.get(tagValue));
			testData.remove(tagValue);
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, JSONException {
		/*DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse("C:\\Users\\nagarjun.veerapu\\Downloads\\ECMBaseFeb09\\ECMBaseFeb09\\responseXml\\VIGenericOutgoingWebService1457338821449.xml");
		NodeList nList = document.getChildNodes();
		Node n=null;
		Element eElement=null;
		for (int i = 0; i < nList.getLength(); i++) {           
			  System.out.println(nList.getLength());     
			  n= nList.item(i);                            
			  System.out.println("\nCurrent Element :" + n.getNodeValue());


			  if (n.getNodeType() == Node.ELEMENT_NODE) {
			      NodeList list = n.getChildNodes();
			      System.out.println(list.getLength());
			      System.out.println(list.item(5).getChildNodes().item(1).getNodeValue());
			  //  System.out.println("\nCurrent Element :" + n.getNodeName());
			  //  System.out.println(eElement.getNodeName());

			  }
			  n.getNextSibling();
			}*/

		String xml = FileUtils.readFileToString(new File("C:\\Users\\nagarjun.veerapu\\Downloads\\ECMBaseFeb09\\ECMBaseFeb09\\responseXml\\VIGenericOutgoingWebService1457338821449.xml"));
		JSONObject json = XML.toJSONObject(xml);

		System.out.println(json.getJSONObject("CircuitData").getJSONObject("CircuitLst").getJSONObject("Circuit").getString("CircuitName"));
		//System.out.println(JsonPath.read(json.toString(), "CircuitData.CircuitLst.Circuit.CircuitName"));
	}


}
