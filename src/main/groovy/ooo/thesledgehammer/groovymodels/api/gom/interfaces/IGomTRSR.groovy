package ooo.thesledgehammer.groovymodels.api.gom.interfaces

/* Translation Rotation Scale Rotation(TRSR) */
interface IGomTRSR {

    def Display();

    def DisplayName(String name);

    ArrayList<Float> Translation(String name);

    ArrayList<Float> Rotation(String name);

    ArrayList<Float> Scale(String name);
}
