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
package org.apache.isis.core.interaction.session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.commons.internal.collections._Lists;

import lombok.val;

/**
 * Holder of queued up messages, to be accessed/drained cross interaction boundary.
 *
 * @implNote Serializable and thread-safe
 *
 * @since 1.x
 */
public class MessageBroker implements Serializable {

    private static final long serialVersionUID = 1L;

    // serializable lock
    private final Object $lock = new Object[0];

    private final List<String> messages = _Lists.newArrayList();
    private final List<String> warnings = _Lists.newArrayList();
    private String applicationError;

    public MessageBroker() {
    }

    // -- RESET

    public void reset() {
        synchronized ($lock) {
            warnings.clear();
            messages.clear();
            applicationError = null;
        }
    }

    // -- MESSAGES & WARNINGS

    public Can<String> drainMessages() {
        return copyAndClear(messages);
    }

    public void addMessage(final String message) {
        addIfNotAlreadyPresent(this.messages, message);
    }

    public Can<String> drainWarnings() {
        return copyAndClear(warnings);
    }

    public void addWarning(final String warning) {
        addIfNotAlreadyPresent(this.warnings, warning);
    }

    private void addIfNotAlreadyPresent(List<String> strings, String string) {
        synchronized ($lock) {
            if (strings.contains(string)) {
                // just ignore it...
                return;
            }
            strings.add(string);
        }
    }

    // -- APPLICATION ERROR

    public Optional<String> drainApplicationError() {
        synchronized ($lock) {
            final String error = applicationError;
            setApplicationError(null);
            return Optional.ofNullable(error);
        }
    }

    public void setApplicationError(String applicationError) {
        synchronized ($lock) {
            this.applicationError = applicationError;
        }
    }


    // -- HELPERS

    private Can<String> copyAndClear(final List<String> messages) {
        synchronized ($lock) {
            val copy = Can.ofCollection(messages);
            messages.clear();
            return copy;
        }
    }

}
