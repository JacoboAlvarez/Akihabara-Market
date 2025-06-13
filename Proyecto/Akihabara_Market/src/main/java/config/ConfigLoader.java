package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties prop;
    
    // Carga las propiedades (ahora privado y más eficiente)
    private static void loadConfig() throws Exception {
        if (prop == null) {
            prop = new Properties();
            try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new Exception("Archivo config.properties no encontrado en el classpath");
                }
                prop.load(input);
            } catch (Exception e) {
                throw new Exception("Error cargando configuración", e);
            }
        }
    }
    
    // Versión de instancia
    public String getProperty(String key) throws Exception {
        return getPropertyAsStatic(key, null);
    }

    // Versión estática [Esta funciona mejor]
    public static String getPropertyAsStatic(String key) throws Exception {
        return getPropertyAsStatic(key, null);
    }
    
    public static String getPropertyAsStatic(String key, String defaultValue) throws Exception {
        loadConfig();
        return prop.getProperty(key, defaultValue);
    }
}
