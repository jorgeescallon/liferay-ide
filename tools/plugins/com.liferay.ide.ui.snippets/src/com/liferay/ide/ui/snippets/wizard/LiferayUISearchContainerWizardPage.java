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
public class LiferayUISearchContainerWizardPage extends AbstractModelWizardPage
{

    public LiferayUISearchContainerWizardPage( String pageName, IEditorPart editor )
    {
        super( pageName, editor );
        setTitle( Msgs.liferayUISearchContainer );
        setDescription( Msgs.insertLiferayUISearchContainerJSPTag );
    }

    private static class Msgs extends NLS
    {
        public static String insertLiferayUISearchContainerJSPTag;
        public static String liferayUISearchContainer;

        static
        {
            initializeMessages( LiferayUISearchContainerWizardPage.class.getName(), Msgs.class );
        }
    }
}
