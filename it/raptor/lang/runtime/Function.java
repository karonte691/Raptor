/*

 Raptor Interpreter engine

 Copyright (C) 2013  Luca Magistrelli <blackstorm010[at]gmail[dot]com>

 Based on ZemScript interpreter by Cameron Zemek Copyright (c) 2008 grom[at]zeminvaders[dot]net

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

*/
package it.raptor.lang.runtime;

import it.raptor.lang.Interpreter;
import it.raptor.lang.SourcePosition;

/**
 * A function callable by the interpreter.
 *
 *  
 */
public abstract class Function extends ZemObject {
    /**
     * Get the number of parameters to this function.
     *
     * @return Number of parameters
     */
    abstract public int getParameterCount();

    /**
     * Get the name of the parameter
     *
     * @param index Parameter index
     * @return The name of the parameter
     */
    abstract public String getParameterName(int index);

    /**
     * Get the default value of the functions parameter.
     *
     * @param index Parameter index
     * @return The default value of the parameter. Return null if no default.
     */
    abstract public ZemObject getDefaultValue(int index);

    /**
     * Evaluate the function.
     *
     * @param interpreter
     * @param pos Source position of function call
     * @return The result of evaluating the function.
     */
    abstract public ZemObject eval(Interpreter interpreter, SourcePosition pos);

    @Override
    public int compareTo(ZemObject o) {
        throw new UnsupportedOperationException();
    }
}
