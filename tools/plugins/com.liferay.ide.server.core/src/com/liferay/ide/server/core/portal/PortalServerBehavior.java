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
import com.liferay.ide.server.core.LiferayServerCore;
import com.liferay.ide.server.util.PingThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.internal.Server;
import org.eclipse.wst.server.core.model.ServerBehaviourDelegate;


/**
 * @author Gregory Amerson
 */
@SuppressWarnings( "restriction" )
public class PortalServerBehavior extends ServerBehaviourDelegate implements IJavaLaunchConfigurationConstants
{
    private static final String[] JMX_EXCLUDE_ARGS = new String []
    {
        "-Dcom.sun.management.jmxremote",
        "-Dcom.sun.management.jmxremote.port=",
        "-Dcom.sun.management.jmxremote.ssl=",
        "-Dcom.sun.management.jmxremote.authenticate="
    };

    private static final String ATTR_STOP = "stop-server";

    private transient IDebugEventSetListener processListener;
    private transient PingThread ping = null;

    public PortalServerBehavior()
    {
        super();
    }

    @Override
    protected void publishServer( int kind, IProgressMonitor monitor ) throws CoreException
    {
        setServerPublishState(IServer.PUBLISH_STATE_NONE);
    }

    @Override
    protected void publishModule(
        final int kind, final int deltaKind, final IModule[] modules, final IProgressMonitor monitor )
        throws CoreException
    {
        PublishOp op = null;

        switch( kind )
        {
            case IServer.PUBLISH_FULL:
                switch( deltaKind )
                {
                    case ServerBehaviourDelegate.ADDED:
                    case ServerBehaviourDelegate.CHANGED:
                        op = new BundlePublishFullAdd( getServer() );
                        break;

                    case ServerBehaviourDelegate.REMOVED:
                        op = new BundlePublishFullRemove( getServer() );
                        break;

                    default:
                        break;
                }
                break;

            default:
                break;
        }

        if( op != null )
        {
            for( IModule module : modules )
            {
                // TODO add a popup notification for CoreEx instead of letting bubble up to user
                IStatus status = op.publish( module, monitor );

                if( status.isOK() )
                {
                    setModulePublishState( new IModule[] { module }, IServer.PUBLISH_STATE_NONE );
                }
            }
        }
    }

    @Override
    public void stop( boolean force )
    {
        if( force )
        {
            terminate();
            return;
        }

        int state = getServer().getServerState();

        // If stopped or stopping, no need to run stop command again
        if (state == IServer.STATE_STOPPED || state == IServer.STATE_STOPPING)
        {
            return;
        }
        else if (state == IServer.STATE_STARTING)
        {
            terminate();
            return;
        }

        try
        {
            if( state != IServer.STATE_STOPPED )
            {
                setServerState( IServer.STATE_STOPPING );
            }

            final ILaunchConfiguration launchConfig = ( (Server) getServer() ).getLaunchConfiguration( true, null );
            final ILaunchConfigurationWorkingCopy wc = launchConfig.getWorkingCopy();

            final String args = renderCommandLine( getRuntimeProgArgs( "stop" ), " " );
            // Remove JMX arguments if present
            final String existingVMArgs =
                wc.getAttribute( IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, (String) null );

            if( existingVMArgs.indexOf( JMX_EXCLUDE_ARGS[0] ) >= 0 )
            {
                wc.setAttribute(
                    IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
                    mergeArguments( existingVMArgs, new String[] {}, JMX_EXCLUDE_ARGS, false ) );
            }

            wc.setAttribute( IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, args );
            wc.setAttribute( "org.eclipse.debug.ui.private", true );
            wc.setAttribute( ATTR_STOP, "true" );
            wc.launch( ILaunchManager.RUN_MODE, new NullProgressMonitor() );
        }
        catch( Exception e )
        {
            LiferayServerCore.logError( "Error stopping portal", e );
        }
    }

    protected static String renderCommandLine( String[] commandLine, String separator )
    {
        if( commandLine == null || commandLine.length < 1 )
        {
            return "";
        }

        StringBuffer buf = new StringBuffer( commandLine[0] );

        for( int i = 1; i < commandLine.length; i++ )
        {
            buf.append( separator );
            buf.append( commandLine[i] );
        }

        return buf.toString();
    }

    protected void terminate()
    {
        if( getServer().getServerState() == IServer.STATE_STOPPED )
        {
            return;
        }

        try
        {
            setServerState( IServer.STATE_STOPPING );

            ILaunch launch = getServer().getLaunch();

            if( launch != null )
            {
                launch.terminate();
                cleanup();
            }
        }
        catch( Exception e )
        {
            LiferayServerCore.logError( "Error killing the process", e );
        }
    }

