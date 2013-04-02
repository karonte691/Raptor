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
final public class ZemString extends ZemObject {
    private String value;

    public ZemString(String value) {
        this.value = value;
    }

    public ZemString concat(ZemString other) {
        return new ZemString(value + other.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(ZemObject object) {
        ZemString str = (ZemString) object;
        return value.compareTo(str.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return compareTo((ZemObject) object) == 0;
    }
}
