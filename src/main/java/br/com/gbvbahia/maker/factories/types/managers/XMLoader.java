package br.com.gbvbahia.maker.factories.types.managers;

import br.com.gbvbahia.i18n.I18N;
import br.com.gbvbahia.maker.factories.types.properties.exception.XMLoaderException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.IllegalComponentStateException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * This class reads the xml tests setup for executing the tests.
 * 
 * @author guilhermebraga
 * @since v2.0 Jan/2016
 */
public class XMLoader {

   private String xmlSetupTestFile = "make.xml";

   private Document document = null;

   private static XMLoader loader = null;

   /**
    * Call after a loader was created using the method getLoader(String
    * xmlName).
    * 
    * @return a previously loader created.
    * @exception IllegalComponentStateException
    *               if the method getLoader(String xmlName) was not called
    *               before.
    */
   public static XMLoader getLoader() {
      if (loader == null) {
         throw new IllegalComponentStateException(
               I18N.getMsg("xmlLoaderNullException"));
      }
      return loader;
   }

   /**
    * This is a singleton class for xml file. If you send another xml a new
    * XMLoader will be created, if the XML is the same keeps the same instance.
    * 
    * @param xmlName
    *           if you will use make.xml (name default) you can pass null.
    * @return a reader for xml configurations of tests.
    */
   public static XMLoader getLoader(String xmlName) {
      boolean load = false;
      if (loader == null) {
         loader = new XMLoader();
         load = true;
      }
      if (!StringUtils.isBlank(xmlName)
            && !StringUtils.equals(loader.xmlSetupTestFile, xmlName)) {
         loader.xmlSetupTestFile = xmlName;
         load = true;
      }
      if (load) {
         loader.loadDocument();
      }
      return loader;
   }

   /**
    * Loads the informations from make.xml or other xml setup.
    * 
    * @throws XMLoaderException
    *            if cannot read any xml setup.
    */
   private XMLoader() {
   }

   /**
    * Load the document that represents XML with SQLs.
    * 
    * @throws XMLoaderException
    *            If anything goes wrong when the XML is about to be read or its
    *            be reading a XMLoaderException can be thrown with a real
    *            exception problem inside.
    */
   private void loadDocument() {
      try {
         File fileXmlFile = this.loadFile();
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = dbf.newDocumentBuilder();
         this.document = docBuilder.parse(fileXmlFile);
         this.document.getDocumentElement().normalize();
      } catch (ParserConfigurationException pE) {
         throw new XMLoaderException(pE,
               "Error trying to do parse of XML file.");
      } catch (IOException ioE) {
         throw new XMLoaderException(ioE,
               "IO Error got when tried to read xml file.");
      } catch (SAXException saxE) {
         throw new XMLoaderException(saxE,
               "SAXException got when tried to read " + this.xmlSetupTestFile
               + " file.");
      }
   }

   /**
    * Load the XML file in hard disk.
    * 
    * @return XML File with setup.
    * @throws XMLSQLExeption
    *            If anything goes wrong when the XML is about to be read or its
    *            be reading a XMLoaderException can be thrown with a real
    *            exception problem inside.
    */
   private File loadFile() throws XMLoaderException {
      try {
         File xmlFile = null;
         String path = "/" + this.xmlSetupTestFile;
         URL filePath = this.getClass().getResource(path);
         if (filePath == null) {
            throw new XMLoaderException(null, "File " + this.xmlSetupTestFile
                  + " was not found.");
         }
         xmlFile = new File(filePath.toURI());
         if (!xmlFile.exists()) {
            throw new IOException("File make xml doesn't exist.");
         }
         return xmlFile;
      } catch (IOException ioE) {
         throw new XMLoaderException(ioE,
               "IO Error got when tried to read xml file.\nFile: "
                     + this.xmlSetupTestFile);
      } catch (URISyntaxException uriE) {
         throw new XMLoaderException(uriE, "URI wrong in jar file for "
               + this.xmlSetupTestFile + " file.");
      }
   }

   /**
    * Returns all factories informed at xml setup file.<br>
    * <factories><br>
    * <factory>br.com.gvt.maker.testes.factory.CEPWorkTest</factory><br>
    * <factory>br.com.gvt.maker.testes.factory.FactoryCustomerService</factory><br>
    * </factories><br>
    * 
    * @return a list with the names of all factories class declared in xml file
    *         setup.
    */
   public List<String> getFactories() {
      List<String> factories = new ArrayList<String>();
      NodeList nodeList = this.document.getElementsByTagName("factories");
      for (int i = 0; i < nodeList.getLength(); i++) {
         Node node = nodeList.item(i);
         if (Node.ELEMENT_NODE == node.getNodeType()) {
            Element element = (Element) node;
            NodeList factoriesNode = element.getElementsByTagName("factory");
            for (int ii = 0; ii < factoriesNode.getLength(); ii++) {
               factories.add(factoriesNode.item(ii).getTextContent());
            }
         }
      }
      return factories;
   }