    @Override
    public void setupLaunchConfiguration( ILaunchConfigurationWorkingCopy launch, IProgressMonitor monitor )
        throws CoreException
    {
        final String existingProgArgs = launch.getAttribute( ATTR_PROGRAM_ARGUMENTS, (String) null );
        launch.setAttribute( ATTR_PROGRAM_ARGUMENTS, mergeArguments( existingProgArgs, getRuntimeProgArgs( PortalServer.START ), null, true ) );

        final String existingVMArgs = launch.getAttribute( ATTR_VM_ARGUMENTS, (String) null );

//      String[] parsedVMArgs = null;
//
//      if( existingVMArgs != null )
//      {
//          parsedVMArgs = DebugPlugin.parseArguments( existingVMArgs );
//      }

        final String[] configVMArgs = getRuntimeVMArguments();
        launch.setAttribute( ATTR_VM_ARGUMENTS, mergeArguments( existingVMArgs, configVMArgs, null, false ) );

        final PortalRuntime portalRuntime = getPortalRuntime();
        final IVMInstall vmInstall = portalRuntime.getVMInstall();

        if( vmInstall != null )
        {
            launch.setAttribute( ATTR_JRE_CONTAINER_PATH, JavaRuntime.newJREContainerPath( vmInstall ).toPortableString() );
        }

        final IRuntimeClasspathEntry[] orgClasspath = JavaRuntime.computeUnresolvedRuntimeClasspath( launch );
        final int orgClasspathSize = orgClasspath.length;

        final List<IRuntimeClasspathEntry> oldCp = new ArrayList<IRuntimeClasspathEntry>( orgClasspathSize );
        Collections.addAll( oldCp, orgClasspath );

        final List<IRuntimeClasspathEntry> runCpEntries = portalRuntime.getRuntimeClasspathEntries();

        for( IRuntimeClasspathEntry cpEntry : runCpEntries )
        {
            mergeClasspath( oldCp, cpEntry );
        }

        if( vmInstall != null )
        {
            try
            {
                final String typeId = vmInstall.getVMInstallType().getId();
                final IRuntimeClasspathEntry newJRECp =
                    JavaRuntime.newRuntimeContainerClasspathEntry(
                        new Path( JavaRuntime.JRE_CONTAINER ).append( typeId ).append( vmInstall.getName() ),
                        IRuntimeClasspathEntry.BOOTSTRAP_CLASSES );
                replaceJREConatiner( oldCp, newJRECp );
            }
            catch( Exception e )
            {
                // ignore
            }

            final IPath jrePath = new Path( vmInstall.getInstallLocation().getAbsolutePath() );

            if( jrePath != null )
            {
                final IPath toolsPath = jrePath.append( "lib/tools.jar" );

                if( toolsPath.toFile().exists() )
                {
                    final IRuntimeClasspathEntry toolsJar = JavaRuntime.newArchiveRuntimeClasspathEntry( toolsPath );
                    // Search for index to any existing tools.jar entry
                    int toolsIndex;

                    for( toolsIndex = 0; toolsIndex < oldCp.size(); toolsIndex++ )
                    {
                        final IRuntimeClasspathEntry entry = oldCp.get( toolsIndex );

                        if( entry.getType() == IRuntimeClasspathEntry.ARCHIVE &&
                            entry.getPath().lastSegment().equals( "tools.jar" ) )
                        {
                            break;
                        }
                    }

                    // If existing tools.jar found, replace in case it's different. Otherwise add.
                    if( toolsIndex < oldCp.size() )
                    {
                        oldCp.set( toolsIndex, toolsJar );
                    }
                    else
                    {
                        mergeClasspath( oldCp, toolsJar );
                    }
                }
            }
        }

        final List<String> cp = new ArrayList<String>();

        for( IRuntimeClasspathEntry entry : oldCp )
        {
            try
            {
                cp.add( entry.getMemento() );
            }
            catch( Exception e )
            {
                LiferayServerCore.logError( "Could not resolve cp entry " + entry, e );
            }
        }

        launch.setAttribute( ATTR_CLASSPATH, cp );
        launch.setAttribute( ATTR_DEFAULT_CLASSPATH, false );
    }

    private void replaceJREConatiner( List<IRuntimeClasspathEntry> oldCp, IRuntimeClasspathEntry newJRECp )
    {
        int size = oldCp.size();

        for( int i = 0; i < size; i++ )
        {
            final IRuntimeClasspathEntry entry2 = oldCp.get( i );

            if( entry2.getPath().uptoSegment( 2 ).isPrefixOf( newJRECp.getPath() ) )
            {
                oldCp.set( i, newJRECp );
                return;
            }
        }

        oldCp.add( 0, newJRECp );
    }

