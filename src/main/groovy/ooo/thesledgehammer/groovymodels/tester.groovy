package ooo.thesledgehammer.groovymodels


import ooo.thesledgehammer.groovymodels.api.gom.GomItem
import ooo.thesledgehammer.groovymodels.api.gom.GomItemProvider
import ooo.thesledgehammer.groovymodels.config.Constants

class tester {

    static void main(String[] args) {
        //SuperGom.init();
        GomItem gom = new GomItem(Constants.SUPER_GOM_PATH, "1.0.2", Constants.GOM_TYPE_DEFAULT);
        GomItemProvider provider = new GomItemProvider(gom, "");
        MinecraftItemObjectModel itemM = new MinecraftItemObjectModel(provider, "");

        String ver = gom.getGOMVersion();
        println(ver);
    }
}
