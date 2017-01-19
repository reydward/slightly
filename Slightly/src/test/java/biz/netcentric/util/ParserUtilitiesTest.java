/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.netcentric.util;

import biz.netcentric.bean.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.nodes.Document;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edureyes1
 */
public class ParserUtilitiesTest {
    
    public ParserUtilitiesTest() {
    }
    
    /**
     * Test of getJsVarNames method, of class ParserUtilities.
     */
    @Test
    public void testGetJsVarNames() {
        StringBuilder html = new StringBuilder("var Person = Java.type('biz.netcentric.bean.Person');");
        html.append("var id = getParameterByName('id');");
	html.append("var person = Person.lookup(id);");
			        
        ParserUtilities instance = new ParserUtilities();
        List<String> expResult = new ArrayList<String>();
        expResult.add("id");
        expResult.add("person");
        
        List<String> result = instance.getJsVarNames(html.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getExpressionValue method, of class ParserUtilities.
     */
    @Test
    public void testGetExpressionValue() throws Exception {
        String dataAttribute = "person.name";
        HashMap<String, Object> jsObjects = new HashMap<String, Object>();
        jsObjects.put("person", new Person("Name 1", "Spouse 1", false, 1));
        
        ParserUtilities instance = new ParserUtilities();
        String expResult = "Name 1";
        String result = instance.getExpressionValue(dataAttribute, jsObjects);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttributeObject method, of class ParserUtilities.
     */
    @Test
    public void testGetAttributeObject() {
        String dataAttribute = "person.name";
        HashMap<String, Object> jsObjects = new HashMap<String, Object>();
        Person person = new Person("Name 1", "Spouse 1", false, 1);
        jsObjects.put("person", person);
        ParserUtilities instance = new ParserUtilities();
        Object[] expResult = {person, "Name"};
        Object[] result = instance.getAttributeObject(dataAttribute, jsObjects);
        assertArrayEquals(expResult, result);
    }

}
