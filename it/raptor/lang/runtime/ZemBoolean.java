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

/**
 *
 *
 *  
 */
final public class ZemBoolean extends ZemObject {
    static final public ZemBoolean TRUE = new ZemBoolean(true);
    static final public ZemBoolean FALSE = new ZemBoolean(false);

    private boolean value;

    private ZemBoolean(boolean value) {
        this.value = value;
    }

    public boolean booleanValue() {
        return this.value;
    }

    static public ZemBoolean valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }

    public ZemBoolean and(ZemBoolean bool) {
        return valueOf(this.value && bool.value);
    }

    public ZemBoolean or(ZemBoolean bool) {
        return valueOf(this.value || bool.value);
    }

    public ZemBoolean not() {
        return valueOf(!this.value);
    }

    @Override
    public ZemString toZString() {
        return new ZemString(this.toString());
    }

    @Override
    public String toString() {
        return this == TRUE ? "true" : "false";
    }

    @Override
    public int compareTo(ZemObject o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object object) {
        return this == object;
    }
}
