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

package com.liferay.ide.xml.search.ui.validators;

import com.liferay.ide.core.ILiferayConstants;
import com.liferay.ide.project.core.ValidationPreferences.ValidationType;
import com.liferay.ide.xml.search.ui.LiferayXMLSearchUI;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.sse.core.internal.validate.ValidationMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.search.core.util.DOMUtils;
import org.eclipse.wst.xml.search.editor.references.IXMLReference;
import org.eclipse.wst.xml.search.editor.references.IXMLReferenceTo;
import org.eclipse.wst.xml.search.editor.util.JdtUtils;
import org.eclipse.wst.xml.search.editor.validation.XMLReferencesBatchValidator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author Kuo Zhang
 */
@SuppressWarnings( "restriction" )
public class LiferayHookDescriptorValidator extends LiferayBaseValidator
{
    public static final String MARKER_TYPE = "com.liferay.ide.xml.search.ui.liferayHookDescriptorMarker";
    public static final String MESSAGE_PROPERTIES_NOT_END_WITH_PROPERTIES = Msgs.propertiesNotEndWithProperties;
    public static final String MESSAGE_SERVICE_IMPL_TYPE_INCORRECT = Msgs.serviceImplTypeIncorrect;
    public static final String MESSAGE_SERVICE_TYPE_INVALID = Msgs.serviceTypeInvalid;
    public static final String MESSAGE_SERVICE_TYPE_NOT_INTERFACE = Msgs.serviceTypeNotInterface;


    @Override
    protected void setMarker( IValidator validator, IFile file )
    {
        if( validator instanceof XMLReferencesBatchValidator &&
            ILiferayConstants.LIFERAY_HOOK_XML_FILE.equals( file.getName() ) )
        {
            ( (XMLReferencesBatchValidator) validator ).getParent().setMarkerId( MARKER_TYPE );
        }
    }

