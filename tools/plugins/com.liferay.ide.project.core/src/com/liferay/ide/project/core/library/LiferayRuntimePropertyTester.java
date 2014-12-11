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

package com.liferay.ide.project.core.library;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jst.common.project.facet.core.libprov.EnablementExpressionContext;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectBase;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.internal.facets.FacetUtil;

/**
 * @author Greg Amerson
 */
@SuppressWarnings( "restriction" )
public class LiferayRuntimePropertyTester extends PropertyTester
{

    public boolean test( Object receiver, String property, Object[] args, Object expectedValue )
    {
        boolean retval = false;

        try
        {
            EnablementExpressionContext ctx = (EnablementExpressionContext) receiver;
            IFacetedProjectBase projectBase = ctx.getFacetedProject();
            IRuntime serverRuntime = FacetUtil.getRuntime( projectBase.getPrimaryRuntime() );

            if( serverRuntime.getRuntimeType().getId().startsWith( "com.liferay." ) ) //$NON-NLS-1$
            {
                retval = true;
            }
        }
        catch( Throwable t )
        {
            // don't log error just means test returns false;
        }

        return retval;
    }

}