    private void mergeClasspath( List<IRuntimeClasspathEntry> oldCpEntries, IRuntimeClasspathEntry cpEntry )
    {
        for( IRuntimeClasspathEntry oldCpEntry : oldCpEntries )
        {
            if( oldCpEntry.getPath().equals( cpEntry.getPath() ) )
            {
                return;
            }
        }

        oldCpEntries.add( cpEntry );
    }

    private PortalRuntime getPortalRuntime()
    {
        PortalRuntime retval = null;

        if( getServer().getRuntime() != null )
        {
            retval = (PortalRuntime) getServer().getRuntime().loadAdapter( PortalRuntime.class, null );
        }

        return retval;
    }

    private String[] getRuntimeVMArguments()
    {
        final List<String> retval = new ArrayList<String>();

        Collections.addAll( retval, getPortalServer().getMemoryArgs() );

        Collections.addAll( retval, getPortalRuntime().getPortalBundle().getRuntimeVMArgs() );

        return retval.toArray( new String[0] );
    }

    private PortalServer getPortalServer()
    {
        PortalServer retval = null;

        if( getServer() != null )
        {
            retval = (PortalServer) getServer().loadAdapter( PortalServer.class, null );
        }

        return retval;
    }

    private String mergeArguments( final String orgArgsString, final String[] newArgs, final String[] excludeArgs, boolean keepActionLast )
    {
        String retval = null;

        if( CoreUtil.isNullOrEmpty( newArgs ) && CoreUtil.isNullOrEmpty( excludeArgs ) )
        {
            retval = orgArgsString;
        }
        else
        {
            retval = orgArgsString == null ? "" : orgArgsString;

            // replace and null out all newArgs that already exist
            final int size = newArgs.length;

            for( int i = 0; i < size; i++ )
            {
                final int ind = newArgs[i].indexOf( " " );
                final int ind2 = newArgs[i].indexOf( "=" );

                if( ind >= 0 && ( ind2 == -1 || ind < ind2 ) )
                { // -a bc style
                    int index = retval.indexOf( newArgs[i].substring( 0, ind + 1 ) );

                    if( index == 0 || ( index > 0 && Character.isWhitespace( retval.charAt( index - 1 ) ) ) )
                    {
                        // replace
                        String s = retval.substring( 0, index );
                        int index2 = getNextToken( retval, index + ind + 1 );

                        if( index2 >= 0 )
                        {
                            retval = s + newArgs[i] + retval.substring( index2 );
                        }
                        else
                        {
                            retval = s + newArgs[i];
                        }

                        newArgs[i] = null;
                    }
                }
                else if( ind2 >= 0 )
                { // a=b style
                    int index = retval.indexOf( newArgs[i].substring( 0, ind2 + 1 ) );

                    if( index == 0 || ( index > 0 && Character.isWhitespace( retval.charAt( index - 1 ) ) ) )
                    {
                        // replace
                        String s = retval.substring( 0, index );
                        int index2 = getNextToken( retval, index );

                        if( index2 >= 0 )
                        {
                            retval = s + newArgs[i] + retval.substring( index2 );
                        }
                        else
                        {
                            retval = s + newArgs[i];
                        }

                        newArgs[i] = null;
                    }
                }
                else
                { // abc style
                    int index = retval.indexOf( newArgs[i] );

                    if( index == 0 || ( index > 0 && Character.isWhitespace( retval.charAt( index - 1 ) ) ) )
                    {
                        // replace
                        String s = retval.substring( 0, index );
                        int index2 = getNextToken( retval, index );

                        if( !keepActionLast || i < ( size - 1 ) )
                        {
                            if( index2 >= 0 )
                            {
                                retval = s + newArgs[i] + retval.substring( index2 );
                            }
                            else
                            {
                                retval = s + newArgs[i];
                            }

                            newArgs[i] = null;
                        }
                        else
                        {
                            // The last VM argument needs to remain last,
                            // remove original arg and append the vmArg later
                            if( index2 >= 0 )
                            {
                                retval = s + retval.substring( index2 );
                            }
                            else
                            {
                                retval = s;
                            }
                        }
                    }
                }
            }

            // remove excluded arguments
            if( excludeArgs != null && excludeArgs.length > 0 )
            {
                for( int i = 0; i < excludeArgs.length; i++ )
                {
                    int ind = excludeArgs[i].indexOf( " " );
                    int ind2 = excludeArgs[i].indexOf( "=" );

                    if( ind >= 0 && ( ind2 == -1 || ind < ind2 ) )
                    { // -a bc style
                        int index = retval.indexOf( excludeArgs[i].substring( 0, ind + 1 ) );

                        if( index == 0 || ( index > 0 && Character.isWhitespace( retval.charAt( index - 1 ) ) ) )
                        {
                            // remove
                            String s = retval.substring( 0, index );
                            int index2 = getNextToken( retval, index + ind + 1 );

                            if( index2 >= 0 )
                            {
                                // If remainder will become the first argument, remove leading blanks
                                while( index2 < retval.length() &&
                                    Character.isWhitespace( retval.charAt( index2 ) ) )
                                    index2 += 1;
                                retval = s + retval.substring( index2 );
                            }
                            else
                                retval = s;
                        }
                    }
                    else if( ind2 >= 0 )
                    { // a=b style
                        int index = retval.indexOf( excludeArgs[i].substring( 0, ind2 + 1 ) );

                        if( index == 0 || ( index > 0 && Character.isWhitespace( retval.charAt( index - 1 ) ) ) )
                        {
                            // remove
                            String s = retval.substring( 0, index );
                            int index2 = getNextToken( retval, index );

                            if( index2 >= 0 )
                            {
                                // If remainder will become the first argument, remove leading blanks
                                while( index2 < retval.length() &&
                                    Character.isWhitespace( retval.charAt( index2 ) ) )
                                    index2 += 1;
                                retval = s + retval.substring( index2 );
                            }
                            else
                                retval = s;
                        }
                    }
                    else
                    { // abc style
                        int index = retval.indexOf( excludeArgs[i] );

                        if( index == 0 || ( index > 0 && Character.isWhitespace( retval.charAt( index - 1 ) ) ) )
                        {
                            // remove
                            String s = retval.substring( 0, index );
                            int index2 = getNextToken( retval, index );
                            if( index2 >= 0 )
                            {
                                // Remove leading blanks
                                while( index2 < retval.length() &&
                                    Character.isWhitespace( retval.charAt( index2 ) ) )
                                    index2 += 1;
                                retval = s + retval.substring( index2 );
                            }
                            else
                                retval = s;
                        }
                    }
                }
            }

            // add remaining vmargs to the end
            for( int i = 0; i < size; i++ )
            {
                if( newArgs[i] != null )
                {
                    if( retval.length() > 0 && !retval.endsWith( " " ) )
                    {
                        retval += " ";
                    }

                    retval += newArgs[i];
                }
            }
        }

        return retval;
    }

