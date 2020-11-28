package subproject.com.thesledgehammer.groovymodels.client.model.gom

interface GomSerializer<T> {

    void Serialize(String path, String version, String type);
}