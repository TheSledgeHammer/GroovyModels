package subproject.com.thesledgehammer.groovymodels.client.model.gom

interface GomDeserializer<T> {

    def Deserialize(String path, String version, String type);
}