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

package com.liferay.ide.project.ui.action;

import com.liferay.ide.project.ui.ProjectUI;

import java.net.URL;

import org.eclipse.sapphire.ui.Presentation;
import org.eclipse.sapphire.ui.SapphireAction;
import org.eclipse.sapphire.ui.SapphireActionHandler;
import org.eclipse.sapphire.ui.def.ActionHandlerDef;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;


/**
 * @author Kuo Zhang
 */
public class LinkToPluginDocAction extends SapphireActionHandler
{
    private String url;

    @Override
    public void init( SapphireAction action, ActionHandlerDef def )
    {
        super.init( action, def );

        url = def.getParam( "url" );
    }

    @Override
    protected Object run( Presentation context )
    {
        final IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();

        try
        {
            support.getExternalBrowser().openURL( new URL( url ) );

        }
        catch( Exception ex )
        {
            ProjectUI.logError( "Could not open external browser.", ex );
        }

        return null;
    }

}
