/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vgv.excel.io;

import com.jcabi.immutable.Array;

/**
 * Properties.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @param <T> Props
 * @since 0.1
 */
public final class XsProps<T> implements Props<T> {

    /**
     * List of properties.
     */
    private final Array<Props<T>> props;

    /**
     * Ctor.
     */
    public XsProps() {
        this(new Array<>());
    }

    /**
     * Ctor.
     * @param properties Properties
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public XsProps(final Props<T>... properties) {
        this(new Array<>(properties));
    }

    /**
     * Ctor.
     * @param properties Properties
     */
    public XsProps(final Iterable<Props<T>> properties) {
        this.props = new Array<>(properties);
    }

    @Override
    public void accept(final T element) {
        this.props.forEach(prop -> prop.accept(element));
    }
}
