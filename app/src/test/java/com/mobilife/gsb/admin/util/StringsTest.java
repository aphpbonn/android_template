package com.mobilife.gsb.admin.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by thawornlimwattanachai on 11/15/2016 AD.
 */
public class StringsTest {

    @Test
    public void isBlank(){

        String a = "";
        String b = "1234";

        assertTrue(Strings.isBlank(null));
        assertTrue(Strings.isBlank(a));
        assertFalse(Strings.isBlank(b));
    }

    @Test
    public void isEmpty(){

        String a = "";
        String b = "1234";

        assertTrue(Strings.isEmpty(null));
        assertTrue(Strings.isEmpty(a));
        assertFalse(Strings.isEmpty(b));
    }

    @Test
    public void isEqual(){

        String a = "";
        String b = "1234";

        assertTrue(Strings.isEquals(null,null));
        assertTrue(Strings.isEquals("", ""));
        assertFalse(Strings.isEquals("", "12312"));
    }

    @Test
    public void length() {
        assertEquals(0,Strings.length(""));
        assertEquals(4,Strings.length("1234"));
        assertEquals(0,Strings.length(null));
    }

    @Test
    public void testMD5() {
        assertEquals("f561aaf6ef0bf14d4208bb46a4ccb3ad",Strings.md5("xxx"));
    }

//    @Test
//    public void capitalizeFirstLetter() {
//        assertTrue(Strings.capitalizeFirstLetter(""));
//        assertFalse(Strings.capitalizeFirstLetter(""));
//
//    }

}