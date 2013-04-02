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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/**
 * Wraps a Reader to provide peeking into the character stream.
 *
 */
public class PeekReader {
    private Reader in;
    private CharBuffer peakBuffer;

    public PeekReader(Reader in, int peekLimit) throws IOException {
        if (!in.markSupported()) {
            // Wrap with buffered reader, since it supports marking
            in = new BufferedReader(in);
        }
        this.in = in;
        peakBuffer = CharBuffer.allocate(peekLimit);
        fillPeekBuffer();
    }

    public void close() throws IOException {
        in.close();
    }

    private void fillPeekBuffer() throws IOException {
        peakBuffer.clear();
        in.mark(peakBuffer.capacity());
        in.read(peakBuffer);
        in.reset();
        peakBuffer.flip();
    }

    public int read() throws IOException {
        int c = in.read();
        fillPeekBuffer();
        return c;
    }

    /**
     * Return a character that is further in the stream.
     *
     * @param lookAhead How far to look into the stream.
     * @return Character that is lookAhead characters into the stream.
     */
    public int peek(int lookAhead) {
        if (lookAhead < 1 || lookAhead > peakBuffer.capacity()) {
            throw new IndexOutOfBoundsException("lookAhead must be between 1 and " + peakBuffer.capacity());
        }
        if (lookAhead > peakBuffer.limit()) {
            return -1;
        }
        return peakBuffer.get(lookAhead - 1);
    }
}
