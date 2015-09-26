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

import java.util.List;

/**
 * A model for use by SpanTable, to hold a set of spans that are to be
 * wider or higher than a single cell.
 * @author Jonathan Keatley
 */
public interface SpanModel {
	/**
	 * Adds a Span to the model.
	 * @param span The span to add.
	 * @throws IllegalArgumentException if the new span intersects any that are
	 *  already in the model.
	 */
	void addSpan(Span span);

	/**
	 * Removes a span from the model.
	 * @param span The span to remove.
	 */
	void removeSpan(Span span);

	/**
	 * Remove all spans from the model.
	 */
	void clear();

	/**
	 * Get the span that is defined at this row and column.
	 * @param row The row
	 * @param column The column
	 * @return The active span, or {@code null} if none is there.
	 */
	Span getDefinedSpan(int row, int column);

	/**
	 * Get all spans in the model.
	 * @return The list of all spans in the model.
	 */
	List<Span> getSpans();

	/**
	 * Adds a SpanListener.
	 * @param listener The listener
	 */
	void addSpanListener(SpanListener listener);

	/**
	 * Removes a SpanListener.
	 * @param listener The listener
	 */
	void removeSpanListener(SpanListener listener);
}

