/*******************************************************************************
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/

package com.liferay.ide.hook.core.operation;

import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;

/**
 * @author Greg Amerson
 * @author Terry Jia
 */
@SuppressWarnings( "restriction" )
public interface INewHookDataModelProperties extends IArtifactEditOperationDataModelProperties
{

    // new language properties
    public static final String CONTENT_FOLDER = "INewHookDataModelProperties.CONTENT_FOLDER"; //$NON-NLS-1$

    // new hook type properties
    public static final String CREATE_CUSTOM_JSPS = "INewHookDataModelProperties.CREATE_CUSTOM_JSPS"; //$NON-NLS-1$

    public static final String CREATE_LANGUAGE_PROPERTIES = "INewHookDataModelProperties.CREATE_LANGUAGE_PROPERTIES"; //$NON-NLS-1$

    public static final String CREATE_PORTAL_PROPERTIES = "INewHookDataModelProperties.CREATE_PORTAL_PROPERTIES"; //$NON-NLS-1$

    public static final String CREATE_SERVICES = "INewHookDataModelProperties.CREATE_SERVICES"; //$NON-NLS-1$

    public static final String CUSTOM_JSPS_FILES_CREATED = "INewHookDataModelProperties.CUSTOM_JSPS_FILES_CREATED"; //$NON-NLS-1$

    // new custom jsps properties
    public static final String CUSTOM_JSPS_FOLDER = "INewHookDataModelProperties.CUSTOM_JSPS_FOLDER"; //$NON-NLS-1$

    public static final String CUSTOM_JSPS_ITEMS = "INewHookDataModelProperties.CUSTOM_JSPS_ITEMS"; //$NON-NLS-1$

    public static final String DISABLE_CUSTOM_JSP_FOLDER_VALIDATION =
        "INewHookDataModelProperties.DISABLE_CUSTOM_JSP_FOLDER_VALIDATION"; //$NON-NLS-1$

    public static final String LANGUAGE_PROPERTIES_FILES_CREATED =
        "INewHookDataModelProperties.LANGUAGE_PROPERTIES_FILES_CREATED"; //$NON-NLS-1$

    public static final String LANGUAGE_PROPERTIES_ITEMS = "INewHookDataModelProperties.LANGUAGE_PROPERTIES_ITEMS"; //$NON-NLS-1$

    public static final String PORTAL_PROPERTIES_ACTION_ITEMS =
        "INewHookDataModelProperties.PORTAL_PROPERTIES_ACTIONS_ITEMS"; //$NON-NLS-1$

    // new portal properties
    public static final String PORTAL_PROPERTIES_FILE = "INewHookDataModelProperties.PORTAL_PROPERTIES_FILE"; //$NON-NLS-1$

    public static final String PORTAL_PROPERTIES_OVERRIDE_ITEMS =
        "INewHookDataModelProperties.PORTAL_PROPERTIES_OVERRIDE_ITEMS"; //$NON-NLS-1$

    public static final String SELECTED_PROJECT = "INewHookDataModelProperties.SELECTED_PROJECT"; //$NON-NLS-1$

    // new services
    public static final String SERVICES_ITEMS = "INewHookDataModelProperties.SERVICES_ITEMS"; //$NON-NLS-1$

    public static final String WEB_ROOT_FOLDER = "INewHookDataModelProperties.WEB_ROOT_FOLDER"; //$NON-NLS-1$

}
