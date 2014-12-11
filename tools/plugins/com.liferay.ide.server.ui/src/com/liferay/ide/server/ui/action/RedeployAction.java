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

package com.liferay.ide.server.ui.action;

import com.liferay.ide.server.core.ILiferayServerBehavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.ui.internal.view.servers.ModuleServer;

/**
 * @author Greg Amerson
 * @author Simon Jiang
 */
@SuppressWarnings( { "restriction", "rawtypes" } )
public class RedeployAction extends AbstractServerRunningAction
{
    private ModuleServer[] selectedModules;

    public RedeployAction()
    {
        super();
    }

    @Override
    protected int getRequiredServerState()
    {
        return IServer.STATE_STARTED | IServer.STATE_STOPPED;
    }

    public void run( IAction action )
    {
        if( selectedModules == null )
        {
            return; // can't do anything if server has not been selected
        }

        if( selectedModules != null )
        {
            for( ModuleServer moduleServer : selectedModules )
            {
                final ILiferayServerBehavior liferayServerBehavior =
                    (ILiferayServerBehavior) moduleServer.getServer().loadAdapter(
                        ILiferayServerBehavior.class, null );

                if( liferayServerBehavior != null )
                {
                    liferayServerBehavior.redeployModule( moduleServer.getModule() );
                }
            }
        }
    }

    @Override
    public void selectionChanged( IAction action, ISelection selection )
    {
        boolean validServerState = true;

        if( !selection.isEmpty() )
        {
            final List<ModuleServer> newModules = new ArrayList<ModuleServer>();

            if( selection instanceof IStructuredSelection )
            {
                final IStructuredSelection obj = (IStructuredSelection) selection;
                final Iterator selectionIterator = obj.iterator();

                while( selectionIterator.hasNext() )
                {
                    ModuleServer moduleServer = (ModuleServer) selectionIterator.next();
                    newModules.add( moduleServer );
                    validServerState =
                        validServerState &&
                            ( ( moduleServer.getServer().getServerState() & getRequiredServerState() ) > 0 );
                }

                this.selectedModules = newModules.toArray( new ModuleServer[0] );

                action.setEnabled( validServerState );
            }
        }
    }
}
