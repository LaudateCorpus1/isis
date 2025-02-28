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
package org.apache.isis.tooling.model4adoc.ast;

import java.util.ArrayList;
import java.util.List;

import org.asciidoctor.ast.Column;
import org.asciidoctor.ast.Row;
import org.asciidoctor.ast.Table;

import lombok.Getter;
import lombok.Setter;

public class SimpleTable extends SimpleStructuralNode implements Table {

    @Getter @Setter private String frame;
    @Getter @Setter private String grid;
    @Getter private final List<Column> columns = new ArrayList<>();
    @Getter private final List<Row> header = new ArrayList<>();
    @Getter private final List<Row> body = new ArrayList<>();
    @Getter private final List<Row> footer = new ArrayList<>();


    @Override
    public boolean hasHeaderOption() {
        return false;
    }

}
