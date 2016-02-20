package br.com.gbvbahia.maker.factories.types.properties;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.gbvbahia.maker.factories.types.properties.exception.XMLoaderException;

public class XMLoader {

  private String xmlMakeFile = "make.xml";

  private Document document = null;

  private static XMLoader loader = null;

  /**
   * Singleton class.
   * 
   * @return Reader
   */
  public static XMLoader getLoader() {
    if (loader == null) {
      loader = new XMLoader();
    }
    return loader;
  }

  /**
   * Carrega as informações do make.xml
   * 
   * @throws XMLoaderException se não conseguir encontrar o make.xml
   */
  private XMLoader() {
    this.loadDocument();
  }

  /**
   * Load the document that represents XML with SQLs.
   * 
   * @throws SAXException
   * @throws IOException
   * @throws ParserConfigurationException
   * @throws URISyntaxException
   */
  private void loadDocument() {
    try {
      File fileXmlFile = this.loadFile();
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      this.document = docBuilder.parse(fileXmlFile);
      this.document.getDocumentElement().normalize();
    } catch (ParserConfigurationException pE) {
      throw new XMLoaderException(pE, "Error trying to do parse of XML file.");
    } catch (IOException ioE) {
      throw new XMLoaderException(ioE, "IO Error got when tried to read xml file.");
    } catch (SAXException saxE) {
      throw new XMLoaderException(saxE,
          "SAXException got when tried to read " + this.xmlMakeFile + " file.");
    }
  }

  /**
   * Load the XML file in hard disk.
   * 
   * @return
   * @throws XMLSQLExeption
   */
  private File loadFile() throws XMLoaderException {
    try {
      File xmlFile = null;
      String path = "/" + this.xmlMakeFile;
      xmlFile = new File(this.getClass().getResource(path).toURI());
      if (!xmlFile.exists()) {
        throw new IOException("File make xml doesn't exist.");
      }
      return xmlFile;
    } catch (IOException ioE) {
      throw new XMLoaderException(ioE,
          "IO Error got when tried to read xml file.\nFile: " + this.xmlMakeFile);
    } catch (URISyntaxException uriE) {
      throw new XMLoaderException(uriE, "URI wrong in jar file for " + this.xmlMakeFile + " file.");
    }
  }

  /**
   * Retorna todas as factories dentro do make.xml
   * 
   * @return
   */
  public List<String> getFactories() {
    List<String> factories = new ArrayList<String>();
    NodeList nList = this.document.getElementsByTagName("factories");
    for (int i = 0; i < nList.getLength(); i++) {
      Node nNode = nList.item(i);
      if (Node.ELEMENT_NODE == nNode.getNodeType()) {
        Element element = (Element) nNode;
        NodeList factoriesNode = element.getElementsByTagName("factory");
        for (int ii = 0; ii < nList.getLength(); ii++) {
          factories.add(factoriesNode.item(ii).getTextContent());
        }
      }
    }
    return factories;
  }

  /**
   * Retorna um map mapeado da seguinte forma: key <> value<br>
   * br.com.gvt.testes.MockEntities.enities.Employee.age <> between{18,69}
   * br.com.gvt.testes.MockEntities.enities.Employee.name <> isName Todos os testes serão incluidos,
   * a busca ira comecar pelo primeiro teste do array e caso a mesma classe for utilizada em dois ou
   * mais testes a do primeiro teste será mantida.
   * 
   * @param testName
   * @return
   */
  public Map<String, Map<String, String>> getMapRulesFieldsByTestName(String... testName) {
    Map<String, Map<String, String>> testsParans = new HashMap<String, Map<String, String>>();
    for (int i = 0; i < testName.length; i++) {
      Map<String, Map<String, String>> testParans = this.getMapFielsByTestName(testName[i]);
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
   * Faz o trabaho unitário para o metodo chamador.
   * 
   * @param testName
   * @return
   */
  private Map<String, Map<String, String>> getMapFielsByTestName(String testName) {
    Map<String, Map<String, String>> testsParans = new HashMap<String, Map<String, String>>();
    NodeList nTests = this.document.getElementsByTagName("test");// <test>
    for (int i = 0; i < nTests.getLength(); i++) {
      NodeList nTestChilds = nTests.item(i).getChildNodes();// names||entities
      List<NodeList> testList = this.recoverTestNodeByName(testName, nTestChilds);
      List<Node> entityList = this.recoverEntityNodes(nTestChilds, testList);
      this.populateMapWithEntityRules(testsParans, entityList);
    }
    return testsParans;
  }

  /**
   * Irá retornar todas os nodes <test> que contenham o nome especificado dentro do node <name> de
   * <names>
   * 
   * @param testName
   * @param nodeTestChilds
   * @return
   */
  private List<NodeList> recoverTestNodeByName(String testName, NodeList nodeTestChilds) {
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
   * Recupera todos os nodes <entity> de uma lista de node <test>
   * 
   * @param nodeTestChilds
   * @param testList
   * @return
   */
  private List<Node> recoverEntityNodes(NodeList nodeTestChilds, List<NodeList> testList) {
    List<Node> entityList = new ArrayList<Node>();
    for (NodeList test : testList) {
      for (int ii = 0; ii < test.getLength(); ii++) {
        NodeList entitiesNodes = nodeTestChilds.item(ii).getChildNodes();
        for (int iii = 0; iii < entitiesNodes.getLength(); iii++) {
          if (StringUtils.equals(entitiesNodes.item(iii).getNodeName(), "entity")) { // <entity>
            entityList.add(entitiesNodes.item(iii));
          }
        }
      }
    }
    return entityList;
  }

  /**
   * Insere no map as regras do teste encontradas na tag <entity>.
   * 
   * @param testsParans
   * @param entityList
   */
  private void populateMapWithEntityRules(Map<String, Map<String, String>> testsParans,
      List<Node> entityList) {
    for (Node entity : entityList) {
      String clazz = entity.getAttributes().getNamedItem("class").getTextContent();
      Map<String, String> fieldsRules = new HashMap<String, String>();
      NodeList fields = entity.getChildNodes();
      for (int ii = 0; ii < fields.getLength(); ii++) {
        Node nodeField = fields.item(ii);
        if (Node.ELEMENT_NODE == nodeField.getNodeType()) {
          Element element = (Element) nodeField;
          fieldsRules.put(element.getAttribute("name"), element.getTextContent());
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
   * Recupera as informacoes de setup do framework dentro do node <setup>
   * 
   * @return
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
          setupMap.put(element.getNodeName(), element.getAttribute("value"));
        }
      }
    }
    return setupMap;
  }
}