    private int getNextToken( String s, int start )
    {
        int i = start;
        int length = s.length();
        char lookFor = ' ';

        while( i < length )
        {
            char c = s.charAt( i );

            if( lookFor == c )
            {
                if( lookFor == '"' )
                    return i + 1;
                return i;
            }

            if( c == '"' )
            {
                lookFor = '"';
            }

            i++;
        }
        return -1;
    }

    private String[] getRuntimeProgArgs( String launchMode )
    {
        return getPortalRuntime().getPortalBundle().getRuntimeProgArgs( launchMode );
    }

    public String getClassToLaunch()
    {
        return "org.apache.catalina.startup.Bootstrap";
    }

    public void launchServer( ILaunch launch, String mode, IProgressMonitor monitor ) throws CoreException
    {
        if( "true".equals( launch.getLaunchConfiguration().getAttribute( ATTR_STOP, "false" ) ) )
        {
            return;
        }

        final IStatus status = getPortalRuntime().validate();

        if( status != null && status.getSeverity() == IStatus.ERROR ) throw new CoreException( status );

        // TODO check that ports are free

        setServerRestartState( false );
        setServerState( IServer.STATE_STARTING );
        setMode( mode );

        try
        {
            String url = "http://" + getServer().getHost();
            // TODO int port = configuration.getMainPort().getPort();
            final int port = 8080;

            if( port != 80 )
            {
                url += ":" + port;
            }

            ping = new PingThread( getServer(), url, -1, this );
        }
        catch( Exception e )
        {
            LiferayServerCore.logError( "Can't ping for portal startup." );
        }
    }

    public void setServerStarted()
    {
        setServerState( IServer.STATE_STARTED );
    }

    public void cleanup()
    {
        if( ping != null )
        {
            ping.stop();
            ping = null;
        }

        if( processListener != null )
        {
            DebugPlugin.getDefault().removeDebugEventListener( processListener );
            processListener = null;
        }

        setServerState( IServer.STATE_STOPPED );
    }

    public void addProcessListener( final IProcess newProcess )
    {
        if( processListener != null || newProcess == null )
        {
            return;
        }

        processListener = new IDebugEventSetListener()
        {
            public void handleDebugEvents( DebugEvent[] events )
            {
                if( events != null )
                {
                    for( DebugEvent event : events )
                    {
                        if( newProcess != null && newProcess.equals( event.getSource() ) &&
                            event.getKind() == DebugEvent.TERMINATE )
                        {
                            cleanup();
                        }
                    }
                }
            }
        };

        DebugPlugin.getDefault().addDebugEventListener( processListener );
    }
}
