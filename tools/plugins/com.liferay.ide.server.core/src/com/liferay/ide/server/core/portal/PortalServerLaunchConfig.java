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
package com.liferay.ide.server.core.portal;

import com.liferay.ide.core.util.CoreUtil;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMRunner;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerUtil;


/**
 * @author Gregory Amerson
 */
public class PortalServerLaunchConfig extends AbstractJavaLaunchConfigurationDelegate
{

    public void launch( ILaunchConfiguration config, String mode, ILaunch launch, IProgressMonitor monitor )
        throws CoreException
    {
        final IServer server = ServerUtil.getServer( config );

        if( server != null )
        {
            if( server.shouldPublish() && ServerCore.isAutoPublishing() )
            {
                server.publish( IServer.PUBLISH_INCREMENTAL, monitor );
            }

            launchServer( server, config, mode, launch, monitor );
        }
    }

    private void launchServer(
        final IServer server, final ILaunchConfiguration config, final String mode, final ILaunch launch,
        final IProgressMonitor monitor ) throws CoreException
    {
        final IVMInstall vm = verifyVMInstall( config );

        final IVMRunner runner =
            vm.getVMRunner( mode ) != null ? vm.getVMRunner( mode ) : vm.getVMRunner( ILaunchManager.RUN_MODE );

        final File workingDir = verifyWorkingDirectory( config );
        final String workingDirPath = workingDir != null ? workingDir.getAbsolutePath() : null;

        final String progArgs = getProgramArguments( config );
        final String vmArgs = getVMArguments( config );
        final String[] envp = getEnvironment( config );

        final ExecutionArguments execArgs = new ExecutionArguments( vmArgs, progArgs );

        final Map<String, Object> vmAttributesMap = getVMSpecificAttributesMap( config );

        final PortalServerBehavior portalServer =
            (PortalServerBehavior) server.loadAdapter ( PortalServerBehavior.class, monitor );

        final String classToLaunch = portalServer.getClassToLaunch();
        final String[] classpath = getClasspath( config );

        final VMRunnerConfiguration runConfig = new VMRunnerConfiguration( classToLaunch, classpath );
        runConfig.setProgramArguments( execArgs.getProgramArgumentsArray() );
        runConfig.setVMArguments( execArgs.getVMArgumentsArray() );
        runConfig.setWorkingDirectory( workingDirPath );
        runConfig.setEnvironment( envp );
        runConfig.setVMSpecificAttributesMap( vmAttributesMap );

        final String[] bootpath = getBootpath( config );

        if( ! CoreUtil.isNullOrEmpty( bootpath ) )
        {
            runConfig.setBootClassPath( bootpath );
        }

        setDefaultSourceLocator( launch, config );

        portalServer.launchServer( launch, mode, monitor );

        try
        {
            runner.run( runConfig, launch, monitor );
            portalServer.addProcessListener( launch.getProcesses()[0] );
        }
        catch( Exception e )
        {
            portalServer.cleanup();
        }
    }

}
