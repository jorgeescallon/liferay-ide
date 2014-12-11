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

package com.liferay.ide.ui;

import java.util.Map;

import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;

/**
 * @author Greg Amerson
 */
@SuppressWarnings( { "restriction", "rawtypes", "unchecked", "deprecation" } )
public class LiferayDefaultCodeFormatterSettings extends DefaultCodeFormatterConstants
{

    public final static Map settings = DefaultCodeFormatterOptions.getEclipseDefaultSettings().getMap();
    static
    {
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_IF, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COLON_IN_ASSERT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_ENUM_CONSTANT, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_SEMICOLON, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGN_TYPE_MEMBERS_ON_COLUMNS, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_CASE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_FORMAT_LINE_COMMENT, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_NUMBER_OF_EMPTY_LINES_TO_PRESERVE, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_BRACKETS_IN_ARRAY_TYPE_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_SWITCH, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BETWEEN_TYPE_DECLARATIONS, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_PARENTHESIZED_EXPRESSION_IN_RETURN, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_METHOD_BODY, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ANNOTATION_TYPE_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_STATEMENTS_COMPARE_TO_BODY, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_AFTER_OPENING_BRACE_IN_ARRAY_INITIALIZER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_KEEP_GUARDIAN_CLAUSE_ON_ONE_LINE, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_INSERT_EMPTY_LINE_BEFORE_ROOT_TAGS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COLON_IN_FOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_TAB_SIZE, "4" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_ANGLE_BRACKET_IN_TYPE_PARAMETERS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_IMPORTS, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COLON_IN_CASE, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ENUM_CONSTANT_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_NEW_CHUNK, "0" ); //$NON-NLS-1$
        settings.put( FORMATTER_CONTINUATION_INDENTATION, "4" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_BINARY_OPERATOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_CONSTRUCTOR_DECLARATION_PARAMETERS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_FOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_SUPERINTERFACES, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_PARAMETERS_IN_METHOD_DECLARATION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ASSIGNMENT, "20" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_MEMBER_TYPE, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_CONSTRUCTOR_DECLARATION_THROWS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_CONDITIONAL_EXPRESSION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_WHILE, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_INDENT_PARAMETER_DESCRIPTION, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_FORMAT_HTML, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ALLOCATION_EXPRESSION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_METHOD_DECLARATION_THROWS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_ENUM_CONSTANT, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_FORMAT_SOURCE, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ENUM_DECLARATIONS, "insert" ); //$NON-NLS-1$
        settings.put(
            FORMATTER_INSERT_SPACE_BEFORE_CLOSING_ANGLE_BRACKET_IN_PARAMETERIZED_TYPE_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_ANNOTATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_METHOD_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_CONDITIONAL, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_UNARY_OPERATOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_QUESTION_IN_CONDITIONAL, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ANNOTATION_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENTATION_SIZE, "4" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_MULTIPLE_LOCAL_DECLARATIONS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_POSTFIX_OPERATOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_SUPERINTERFACES_IN_ENUM_DECLARATION, "21" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ENUM_CONSTANT_ARGUMENTS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_SEMICOLON_IN_FOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_CONSTRUCTOR_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_AT_IN_ANNOTATION_TYPE_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_EXPLICIT_CONSTRUCTOR_CALL_ARGUMENTS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_ANONYMOUS_TYPE_DECLARATION, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_LINE_SPLIT, "80" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_TYPE_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_BLOCK, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_TYPE_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_METHOD_INVOCATION_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_WHILE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ENUM_CONSTANT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_CLEAR_BLANK_LINES_IN_BLOCK_COMMENT, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_AT_IN_ANNOTATION_TYPE_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ENUM_CONSTANT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_ANGLE_BRACKET_IN_TYPE_PARAMETERS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_BEFORE_CLOSING_BRACE_IN_ARRAY_INITIALIZER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_ARRAY_INITIALIZER, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_SUPERCLASS_IN_TYPE_DECLARATION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_CAST, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ENUM_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_SYNCHRONIZED, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_FORMAT_HEADER, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_FOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_AT_IN_ANNOTATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_BEFORE_ELSE_IN_IF_STATEMENT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_EXPLICIT_CONSTRUCTOR_CALL, "5" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_METHOD_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ALLOCATION_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_MULTIPLE_FIELDS, "16" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_AT_END_OF_FILE_IF_MISSING, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_EXPLICIT_CONSTRUCTOR_CALL_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_BLOCK, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_CLOSING_PAREN_IN_CAST, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_BEFORE_FINALLY_IN_TRY_STATEMENT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_KEEP_THEN_STATEMENT_ON_SAME_LINE, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_BINARY_OPERATOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_ANNOTATION_DECLARATION_HEADER, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_CONSTRUCTOR_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_METHOD_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_EXPRESSIONS_IN_ARRAY_INITIALIZER, "20" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_METHOD_DECLARATION_PARAMETERS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_METHOD_DECLARATION, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_ENUM_CONSTANT, "do not insert" ); //$NON-NLS-1$
        settings.put(
            FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_ANNOTATION_TYPE_MEMBER_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put(
            FORMATTER_INSERT_SPACE_AFTER_OPENING_ANGLE_BRACKET_IN_PARAMETERIZED_TYPE_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put(
            FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_ANNOTATION_TYPE_MEMBER_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_FIELD, "0" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_THROWS_CLAUSE_IN_METHOD_DECLARATION, "37" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_METHOD_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_CONSTRUCTOR_DECLARATION_PARAMETERS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_TYPE_PARAMETERS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_SWITCH, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_FORMAT_JAVADOC_COMMENT, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_BRACKET_IN_ARRAY_ALLOCATION_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_ANNOTATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_FORMAT_BLOCK_COMMENT, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ARRAY_INITIALIZER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_IN_EMPTY_ANONYMOUS_TYPE_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_BINARY_EXPRESSION, "20" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_BRACES_IN_ARRAY_INITIALIZER, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_WRAP_BEFORE_BINARY_OPERATOR, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_AFTER_PACKAGE, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_CATCH, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_SUPERINTERFACES_IN_TYPE_DECLARATION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_LABELED_STATEMENT, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_SEMICOLON_IN_FOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_AND_IN_TYPE_PARAMETER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_CATCH, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_BEFORE_WHILE_IN_DO_STATEMENT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BETWEEN_IMPORT_GROUPS, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_METHOD_DECLARATION_THROWS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_PREFIX_OPERATOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_ELLIPSIS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_CONSTRUCTOR_DECLARATION, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_QUESTION_IN_WILDCARD, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_CLEAR_BLANK_LINES_IN_JAVADOC_COMMENT, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_ALLOCATION_EXPRESSION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_TYPE_PARAMETERS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_AFTER_IMPORTS, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COLON_IN_CONDITIONAL, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ENUM_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_PARAMETERIZED_TYPE_REFERENCE, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_BEFORE_CATCH_IN_TRY_STATEMENT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_ENUM_CONSTANT, "20" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_BLOCK_IN_CASE, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_ENUM_DECLARATION, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_FOR_INCREMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_FOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_FIRST_CLASS_BODY_DECLARATION, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_KEEP_ELSE_STATEMENT_ON_SAME_LINE, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_EMPTY_LINES, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_INSERT_NEW_LINE_FOR_PARAMETER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_PARENTHESIZED_EXPRESSION_IN_THROW, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_WHILE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_CLOSING_BRACE_IN_BLOCK, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_FOR_INCREMENTS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_ENUM_DECLARATION_HEADER, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_CONSTRUCTOR_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_LINE_LENGTH, "80" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_PREFIX_OPERATOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_ASSIGNMENT_OPERATOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_METHOD_INVOCATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMPACT_ELSE_IF, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACKET_IN_ARRAY_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ENUM_DECLARATIONS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_QUESTION_IN_CONDITIONAL, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_ANGLE_BRACKET_IN_TYPE_PARAMETERS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_METHOD_INVOCATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_USE_TABS_ONLY_FOR_LEADING_INDENTATIONS, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_TYPE_ARGUMENTS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_SWITCH, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_PARAMETERS_IN_CONSTRUCTOR_DECLARATION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_BRACKETS_IN_ARRAY_ALLOCATION_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_FOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_CONSTRUCTOR_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_SYNCHRONIZED, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_AT_BEGINNING_OF_METHOD_BODY, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ANNOTATION, "insert" ); //$NON-NLS-1$
        settings.put(
            FORMATTER_INSERT_SPACE_BEFORE_OPENING_ANGLE_BRACKET_IN_PARAMETERIZED_TYPE_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_SWITCHSTATEMENTS_COMPARE_TO_SWITCH, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_CONSTRUCTOR_DECLARATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_ANNOTATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_IF, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_DEFAULT, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ANNOTATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_ENUM_CONSTANT, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_BRACKET_IN_ARRAY_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACKET_IN_ARRAY_TYPE_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_ARRAY_INITIALIZER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_CATCH, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_SYNCHRONIZED, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_KEEP_EMPTY_ARRAY_INITIALIZER_ON_ONE_LINE, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_BRACKET_IN_ARRAY_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_SWITCH, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_BRACE_IN_ARRAY_INITIALIZER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_COMPACT_IF, "20" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_QUESTION_IN_WILDCARD, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COLON_IN_ASSERT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_METHOD_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_ELLIPSIS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_QUALIFIED_ALLOCATION_EXPRESSION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_STATEMENTS_COMPARE_TO_BLOCK, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_PAREN_IN_PARENTHESIZED_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_TYPE_HEADER, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_CLOSING_ANGLE_BRACKET_IN_TYPE_PARAMETERS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_TYPE_ARGUMENTS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_KEEP_SIMPLE_IF_ON_ONE_LINE, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BETWEEN_EMPTY_PARENS_IN_METHOD_INVOCATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_MULTIPLE_LOCAL_DECLARATIONS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_ANNOTATION_TYPE_DECLARATION, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_SELECTOR_IN_METHOD_INVOCATION, "5" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_BODY_DECLARATIONS_COMPARE_TO_ENUM_CONSTANT_HEADER, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_SWITCH, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_ASSIGNMENT_OPERATOR, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_NEVER_INDENT_LINE_COMMENTS_ON_FIRST_COLUMN, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_UNARY_OPERATOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_IF, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COLON_IN_LABELED_STATEMENT, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACKET_IN_ARRAY_ALLOCATION_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_SWITCHSTATEMENTS_COMPARE_TO_CASES, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_CONTINUATION_INDENTATION_FOR_ARRAY_INITIALIZER, "4" ); //$NON-NLS-1$
        settings.put( FORMATTER_COMMENT_INDENT_ROOT_TAGS, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ENUM_CONSTANTS, "20" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_PARAMETERIZED_TYPE_REFERENCE, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_PARENTHESIZED_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_CONSTRUCTOR_DECLARATION_THROWS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_THROWS_CLAUSE_IN_CONSTRUCTOR_DECLARATION, "37" ); //$NON-NLS-1$
        settings.put( FORMATTER_ALIGNMENT_FOR_ARGUMENTS_IN_METHOD_INVOCATION, "36" ); //$NON-NLS-1$
        settings.put( FORMATTER_TAB_CHAR, "tab" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_PACKAGE, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_METHOD_INVOCATION_ARGUMENTS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INDENT_BREAKS_COMPARE_TO_CASES, "true" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_FOR_INITS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_MULTIPLE_FIELD_DECLARATIONS, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_SUPERINTERFACES, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_PUT_EMPTY_STATEMENT_ON_NEW_LINE, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_METHOD_DECLARATION_PARAMETERS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BLANK_LINES_BEFORE_METHOD, "1" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_MULTIPLE_FIELD_DECLARATIONS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_COMMA_IN_FOR_INITS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_BRACE_IN_ANONYMOUS_TYPE_DECLARATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_NEW_LINE_AFTER_ANNOTATION, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_CAST, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_CLOSING_ANGLE_BRACKET_IN_TYPE_ARGUMENTS, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_NEVER_INDENT_BLOCK_COMMENTS_ON_FIRST_COLUMN, "false" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_COMMA_IN_ARRAY_INITIALIZER, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_PARENTHESIZED_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_OPENING_PAREN_IN_ENUM_CONSTANT, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_PAREN_IN_METHOD_INVOCATION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_POSTFIX_OPERATOR, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_BRACE_POSITION_FOR_BLOCK, "end_of_line" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_AFTER_OPENING_BRACE_IN_ARRAY_INITIALIZER, "insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_CLOSING_BRACKET_IN_ARRAY_ALLOCATION_EXPRESSION, "do not insert" ); //$NON-NLS-1$
        settings.put( FORMATTER_INSERT_SPACE_BEFORE_AND_IN_TYPE_PARAMETER, "insert" ); //$NON-NLS-1$

    }

}
