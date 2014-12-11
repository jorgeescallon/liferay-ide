/******************************************************************************
 * Copyright (c) 2013 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 ******************************************************************************/

package com.liferay.ide.project.ui.wizard;

import static org.eclipse.sapphire.ui.forms.PropertyEditorPart.DATA_BINDING;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.gd;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.gdhfill;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.gdhindent;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.gdhspan;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.gdvalign;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.gdvindent;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.glayout;
import static org.eclipse.sapphire.ui.forms.swt.GridLayoutUtil.glspacing;

import org.eclipse.sapphire.modeling.CapitalizationType;
import org.eclipse.sapphire.ui.SapphirePart.LabelChangedEvent;
import org.eclipse.sapphire.ui.assist.internal.PropertyEditorAssistDecorator;
import org.eclipse.sapphire.ui.forms.FormComponentPart;
import org.eclipse.sapphire.ui.forms.PropertyEditorPart;
import org.eclipse.sapphire.ui.forms.swt.RadioButtonsGroup;
import org.eclipse.sapphire.ui.forms.swt.SwtPresentation;
import org.eclipse.sapphire.ui.forms.swt.ValuePropertyEditorPresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Gregory Amerson
 */
@SuppressWarnings( "restriction" )
public final class PossibleValuesRadioPropertyEditorPresentation<T> extends ValuePropertyEditorPresentation
{
    private RadioButtonsGroup control;

    public PossibleValuesRadioPropertyEditorPresentation(
        FormComponentPart part, SwtPresentation parent, Composite composite )
    {
        super( part, parent, composite );
    }

    @Override
    protected void createContents( final Composite parent )
    {
        final PropertyEditorPart part = part();

        final boolean showLabel = part.label() != null;
        final int leftMargin = part.getMarginLeft();

        PropertyEditorAssistDecorator decorator = null;

        final Composite composite = createMainComposite
        (
            parent,
            new CreateMainCompositeDelegate( part )
            {
                @Override
                public boolean getShowLabel()
                {
                    return false;
                }

                @Override
                public boolean getSpanBothColumns()
                {
                    return true;
                }
            }
        );

        composite.setLayout( glspacing( glayout( 2, 0, 0 ), 2, 5 ) );

        decorator = createDecorator( composite );
        decorator.addEditorControl( composite );

        if( showLabel )
        {
            decorator.control().setLayoutData( gdvalign( gd(), SWT.CENTER ) );

            final Label label = new Label( composite, SWT.WRAP );
            label.setLayoutData( gd() );

            final Runnable updateLabelOp = new Runnable()
            {
                public void run()
                {
                    label.setText( part.label( CapitalizationType.FIRST_WORD_ONLY, true ) );
                }
            };

            final org.eclipse.sapphire.Listener listener = new org.eclipse.sapphire.Listener()
            {
                @Override
                public void handle( final org.eclipse.sapphire.Event event )
                {
                    if( event instanceof LabelChangedEvent )
                    {
                        updateLabelOp.run();
                        PossibleValuesRadioPropertyEditorPresentation.this.layout();
                    }
                }
            };

            part.attach( listener );
            updateLabelOp.run();

            label.addDisposeListener
            (
                new DisposeListener()
                {
                    public void widgetDisposed( final DisposeEvent event )
                    {
                        part.detach( listener );
                    }
                }
            );

            decorator.addEditorControl( label );
        }
        else
        {
            decorator.control().setLayoutData( gdvindent( gdvalign( gd(), SWT.TOP ), 4 ) );
        }

        this.control = new RadioButtonsGroup( composite, true );

        if( showLabel )
        {
            this.control.setLayoutData( gdhindent( gdhspan( gdhfill(), 2 ), leftMargin + 20 ) );
        }
        else
        {
            this.control.setLayoutData( gdhfill() );
        }

        this.binding = new PossibleValuesRadioButtonsGroupBinding<T>( this, (RadioButtonsGroup) this.control );

        this.control.setData( DATA_BINDING, this.binding );
        decorator.addEditorControl( this.control, true );

        addControl( this.control );
    }

    @Override
    protected void handleFocusReceivedEvent()
    {
        this.control.setFocus();
    }

}
