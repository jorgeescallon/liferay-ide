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

package com.liferay.ide.ui.dialog;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.internal.ui.viewsupport.FilteredElementTreeSelectionDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * @author Greg Amerson
 */
@SuppressWarnings( "restriction" )
public class StringsFilteredDialog extends FilteredElementTreeSelectionDialog
{

    public static class StringsContentProvider implements ITreeContentProvider
    {
        protected String[] strings;

        public void dispose()
        {
        }

        public Object[] getChildren( Object parentElement )
        {
            return null;
        }

        public Object[] getElements( Object inputElement )
        {
            if( strings == null && inputElement instanceof String[] )
            {
                strings = (String[]) inputElement;
            }

            return strings;
        }

        public Object getParent( Object element )
        {
            return null;
        }

        public boolean hasChildren( Object element )
        {
            return false;
        }

        public void inputChanged( Viewer viewer, Object oldInput, Object newInput )
        {
            this.strings = null;
        }

    }

    public static class StringsLabelProvider extends LabelProvider
    {

    }

    public class StringsFilter extends ViewerFilter
    {

        protected String fixedPattern;

        public StringsFilter( String fixedPattern )
        {
            this.fixedPattern = fixedPattern;
        }

        @Override
        public boolean select( Viewer viewer, Object parentElement, Object element )
        {
            return fixedPattern == null ? true : element != null && element.toString().matches( fixedPattern );
        }

    }

    public class StringsSelectionValidator implements ISelectionStatusValidator
    {

        public StringsSelectionValidator( boolean multiSelect )
        {
        }

        public IStatus validate( Object[] selection )
        {
            return Status.OK_STATUS;
        }

    }

    public class StringsViewerComparator extends ViewerComparator
    {

    }

    protected static final String DIALOG_SETTINGS = "com.liferay.ide.server.ui.wizard.StringsFilteredDialog"; //$NON-NLS-1$

    public StringsFilteredDialog( Shell shell )
    {
        this( shell, null );
    }

    public StringsFilteredDialog( Shell shell, String fixedPattern )
    {
        super( shell, new StringsLabelProvider(), new StringsContentProvider() );

        setAllowMultiple( false );

        setComparator( new StringsViewerComparator() );

        addFilter( new StringsFilter( fixedPattern ) );

        setValidator( new StringsSelectionValidator( false ) );

        setHelpAvailable( false );
    }

}
