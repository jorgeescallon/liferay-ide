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

package com.liferay.ide.portlet.core.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.internal.web.operations.CreateWebClassTemplateModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author Greg Amerson
 * @author Cindy Li
 * @author Simon Jiang
 */
@SuppressWarnings( "restriction" )
public class CreatePortletTemplateModel extends CreateWebClassTemplateModel
{
    protected boolean generateGenericInclude = false;

    public CreatePortletTemplateModel( IDataModel dataModel )
    {
        super( dataModel );
    }

    public String getClassName()
    {
        return dataModel.getStringProperty( INewPortletClassDataModelProperties.CLASS_NAME );
    }

    @Override
    public Collection<String> getImports()
    {
        final List<String> collectionList = new ArrayList<String>();

        for( String importItem : super.getImports() )
        {
            if( importItem.contains( "<" ) && importItem.contains( ">" ) )
            {
                continue;
            }

            collectionList.add( importItem );
        }

        if( !isMVCPortletSuperclass() )
        {
            collectionList.add( "java.io.IOException" ); //$NON-NLS-1$
            collectionList.add( "javax.portlet.PortletException" ); //$NON-NLS-1$
            // collection.add("javax.portlet.PortletRequest");
            collectionList.add( "javax.portlet.PortletRequestDispatcher" ); //$NON-NLS-1$
            // collection.add("javax.portlet.PortletResponse");
            collectionList.add( "javax.portlet.RenderRequest" ); //$NON-NLS-1$
            collectionList.add( "javax.portlet.RenderResponse" ); //$NON-NLS-1$
            collectionList.add( "com.liferay.portal.kernel.log.Log" ); //$NON-NLS-1$
            collectionList.add( "com.liferay.portal.kernel.log.LogFactoryUtil" ); //$NON-NLS-1$
        }

        if( shouldGenerateOverride( INewPortletClassDataModelProperties.PROCESSACTION_OVERRIDE ) )
        {
            collectionList.add( "javax.portlet.ActionRequest" ); //$NON-NLS-1$
            collectionList.add( "javax.portlet.ActionResponse" ); //$NON-NLS-1$
        }

        if( shouldGenerateOverride( INewPortletClassDataModelProperties.SERVERESOURCE_OVERRIDE ) )
        {
            collectionList.add( "javax.portlet.ResourceRequest" ); //$NON-NLS-1$
            collectionList.add( "javax.portlet.ResourceResponse" ); //$NON-NLS-1$
        }

        return collectionList;
    }

    public String getInitParameterName()
    {
        return dataModel.getStringProperty( INewPortletClassDataModelProperties.INIT_PARAMETER_NAME );
    }

    public boolean hasPortletMode( String portletModeProperty )
    {
        return dataModel.getBooleanProperty( portletModeProperty );
    }

    public boolean isGenericPortletSuperclass()
    {
        return isGenericPortletSuperclass( false );
    }

    public boolean isGenericPortletSuperclass( boolean checkHierarchy )
    {
        return PortletSupertypesValidator.isGenericPortletSuperclass( dataModel, checkHierarchy );
    }

    public boolean isLiferayPortletSuperclass()
    {
        return PortletSupertypesValidator.isLiferayPortletSuperclass( dataModel );
    }

    public boolean isMVCPortletSuperclass()
    {
        return PortletSupertypesValidator.isMVCPortletSuperclass( dataModel );
    }

    public void setGenerateGenericInclude( boolean include )
    {
        this.generateGenericInclude = include;
    }

    public boolean shouldGenerateGenericInclude()
    {
        return this.generateGenericInclude;
    }

    public boolean shouldGenerateOverride( String generateProperty )
    {
        if( isMVCPortletSuperclass() )
        {
            return false;
        }

        return dataModel.getBooleanProperty( generateProperty );
    }

}
