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
package org.apache.isis.testing.fakedata.applib.services;

import org.apache.isis.applib.value.Password;

/**
 * Returns random {@link Password} values, of fixed length.
 * @since 2.0 {@index}
 */
public class IsisPasswords extends AbstractRandomValueGenerator {

    public IsisPasswords(final FakeDataService fakeDataService) {
        super(fakeDataService);
    }

    /**
     * Returns a {@link Password} of 12 characters length.
     */
    public Password any() {
        return any(12);
    }

    public Password any(final int numCharacters) {
        return new Password(fake.strings().fixed(numCharacters));
    }
}
