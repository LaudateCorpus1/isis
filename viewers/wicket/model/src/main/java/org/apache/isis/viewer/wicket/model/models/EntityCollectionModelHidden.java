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
package org.apache.isis.viewer.wicket.model.models;

import lombok.NonNull;

public class EntityCollectionModelHidden
extends EntityCollectionModelAbstract {

    private static final long serialVersionUID = 1L;

    public static EntityCollectionModelHidden forCollectionModel(
            final @NonNull EntityCollectionModelAbstract collectionModel) {
        return new EntityCollectionModelHidden(collectionModel);
    }

    protected EntityCollectionModelHidden(
            final @NonNull EntityCollectionModelAbstract collectionModel) {
        super(collectionModel.delegate(), collectionModel.getVariant());
    }

    @Override
    public int getCount() {
        return 0;
    }

}
