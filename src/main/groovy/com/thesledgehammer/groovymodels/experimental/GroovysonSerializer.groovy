package com.thesledgehammer.groovymodels.experimental

import groovy.json.JsonOutput

interface GroovysonSerializer<T> {

    JsonOutput serializeJson(T obj, FileWriter writer);

    groovy.xml.XmlSlurper serializeXml(T obj, FileWriter writer);
}