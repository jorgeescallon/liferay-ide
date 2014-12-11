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

package com.liferay.ide.project.ui;

import com.liferay.ide.project.core.model.LiferayPluginSDKOp;
import com.liferay.ide.sdk.core.SDKUtil;
import com.liferay.ide.ui.util.UIUtil;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sapphire.ui.def.DefinitionLoader;
import org.eclipse.sapphire.ui.def.DefinitionLoader.Reference;
import org.eclipse.sapphire.ui.forms.DialogDef;
import org.eclipse.sapphire.ui.forms.swt.SapphireDialog;
import org.eclipse.ui.IMarkerResolution;

/**
 * @author Simon Jiang
 */
public class PluginsSDKNotSetResolution implements IMarkerResolution
{

    public String getLabel()
    {
        return Msgs.setSDKForProject;
    }

    public void run( IMarker marker )
    {
        if( marker.getResource() instanceof IProject )
        {
            final IProject proj = (IProject) marker.getResource();

            final LiferayPluginSDKOp op =
                ( ( LiferayPluginSDKOp ) ( LiferayPluginSDKOp.TYPE.instantiate().initialize() ) );
            final Reference<DialogDef> dialogRef =
                DefinitionLoader.context( this.getClass().getClassLoader() ).sdef(
                    "com.liferay.ide.project.ui.dialog.SelectPluginsSDKDialog" ).dialog( "ConfigureLiferaySDK" );
            final SapphireDialog dialog = new SapphireDialog( UIUtil.getActiveShell(), op, dialogRef );

            dialog.setBlockOnOpen( true );
            final int result = dialog.open();

            if( result != SapphireDialog.CANCEL )
            {
                String sdkName = op.getPluginsSDKName().content();
                SDKUtil.saveSDKNameSetting( proj, sdkName );
            }
        }
    }

    private static class Msgs extends NLS
    {
        public static String setSDKForProject;

        static
        {
            initializeMessages( PluginsSDKNotSetResolution.class.getName(), Msgs.class );
        }
    }
}
