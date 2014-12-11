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
/**
 * 
 */

package com.liferay.ide.server.ui;

import java.util.List;

import org.eclipse.wst.server.ui.internal.view.servers.ModuleServer;

/**
 * @author Greg Amerson
 */
@SuppressWarnings( "restriction" )
public class PluginsContent
{

    private Object parent;

    protected List<ModuleServer> input;

    public PluginsContent( List<ModuleServer> input, Object parent )
    {
        this.input = input;

        this.parent = parent;
    }

    public Object[] getChildren()
    {
        return input.toArray();
    }

    public List<ModuleServer> getInput()
    {
        return input;
    }

    public Object getParent()
    {
        return parent;
    }

    public int getSize()
    {
        return input != null ? input.size() : 0;
    }
}
