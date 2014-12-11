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
package com.liferay.ide.maven.core;

import com.liferay.ide.core.util.CoreUtil;

import java.io.File;
import java.util.Set;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.osgi.util.NLS;


/**
 * @author Gregory Amerson
 * @author Simon Jiang
 */
public class BuildCSSBuildParticipant extends ThemePluginBuildParticipant
{

    @Override
    public Set<IProject> build( int kind, IProgressMonitor monitor ) throws Exception
    {
        IProgressMonitor sub = new SubProgressMonitor( monitor, 100 );

        sub.beginTask( Msgs.sassToCssBuilder, 100 );

        final Set<IProject> retval = super.build( kind, monitor );

        sub.done();

        return retval;
    }

    @Override
    protected void configureExecution( IMavenProjectFacade facade, Xpp3Dom config )
    {
        super.configureExecution( facade, config );

        final IPath m2eLiferayFolder =
                        MavenUtil.getM2eLiferayFolder( facade.getMavenProject(), facade.getProject() );
        final IPath themeResourcesFolder =
                        m2eLiferayFolder.append( ILiferayMavenConstants.THEME_RESOURCES_FOLDER );
        // Must use full path because sassDirNames is a string and not a "file"
        final File projectDir = new File( facade.getProject().getLocationURI() );
        final File themeResourcesDir = new File( projectDir, themeResourcesFolder.toOSString() );

        MavenUtil.setConfigValue(
            config, ILiferayMavenConstants.PLUGIN_CONFIG_SASS_DIR_NAMES, themeResourcesDir.getAbsolutePath() );
//        MavenUtil.setConfigValue(
//            config, ILiferayMavenConstants.PLUGIN_CONFIG_SASS_DIR_NAMES, "/../m2e-liferay/theme-resources" );
    }

    @Override
    protected String getGoal()
    {
        return ILiferayMavenConstants.PLUGIN_GOAL_BUILD_CSS;
    }

    @Override
    protected boolean shouldBuild( int kind, IMavenProjectFacade facade )
    {
        boolean retval = false;

        final IResourceDelta delta = this.getDelta( facade.getProject() );

        final String warSourceDirectory = MavenUtil.getWarSourceDirectory( facade );

        if( ! CoreUtil.isNullOrEmpty( warSourceDirectory ) )
        {
            final IPath cssFolderPath =
                facade.getProject().getFolder( warSourceDirectory + "/css" ).getProjectRelativePath();

            if( delta != null && delta.findMember( cssFolderPath ) != null )
            {
                //TODO IDE-1319
                //retval = true;
            }
        }

        return retval;
    }

    private static class Msgs extends NLS
    {
        public static String sassToCssBuilder;

        static
        {
            initializeMessages( BuildCSSBuildParticipant.class.getName(), Msgs.class );
        }
    }

}
