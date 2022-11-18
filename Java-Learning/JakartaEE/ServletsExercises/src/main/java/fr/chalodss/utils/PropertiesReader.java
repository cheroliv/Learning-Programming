package fr.chalodss.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Charles T.
 * 
 */
public final class PropertiesReader {

  private static final PropertiesReader reader = new PropertiesReader();
  private Properties                    props  = new Properties();

  private PropertiesReader() {
    try {
      initProperties();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static PropertiesReader getInstance() {
    return reader;
  }

  public void initProperties() throws IOException {
    var classLoader = Thread.currentThread().getContextClassLoader();
    var input       = classLoader.getResourceAsStream("resources.properties");

    props.load(input);
  }

  public Properties getProps() {
    return props;
  }

  public void setProperties(Properties props) {
    this.props = props;
  }

}
