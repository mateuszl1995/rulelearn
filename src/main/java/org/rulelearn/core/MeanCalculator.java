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

package org.rulelearn.core;

import org.rulelearn.data.AttributePreferenceType;
import org.rulelearn.types.ElementList;
import org.rulelearn.types.EnumerationField;
import org.rulelearn.types.EnumerationFieldFactory;
import org.rulelearn.types.EvaluationField;
import org.rulelearn.types.IntegerField;
import org.rulelearn.types.IntegerFieldFactory;
import org.rulelearn.types.PairField;
import org.rulelearn.types.RealField;
import org.rulelearn.types.RealFieldFactory;
import org.rulelearn.types.SimpleField;
import org.rulelearn.types.UnknownSimpleField;
import org.rulelearn.types.UnknownSimpleFieldMV15;
import org.rulelearn.types.UnknownSimpleFieldMV2;

/**
 * Central tendency (mean) calculator for different field {@link EvaluationField} types. Contributes to realization of visitor design pattern.
 *
 * @author Jerzy Błaszczyński (<a href="mailto:jurek.blaszczynski@cs.put.poznan.pl">jurek.blaszczynski@cs.put.poznan.pl</a>)
 * @author Marcin Szeląg (<a href="mailto:marcin.szelag@cs.put.poznan.pl">marcin.szelag@cs.put.poznan.pl</a>)
 */
public class MeanCalculator implements EvaluationFieldCalculator {

	/**
	 * Calculates mean for integer fields {@link IntegerField}. 
	 * The preference type {@link AttributePreferenceType} of returned field is the same as the preference type of the first field.
	 * 
	 * @param firstField first filed to make calculations 
	 * @param secondField second filed to make calculations
	 * @return mean of arguments or {@link UnknownSimpleField} if second field is unknown
	 * @throws ClassCastException when it is impossible to cast second field to {@link IntegerField}
	 * @throws NullPointerException when at least one of fields is {@code null}
	 */
	@Override
	public EvaluationField calculate(IntegerField firstField, EvaluationField secondField) {
		EvaluationField mean = null;
		if ((firstField == null) || (secondField == null)) {
			throw new NullPointerException("At least one of fields is null.");
		}
		else if (secondField instanceof UnknownSimpleField) {
			mean = secondField;
		}
		else {
			if ((firstField.isEqualTo(secondField) == TernaryLogicValue.TRUE)) {
				mean = firstField;
			}
			else {
				mean = IntegerFieldFactory.getInstance().create((firstField.getValue() + ((IntegerField)secondField).getValue())/2, firstField.getPreferenceType());
			}
		}
		return mean;
	}

	/**
	 * Calculates mean for real fields {@link RealField}.
	 * The preference type {@link AttributePreferenceType} of returned field is the same as the preference type of the first field.
	 * 
	 * @param firstField first filed to make calculations 
	 * @param secondField second filed to make calculations
	 * @return mean of arguments or {@link UnknownSimpleField} if second field is unknown
	 * @throws ClassCastException when it is impossible to cast second field to {@link RealField}
	 * @throws NullPointerException when at least one of fields is {@code null}
	 */
	@Override
	public EvaluationField calculate(RealField firstField, EvaluationField secondField) {
		EvaluationField mean = null;
		if ((firstField == null) || (secondField == null)) {
			throw new NullPointerException("At least one of fields is null.");
		}
		else if (secondField instanceof UnknownSimpleField) {
			mean = secondField;
		}
		else {
			if ((firstField.isEqualTo(secondField) == TernaryLogicValue.TRUE)) {
				mean = firstField;
			}
			else {
				mean = RealFieldFactory.getInstance().create((firstField.getValue() + ((RealField)secondField).getValue())/2, firstField.getPreferenceType());
			}
		}
		return mean;
	}

	/**
	 * Calculates mean for enumeration fields {@link EnumerationField}. 
	 * The preference type {@link AttributePreferenceType} of returned field is the same as the preference type of the first field.
	 * 
	 * @param firstField first filed to make calculations 
	 * @param secondField second filed to make calculations
	 * @return mean of arguments or {@link UnknownSimpleField} if second field is unknown
	 * @throws ClassCastException when it is impossible to cast second field to {@link EnumerationField}
	 * @throws NullPointerException when at least one of fields is {@code null}
	 * @throws InvalidValueException when fields have different element lists {@link ElementList}
	 */
	@Override
	public EvaluationField calculate(EnumerationField firstField, EvaluationField secondField) {
		EvaluationField mean = null;
		if ((firstField == null) || (secondField == null)) {
			throw new NullPointerException("At least one of fields is null.");
		}
		else if (secondField instanceof UnknownSimpleField) {
			mean = secondField;
		}
		else {
			if (firstField.isEqualTo(secondField) == TernaryLogicValue.TRUE) {
				if (firstField.hasEqualHashOfElementList((EnumerationField)secondField) == TernaryLogicValue.TRUE) {
					mean = firstField;
				}
				else {
					throw new InvalidValueException("Fields have different element lists.");
				}
			}
			else if (firstField.hasEqualHashOfElementList((EnumerationField)secondField) == TernaryLogicValue.TRUE) {
				mean = EnumerationFieldFactory.getInstance().create(firstField.getElementList(), (firstField.getValue() + ((EnumerationField)secondField).getValue())/2, firstField.getPreferenceType());
			}
			else {
				throw new InvalidValueException("Fields have different element lists.");
			}
		}
		return mean;
	}
	
	/**
	 * Calculates mean for integer fields {@link PairField}. 
	 * The preference type {@link AttributePreferenceType} of returned field is the same as the preference type of the first field.
	 * 
	 * @param firstField first filed to make calculations 
	 * @param secondField second filed to make calculations
	 * @return mean of arguments or {@link UnknownSimpleField} if second field is unknown
	 * @throws ClassCastException when it is impossible to cast second field to {@link PairField}
	 */
	@Override
	public EvaluationField calculate(PairField<? extends SimpleField> firstField, EvaluationField secondField) {
		EvaluationField mean = null;
		if ((firstField == null) || (secondField == null)) {
			throw new NullPointerException("At least one of fields is null.");
		}
		else if (secondField instanceof UnknownSimpleField) {
			mean = secondField;
		}
		// TODO implementation
		
		return mean;
	}

	/**
	 * Calculates mean as equal to first field passed as parameter. 
	 * 
	 * @param firstField first filed to make calculations 
	 * @param secondField second filed to make calculations
	 * @return first field {@link UnknownSimpleFieldMV15}
	 */
	@Override
	public EvaluationField calculate(UnknownSimpleFieldMV15 firstField, EvaluationField secondField) {
		return firstField;
	}

	/**
	 * Calculates mean as equal to first field passed as parameter.
	 * 
	 * @param firstField first filed to make calculations 
	 * @param secondField second filed to make calculations
	 * @return first field {@link UnknownSimpleFieldMV2}
	 */
	@Override
	public EvaluationField calculate(UnknownSimpleFieldMV2 firstField, EvaluationField secondField) {
		return firstField;
	}

}
