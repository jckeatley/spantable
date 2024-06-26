/*
 * Copyright 2015 Jonathan Keatley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spantable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The default implementation of SpanModel.
 * @author Jonathan Keatley
 */
public class DefaultSpanModel implements SpanModel {
    /**
     *  Set of span definitions.
     */
	private Set<Span> spans = new HashSet<>();

    /**
     * The listeners for SpanEvents.
     */
    private List<SpanListener> listeners = new ArrayList<>();

    public DefaultSpanModel() {
    }

    /**
     * Adds a Span to the model.
     * @param span The span to add.
     * @throws IllegalArgumentException if the new span intersects any that are
     *  already in the model.
     */
    public void addSpan(Span span) {
        if (span == null) {
            throw new NullPointerException("Span is null.");
        }
        for (Span s: spans) {
            if (s.intersects(span)) {
                throw new IllegalArgumentException(
                    "Cannot insert a Span that intersects with another Span in the model.");
            }
        }
        spans.add(span);
        fireSpanAdded(span);
    }

    /**
     * Removes a span from the model.
     * @param span The span to remove.
     */
    public void removeSpan(Span span) {
        if (spans.remove(span)) {
            fireSpanDeleted(span);
        }
    }

    /**
     * Remove all spans from the model.
     */
    public void clear() {
    	for (Span span: spans) {
            fireSpanDeleted(span);
    	}
    	spans.clear();
    }

    /**
     * Get the span that is defined at this row and column.
     * @param row The row
     * @param column The column
     * @return The active span, or {@code null} if none is there.
     */
    public Span getDefinedSpan(int row, int column) {
        for (Span rcs: spans) {
            if (rcs.isDefined(row, column)) {
                return rcs;
            }
        }
        return null;
    }

	/**
	 * Get all spans in the model.
	 * @return The list of all spans in the model.
	 */
    public List<Span> getSpans() {
        return new ArrayList<>(spans);
	}

	/**
     * Adds a SpanListener.
     * @param listener The listener
     */
    public void addSpanListener(SpanListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a SpanListener.
     * @param listener The listener
     */
    public void removeSpanListener(SpanListener listener) {
        listeners.remove(listener);
    }

    private void fireSpanAdded(Span addedSpan) {
        SpanEvent sme = new SpanEvent(this, addedSpan);
        for (SpanListener sml: listeners) {
            sml.spanAdded(sme);
        }
    }

    private void fireSpanDeleted(Span addedSpan) {
        SpanEvent sme = new SpanEvent(this, addedSpan);
        for (SpanListener sml: listeners) {
            sml.spanDeleted(sme);
        }
    }
}

