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

import com.liferay.ide.core.util.CoreUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @author Terry Jia
 */
public class AddResourceKeyMarkerResolution extends AbstractResourceBundleMarkerResolution
{

    private IFile resourceBundle = null;

    public AddResourceKeyMarkerResolution( IMarker marker, IFile languageFile )
    {
        super( marker );

        this.resourceBundle = languageFile;
    }

    public String getLabel()
    {
        final StringBuffer sb = new StringBuffer();

        sb.append( "Add missing key to " );
        sb.append( resourceBundle.getProjectRelativePath().toString() );

        return sb.toString();
    }

    public Image getImage()
    {
        final URL url = LiferayXMLSearchUI.getDefault().getBundle().getEntry( "/icons/resource-bundle.png" );

        return ImageDescriptor.createFromURL( url ).createImage();
    }

    protected void resolve( final IMarker marker )
    {
        final String message = marker.getAttribute( IMarker.MESSAGE, "" );

        if( ( message == null ) || ( resourceBundle == null ) )
        {
            return;
        }

        InputStream is = null;

        try
        {
            is = resourceBundle.getContents();

            final String languageKey = getResourceKey( marker );

            if( CoreUtil.isNullOrEmpty( languageKey ) )
            {
                return;
            }

            final Properties properties = new Properties();

            properties.load( is );

            if( properties.get( languageKey ) != null )
            {
                return;
            }

            final String resourceValue = getDefaultResourceValue( languageKey );

            final String resourcePropertyLine = languageKey + "=" + resourceValue;

            final String contents = CoreUtil.readStreamToString( resourceBundle.getContents() );

            final StringBuffer contentSb = new StringBuffer();

            contentSb.append( contents );

            if( !contents.endsWith( "\n" ) )
            {
                contentSb.append( "\n" );
            }

            contentSb.append( resourcePropertyLine );

            resourceBundle.setContents(
                new ByteArrayInputStream( contentSb.toString().trim().getBytes( "UTF-8" ) ), IResource.FORCE,
                new NullProgressMonitor() );

            openEditor( resourceBundle );
        }
        catch( Exception e )
        {
            LiferayXMLSearchUI.logError( e );
        }
        finally
        {
            if( is != null )
            {
                try
                {
                    is.close();
                }
                catch( IOException e )
                {
                }
            }
        }
    }

}
