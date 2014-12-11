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
package com.liferay.ide.xml.search.ui.validators;

import com.liferay.ide.core.ILiferayConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xml.search.editor.validation.XMLReferencesBatchValidator;


/**
 * @author Kuo Zhang
 */
public class LiferayLayoutTplDescriptorValidator extends LiferayBaseValidator
{
    public static final String MARKER_TYPE = "com.liferay.ide.xml.search.ui.liferayLayoutTplDescriptorMarker";

    @Override
    protected void setMarker( IValidator validator, IFile file )
    {
        if( validator instanceof XMLReferencesBatchValidator &&
            ILiferayConstants.LIFERAY_LAYOUTTPL_XML_FILE.equals( file.getName() ) )
        {
            ( (XMLReferencesBatchValidator) validator ).getParent().setMarkerId( MARKER_TYPE );
        }
    }

}
