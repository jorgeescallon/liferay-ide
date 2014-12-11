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
package com.liferay.ide.project.ui.action;

import com.liferay.ide.core.util.CoreUtil;
import com.liferay.ide.project.core.model.NewLiferayPluginProjectOp;
import com.liferay.ide.project.core.model.NewLiferayPluginProjectOpMethods;
import com.liferay.ide.project.core.model.Profile;
import com.liferay.ide.project.ui.wizard.NewLiferayPluginProjectWizard;

import org.eclipse.sapphire.ElementList;
import org.eclipse.sapphire.ui.Presentation;
import org.eclipse.sapphire.ui.def.DefinitionLoader;
import org.eclipse.sapphire.ui.forms.PropertyEditorActionHandler;
import org.eclipse.sapphire.ui.forms.swt.SapphireDialog;
import org.eclipse.sapphire.ui.forms.swt.SwtPresentation;


/**
 * @author Gregory Amerson
 */
public class SelectActiveProfilesActionHandler extends PropertyEditorActionHandler
{

    @Override
    protected Object run( Presentation context )
    {
        if( context instanceof SwtPresentation )
        {
            final SwtPresentation swt = (SwtPresentation) context;

            final NewLiferayPluginProjectOp op = getModelElement().nearest( NewLiferayPluginProjectOp.class );
            final String previousActiveProfilesValue = op.getActiveProfilesValue().content();

            // we need to rebuild the 'selected' list from what is specified in the 'activeProfiles' property
            op.getSelectedProfiles().clear();

            final String activeProfiles = op.getActiveProfilesValue().content();

            if( ! CoreUtil.isNullOrEmpty( activeProfiles ) )
            {
                final String[] profileIds = activeProfiles.split( "," );

                if( ! CoreUtil.isNullOrEmpty( profileIds ) )
                {
                    for( String profileId : profileIds )
                    {
                        if( ! CoreUtil.isNullOrEmpty( profileId ) )
                        {
                            boolean foundExistingProfile = false;

                            for( Profile profile : op.getSelectedProfiles() )
                            {
                                if( profileId.equals( profile.getId().content() ) )
                                {
                                    foundExistingProfile = true;
                                    break;
                                }
                            }

                            if( ! foundExistingProfile )
                            {
                                Profile newlySelectedProfile = op.getSelectedProfiles().insert();
                                newlySelectedProfile.setId( profileId );
                            }
                        }
                    }
                }
            }

            final SapphireDialog dialog =
                new SapphireDialog( swt.shell(), op, DefinitionLoader.sdef(
                    NewLiferayPluginProjectWizard.class ).dialog( "SelectActiveProfiles" ) );

            dialog.setBlockOnOpen( true );
            final int result = dialog.open();

            if( result == SapphireDialog.CANCEL )
            {
                // restore previous value
                op.setActiveProfilesValue( previousActiveProfilesValue );
            }
            else
            {
                final ElementList<Profile> selectedProfiles = op.getSelectedProfiles();

                NewLiferayPluginProjectOpMethods.updateActiveProfilesValue( op, selectedProfiles );
            }
        }

        return null;
    }

}
