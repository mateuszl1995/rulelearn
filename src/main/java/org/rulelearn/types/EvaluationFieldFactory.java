/**
 * Copyright (C) Jerzy Błaszczyński, Marcin Szeląg
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

package org.rulelearn.types;

import org.rulelearn.core.FieldParseException;
import org.rulelearn.core.InvalidTypeException;
import org.rulelearn.data.EvaluationAttribute;

/**
 * Contract for a factory capable of constructing evaluation fields based on their textual representation and corresponding attribute.
 *
 * @author Jerzy Błaszczyński (<a href="mailto:jurek.blaszczynski@cs.put.poznan.pl">jurek.blaszczynski@cs.put.poznan.pl</a>)
 * @author Marcin Szeląg (<a href="mailto:marcin.szelag@cs.put.poznan.pl">marcin.szelag@cs.put.poznan.pl</a>)
 */
public interface EvaluationFieldFactory {
	
	/**
	 * Constructs an evaluation field.
	 * 
	 * @param value textual representation of an evaluation field
	 * @param attribute evaluation attribute for which a field should be constructed
	 *  
	 * @return constructed field
	 * 
	 * @throws FieldParseException if given value cannot be parsed as a value of the given attribute
	 * @throws NullPointerException if given attribute is {@code null}
	 * @throws InvalidTypeException if type of {@link EvaluationAttribute#getValueType() attribute's value}
	 *         is incompatible with type of constructed evaluation field
	 */
	public EvaluationField create(String value, EvaluationAttribute attribute);
	
}
