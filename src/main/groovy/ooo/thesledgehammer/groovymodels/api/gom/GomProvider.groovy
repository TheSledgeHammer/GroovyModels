package ooo.thesledgehammer.groovymodels.api.gom

import ooo.thesledgehammer.groovymodels.api.gom.interfaces.IGomProvider

abstract class GomProvider extends GomPart implements IGomProvider {

    GomProvider(GomItem item, String elem) {
        super(item, elem)
    }

    GomProvider(GomBlock block, String elem) {
        super(block, elem)
    }
}