    @Override
    protected boolean validateSyntax( IXMLReference reference, IDOMNode node, IFile file,
                                      IValidator validator, IReporter reporter, boolean batchMode )
    {
        // validate syntax of value of elements <portal-properties> and <language-properties>
        int severity = getServerity( ValidationType.SYNTAX_INVALID, file );

        if( severity != ValidationMessage.IGNORE )
        {
            if( node.getNodeType() == Node.TEXT_NODE )
            {
                String validationMsg = null;

                final String nodeValue = DOMUtils.getNodeValue( node );

                if( nodeValue != null && nodeValue.length() > 0 )
                {
                    if( "portal-properties".equals( node.getParentNode().getNodeName() ) || 
                        "language-properties".equals( node.getParentNode().getNodeName() ) )
                    {
                        if( ! nodeValue.endsWith( ".properties" ) )
                        {
                            validationMsg = NLS.bind( MESSAGE_PROPERTIES_NOT_END_WITH_PROPERTIES, nodeValue );
                        }
                    }

                    if( validationMsg != null )
                    {
                        addMessage( node, file, validator, reporter, batchMode, validationMsg, severity );
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    protected void validateReferenceToJava( IXMLReferenceTo referenceTo, IDOMNode node, IFile file,
                                            IValidator validator, IReporter reporter, boolean batchMode )
    {
        if( node.getNodeType() == Node.TEXT_NODE )
        {
            if( "service-type".equals( node.getParentNode().getNodeName() ) )
            {
                ValidationInfo valInfo = validateServiceType( node, file );

                if( valInfo != null )
                {
                    addMessage( node, file, validator, reporter, batchMode, valInfo.getValidationMessge(),
                                getServerity( valInfo.getValidationType(), file ) );
                }

                return;

            }
            else if( "service-impl".equals( node.getParentNode().getNodeName() ) )
            {
                ValidationInfo valInfo = validateServiceImpl( node, file );

                if( valInfo != null )
                {
                    addMessage( node, file, validator, reporter, batchMode, valInfo.getValidationMessge(),
                                getServerity( valInfo.getValidationType(), file ) );
                }

                return;
            }
        }

        super.validateReferenceToJava( referenceTo, node, file, validator, reporter, batchMode );
    }

    private ValidationInfo validateServiceImpl( IDOMNode node, IFile file )
    {
        final String serviceImplContent= DOMUtils.getNodeValue( node );

        IType type = JdtUtils.getJavaType(file.getProject(), serviceImplContent );

        String msg = null;

        // validate type existence
        if( type == null )
        {
            msg = getMessageText( ValidationType.TYPE_NOT_FOUND, node );
            return new ValidationInfo( msg, ValidationType.TYPE_NOT_FOUND );
        }

        NodeList siblingNodes = node.getParentNode().getParentNode().getChildNodes();
        IDOMNode serviceTypeNode = null;

        for( int i = 0; i < siblingNodes.getLength(); i++ )
        {
            if( "service-type".equals( siblingNodes.item( i ).getNodeName() ) )
            {
                serviceTypeNode = (IDOMNode) siblingNodes.item( i );
                break;
            }
        }

        try
        {
            if( serviceTypeNode != null && validateServiceType( (IDOMNode) serviceTypeNode.getFirstChild(), file ) == null )
            {
                // validate type hierarchy
                final String serviceTypeContent = serviceTypeNode.getFirstChild().getNodeValue().trim();

                final String superTypeName = serviceTypeContent + "Wrapper";
                final IType superType = JdtUtils.getJavaType( file.getProject(), superTypeName );

                boolean typeCorrect = false;

                if( superType != null && JdtUtils.hierarchyContainsComponent( type, superType.getFullyQualifiedName() ) )
                {
                    typeCorrect = true;
                }

                if( !typeCorrect )
                {
                    msg = NLS.bind( MESSAGE_SERVICE_IMPL_TYPE_INCORRECT, serviceImplContent, superTypeName );
                    return new ValidationInfo( msg, ValidationType.TYPE_HIERARCHY_INCORRECT );
                }
            }
        }
        catch( Exception e )
        {
            LiferayXMLSearchUI.logError( e );
        }

        return null;
    }

    private ValidationInfo validateServiceType( IDOMNode node, IFile file )
    {
        try
        {
            final String serviceTypeContent= DOMUtils.getNodeValue( node );

            final IType type = JdtUtils.getJavaType(file.getProject(), serviceTypeContent );

            String msg = null;

            // validate type existence
            if( type == null )
            {
                msg = getMessageText( ValidationType.TYPE_NOT_FOUND, node );
                return new ValidationInfo( msg, ValidationType.TYPE_NOT_FOUND );
            }

            // validate if it is an interface
            if( ! type.isInterface() )
            {
                msg = NLS.bind( MESSAGE_SERVICE_TYPE_NOT_INTERFACE, serviceTypeContent );
                return new ValidationInfo( msg, ValidationType.TYPE_HIERARCHY_INCORRECT );
            }

            // validate type hierarchy
            if( ! serviceTypeContent.matches( "com.liferay.*Service" ) )
            {
                msg = MESSAGE_SERVICE_TYPE_INVALID;
                return new ValidationInfo( msg, ValidationType.TYPE_HIERARCHY_INCORRECT );
            }
        }
        catch( Exception e )
        {
            LiferayXMLSearchUI.logError( e );
        }

        return null;
    }

    private static class Msgs extends NLS
    {
        public static String propertiesNotEndWithProperties;

        public static String serviceTypeInvalid;
        public static String serviceTypeNotInterface;

        public static String serviceImplTypeIncorrect;

        static
        {
            initializeMessages( LiferayHookDescriptorValidator.class.getName(), Msgs.class );
        }

        static
        {
            initializeMessages( LiferayHookDescriptorValidator.class.getName(), Msgs.class );
        }
    }

    private class ValidationInfo
    {
        private String validationMessage;
        private ValidationType validationType;

        public ValidationInfo( String msg, ValidationType type )
        {
            this.validationMessage = msg;
            this.validationType= type;
        }

        public String getValidationMessge()
        {
            return validationMessage;
        }

        public ValidationType getValidationType()
        {
            return validationType;
        }
    }

}
