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

package com.liferay.ide.layouttpl.ui.wizard;

import com.liferay.ide.layouttpl.core.operation.INewLayoutTplDataModelProperties;
import com.liferay.ide.layouttpl.core.operation.NewLayoutTplDataModelProvider;
import com.liferay.ide.layouttpl.ui.LayoutTplUI;
import com.liferay.ide.project.ui.wizard.ValidProjectChecker;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizard;

/**
 * @author Greg Amerson
 */
@SuppressWarnings( "restriction" )
public class NewLayoutTplWizard extends DataModelWizard implements INewWizard, INewLayoutTplDataModelProperties
{

    public static final String ID = "com.liferay.ide.eclipse.layouttpl.ui.wizard.layouttemplate"; //$NON-NLS-1$

    public static final String LAYOUTTPL_LAYOUT_PAGE = "layoutTplLayoutPage"; //$NON-NLS-1$

    public static final String LAYOUTTPL_PAGE = "layoutTplPage"; //$NON-NLS-1$

    public static final String[] WIZARD_PAGES = new String[] { LAYOUTTPL_PAGE, LAYOUTTPL_LAYOUT_PAGE };

    protected NewLayoutTplWizardPage layoutTplPage;

    protected NewLayoutTplLayoutWizardPage layoutTplStartPage;

    public NewLayoutTplWizard()
    {
        this( null );
    }

    public NewLayoutTplWizard( IDataModel dataModel )
    {
        super( dataModel );

        this.setWindowTitle( Msgs.newLayoutTemplate );
        this.setDefaultPageImageDescriptor( getDefaultImageDescriptor() );
    }

    public void init( IWorkbench workbench, IStructuredSelection selection )
    {
        getDataModel();
        ValidProjectChecker checker = new ValidProjectChecker( ID );
        checker.checkValidProjectTypes();
    }

    @Override
    protected void doAddPages()
    {
        layoutTplPage = new NewLayoutTplWizardPage( getDataModel(), LAYOUTTPL_PAGE );
        addPage( layoutTplPage );

        layoutTplStartPage = new NewLayoutTplLayoutWizardPage( getDataModel(), LAYOUTTPL_LAYOUT_PAGE );
        addPage( layoutTplStartPage );
    }

    protected ImageDescriptor getDefaultImageDescriptor()
    {
        return LayoutTplUI.imageDescriptorFromPlugin( LayoutTplUI.PLUGIN_ID, "/icons/wizban/layout_template_wiz.png" ); //$NON-NLS-1$
    }

    @Override
    protected IDataModelProvider getDefaultProvider()
    {
        final TemplateStore templateStore = LayoutTplUI.getDefault().getTemplateStore();

        final TemplateContextType contextType =
            LayoutTplUI.getDefault().getTemplateContextRegistry().getContextType( LayoutTplTemplateContextTypeIds.NEW );

        return new NewLayoutTplDataModelProvider()
        {
            @Override
            public IDataModelOperation getDefaultOperation()
            {
                return new AddLayoutTplOperation( getDataModel(), templateStore, contextType );
            }
        };
    }

    protected void openEditor( final IFile file )
    {
        if( file != null )
        {
            getShell().getDisplay().asyncExec
            (
                new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                            IDE.openEditor( page, file, true );
                        }
                        catch( PartInitException e )
                        {
                            LayoutTplUI.logError( e );
                        }
                    }
                }
            );
        }
    }

    protected void openWebFile( IFile file )
    {
        try
        {
            openEditor( file );
        }
        catch( Exception cantOpen )
        {
            LayoutTplUI.logError( cantOpen );
        }
    }

    @Override
    protected void postPerformFinish() throws InvocationTargetException
    {
        super.postPerformFinish();

        IFile layoutTplFile = (IFile) getDataModel().getProperty( LAYOUT_TPL_FILE_CREATED );

        if( layoutTplFile != null && layoutTplFile.exists() )
        {
            openWebFile( layoutTplFile );
        }
    }

    @Override
    protected boolean runForked()
    {
        return false;
    }

    private static class Msgs extends NLS
    {
        public static String newLayoutTemplate;

        static
        {
            initializeMessages( NewLayoutTplWizard.class.getName(), Msgs.class );
        }
    }
}
