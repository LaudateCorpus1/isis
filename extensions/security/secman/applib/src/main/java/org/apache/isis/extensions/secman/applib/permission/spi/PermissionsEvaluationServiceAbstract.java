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
package org.apache.isis.extensions.secman.applib.permission.spi;

import java.util.Collection;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.commons.internal.base._NullSafe;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionValue;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionValueSet;

/**
 * @since 2.0 {@index}
 */
public abstract class PermissionsEvaluationServiceAbstract
implements PermissionsEvaluationService {

    private static final long serialVersionUID = 1L;

    @Override
    public ApplicationPermissionValueSet.Evaluation evaluate(
            final ApplicationFeatureId targetMemberId,
            final ApplicationPermissionMode mode,
            final Collection<ApplicationPermissionValue> permissionValues) {

        if(_NullSafe.isEmpty(permissionValues)) {
            return null;
        }

        final Collection<ApplicationPermissionValue> ordered = ordered(permissionValues);

        for (final ApplicationPermissionValue permissionValue : ordered) {
            if(permissionValue.implies(targetMemberId, mode)) {
                return new ApplicationPermissionValueSet.Evaluation(permissionValue, true);
            } else if(permissionValue.refutes(targetMemberId, mode)) {
                return new ApplicationPermissionValueSet.Evaluation(permissionValue, false);
            }
        }
        return null;
    }

    protected abstract Collection<ApplicationPermissionValue> ordered(
            Collection<ApplicationPermissionValue> permissionValues);

}
