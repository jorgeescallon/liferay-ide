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
package com.liferay.ide.maven.ui;

import com.liferay.ide.maven.core.ILiferayMavenConstants;
import com.liferay.ide.maven.core.MavenUtil;

import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

/**
 * @author Kuo Zhang
 */
public class MavenConfigProblemMarkerResolutionGenerator extends ConfigProblemMarkerResolutionGenerator
{

    @Override
    protected boolean correctMarker( IMarker marker )
    {
        boolean retval = false;

        final IProject project = marker.getResource().getProject();

        if( project != null && project.exists() )
        {
            final IMavenProjectFacade projectFacade =
                MavenPlugin.getMavenProjectRegistry().getProject( marker.getResource().getProject() );

            if( projectFacade != null )
            {
                MavenProject mavenProject = null;
                final IProgressMonitor npm = new NullProgressMonitor();

                try
                {
                    mavenProject = projectFacade.getMavenProject( npm );
                }
                catch( CoreException e )
                {
                }

                if( mavenProject != null )
                {
                    try
                    {
                        final Plugin liferayMavenPlugin =
                            MavenUtil.getPlugin(
                                projectFacade, ILiferayMavenConstants.LIFERAY_MAVEN_PLUGIN_KEY, npm );

                        if( liferayMavenPlugin != null )
                        {
                            retval = true;
                        }
                    }
                    catch( CoreException e )
                    {
                    }
                }
            }
        }

        return retval;
    }

}
