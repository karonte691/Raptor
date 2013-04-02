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
package it.raptor.lang;

import it.raptor.lang.runtime.Dictionary;
import it.raptor.lang.runtime.ZemArray;
import it.raptor.lang.runtime.ZemBoolean;
import it.raptor.lang.runtime.ZemNumber;
import it.raptor.lang.runtime.ZemString;

/**
 * Types don't match.
 *
 *  
 */
public class TypeMismatchException extends ZemException {
    private static final long serialVersionUID = 9115378805326306069L;

    static private String toString(Class type) {
        if (type == Dictionary.class) {
            return "dictionary";
        } else if (type == ZemArray.class) {
            return "array";
        } else if (type == ZemBoolean.class) {
            return "boolean";
        } else if (type == ZemNumber.class) {
            return "number";
        } else if (type == ZemString.class) {
            return "string";
        } else {
            return type.getName();
        }
    }

    public TypeMismatchException(SourcePosition pos, Class expect, Class actual) {
        super("Type mismatch - Excepted type '" + toString(expect) + "' but got type '" + toString(actual) + "'", pos);
    }
}
