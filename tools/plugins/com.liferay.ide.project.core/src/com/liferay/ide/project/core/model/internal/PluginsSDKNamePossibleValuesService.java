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
package com.liferay.ide.project.core.model.internal;

import com.liferay.ide.sdk.core.ISDKListener;
import com.liferay.ide.sdk.core.SDK;
import com.liferay.ide.sdk.core.SDKManager;

import java.util.Set;

import org.eclipse.sapphire.PossibleValuesService;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.modeling.Status;


/**
 * @author Gregory Amerson
 */
public class PluginsSDKNamePossibleValuesService extends PossibleValuesService implements ISDKListener
{

    @Override
    protected void compute( Set<String> values )
    {
        SDK[] validSDKs = SDKManager.getInstance().getSDKs();

        if( validSDKs.length > 0 )
        {
            for( SDK validSDK : validSDKs )
            {
                values.add( validSDK.getName() );
            }
        }
    }

    @Override
    public void dispose()
    {
        SDKManager.getInstance().removeSDKListener( this );

        super.dispose();
    }

    @Override
    protected void initPossibleValuesService()
    {
        super.initPossibleValuesService();

        SDKManager.getInstance().addSDKListener( this );
    }

    @Override
    public boolean ordered()
    {
        return true;
    }

    @Override
    public Status problem( Value<?> value )
    {
        if( PluginsSDKNameDefaultValueService.NONE.equals( value.text() ) )
        {
            return Status.createOkStatus();
        }

        return super.problem( value );
    }

    public void sdksAdded( SDK[] sdk )
    {
        refresh();
    }

    public void sdksChanged( SDK[] sdk )
    {
        refresh();
    }

    public void sdksRemoved( SDK[] sdk )
    {
        refresh();
    }

}
