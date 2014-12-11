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

package com.liferay.ide.project.core;

import com.liferay.ide.project.core.util.ProjectUtil;
import com.liferay.ide.sdk.core.SDK;
import com.liferay.ide.sdk.core.SDKManager;
import com.liferay.ide.sdk.core.SDKUtil;

import java.io.File;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

/**
 * @author Greg Amerson
 */
public class LiferayProjectImportOperation extends AbstractDataModelOperation
    implements ILiferayProjectImportDataModelProperties
{

    public LiferayProjectImportOperation( IDataModel model )
    {
        super( model );
    }

    @Override
    public IStatus execute( IProgressMonitor monitor, IAdaptable info ) throws ExecutionException
    {
        ProjectRecord projectRecord = (ProjectRecord) getDataModel().getProperty( PROJECT_RECORD );

        if( projectRecord == null )
        {
            return ProjectCore.createErrorStatus( "Project record to import is null." ); //$NON-NLS-1$
        }

        File projectDir = projectRecord.getProjectLocation().toFile();

        SDK sdk = SDKUtil.getSDKFromProjectDir( projectDir );

        if( sdk != null )
        {
            if( !( SDKManager.getInstance().containsSDK( sdk ) ) )
            {
                SDKManager.getInstance().addSDK( sdk );
            }
        }

        IRuntime runtime = (IRuntime) model.getProperty( IFacetProjectCreationDataModelProperties.FACET_RUNTIME );

        try
        {
            ProjectUtil.importProject( projectRecord, runtime, sdk.getLocation().toOSString(), monitor );
        }
        catch( CoreException e )
        {
            return ProjectCore.createErrorStatus( e );
        }

        return Status.OK_STATUS;
    }

}
