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
package demoapp.dom.domain.actions.Action.commandPublishing;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.MemberSupport;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;

//tag::class[]
@Action(
    commandPublishing = Publishing.ENABLED        // <.>
    , semantics = SemanticsOf.IDEMPOTENT
)
@ActionLayout(
    named = "Mixin Update Property"
    , describedAs = "@Action(command = ENABLED)"
    , associateWith = "property"
    , sequence = "2"
)
public class ActionCommandPublishingEntity_mixinUpdateProperty {
    // ...
//end::class[]

    private final ActionCommandPublishingEntity actionCommandEntity;

    public ActionCommandPublishingEntity_mixinUpdateProperty(final ActionCommandPublishingEntity actionCommandEntity) {
        this.actionCommandEntity = actionCommandEntity;
    }

    @MemberSupport public ActionCommandPublishingEntity act(final String value) {
        actionCommandEntity.setProperty(value);
        return actionCommandEntity;
    }
    @MemberSupport public String default0Act() {
        return actionCommandEntity.getProperty();
    }
//tag::class[]
}
//end::class[]
