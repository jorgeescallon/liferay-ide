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

package com.liferay.ide.hook.core.model.internal;

import static com.liferay.ide.core.util.CoreUtil.empty;

import com.liferay.ide.core.ILiferayPortal;
import com.liferay.ide.core.ILiferayProject;
import com.liferay.ide.core.LiferayCore;
import com.liferay.ide.hook.core.model.Hook;
import com.liferay.ide.hook.core.model.StrutsAction;

import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.sapphire.PossibleValuesService;

/**
 * @author Gregory Amerson
 */
public class StrutsActionPathPossibleValuesService extends PossibleValuesService
{

    private IPath portalDir;
    private TreeSet<String> possibleValues;

    @Override
    protected void compute( Set<String> values )
    {
        if( this.portalDir != null && this.portalDir.toFile().exists() )
        {
            if( this.possibleValues == null )
            {
                final IPath strutsConfigPath = this.portalDir.append( "WEB-INF/struts-config.xml" );
                final StrutsActionPathPossibleValuesCacheService cacheService =
                    this.context().service( StrutsActionPathPossibleValuesCacheService.class );

                this.possibleValues = cacheService.getPossibleValuesForPath( strutsConfigPath );
            }

            values.addAll( this.possibleValues );

            // add the value that is current set by the user
            String actionPath = context( StrutsAction.class ).getStrutsActionPath().content( false );

            if( !empty( actionPath ) )
            {
                values.add( actionPath );
            }
        }
    }

    @Override
    protected void initPossibleValuesService()
    {
        super.initPossibleValuesService();

        final ILiferayProject liferayProject = LiferayCore.create( project() );

        if( liferayProject != null )
        {
            final ILiferayPortal portal = liferayProject.adapt( ILiferayPortal.class );

            if( portal != null )
            {
                this.portalDir = portal.getAppServerPortalDir();
            }
        }
    }

    protected Hook hook()
    {
        return this.context().find( Hook.class );
    }

    protected IProject project()
    {
        return this.hook().adapt( IProject.class );
    }
}
