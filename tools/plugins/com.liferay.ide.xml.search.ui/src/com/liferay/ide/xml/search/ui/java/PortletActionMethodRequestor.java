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
package com.liferay.ide.xml.search.ui.java;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.wst.xml.search.editor.searchers.javamethod.requestor.AbstractJavaMethodRequestor;
import org.eclipse.wst.xml.search.editor.searchers.javamethod.requestor.IJavaMethodRequestor;


/**
 * @author Gregory Amerson
 */
public class PortletActionMethodRequestor extends AbstractJavaMethodRequestor
{
    public static final IJavaMethodRequestor INSTANCE = new PortletActionMethodRequestor();

    @Override
    protected IStatus doValidate( IMethod method )
    {
        final String[] parameterTypes = method.getParameterTypes();

        boolean valid =
            parameterTypes != null &&
                parameterTypes.length == 2 &&
                    ( parameterTypes[0].equals( "QActionRequest;" ) ||
                      parameterTypes[0].equals( "Qjavax.portlet.ActionRequest;" ) ) &&
                    ( parameterTypes[1].equals( "QActionResponse;" ) ||
                      parameterTypes[1].equals( "Qjavax.portlet.ActionResponse;" ) );

        return valid ? Status.OK_STATUS : null;
    }

    private IMemberValuePair findNamePair( IAnnotation annotation )
    {
        IMemberValuePair retval = null;

        try
        {
            final IMemberValuePair[] pairs = annotation.getMemberValuePairs();

            for( IMemberValuePair pair : pairs )
            {
                if( "name".equals( pair.getMemberName() ) )
                {
                    retval = pair;
                    break;
                }
            }
        }
        catch( JavaModelException e )
        {
        }

        return retval;
    }

    @Override
    protected String formatMethodName( Object selectedNode, IMethod method )
    {
        String retval = null;

        if( hasProcessActionAnnotation( method ) )
        {
            final IAnnotation annotation = method.getAnnotation( "ProcessAction" );
            final IMemberValuePair pair = findNamePair( annotation );

            if( pair != null )
            {
                retval = pair.getValue().toString();
            }
        }
        else
        {
            retval = method.getElementName();
        }

        return retval;
    }

    private boolean hasProcessActionAnnotation( IMethod method )
    {
        try
        {
            IAnnotation[] annots = method.getAnnotations();

            for( IAnnotation annot : annots )
            {
                if( "ProcessAction".equals( annot.getElementName() ) )
                {
                    return true;
                }
            }
        }
        catch( JavaModelException e )
        {
        }

        return false;
    }

}
