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

import java.math.BigDecimal;

/**
 *
 *
 *  
 */
final public class ZemNumber extends ZemObject {
    private BigDecimal value;

    public ZemNumber(String value) {
        this.value = new BigDecimal(value);
    }

    protected ZemNumber(BigDecimal value) {
        this.value = value;
    }

    protected ZemNumber(int value) {
        this.value = new BigDecimal(value);
    }

    public ZemNumber add(ZemNumber augend) {
        return new ZemNumber(value.add(augend.value));
    }

    public ZemNumber subtract(ZemNumber subtrahend) {
        return new ZemNumber(value.subtract(subtrahend.value));
    }

    public ZemNumber multiply(ZemNumber multiplicand) {
        return new ZemNumber(value.multiply(multiplicand.value));
    }

    public ZemNumber divide(ZemNumber divisor) {
        return new ZemNumber(value.divide(divisor.value));
    }

    public ZemNumber remainder(ZemNumber divisor) {
        return new ZemNumber(value.remainder(divisor.value));
    }

    public ZemNumber power(ZemNumber n) {
        return new ZemNumber(value.pow(n.value.intValueExact()));
    }

    public ZemNumber negate() {
        return new ZemNumber(value.negate());
    }

    public int intValue() {
        return value.intValue();
    }

    public int compareTo(ZemObject object) {
        ZemNumber number = (ZemNumber) object;
        return value.compareTo(number.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object object) {
        return compareTo((ZemObject) object) == 0;
    }
}
