package fr.chalodss.classes;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * @author Charles T.
 *
 */
public final class XmlFile {

  private XmlFile() {

  }

  public static void convertJavaToXml(Object o, String file) throws JAXBException {
    var jaxbContext    = JAXBContext.newInstance(o.getClass());
    var jaxbMarshaller = jaxbContext.createMarshaller();

    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    jaxbMarshaller.marshal(o, new File(file));
  }

  public static Object convertXmlToJava(Class<?> c, String file) throws JAXBException {
    var jaxbContext      = JAXBContext.newInstance(c);
    var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    var xmlFile          = new File(file);

    return jaxbUnmarshaller.unmarshal(xmlFile);
  }

}