   /**
    * Returns a map with: key <> value<br>
    * br.com.gvt.testes.MockEntities.enities.Employee.age <> between{18,69}
    * br.com.gvt.testes.MockEntities.enities.Employee.name <> isName<br>
    * All tests with the testName will be loaded. If the same class is founded
    * in more the one test the first will be kept.<br>
    * If a class in two tests with different fields in each test the class will
    * be loaded with both fields.
    * 
    * @param testName
    *           The names of all tests to be loaded.
    * @return The map with class and fields with rules
    */
   public Map<String, Map<String, String>> getMapRulesFieldsByTestName(
         String... testName) {
      Map<String, Map<String, String>> testsParans = new HashMap<String, Map<String, String>>();
      for (int i = 0; i < testName.length; i++) {
         Map<String, Map<String, String>> testParans = this
               .getMapFielsByTestName(testName[i]);
         Set<String> keysMapTest = testParans.keySet();
         for (String keyMapTest : keysMapTest) {
            if (testsParans.containsKey(keyMapTest)) {
               Map<String, String> testsFields = testsParans.get(keyMapTest);
               Map<String, String> testFields = testParans.get(keyMapTest);
               Set<String> keysFields = testFields.keySet();
               for (String keyFields : keysFields) {
                  if (!testsFields.containsKey(keyFields)) {
                     testsFields.put(keyFields, testFields.get(keyFields));
                  }
               }
            } else {
               testsParans.put(keyMapTest, testParans.get(keyMapTest));
            }
         }
      }
      return testsParans;
   }

   /**
    * Works with each test, unit work.
    * 
    * @param testName
    *           The names of all tests to be loaded.
    * @return The map with class and fields with rules
    */
   private Map<String, Map<String, String>> getMapFielsByTestName(
         String testName) {
      Map<String, Map<String, String>> testsParans = new HashMap<String, Map<String, String>>();
      NodeList nodeTests = this.document.getElementsByTagName("test");// <test>
      for (int i = 0; i < nodeTests.getLength(); i++) {
         NodeList nodeTestChilds = nodeTests.item(i).getChildNodes();// names||entities
         List<NodeList> testList = this.recoverTestNodeByName(testName,
               nodeTestChilds);
         List<Node> entityList = this.recoverEntityNodes(testList);
         this.populateMapWithEntityRules(testsParans, entityList);
      }
      return testsParans;
   }

   /**
    * Returns all NodeList test thats contains the testName in the node names.
    * 
    * @param testName
    *           the name of the test
    * @param nodeTestChilds
    *           all nodes test, where the name will be searched for.
    * @return List of the test nodes thats contains the name of the test in the
    *         tag names.
    */
   private List<NodeList> recoverTestNodeByName(String testName,
         NodeList nodeTestChilds) {
      List<NodeList> testList = new ArrayList<NodeList>();
      for (int ii = 0; ii < nodeTestChilds.getLength(); ii++) {
         if (StringUtils.equals(nodeTestChilds.item(ii).getNodeName(), "names")) { // <names>
            NodeList nameNodes = nodeTestChilds.item(ii).getChildNodes();
            for (int iii = 0; iii < nameNodes.getLength(); iii++) {
               if (Node.ELEMENT_NODE == nameNodes.item(iii).getNodeType()) {
                  Element element = (Element) nameNodes.item(iii);
                  String testFoundName = element.getTextContent();
                  if (StringUtils.equals(testFoundName, testName)) {
                     testList.add(nodeTestChilds);
                  }
               }
            }
         }
      }
      return testList;
   }

   /**
    * Gets all nodes entity from a list of test node.
    * 
    * @param testList
    *           only test with name in test.
    * @return a list with all entity node founded at testList.
    */
   private List<Node> recoverEntityNodes(List<NodeList> testList) {
      List<Node> entityList = new ArrayList<Node>();
      for (NodeList test : testList) {
         for (int ii = 0; ii < test.getLength(); ii++) {
            NodeList entitiesNodes = test.item(ii).getChildNodes();
            for (int iii = 0; iii < entitiesNodes.getLength(); iii++) {
               if (StringUtils.equals(entitiesNodes.item(iii).getNodeName(),
                     "entity")) { // <entity>
                  entityList.add(entitiesNodes.item(iii));
               }
            }
         }
      }
      return entityList;
   }

   /**
    * Populate a map with entities founded.
    * 
    * @param testsParans
    *           a map that will be populated.
    * @param entityList
    *           a list with entities used to populate the map.
    */
   private void populateMapWithEntityRules(
         Map<String, Map<String, String>> testsParans, List<Node> entityList) {
      for (Node entity : entityList) {
         String clazz = entity.getAttributes().getNamedItem("class")
               .getTextContent();
         Map<String, String> fieldsRules = new HashMap<String, String>();
         NodeList fields = entity.getChildNodes();
         for (int ii = 0; ii < fields.getLength(); ii++) {
            Node nodeField = fields.item(ii);
            if (Node.ELEMENT_NODE == nodeField.getNodeType()) {
               Element element = (Element) nodeField;
               fieldsRules.put(element.getAttribute("name"),
                     element.getTextContent());
            }
         }
         if (testsParans.containsKey(clazz)) {
            Map<String, String> fieldsMap = testsParans.get(clazz);
            Set<String> keyFields = fieldsRules.keySet();
            for (String key : keyFields) {
               if (!fieldsMap.containsKey(key)) {
                  fieldsMap.put(key, fieldsRules.get(key));
               }
            }
         } else {
            testsParans.put(clazz, fieldsRules);
         }
      }
   }

   /**
    * Gets all information in tag setup.
    * 
    * @return a map with setup node information
    */
   public Map<String, String> loadSetup() {
      Map<String, String> setupMap = new HashMap<String, String>();
      NodeList nodeSetup = this.document.getElementsByTagName("setup");// <setup>
      for (int i = 0; i < nodeSetup.getLength(); i++) {
         NodeList nodeSeupChilds = nodeSetup.item(i).getChildNodes();
         for (int ii = 0; ii < nodeSeupChilds.getLength(); ii++) {
            Node nodeConf = nodeSeupChilds.item(ii);
            if (Node.ELEMENT_NODE == nodeConf.getNodeType()) {
               Element element = (Element) nodeConf;
               setupMap.put(element.getNodeName(),
                     element.getAttribute("value"));
            }
         }
      }
      return setupMap;
   }
}
