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

package com.liferay.ide.portlet.ui.jsf;

import com.liferay.ide.core.LiferayCore;
import com.liferay.ide.portlet.core.jsf.INewJSFPortletClassDataModelProperties;
import com.liferay.ide.portlet.core.jsf.NewJSFPortletClassDataModelProvider;
import com.liferay.ide.portlet.ui.PortletUIPlugin;
import com.liferay.ide.portlet.ui.wizard.NewLiferayPortletWizardPage;
import com.liferay.ide.portlet.ui.wizard.NewPortletWizard;
import com.liferay.ide.project.ui.wizard.ValidProjectChecker;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * @author Greg Amerson
 * @author Simon Jiang
 */
@SuppressWarnings( "restriction" )
public class NewJSFPortletWizard extends NewPortletWizard implements INewJSFPortletClassDataModelProperties
{

    public static final String ID = "com.liferay.ide.eclipse.portlet.jsf.ui.wizard.portlet"; //$NON-NLS-1$

    public NewJSFPortletWizard()
    {
        super();
    }

    public NewJSFPortletWizard( IDataModel model )
    {
        super( model );
    }

    @Override
    public String getTitle()
    {
        return Msgs.newLiferayJSFPortlet;
    }

    @Override
    protected String getDefaultPageTitle()
    {
        return Msgs.createLiferayJSFPortlet;
    }

    @Override
    protected void doAddPages()
    {
        addPage( new NewJSFPortletClassWizardPage(
            getDataModel(), "pageOne", Msgs.createJSFPortlet, getDefaultPageTitle(), fragment ) ); //$NON-NLS-1$
        addPage( new NewJSFPortletOptionsWizardPage(
            getDataModel(), "pageTwo", Msgs.specifyJSFPortletDeployment, getDefaultPageTitle(), //$NON-NLS-1$
            fragment ) );
        addPage( new NewLiferayPortletWizardPage(
            getDataModel(), "pageThree", Msgs.specifyLiferayPortletDeployment, //$NON-NLS-1$
            getDefaultPageTitle(), fragment ) );
    }

    @Override
    protected ImageDescriptor getImage()
    {
        return ImageDescriptor.createFromURL( PortletUIPlugin.getDefault().getBundle().getEntry(
            "/icons/wizban/liferay_faces_75x66.png" ) ); //$NON-NLS-1$
    }

    @Override
    protected IDataModelProvider getDefaultProvider()
    {
        // for now, no need for own template store and context type
        final TemplateStore templateStore = PortletUIPlugin.getDefault().getTemplateStore();

        final TemplateContextType contextType =
            PortletUIPlugin.getDefault().getTemplateContextRegistry().getContextType( JSFPortletTemplateContextTypeIds.NEW );

        return new NewJSFPortletClassDataModelProvider( fragment )
        {
            @Override
            public IDataModelOperation getDefaultOperation()
            {
                return new AddJSFPortletOperation( this.model, templateStore, contextType );
            }
        };
    }

    @Override
    public void init( IWorkbench workbench, IStructuredSelection selection )
    {
        getDataModel();
        ValidProjectChecker checker = new ValidProjectChecker( ID );
        checker.checkValidProjectTypes();
    }

    @Override
    protected void openJavaClass()
    {
        // instead of opening a java class lets open the view xhtml file
        if( getDataModel().getBooleanProperty( CREATE_JSPS ) )
        {
            try
            {
                final String jspsFolder = getDataModel().getStringProperty( CREATE_JSPS_FOLDER );
                final String projectName = getDataModel().getStringProperty( PROJECT_NAME );
                final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectName );

                // IDE-110 IDE-648
                final IFolder defaultDocroot = LiferayCore.create( project ).getDefaultDocrootFolder();

                if( defaultDocroot != null )
                {
                    final Path path = new Path( jspsFolder + "/view.xhtml" ); //$NON-NLS-1$
                    IFile viewFile = defaultDocroot.getFile( path );

                    if( viewFile.exists() )
                    {
                        IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                        IDE.openEditor( page, viewFile, true );

                        return;
                    }
                }
            }
            catch( Exception e )
            {
                // best effort
            }
        }
    }

    private static class Msgs extends NLS
    {
        public static String createJSFPortlet;
        public static String createLiferayJSFPortlet;
        public static String newLiferayJSFPortlet;
        public static String specifyJSFPortletDeployment;
        public static String specifyLiferayPortletDeployment;

        static
        {
            initializeMessages( NewJSFPortletWizard.class.getName(), Msgs.class );
        }
    }
}
