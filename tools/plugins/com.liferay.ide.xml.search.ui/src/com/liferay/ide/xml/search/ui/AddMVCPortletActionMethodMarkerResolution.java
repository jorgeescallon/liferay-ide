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
package com.liferay.ide.xml.search.ui;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jdt.core.IType;


/**
 * @author Gregory Amerson
 */
public class AddMVCPortletActionMethodMarkerResolution extends AddJSRPortletActionMethodMarkerResolution
{

    private final String CODE = "public void {0}(ActionRequest actionRequest, ActionResponse actionResponse) '{\n}'";
    private final String[] IMPORTS = new String[]
    {
        "javax.portlet.ActionRequest",
        "javax.portlet.ActionResponse"
    };

    public AddMVCPortletActionMethodMarkerResolution( IMarker marker, IType type  )
    {
        super( marker, type );
    }

    @Override
    protected String getCode()
    {
        return CODE;
    }

    protected String[] getImports()
    {
        return IMPORTS;
    }

    @Override
    public String getLabel()
    {
        return "add new Liferay MVC process action method \"" + getTextContent( marker ) + "\"to " +
            this.type.getElementName();
    }

}
