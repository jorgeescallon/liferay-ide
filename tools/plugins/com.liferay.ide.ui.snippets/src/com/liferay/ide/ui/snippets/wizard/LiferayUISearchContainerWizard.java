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

package com.liferay.ide.ui.snippets.wizard;

import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;

/**
 * @author Greg Amerson
 */
public class LiferayUISearchContainerWizard extends AbstractModelWizard
{

    public LiferayUISearchContainerWizard( IEditorPart fEditorPart )
    {
        super( fEditorPart );
        setWindowTitle( Msgs.insertSearchContainer );
    }

    @Override
    protected AbstractModelWizardPage createModelWizardPage( IEditorPart editorPart )
    {
        return new LiferayUISearchContainerWizardPage( "liferayUISearchContainerWizardPage", editorPart ); //$NON-NLS-1$
    }

    public String getModelClass()
    {
        return wizardPage.getModelClass();
    }

    private static class Msgs extends NLS
    {
        public static String insertSearchContainer;

        static
        {
            initializeMessages( LiferayUISearchContainerWizard.class.getName(), Msgs.class );
        }
    }
}
