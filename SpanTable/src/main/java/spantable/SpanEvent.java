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

import java.util.EventObject;

/**
 * An event fired by a SpanModel when some change occurs to it.
 * @author Jonathan Keatley
 */
public class SpanEvent extends EventObject {
	private static final long serialVersionUID = -8615270194976006509L;
	private final Span span;

	/**
	 * Constructs a SpanEvent.
	 * @param source The span model firing the event.
	 * @param span The span associated with the event.
	 * @throws IllegalArgumentException if source is null.
	 */
	public SpanEvent(SpanModel source, Span span) {
		super(source);
		this.span = span;
	}

	/**
	 * Constructs a SpanEvent with no span.
	 * @param source The span model firing the event.
	 */
	public SpanEvent(SpanModel source) {
		this(source, null);
	}

	public Span getSpan() {
		return span;
	}
}

