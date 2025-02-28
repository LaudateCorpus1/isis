/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.core.metamodel.facets.value;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.isis.applib.exceptions.recoverable.TextEntryParseException;
import org.apache.isis.core.metamodel.valuesemantics.IntValueSemantics;

public class IntValueSemanticsProviderTest
extends ValueSemanticsProviderAbstractTestCase {

    private IntValueSemantics value;
    private Integer integer;

    @Before
    public void setUpObjects() throws Exception {
        integer = Integer.valueOf(32);
        allowMockAdapterToReturn(integer);

        setSemantics(value = new IntValueSemantics());
    }

    @Test
    public void testInvalidParse() throws Exception {
        try {
            value.parseTextRepresentation(null, "one");
            fail();
        } catch (final TextEntryParseException expected) {
        }
    }

    @Test
    public void testTitleString() {
        assertEquals("32", value.simpleTextPresentation(null, integer));
    }

    @Test
    public void testParse() throws Exception {
        final Object newValue = value.parseTextRepresentation(null, "120");
        assertEquals(Integer.valueOf(120), newValue);
    }

    @Test
    public void testParseOddlyFormedEntry() throws Exception {
        final Object newValue = value.parseTextRepresentation(null, "1,20.0");
        assertEquals(Integer.valueOf(120), newValue);
    }
}
