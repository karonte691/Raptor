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

import it.raptor.lang.InvalidTypeException;
import it.raptor.lang.SourcePosition;

/**
 *
 *
 *  
 */
public abstract class ZemObject implements Comparable<ZemObject> {
    public ZemNumber toNumber(SourcePosition pos) {
        if (this instanceof ZemNumber) {
            return (ZemNumber) this;
        }
        throw new InvalidTypeException("Expecting number", pos);
    }

    public ZemBoolean toBoolean(SourcePosition pos) {
        if (this instanceof ZemBoolean) {
            return (ZemBoolean) this;
        }
        throw new InvalidTypeException("Expecting boolean", pos);
    }

    public ZemString toZString() {
    /*
        if (this instanceof ZemString) {
            return (ZemString) this;
        }
        throw new InvalidTypeException("Expecting string");
     */
        if (this instanceof ZemString)
            return (ZemString) this;
        // Implicit converting of types to string
        return new ZemString(this.toString());
    }
}
