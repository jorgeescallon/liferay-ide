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

package com.liferay.ide.portlet.core.jsf;

import com.liferay.ide.core.util.CoreUtil;
import com.liferay.ide.portlet.core.PortletCore;
import com.liferay.ide.portlet.core.dd.PortletDescriptorHelper;
import com.liferay.ide.portlet.core.operation.NewPortletClassDataModelProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties;
import org.eclipse.osgi.util.NLS;

/**
 * @author Greg Amerson
 * @author Cindy Li
 * @author Simon Jiang
 * @author Terry Jia
 */
@SuppressWarnings( { "restriction", "rawtypes", "unchecked" } )
public class NewJSFPortletClassDataModelProvider extends NewPortletClassDataModelProvider
    implements INewJSFPortletClassDataModelProperties
{

    public NewJSFPortletClassDataModelProvider( boolean fragment )
    {
        super( fragment );
    }

    @Override
    protected Object getInitParams()
    {
        List<ParamValue> initParams = new ArrayList<ParamValue>();

        // if the user is using FacesPortlet and creating XHTMLs then we need to
        // define init-params for each view mode that is checked
        if( getBooleanProperty( CREATE_JSPS ) )
        {
            String[] modes = ALL_JSF_PORTLET_MODES;

            String[] names =
            {
                "javax.portlet.faces.defaultViewId.view",  //$NON-NLS-1$
                "javax.portlet.faces.defaultViewId.edit", //$NON-NLS-1$
                "javax.portlet.faces.defaultViewId.help"  //$NON-NLS-1$
            };

            String[] values = { "/view.xhtml", "/edit.xhtml", "/help.xhtml" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

            ParamValue[] paramVals = createDefaultParamValuesForModes( modes, names, values );

            Collections.addAll( initParams, paramVals );
        }

        return initParams;
    }

    @Override
    public Object getDefaultProperty( String propertyName )
    {
        if( CLASS_NAME.equals( propertyName ) )
        {
            return "NewJSFPortlet"; //$NON-NLS-1$
        }
        else if( JSF_PORTLET_CLASS.equals( propertyName ) )
        {
            return QUALIFIED_JSF_PORTLET;
        }
        else if( INewWebClassDataModelProperties.USE_EXISTING_CLASS.equals( propertyName ) )
        {
            return true; // to prevent a new class from being created
        }
        else if( CREATE_JSPS_FOLDER.equals( propertyName ) )
        {
            return "/views/" + getProperty( PORTLET_NAME ).toString().toLowerCase(); //$NON-NLS-1$
        }
        else if( SHOW_NEW_CLASS_OPTION.equals( propertyName ) )
        {
            return false;
        }
        else if( ICE_FACES.equals( propertyName ) )
        {
            return false;
        }
        else if( LIFERAY_FACES_ALLOY.equals( propertyName ) )
        {
            return false;
        }
        else if( PRIME_FACES.equals( propertyName ) )
        {
            return false;
        }
        else if( RICH_FACES.equals( propertyName ) )
        {
            return false;
        }
        else if( STANDARD_JSF.equals( propertyName ) )
        {
            return true;
        }

        return super.getDefaultProperty( propertyName );
    }

    @Override
    public Set getPropertyNames()
    {
        Set propertyNames = super.getPropertyNames();

        propertyNames.add( ICE_FACES );
        propertyNames.add( JSF_PORTLET_CLASS );
        propertyNames.add( LIFERAY_FACES_ALLOY );
        propertyNames.add( PRIME_FACES );
        propertyNames.add( RICH_FACES );
        propertyNames.add( STANDARD_JSF );

        return propertyNames;
    }

    @Override
    public IStatus validate( String propertyName )
    {
        if( PORTLET_NAME.equals( propertyName ) )
        {
            IStatus status = super.validate( propertyName );

            if( !status.isOK() )
            {
                return status;
            }

            String currentPortletName = getStringProperty( PORTLET_NAME );
            PortletDescriptorHelper helper = new PortletDescriptorHelper( getTargetProject() );

            for( String portletName : helper.getAllPortletNames() )
            {
                if( currentPortletName.equals( portletName ) )
                {
                    return PortletCore.createErrorStatus( Msgs.duplicatePortletName );
                }
            }
        }

        if( JSF_PORTLET_CLASS.equals( propertyName ) )
        {
            String jsfPortletClass = getStringProperty( propertyName );

            if( CoreUtil.isNullOrEmpty( jsfPortletClass ) )
            {
                return PortletCore.createErrorStatus( Msgs.specifyJSFPortletClass );
            }

            if( this.fragment ) //won't be fragment right now, just in case
            {
                return Status.OK_STATUS;
            }

            if( ! isValidPortletClass( jsfPortletClass ) )
            {
                return PortletCore.createErrorStatus( Msgs.jsfPortletClassValid );
            }
        }

        //IDE-1412
        //Because JSF Portlet wizard doesn't need to to check MVCPortlet super class, just return OK.
        if( SUPERCLASS.equals( propertyName ) )
        {
            return Status.OK_STATUS;
        }

        return super.validate( propertyName );
    }

    private static class Msgs extends NLS
    {
        public static String duplicatePortletName;
        public static String jsfPortletClassValid;
        public static String specifyJSFPortletClass;

        static
        {
            initializeMessages( NewJSFPortletClassDataModelProvider.class.getName(), Msgs.class );
        }
    }
}
